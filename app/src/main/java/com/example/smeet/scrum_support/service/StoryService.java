package com.example.smeet.scrum_support.service;

import android.widget.ListView;

import com.example.smeet.scrum_support.activity.MainActivity;
import com.example.smeet.scrum_support.activity.UserActivity;
import com.example.smeet.scrum_support.fragment.MasterMainFragment;
import com.example.smeet.scrum_support.model.Story;

import java.util.List;

/**
 * Created by Smeet on 08.12.2017.
 */

public interface StoryService {
    Story getById(Integer id);
    Integer create(Story story);
    List<Story> getAll();
    List<Story> getAllStoryByIdSession(Integer idSession, ListView listView);
    List<Story> getAllStoryByIsActive(boolean arg, Integer sessionId, UserActivity userActivity);
    List<Story> getAllStoryByIsActive(boolean arg, Integer sessionId, MasterMainFragment masterMainFragment);
    void showStoriesBySession(List<Story> stories);
    void showDialogWithActiveStories(List<Story> stories, UserActivity userActivity);
    void showDialogWithActiveStories(List<Story> stories, MasterMainFragment masterMainFragment);
}
