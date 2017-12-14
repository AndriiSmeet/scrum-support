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
 * Created by Smeet on 12.12.2017.
 */

public class AsyncFindSessionById extends AsyncTask<Integer, Integer, Void> {

    private Context context;
    private SessionDao sessionDao;
    private Integer id;
    private Session session;
    private ProgressDialog progressDialog;

    public AsyncFindSessionById(Context context, Integer id) {
        this.context = context;
        this.sessionDao = new SessionDaoImpl();
        this.id = id;

    }

    @Override
    protected void onPreExecute() {
        progressDialog = new ProgressDialog(context);
        progressDialog.setCancelable(false);
        progressDialog.show();
        progressDialog.setContentView(R.layout.progress_bar);
    }

    @Override
    protected Void doInBackground(Integer... integers) {
        session = sessionDao.getById(id);
        return null;

    }

    @Override
    protected void onPostExecute(Void s) {

        if(session != null) {
            Toast.makeText(context, "Session founded", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(context, "Session not founded", Toast.LENGTH_SHORT).show();
        }

        progressDialog.dismiss();
        super.onPostExecute(s);
    }


}