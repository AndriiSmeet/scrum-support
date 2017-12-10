package com.example.smeet.scrum_support.async.story;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import com.example.smeet.scrum_support.R;
import com.example.smeet.scrum_support.dao.StoryDao;
import com.example.smeet.scrum_support.dao.impl.StoryDaoImpl;
import com.example.smeet.scrum_support.model.Story;

import java.util.List;

/**
 * Created by Andrii on 09.12.2017.
 */

public class AsyncGetAllStories extends AsyncTask<Integer, Integer, Void> {

    List<Story> stories;
    Context context;
    StoryDao storyDao;
    ProgressDialog progressDialog;

    public AsyncGetAllStories(Context context) {
        this.context = context;
        this.storyDao = new StoryDaoImpl();
    }

    @Override
    protected void onPreExecute() {
        progressDialog = new ProgressDialog(context);
        progressDialog.show();
        progressDialog.setContentView(R.layout.progress_bar);
    }

    @Override
    protected Void doInBackground(Integer... integers) {
        stories = storyDao.getAll();
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        if(stories == null) {
            Toast.makeText(context, "Stories are null", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Stories lenght: " + stories.size(), Toast.LENGTH_SHORT).show();
        }
        progressDialog.dismiss();
        super.onPostExecute(aVoid);
    }
}
