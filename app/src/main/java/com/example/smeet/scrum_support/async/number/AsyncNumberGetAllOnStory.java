package com.example.smeet.scrum_support.async.number;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import com.example.smeet.scrum_support.R;
import com.example.smeet.scrum_support.dao.NumberDao;
import com.example.smeet.scrum_support.dao.impl.NumberDaoImpl;
import com.example.smeet.scrum_support.model.Number;
import com.example.smeet.scrum_support.service.impl.NumberServiceImpl;
import com.example.smeet.scrum_support.service.impl.StoryServiceImpl;
import com.github.mikephil.charting.charts.BarChart;

import java.util.Collections;
import java.util.List;

/**
 * Created by Smeet on 09.12.2017.
 */

public class AsyncNumberGetAllOnStory extends AsyncTask<Integer, Integer, Void> {

    private Context context;
    private final NumberDao numberDao;
    private final StoryServiceImpl storyService;
    private ProgressDialog progressDialog;
    private List<Number> numbers;
    private Integer storyId;

    public AsyncNumberGetAllOnStory(Context context, Integer storyId) {
        this.context = context;
        this.storyId = storyId;
        storyService = new StoryServiceImpl(context);
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
        getNumbers();
        return null;

    }

    @Override
    protected void onPostExecute(Void s) {
        storyService.showDialogWithStatsByStory(numbers, storyId);
        progressDialog.dismiss();
        super.onPostExecute(s);
    }

    private void getNumbers() {
        numbers = numberDao.getAllNumberOnStory(storyId);
    }

}