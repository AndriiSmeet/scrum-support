package com.example.smeet.scrum_support.async.number;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.view.Window;
import android.widget.TextView;
import android.widget.Toast;

import com.example.smeet.scrum_support.R;
import com.example.smeet.scrum_support.dao.NumberDao;
import com.example.smeet.scrum_support.dao.impl.NumberDaoImpl;
import com.example.smeet.scrum_support.model.Number;

import java.util.List;

/**
 * Created by Smeet on 09.12.2017.
 */

public class AsyncNumberUpdate extends AsyncTask<Integer, Integer, Void> {

    private Context context;
    private final NumberDao numberDao;
    private ProgressDialog progressDialog;
    private Integer numberId;
    private Integer value;
    private Integer storyId;


    public AsyncNumberUpdate(Context context, Integer value, Integer storyId) {
        this.context = context;
        this.value = value;
        this.storyId = storyId;
        numberDao = new NumberDaoImpl();

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
        numberId = numberDao.update(value, storyId);
        return null;

    }

    @Override
    protected void onPostExecute(Void s) {

        if(numberId != null) {
            Toast.makeText(context, "Your number was updated on " + value, Toast.LENGTH_SHORT).show();
            System.out.println("Your number was updated on " + value);
            Dialog dialog = new Dialog(context);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setContentView(R.layout.dialog_selected_number);
            ((TextView)dialog.findViewById(R.id.txtSelectedNumber)).setText("" + value);
            dialog.show();
        }

        progressDialog.dismiss();
        super.onPostExecute(s);
    }


}