package com.example.smeet.scrum_support.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.example.smeet.scrum_support.R;
import com.example.smeet.scrum_support.model.Session;
import com.example.smeet.scrum_support.service.SessionService;
import com.example.smeet.scrum_support.service.impl.SessionServiceImpl;

public class LoginActivity extends AppCompatActivity {

    private EditText sessionName;
    private EditText sessionPassword;
    private Button btnCreateSession;
    private Button btnConnectSession;
    private CheckBox isScrumMaster;

    private SessionService sessionService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeForm();
        initClickAction();

    }

    private void initializeForm() {
        sessionService = new SessionServiceImpl(LoginActivity.this);
        sessionName = (EditText) findViewById(R.id.editSessionName);
        sessionPassword = (EditText) findViewById(R.id.editPassword);
        btnConnectSession = (Button) findViewById(R.id.btnConnectSession);
        btnCreateSession = (Button) findViewById(R.id.btnCreateSession);
        isScrumMaster = (CheckBox) findViewById(R.id.checkBox);
    }

    private void initClickAction() {
        btnCreateSession.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sessionService.create(new Session(sessionName.getText().toString(), sessionPassword.getText().toString()));
            }
        });

        btnConnectSession.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sessionService.connect(sessionName.getText().toString(), sessionPassword.getText().toString());
            }
        });
    }


}


