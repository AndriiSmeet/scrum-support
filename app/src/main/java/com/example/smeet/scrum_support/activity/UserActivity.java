package com.example.smeet.scrum_support.activity;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.example.smeet.scrum_support.R;
import com.example.smeet.scrum_support.fragment.MasterMainFragment;
import com.example.smeet.scrum_support.service.NumberService;
import com.example.smeet.scrum_support.service.StoryService;
import com.example.smeet.scrum_support.service.impl.NumberServiceImpl;
import com.example.smeet.scrum_support.service.impl.StoryServiceImpl;

public class UserActivity extends AppCompatActivity {

    private StoryService storyService;
    private NumberService numberService;
    private Button btnSelectStory;

    private Button btnNum1;
    private Button btnNum2;
    private Button btnNum3;
    private Button btnNum5;
    private Button btnNum8;
    private Button btnNum13;
    private Button btnNum21;
    private Button btnNum34;
    private Button btnNum55;
    private Button btnNum84;
    private Button btnNum139;
    private Button btnNum223;

    private Integer sessionId;
    public static Integer numId;
    public static Integer storyId;
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

    private void initElements() {

        sessionId = getIntent().getExtras().getInt("sessionId");
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        btnSelectStory = (Button) findViewById(R.id.btnSelectStory);
        storyService = new StoryServiceImpl(UserActivity.this);
        numberService = new NumberServiceImpl(UserActivity.this);

        btnNum1 = (Button) findViewById(R.id.num1);
        btnNum2 = (Button) findViewById(R.id.num2);
        btnNum3 = (Button) findViewById(R.id.num3);
        btnNum5 = (Button) findViewById(R.id.num5);
        btnNum8 = (Button) findViewById(R.id.num8);
        btnNum13 = (Button) findViewById(R.id.num13);
        btnNum21 = (Button) findViewById(R.id.num21);
        btnNum34 = (Button) findViewById(R.id.num34);
        btnNum55 = (Button) findViewById(R.id.num55);
        btnNum84 = (Button) findViewById(R.id.num84);
        btnNum139 = (Button) findViewById(R.id.num139);
        btnNum223 = (Button) findViewById(R.id.num223);

    }

    private void buttonLogic() {

        btnSelectStory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                storyService.getAllStoryByIsActive(true, sessionId);
                System.out.println(storyId);
            }
        });


        btnNum1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(storyId != null) {
                    if(numId != null) {
                        numberService.update(Integer.parseInt(btnNum1.getText().toString()), numId);
                    }else {
                        numberService.create(Integer.parseInt(btnNum1.getText().toString()), storyId);
                    }

                } else {
                    Dialog dialog = new Dialog(UserActivity.this);
                    dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                    dialog.setContentView(R.layout.dialog_no);
                    ((TextView)dialog.findViewById(R.id.txtDialogMessage)).setText("You need select story");
                    dialog.show();
                }

            }
        });
    }

    @Override
    public void onBackPressed() {
        UserActivity.storyId = null;
        UserActivity.numId = null;
        MasterMainFragment.sessionId = null;
        MasterMainFragment.storyId = null;
        super.onBackPressed();
    }

}
