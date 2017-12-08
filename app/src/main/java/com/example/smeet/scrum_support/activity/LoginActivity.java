package com.example.smeet.scrum_support.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.smeet.scrum_support.R;
import com.example.smeet.scrum_support.config.Connect;
import com.example.smeet.scrum_support.dao.impl.SessionDaoImpl;
import com.example.smeet.scrum_support.model.Session;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginActivity extends AppCompatActivity {

    private EditText sessionName;
    private EditText password;
    private Button btnCreateSession;
    private Button btnConnectSession;
    private CheckBox isScrumMaster;
    private SessionDaoImpl sessionDao;
    private TextView error;

    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        initializeForm();
//        initClickAction();

    }

    private void initializeForm() {
        sessionDao = new SessionDaoImpl();
        sessionName = (EditText) findViewById(R.id.editSessionName);
        password = (EditText) findViewById(R.id.editPassword);
        btnConnectSession = (Button) findViewById(R.id.btnConnectSession);
        btnCreateSession = (Button) findViewById(R.id.btnCreateSession);
        isScrumMaster = (CheckBox) findViewById(R.id.checkBox);
        error = (TextView) findViewById(R.id.error);
    }

//    private void initClickAction() {
//        btnCreateSession.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                try {
//                    if(checkData()) {
//                        createSession();
//                    } else {
//                        Toast.makeText(getApplicationContext(), "Fields must contains more than 1 char", Toast.LENGTH_SHORT).show();
//                    }
//                } catch (SQLException e) {
//                    e.printStackTrace();
//                }
//
//            }
//        });
//
//        btnConnectSession.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                try {
//                    if(checkData()) {
//                        connectSession();
//                    } else {
//                        Toast.makeText(getApplicationContext(), "Fields must contains more than 1 char", Toast.LENGTH_SHORT).show();
//                    }
//                } catch (SQLException e) {
//                    e.printStackTrace();
//                }
//            }
//        });
//    }
//
//    private void connectSession() throws SQLException {
//
//        Session session = sessionDao.connectSession(sessionName.getText().toString(), password.getText().toString());
//        if(session != null) {
//            if(isScrumMaster.isChecked()) {
//                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
//                intent.putExtra("sessionId", session.getId());
//                startActivity(intent);
//            } else {
//                Intent intent = new Intent(getApplicationContext(), UserActivity.class);
//                intent.putExtra("sessionId", session.getId());
//                startActivity(intent);
//            }
//
//        } else {
//            Toast.makeText(getApplicationContext(), "Session with this name not exist", Toast.LENGTH_SHORT).show();
//        }
//    }



//    private void createSession() throws SQLException {
//
//        Session session = sessionDao.getByName(sessionName.getText().toString());
//        if(session == null) {
//                if(sessionDao.createSession(sessionName.getText().toString(), password.getText().toString())) {
//                    Toast.makeText(getApplicationContext(), "Session created", Toast.LENGTH_SHORT).show();
//                } else {
//                    Toast.makeText(getApplicationContext(), "Something went wrong ", Toast.LENGTH_SHORT).show();
//                }
//        } else {
//            Toast.makeText(getApplicationContext(), "Session with this name already exist ", Toast.LENGTH_SHORT).show();
//        }
//    }
//
//    private boolean checkData() {
//        if(sessionName.getText().toString().length() > 1 && password.getText().toString().length() > 1) {
//            return true;
//        }
//        return false;
//    }

}


