package com.example.smeet.scrum_support.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.ViewSwitcher;

import com.example.smeet.scrum_support.R;
import com.example.smeet.scrum_support.dao.impl.NumberDaoImpl;
import com.example.smeet.scrum_support.dao.impl.SessionDaoImpl;
import com.example.smeet.scrum_support.model.Number;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ScrumMasterActivity extends AppCompatActivity {


    private Integer sessionId;
    private Button btnReady;
    private Button btnEnd;
    private Button btnResetSession;
    private TextView textSessionStatus;
    private TextView txtAvarage;

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

    private TextSwitcher textSwitcher;
    private SessionDaoImpl sessionDao;
    private NumberDaoImpl numberDao;

    private List<TextView> numberHolders;
    private List<TextView> countHolders;
    private List<Number> actualNumber;

    private Integer avarage;
    private Handler handler;
    private Integer voted;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrum_master);

        initObj();

    }



    @Override
    public void onBackPressed() {
        handler.removeCallbacksAndMessages(null);
        super.onBackPressed();
    }

//    private void doLogicButton() {
//        btnReady.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                sessionDao.startStatus(sessionId);
//                checkSession();
//            }
//        });
//
//        btnEnd.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                sessionDao.closeStatus(sessionId);
//                checkSession();
//            }
//        });
//
//        btnResetSession.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                sessionDao.resetSession(sessionId);
//                sessionDao.closeStatus(sessionId);
//                txtAvarage.setText("0");
//                checkSession();
//            }
//        });
//
//
//    }

    private void initObj() {

        sessionId = getIntent().getIntExtra("sessionId", 1);
        handler = new Handler();
        sessionDao = new SessionDaoImpl();
        numberDao = new NumberDaoImpl();
        btnEnd = (Button) findViewById(R.id.btnEnd);
        btnReady = (Button) findViewById(R.id.btnReady);
        btnResetSession = (Button) findViewById(R.id.btnResetSession);
        textSessionStatus = (TextView) findViewById(R.id.textSessionStatus);

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

        voted = 0;

        textSwitcher = (TextSwitcher) findViewById(R.id.textStats);

        textSwitcher.setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView() {
                TextView textView = new TextView(getApplicationContext());
                textView.setTextSize(25);
                textView.setTextColor(Color.WHITE);
                return textView;
            }
        });
        textSwitcher.setInAnimation(getApplicationContext(), android.R.anim.slide_in_left);
        textSwitcher.setOutAnimation(getApplicationContext(), android.R.anim.slide_out_right);
        textSwitcher.setText("" + voted);

    }

    private void refreshVoted() {

        int newVotedCount = actualNumber.size();
        if(voted != newVotedCount) {
            voted = newVotedCount;
            textSwitcher.setText("" + newVotedCount);
        }

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




}
