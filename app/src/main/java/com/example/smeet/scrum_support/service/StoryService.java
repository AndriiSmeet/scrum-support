package com.example.smeet.scrum_support.service;

import com.example.smeet.scrum_support.model.Story;

import java.util.List;

/**
 * Created by Smeet on 08.12.2017.
 */

public interface StoryService {
    Story getById(Integer id);
    Integer create(Story story);
    List<Story> getAll();
    List<Story> getAllStoryByIdSession(Integer idSession);
    List<Story> getAllStoryByIsActive(boolean arg);
}
