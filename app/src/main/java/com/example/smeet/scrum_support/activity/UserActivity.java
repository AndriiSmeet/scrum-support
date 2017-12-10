package com.example.smeet.scrum_support.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import com.example.smeet.scrum_support.R;
import com.example.smeet.scrum_support.adapter.UserActiveStories;
import com.example.smeet.scrum_support.service.StoryService;
import com.example.smeet.scrum_support.service.impl.StoryServiceImpl;

public class UserActivity extends AppCompatActivity {

    private StoryService storyService;
    private Button btnSelectStory;

    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_user);

        initElements();

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Scrum");

        buttonLogic();
    }

//    private void doAutoRefresh() {
//        handler.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                actualNumber = numberDao.getAllOnSession(sessionId);
//                setStatistics();
//                doAutoRefresh();
//            }
//        }, 2000);
//    }

    private void initElements() {

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        btnSelectStory = (Button) findViewById(R.id.btnSelectStory);

        storyService = new StoryServiceImpl(UserActivity.this);

    }

    private void buttonLogic() {

        btnSelectStory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                storyService.getAllStoryByIsActive(true);
            }
        });
    }
//
//    private void setStatistics() {
//        HashMap<Integer, Integer> numbers = calculateCountOfNumber();
//
//        setOnZero();
//        int counter = 0;
//        for (Map.Entry pairs : numbers.entrySet()) {
//            numberHolders.get(counter).setText(pairs.getKey().toString());
//            countHolders.get(counter).setText(pairs.getValue().toString());
//            numberHolders.get(counter).setVisibility(View.VISIBLE);
//            countHolders.get(counter).setVisibility(View.VISIBLE);
//            counter++;
//        }
//
//    }
//
//    private void setOnZero() {
//        int counter = 0;
//
//        for (TextView textView : numberHolders) {
//            numberHolders.get(counter).setText("");
//            textView.setVisibility(View.GONE);
//            counter++;
//        }
//
//        int counteri = 0;
//        for (TextView textView : countHolders) {
//            countHolders.get(counteri).setText("");
//            textView.setVisibility(View.GONE);
//            counteri++;
//        }
//    }
//
//    private HashMap<Integer, Integer> calculateCountOfNumber() {
//
//        List<Number> numbers = actualNumber;
//        List<Integer> numbersInt = new ArrayList<>();
//        HashMap<Integer, Integer> numberAndCount = new HashMap<>();
//
//
//        for (Number number : numbers) {
//            numbersInt.add(number.getValue());
//        }
//
//        int countAll = 0;
//        int summ = 0;
//        for(Integer integer : numbersInt) {
//            int counter = Collections.frequency(numbersInt, integer);
//            numberAndCount.put(integer, counter);
//            countAll += counter;
//            summ += integer;
//        }
//
//
//
//
//        return numberAndCount;
//
//    }

//    private void saveNumber(int value)  {
//
//        if(numId == null) {
//            numId = numberDao.create(value, sessionId);
//            Toast.makeText(getApplicationContext(), "You select " + value + ", your id " + numId, Toast.LENGTH_SHORT).show();
//        } else {
//            numberDao.update(numId, value);
//            Toast.makeText(getApplicationContext(), "You value was updated on " + value + ", your id " + numId, Toast.LENGTH_SHORT).show();
//        }
//    }

//    private void buttonLogic() {
//        number1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if(checkSessionOnReady()) {
//                    saveNumber(Integer.parseInt(number1.getText().toString()));
//                } else {
//                    Toast.makeText(getApplicationContext(), "Session not start yet", Toast.LENGTH_SHORT).show();
//                }
//            }
//        });
//
//        number2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                if(checkSessionOnReady()) {
//                    saveNumber(Integer.parseInt(number2.getText().toString()));
//                } else {
//                    Toast.makeText(getApplicationContext(), "Session not start yet", Toast.LENGTH_SHORT).show();
//                }
//
//
//            }
//        });
//
//        number3.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                if(checkSessionOnReady()) {
//                    saveNumber(Integer.parseInt(number3.getText().toString()));
//                } else {
//                    Toast.makeText(getApplicationContext(), "Session not start yet", Toast.LENGTH_SHORT).show();
//                }
//
//
//            }
//        });
//
//        number5.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if(checkSessionOnReady()) {
//                    saveNumber(Integer.parseInt(number5.getText().toString()));
//                } else {
//                    Toast.makeText(getApplicationContext(), "Session not start yet", Toast.LENGTH_SHORT).show();
//                }
//
//
//            }
//        });
//
//        number8.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                if(checkSessionOnReady()) {
//                    saveNumber(Integer.parseInt(number8.getText().toString()));
//                } else {
//                    Toast.makeText(getApplicationContext(), "Session not start yet", Toast.LENGTH_SHORT).show();
//                }
//
//
//            }
//        });
//
//        number12.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                if(checkSessionOnReady()) {
//                    saveNumber(Integer.parseInt(number12.getText().toString()));
//                } else {
//                    Toast.makeText(getApplicationContext(), "Session not start yet", Toast.LENGTH_SHORT).show();
//                }
//
//            }
//        });
//    }

    @Override
    public void onBackPressed() {
//        handler.removeCallbacksAndMessages(null);
        super.onBackPressed();
    }

}
