package com.example.smeet.scrum_support.service.impl;

import android.content.Context;

import com.example.smeet.scrum_support.async.story.AsyncCreateStory;
import com.example.smeet.scrum_support.async.story.AsyncGetAllStoryByIdSession;
import com.example.smeet.scrum_support.async.story.AsyncGetAllStoryByIsActive;
import com.example.smeet.scrum_support.async.story.AsyncGetByIdStory;
import com.example.smeet.scrum_support.model.Story;
import com.example.smeet.scrum_support.service.StoryService;

import java.util.List;

/**
 * Created by Smeet on 08.12.2017.
 */

public class StoryServiceImpl implements StoryService {

    Context context;

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
}
