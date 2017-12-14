package com.example.smeet.scrum_support.async.story;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import com.example.smeet.scrum_support.R;
import com.example.smeet.scrum_support.dao.StoryDao;
import com.example.smeet.scrum_support.dao.impl.StoryDaoImpl;
import com.example.smeet.scrum_support.model.Story;

/**
 * Created by Andrii on 09.12.2017.
 */

public class AsyncGetByIdStory extends AsyncTask<Integer, Integer, Void> {
    Integer id;
    Context context;
    Story story;
    StoryDao storyDao;
    ProgressDialog progressDialog;

    public AsyncGetByIdStory(Integer id, Context context) {
        this.id = id;
        this.context = context;
        storyDao = new StoryDaoImpl();
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
        story = storyDao.getById(id);
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        if(story == null) {
            Toast.makeText(context, "Story is null", Toast.LENGTH_SHORT).show();
        } else {

//            TempSession.setStoryId(story.getId());
//            MasterMainFragment.storyId = story.getId();
//            UserActivity.storyId = story.getId();

            Toast.makeText(context, "Story with id: " + story.getId() + " selected", Toast.LENGTH_SHORT).show();
        }
        progressDialog.dismiss();
        super.onPostExecute(aVoid);
    }
}
