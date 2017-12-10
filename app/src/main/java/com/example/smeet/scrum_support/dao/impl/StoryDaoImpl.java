package com.example.smeet.scrum_support.dao.impl;

import com.example.smeet.scrum_support.config.Connect;
import com.example.smeet.scrum_support.dao.SessionDao;
import com.example.smeet.scrum_support.dao.StoryDao;
import com.example.smeet.scrum_support.dao.impl.sql.StorySql;
import com.example.smeet.scrum_support.model.Story;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Smeet on 08.12.2017.
 */

public class StoryDaoImpl implements StoryDao {

    private SessionDao sessionDao = new SessionDaoImpl();

    @Override
    public List<Story> getAll() {
        try{
            PreparedStatement pr = Connect.getConnection().prepareStatement(StorySql.SELECT_ALL_STORY);
            return extractData(pr.executeQuery());
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Story getById(Integer id) {
        try{
            PreparedStatement pr = Connect.getConnection().prepareStatement(StorySql.SELECT_STORY_BY_ID);
            pr.setInt(1, id);
            return extractData(pr.executeQuery()).get(0);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Integer create(Story story) {
        try{
            PreparedStatement ps = Connect.getConnection().prepareStatement(StorySql.CREATE_STORY, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, story.getTitle());
            ps.setString(2, story.getDescribe());
            ps.setBoolean(3, story.getActive());
            ps.setInt(4, story.getSession().getId());
//            ps.setString(4, story.getSaveDate().toString());

            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
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
    public List<Story> getAllStoryByIdSession(Integer idSession) {
        try{
            PreparedStatement pr = Connect.getConnection().prepareStatement(StorySql.SELECT_ALL_STORY_BY_ID_SESSION);
            pr.setInt(1, idSession);
            return extractData(pr.executeQuery());
        } catch (Exception ex){
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Story> getAllStoryByIsActive(boolean arg) {
        try{
            PreparedStatement pr = Connect.getConnection().prepareStatement(StorySql.SELECT_ALL_STORY_BY_TRUE_OR_FALSE);
            pr.setBoolean(1, arg);
            return extractData(pr.executeQuery());
        }catch (Exception ex){
            ex.printStackTrace();
        }
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
