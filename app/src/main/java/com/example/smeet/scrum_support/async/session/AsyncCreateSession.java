package com.example.smeet.scrum_support.async.session;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import com.example.smeet.scrum_support.R;
import com.example.smeet.scrum_support.dao.SessionDao;
import com.example.smeet.scrum_support.dao.impl.SessionDaoImpl;
import com.example.smeet.scrum_support.model.Session;

/**
 * Created by Smeet on 09.12.2017.
 */

public class AsyncCreateSession extends AsyncTask<Integer, Integer, Void> {

    private Context context;
    private SessionDao sessionDao;
    private Session session;
    private ProgressDialog progressDialog;
    private Integer sessionId;

    public AsyncCreateSession(Context context, Session session) {
        this.context = context;
        this.sessionDao = new SessionDaoImpl();
        this.session = session;

    }

    @Override
    protected void onPreExecute() {
        progressDialog = new ProgressDialog(context);
        progressDialog.show();
        progressDialog.setContentView(R.layout.progress_bar);
    }

    @Override
    protected Void doInBackground(Integer... integers) {
        create();
        return null;

    }

    @Override
    protected void onPostExecute(Void s) {

        if(sessionId == null) {
            Toast.makeText(context, "Session with this name already exist", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Session successfully created", Toast.LENGTH_SHORT).show();
        }
        progressDialog.dismiss();
        super.onPostExecute(s);
    }

    private void create() {
        sessionId = sessionDao.create(session);
    }

}