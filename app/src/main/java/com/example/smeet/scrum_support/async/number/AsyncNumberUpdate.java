package com.example.smeet.scrum_support.async.number;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
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
    private Number number;


    public AsyncNumberUpdate(Context context, Number number) {
        this.context = context;
        this.number = number;
        numberDao = new NumberDaoImpl();

    }

    @Override
    protected void onPreExecute() {
        progressDialog = new ProgressDialog(context);
        progressDialog.show();
        progressDialog.setContentView(R.layout.progress_bar);
    }

    @Override
    protected Void doInBackground(Integer... integers) {
        updateNumber();
        return null;

    }

    @Override
    protected void onPostExecute(Void s) {

        if(numberId != null) {
            Toast.makeText(context, "Your number was updated on " + number.getValue(), Toast.LENGTH_SHORT).show();
        }

        progressDialog.dismiss();
        super.onPostExecute(s);
    }

    private void updateNumber() {
        numberId = numberDao.update(number);
    }

}