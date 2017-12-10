package com.example.smeet.scrum_support.dao;

import com.example.smeet.scrum_support.model.Story;

import java.util.List;

/**
 * Created by Smeet on 08.12.2017.
 */

public interface StoryDao extends CrudDao<Story> {
    List<Story> getAllStoryByIdSession(Integer idSession);
    List<Story> getAllStoryByIsActive(boolean arg);
}
