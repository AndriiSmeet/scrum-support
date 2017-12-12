package com.example.smeet.scrum_support.async.story;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.widget.ListView;
import android.widget.Toast;

import com.example.smeet.scrum_support.adapter.AdapterStoriesBySession;
import com.example.smeet.scrum_support.R;
import com.example.smeet.scrum_support.dao.StoryDao;
import com.example.smeet.scrum_support.dao.impl.StoryDaoImpl;
import com.example.smeet.scrum_support.model.Story;
import com.example.smeet.scrum_support.service.StoryService;
import com.example.smeet.scrum_support.service.impl.StoryServiceImpl;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Andrii on 09.12.2017.
 */

public class AsyncGetAllStoryByIdSession extends AsyncTask<Integer, Integer, Void> {

    private List<Story> stories;
    private Context context;
    private StoryDao storyDao;
    private ProgressDialog progressDialog;
    private Integer idSession;
    private StoryServiceImpl storyService;
    private ListView listView;

    public AsyncGetAllStoryByIdSession(Context context, Integer idSession, ListView listView) {
        this.idSession = idSession;
        this.context = context;
        this.storyDao = new StoryDaoImpl();
        this.listView = listView;
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
        stories = storyDao.getAllStoryByIdSession(idSession);
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        if(stories == null) {
            Toast.makeText(context, "Stories is null", Toast.LENGTH_SHORT).show();
        } else {
            AdapterStoriesBySession adapter = new AdapterStoriesBySession(context, new ArrayList<Story>(stories), R.id.listStory);
            listView.setAdapter(adapter);

//            storyService.showDialogWithActiveStories(stories);
//            Toast.makeText(context, "Stories with Session " + idSession + " lenght: " + stories.size(), Toast.LENGTH_SHORT).show();
        }

        progressDialog.dismiss();
        super.onPostExecute(aVoid);
    }
}
