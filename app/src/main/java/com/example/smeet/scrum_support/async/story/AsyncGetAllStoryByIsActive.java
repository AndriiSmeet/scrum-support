package com.example.smeet.scrum_support.async.story;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import com.example.smeet.scrum_support.R;
import com.example.smeet.scrum_support.activity.UserActivity;
import com.example.smeet.scrum_support.dao.StoryDao;
import com.example.smeet.scrum_support.dao.impl.StoryDaoImpl;
import com.example.smeet.scrum_support.fragment.MasterMainFragment;
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
    private Integer sessionId;
    private StoryServiceImpl storyService;
    private ProgressDialog progressDialog;
    private UserActivity userActivity;
    private MasterMainFragment masterMainFragment;

    public AsyncGetAllStoryByIsActive(Context context, Boolean isActive, Integer sessionId, UserActivity userActivity) {
        this.context = context;
        this.userActivity = userActivity;
        this.isActive = isActive;
        storyDao = new StoryDaoImpl();
        this.sessionId = sessionId;
        storyService = new StoryServiceImpl(context);
    }

    public AsyncGetAllStoryByIsActive(Context context, Boolean isActive, Integer sessionId, MasterMainFragment masterMainFragment ) {
        this.context = context;
        this.masterMainFragment = masterMainFragment;
        this.isActive = isActive;
        storyDao = new StoryDaoImpl();
        this.sessionId = sessionId;
        storyService = new StoryServiceImpl(context);
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
        stories = storyDao.getAllStoryByIsActive(isActive, sessionId);
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        if(stories == null) {
            Toast.makeText(context, "Stories are null", Toast.LENGTH_SHORT).show();
        } else {
            if(isActive == true){
                if (userActivity != null){
                    storyService.showDialogWithActiveStories(stories, userActivity);
                }
                if (masterMainFragment != null){
                    storyService.showDialogWithActiveStories(stories, masterMainFragment);
                }
            } else {
                Toast.makeText(context, "Are not active stories lenght: " + stories.size(), Toast.LENGTH_SHORT).show();
            }
        }
        progressDialog.dismiss();
        super.onPostExecute(aVoid);
    }

}
