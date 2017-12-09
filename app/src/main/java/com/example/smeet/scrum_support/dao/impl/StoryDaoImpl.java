package com.example.smeet.scrum_support.dao.impl;

import com.example.smeet.scrum_support.config.Connect;
import com.example.smeet.scrum_support.dao.SessionDao;
import com.example.smeet.scrum_support.dao.StoryDao;
import com.example.smeet.scrum_support.dao.impl.sql.StorySql;
import com.example.smeet.scrum_support.model.Story;

import java.sql.PreparedStatement;
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

        String selectAllStory = "SELECT * FROM story";
        try{
            PreparedStatement pr = Connect.getConnection().prepareStatement(selectAllStory);
            return extractData(pr.executeQuery());
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Story getById(Integer id) {
        String selectStoryById = "SELECT * FROM story WHERE id =?";
        try{
            PreparedStatement pr = Connect.getConnection().prepareStatement(selectStoryById);
            pr.setInt(1, id);
            return extractData(pr.executeQuery()).get(0);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Integer create(Story story) {
        String createStory = "INSERT INTO story VALUES (?, ?, ?, ?, ?, ?)";
        try{
            PreparedStatement pr = Connect.getConnection().prepareStatement(createStory);
            pr.setInt(1, story.getId());
            pr.setString(2, story.getTitle());
            pr.setInt(3, story.getSession().getId());
            pr.setString(4, story.getDescribe());
            pr.setString(5, story.getSaveDate().toString());
            pr.setBoolean(6, story.getActive());

            ResultSet rs = pr.getGeneratedKeys();
            if (rs.next()){
                return rs.getInt(1);
            }
        }catch(Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Integer delete(Integer id) {
        //TODO write method delete Story
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
