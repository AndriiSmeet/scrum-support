package com.example.smeet.scrum_support.async.number;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import com.example.smeet.scrum_support.model.Number;

import com.example.smeet.scrum_support.R;
import com.example.smeet.scrum_support.dao.NumberDao;
import com.example.smeet.scrum_support.dao.impl.NumberDaoImpl;
import com.example.smeet.scrum_support.service.NumberService;
import com.example.smeet.scrum_support.service.impl.NumberServiceImpl;
import com.github.mikephil.charting.charts.BarChart;

import java.util.List;

/**
 * Created by Smeet on 09.12.2017.
 */

public class AsyncNumberGetAll extends AsyncTask<Integer, Integer, Void> {

    private Context context;
    private final NumberDao numberDao;
    private final NumberServiceImpl numberService;
    private ProgressDialog progressDialog;
    private List<Number> numbers;
    private Integer storyId;
    private BarChart barChart;

    public AsyncNumberGetAll(Context context, Integer storyId, BarChart barChart) {
        this.context = context;
        this.storyId = storyId;
        numberService = new NumberServiceImpl(context);
        numberDao = new NumberDaoImpl();
        this.barChart = barChart;

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
        getNumbers();
        return null;

    }

    @Override
    protected void onPostExecute(Void s) {
        numberService.showStats(numbers, barChart);
        progressDialog.dismiss();
        super.onPostExecute(s);
    }

    private void getNumbers() {
        numbers = numberDao.getAllNumberOnStory(storyId);
    }

}