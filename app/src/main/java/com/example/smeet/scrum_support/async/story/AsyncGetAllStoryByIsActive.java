package com.example.smeet.scrum_support.async.story;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import com.example.smeet.scrum_support.R;
import com.example.smeet.scrum_support.dao.StoryDao;
import com.example.smeet.scrum_support.dao.impl.StoryDaoImpl;
import com.example.smeet.scrum_support.model.Story;
import com.example.smeet.scrum_support.service.StoryService;
import com.example.smeet.scrum_support.service.impl.StoryServiceImpl;

import java.util.List;

/**
 * Created by Andrii on 09.12.2017.
 */

public class AsyncGetAllStoryByIsActive extends AsyncTask<Integer, Integer, Void> {

    private List<Story> stories;
    private Context context;
    private Boolean isActive;
    private StoryDao storyDao;
    private StoryServiceImpl storyService;
    private ProgressDialog progressDialog;

    public AsyncGetAllStoryByIsActive(Context context, Boolean isActive) {
        this.context = context;
        this.isActive = isActive;
        storyDao = new StoryDaoImpl();
        storyService = new StoryServiceImpl(context);
    }

    @Override
    protected void onPreExecute() {
        progressDialog = new ProgressDialog(context);
        progressDialog.show();
        progressDialog.setContentView(R.layout.progress_bar);
    }

    @Override
    protected Void doInBackground(Integer... integers) {
        stories = storyDao.getAllStoryByIsActive(isActive);
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        if(stories == null) {
            Toast.makeText(context, "Stories are null", Toast.LENGTH_SHORT).show();
        } else {
            if(isActive == true){
                storyService.showDialogWithActiveStories(stories);

            } else {
                Toast.makeText(context, "Are not active stories lenght: " + stories.size(), Toast.LENGTH_SHORT).show();
            }
        }
        progressDialog.dismiss();
        super.onPostExecute(aVoid);
    }

}
