package com.example.smeet.scrum_support.service.impl;

import android.app.Dialog;
import android.content.Context;
import android.view.Window;
import android.widget.ListView;

import com.example.smeet.scrum_support.R;
import com.example.smeet.scrum_support.adapter.AdapterStoriesBySession;
import com.example.smeet.scrum_support.adapter.UserActiveStories;
import com.example.smeet.scrum_support.async.story.AsyncCreateStory;
import com.example.smeet.scrum_support.async.story.AsyncGetAllStoryByIdSession;
import com.example.smeet.scrum_support.async.story.AsyncGetAllStoryByIsActive;
import com.example.smeet.scrum_support.async.story.AsyncGetByIdStory;
import com.example.smeet.scrum_support.model.Story;
import com.example.smeet.scrum_support.service.StoryService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Smeet on 08.12.2017.
 */

public class StoryServiceImpl implements StoryService {

    private Context context;

    public StoryServiceImpl(Context context) {
        this.context = context;
    }

    @Override
    public Story getById(Integer id) {
        AsyncGetByIdStory asyncTask = new AsyncGetByIdStory(id, context);
        asyncTask.execute();
        return null;
    }

    @Override
    public Integer create(Story story) {
        AsyncCreateStory asyncTask = new AsyncCreateStory(story, context);
        asyncTask.execute();
        return null;
    }

    @Override
    public List<Story> getAll() {
        return null;
    }

    @Override
    public List<Story> getAllStoryByIdSession(Integer idSession) {
        AsyncGetAllStoryByIdSession asyncTask = new AsyncGetAllStoryByIdSession(context, idSession);
        asyncTask.execute();
        return null;
    }

    @Override
    public List<Story> getAllStoryByIsActive(boolean arg) {
        AsyncGetAllStoryByIsActive asyncTask = new AsyncGetAllStoryByIsActive(context, arg);
        asyncTask.execute();
        return null;
    }

    @Override
    public void showStorieBySession(List<Story> stories){
        AdapterStoriesBySession adapter = new AdapterStoriesBySession(context, new ArrayList<Story>(stories), R.id.listStory);
        ListView lv = adapter.getListView();
        lv.setAdapter(adapter);
    }

    @Override
    public void showDialogWithActiveStories(List<Story> stories) {

        Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_stories_list);

        ListView lv = dialog.findViewById(R.id.lv);
        UserActiveStories adapter = new UserActiveStories(context, new ArrayList<Story>(stories));
        lv.setAdapter(adapter);

        dialog.show();
    }
}
