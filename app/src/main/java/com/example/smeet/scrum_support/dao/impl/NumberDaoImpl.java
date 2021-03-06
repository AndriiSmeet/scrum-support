package com.example.smeet.scrum_support.dao.impl;

import com.example.smeet.scrum_support.config.Connect;
import com.example.smeet.scrum_support.dao.NumberDao;
import com.example.smeet.scrum_support.dao.StoryDao;
import com.example.smeet.scrum_support.dao.impl.sql.NumberSql;
import com.example.smeet.scrum_support.model.Number;
import com.example.smeet.scrum_support.model.Story;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Smeet on 02.12.2017.
 */

public class NumberDaoImpl implements NumberDao {

    private StoryDao storyDao = new StoryDaoImpl();

    @Override
    public List<Number> getAll() {
        return null;
    }

    @Override
    public Number getById(Integer id) {
        return null;
    }

    @Override
    public Integer create(Number number) {
        return null;
    }

    @Override
    public Integer create(Integer value, Integer storyId) {
        try {
            PreparedStatement ps = Connect.getConnection().prepareStatement(NumberSql.CREATE_NUMBER_QUERY, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, value);
            ps.setInt(2, storyId);
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;

    }

    @Override
    public Integer delete(Integer id) {
        try {
            PreparedStatement ps = Connect.getConnection().prepareStatement(NumberSql.DELETE_NUMBER_BY_STORY_ID, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, id);
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                return rs.getInt(1);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }



    @Override
    public List<Number> getAllNumberOnStory(Integer id) {
        try {
            PreparedStatement ps = Connect.getConnection().prepareStatement(NumberSql.GET_ALL_NUM_ON_SESSION_QUERY);
            System.out.println(id);
            ps.setInt(1, id);
            return extractData(ps.executeQuery());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    @Override
    public Integer update(Integer value, Integer storyId) {
            try {
                PreparedStatement ps = Connect.getConnection().prepareStatement(NumberSql.UPDATE_NUMBER_QUERY, Statement.RETURN_GENERATED_KEYS);
                ps.setInt(1, value);
                ps.setInt(2, storyId);
                ps.executeUpdate();
                ResultSet rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    return rs.getInt(1);

                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return null;
    }


    public List<Number> extractData(ResultSet rs) {
        List<Number> numbers = new ArrayList<>();
        try {
            while(rs.next()) {
                Number number = new Number();
                number.setId(rs.getInt(NumberSql.PARAM_NUMBER_ID));
                number.setValue(rs.getInt(NumberSql.PARAM_NUMBER_VALUE));
                number.setStory(storyDao.getById(rs.getInt(NumberSql.PARAM_NUMBER_STORY_ID)));

                numbers.add(number);
            }
            return numbers;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}