package com.example.smeet.scrum_support.async.session;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.Toast;

import com.example.smeet.scrum_support.R;
import com.example.smeet.scrum_support.activity.MainActivity;
import com.example.smeet.scrum_support.dao.SessionDao;
import com.example.smeet.scrum_support.dao.impl.SessionDaoImpl;
import com.example.smeet.scrum_support.model.Session;

/**
 * Created by Smeet on 09.12.2017.
 */

public class AsyncConnectSession extends AsyncTask<Integer, Integer, Void> {

    private Context context;
    private SessionDao sessionDao;
    private String name;
    private String password;
    private Session session;
    private ProgressDialog progressDialog;

    public AsyncConnectSession(Context context, String name, String password) {
        this.context = context;
        this.sessionDao = new SessionDaoImpl();
        this.name = name;
        this.password = password;

    }

    @Override
    protected void onPreExecute() {

        progressDialog = new ProgressDialog(context);
        progressDialog.show();
        progressDialog.setContentView(R.layout.progress_bar);
    }

    @Override
    protected Void doInBackground(Integer... integers) {
        connect();
        return null;

    }

    @Override
    protected void onPostExecute(Void s) {

        if(session == null) {
            Toast.makeText(context, "Connection with this data do not exist", Toast.LENGTH_SHORT).show();
        } else {
            Intent intent = new Intent(context, MainActivity.class);
            intent.putExtra("sessionName", session.getSessionName());
            intent.putExtra("sessionId", session.getId());
            context.startActivity(intent);
        }

        progressDialog.dismiss();
        super.onPostExecute(s);
    }

    private void connect() {
        session = sessionDao.connect(name, password);
    }

}
