package com.example.smeet.scrum_support.async.session;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.view.Window;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.example.smeet.scrum_support.R;
import com.example.smeet.scrum_support.activity.MainActivity;
import com.example.smeet.scrum_support.activity.UserActivity;
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
    private Dialog dialog;
    private CheckBox isScumMaster;

    public AsyncConnectSession(Context context, String name, String password, CheckBox isScrumMaster) {
        this.context = context;
        this.sessionDao = new SessionDaoImpl();
        this.name = name;
        this.password = password;
        this.isScumMaster = isScrumMaster;

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
        connect();
        return null;

    }

    @Override
    protected void onPostExecute(Void s) {

        if(session != null) {
            if(isScumMaster.isChecked()) {
                Intent intent = new Intent(context, MainActivity.class);
                intent.putExtra("sessionName", session.getSessionName());
                intent.putExtra("sessionId", session.getId());
                context.startActivity(intent);
            } else {
                Intent intent = new Intent(context, UserActivity.class);
                intent.putExtra("sessionName", session.getSessionName());
                intent.putExtra("sessionId", session.getId());
                context.startActivity(intent);
            }

        }
        else {
            dialog = new Dialog(context);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setContentView(R.layout.dialog_no);
            ((TextView)dialog.findViewById(R.id.txtDialogMessage)).setText("Session with that data does not exist");
            dialog.show();
            dialog.setCancelable(true);
        }

        progressDialog.dismiss();
        super.onPostExecute(s);
    }

    private void connect() {
        session = sessionDao.connect(name, password);
    }

}
