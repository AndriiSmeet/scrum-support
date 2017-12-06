package com.example.smeet.scrum_support.activity;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.smeet.scrum_support.R;
import com.example.smeet.scrum_support.config.Connect;
import com.example.smeet.scrum_support.dao.impl.NumberDaoImpl;
import com.example.smeet.scrum_support.model.Number;
import com.example.smeet.scrum_support.model.Session;

import org.w3c.dom.Text;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserActivity extends AppCompatActivity {

    private Button number1;
    private Button number2;
    private Button number3;
    private Button number5;
    private Button number8;
    private Button number12;

    private TextView txtNum1;
    private TextView txtNum2;
    private TextView txtNum3;
    private TextView txtNum5;
    private TextView txtNum8;
    private TextView txtNum13;

    private TextView txtCount1;
    private TextView txtCount2;
    private TextView txtCount3;
    private TextView txtCount5;
    private TextView txtCount8;
    private TextView txtCount13;
    private TextView txtAvarage;

    private Integer sessionId;
    private Integer numId;
    private NumberDaoImpl numberDao;
    private Integer avarage;

    private List<TextView> numberHolders;
    private List<TextView> countHolders;
    private List<Number> actualNumber;

    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_session);

        initElements();
        buttonLogic();
        doAutoRefresh();
    }
    private void doAutoRefresh() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                actualNumber = numberDao.getAllOnSession(sessionId);
                setStatistics();
                doAutoRefresh();
            }
        }, 2000);
    }

    private void initElements() {
        number1 = (Button) findViewById(R.id.num1);
        number2 = (Button) findViewById(R.id.num2);
        number3 = (Button) findViewById(R.id.num3);
        number5 = (Button) findViewById(R.id.num5);
        number8 = (Button) findViewById(R.id.num8);
        number12 = (Button) findViewById(R.id.num12);

        txtAvarage = (TextView) findViewById(R.id.avarage);

        txtNum1 = (TextView) findViewById(R.id.txtNum1);
        txtNum2 = (TextView) findViewById(R.id.txtNum2);
        txtNum3 = (TextView) findViewById(R.id.txtNum3);
        txtNum5 = (TextView) findViewById(R.id.txtNum5);
        txtNum8 = (TextView) findViewById(R.id.txtNum8);
        txtNum13 = (TextView) findViewById(R.id.txtNum13);

        txtCount1 = (TextView) findViewById(R.id.txtCount1);
        txtCount2 = (TextView) findViewById(R.id.txtCount2);
        txtCount3 = (TextView) findViewById(R.id.txtCount3);
        txtCount5 = (TextView) findViewById(R.id.txtCount5);
        txtCount8 = (TextView) findViewById(R.id.txtCount8);
        txtCount13 = (TextView) findViewById(R.id.txtCount13);

        numberHolders = new ArrayList<>();
        countHolders = new ArrayList<>();

        numberHolders.add(txtNum1);
        numberHolders.add(txtNum2);
        numberHolders.add(txtNum3);
        numberHolders.add(txtNum5);
        numberHolders.add(txtNum8);
        numberHolders.add(txtNum13);

        countHolders.add(txtCount1);
        countHolders.add(txtCount2);
        countHolders.add(txtCount3);
        countHolders.add(txtCount5);
        countHolders.add(txtCount8);
        countHolders.add(txtCount13);

        handler = new Handler();
        numId = null;
        sessionId = getIntent().getIntExtra("sessionId", 1);
        numberDao = new NumberDaoImpl();
    }

    private boolean checkSessionOnReady() {
        String selectSessionSQL = "SELECT * FROM session WHERE id = ?";
        try {
            PreparedStatement ps = Connect.getConnection().prepareStatement(selectSessionSQL);
            ps.setInt(1, sessionId);
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                Session session = new Session();
                session.setId(rs.getInt("id"));
                session.setSessionName(rs.getString("session_name"));
                session.setReady(rs.getBoolean("is_ready"));

                if(session.getReady()) {
                    return true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    private void setStatistics() {
        HashMap<Integer, Integer> numbers = calculateCountOfNumber();

        setOnZero();
        int counter = 0;
        for (Map.Entry pairs : numbers.entrySet()) {
            numberHolders.get(counter).setText(pairs.getKey().toString());
            countHolders.get(counter).setText(pairs.getValue().toString());
            numberHolders.get(counter).setVisibility(View.VISIBLE);
            countHolders.get(counter).setVisibility(View.VISIBLE);
            counter++;
        }
        if(avarage != null) {
            txtAvarage.setText(avarage.toString());
        } else {
            txtAvarage.setText("0");
        }

    }

    private void setOnZero() {
        int counter = 0;

        for (TextView textView : numberHolders) {
            numberHolders.get(counter).setText("");
            textView.setVisibility(View.GONE);
            counter++;
        }

        int counteri = 0;
        for (TextView textView : countHolders) {
            countHolders.get(counteri).setText("");
            textView.setVisibility(View.GONE);
            counteri++;
        }
    }

    private HashMap<Integer, Integer> calculateCountOfNumber() {

        List<Number> numbers = actualNumber;
        List<Integer> numbersInt = new ArrayList<>();
        HashMap<Integer, Integer> numberAndCount = new HashMap<>();


        for (Number number : numbers) {
            numbersInt.add(number.getValue());
        }

        int countAll = 0;
        int summ = 0;
        for(Integer integer : numbersInt) {
            int counter = Collections.frequency(numbersInt, integer);
            numberAndCount.put(integer, counter);
            countAll += counter;
            summ += integer;
        }

        if(summ != 0 && countAll != 0) {
            avarage = summ/countAll;
        }


        return numberAndCount;

    }

    private void saveNumber(int value)  {

        if(numId == null) {
            numId = numberDao.create(value, sessionId);
            Toast.makeText(getApplicationContext(), "You select " + value + ", your id " + numId, Toast.LENGTH_SHORT).show();
        } else {
            numberDao.update(numId, value);
            Toast.makeText(getApplicationContext(), "You value was updated on " + value + ", your id " + numId, Toast.LENGTH_SHORT).show();
        }
    }

    private void buttonLogic() {
        number1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(checkSessionOnReady()) {
                    saveNumber(Integer.parseInt(number1.getText().toString()));
                } else {
                    Toast.makeText(getApplicationContext(), "Session not start yet", Toast.LENGTH_SHORT).show();
                }
            }
        });

        number2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(checkSessionOnReady()) {
                    saveNumber(Integer.parseInt(number2.getText().toString()));
                } else {
                    Toast.makeText(getApplicationContext(), "Session not start yet", Toast.LENGTH_SHORT).show();
                }


            }
        });

        number3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(checkSessionOnReady()) {
                    saveNumber(Integer.parseInt(number3.getText().toString()));
                } else {
                    Toast.makeText(getApplicationContext(), "Session not start yet", Toast.LENGTH_SHORT).show();
                }


            }
        });

        number5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(checkSessionOnReady()) {
                    saveNumber(Integer.parseInt(number5.getText().toString()));
                } else {
                    Toast.makeText(getApplicationContext(), "Session not start yet", Toast.LENGTH_SHORT).show();
                }


            }
        });

        number8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(checkSessionOnReady()) {
                    saveNumber(Integer.parseInt(number8.getText().toString()));
                } else {
                    Toast.makeText(getApplicationContext(), "Session not start yet", Toast.LENGTH_SHORT).show();
                }


            }
        });

        number12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(checkSessionOnReady()) {
                    saveNumber(Integer.parseInt(number12.getText().toString()));
                } else {
                    Toast.makeText(getApplicationContext(), "Session not start yet", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    @Override
    public void onBackPressed() {
        handler.removeCallbacksAndMessages(null);
        super.onBackPressed();
    }

}
