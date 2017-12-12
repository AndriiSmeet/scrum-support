package com.example.smeet.scrum_support.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import com.example.smeet.scrum_support.R;
import com.example.smeet.scrum_support.service.StoryService;
import com.example.smeet.scrum_support.service.impl.StoryServiceImpl;

public class UserActivity extends AppCompatActivity {

    private StoryService storyService;
    private Button btnSelectStory;

    private Integer sessionId;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_user);

        sessionId = getIntent().getExtras().getInt("sessionId");
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
                storyService.getAllStoryByIsActive(true, sessionId);
            }
        });
    }

    @Override
    public void onBackPressed() {
//        handler.removeCallbacksAndMessages(null);
        super.onBackPressed();
    }

}
