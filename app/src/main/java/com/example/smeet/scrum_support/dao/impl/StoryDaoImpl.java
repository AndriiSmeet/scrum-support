package com.example.smeet.scrum_support.dao.impl;

import com.example.smeet.scrum_support.dao.SessionDao;
import com.example.smeet.scrum_support.dao.StoryDao;
import com.example.smeet.scrum_support.dao.impl.sql.StorySql;
import com.example.smeet.scrum_support.model.Story;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Smeet on 08.12.2017.
 */

public class StoryDaoImpl implements StoryDao {

    private SessionDao sessionDao = new SessionDaoImpl();

    @Override
    public List<Story> getAll() {
        return null;
    }

    @Override
    public Story getById(Integer id) {
        return null;
    }

    @Override
    public Integer create(Story story) {
        return null;
    }

    @Override
    public Integer delete(Integer id) {
        return null;
    }

    @Override
    public List<Story> extractData(ResultSet resultSet) {
        List<Story> stories = new ArrayList<>();
        try {
            while(resultSet.next()) {
                Story story = new Story();
                story.setId(resultSet.getInt(StorySql.PARAM_STORY_ID));
                story.setTitle(resultSet.getString(StorySql.PARAM_STORY_TITLE));
                story.setDescribe(resultSet.getString(StorySql.PARAM_STORY_DESCRIBE));
                story.setActive(resultSet.getBoolean(StorySql.PARAM_STORY_IS_ACTIVE));
                story.setSaveDate(resultSet.getDate(StorySql.PARAM_STORY_SAVE_DATE));
                story.setSession(sessionDao.getById(resultSet.getInt(StorySql.PARAM_STORY_SESSION_ID)));

                stories.add(story);
            }
            return stories;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
