package com.example.smeet.scrum_support.dao.impl;

import com.example.smeet.scrum_support.config.Connect;
import com.example.smeet.scrum_support.dao.SessionDao;
import com.example.smeet.scrum_support.dao.impl.sql.SessionSql;
import com.example.smeet.scrum_support.model.Session;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Smeet on 02.12.2017.
 */

public class SessionDaoImpl implements SessionDao {

    @Override
    public List<Session> getAll() {

        try{
            PreparedStatement pr = Connect.getConnection().prepareStatement(SessionSql.GET_ALL_SESSION_QUERY);
            return extractData(pr.executeQuery());
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Session connect(String name, String password) {

        try {
            PreparedStatement ps = Connect.getConnection().prepareStatement(SessionSql.CONNECT_SESSION_QUERY);
            ps.setString(1, name);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Session session = new Session();
                session.setId(rs.getInt(SessionSql.PARAM_SESSION_ID));
                session.setSessionName(rs.getString(SessionSql.PARAM_SESSION_NAME));
                return session;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Integer create(Session session) {

        try {
            PreparedStatement ps = Connect.getConnection().prepareStatement(SessionSql.CREATE_SESSION_QUERY, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, session.getSessionName());
            ps.setString(2, session.getPassword());
            ps.setBoolean(3, false);
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
    public Session getById(Integer id) {

        try {
            PreparedStatement ps = Connect.getConnection().prepareStatement(SessionSql.GET_SESSION_BY_ID_QUERY);
            ps.setInt(1, id);
            return extractData(ps.executeQuery()).get(0);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Integer delete(Integer id) {
        return null;
    }

    @Override
    public Session getByName(String name) {

        try {
            PreparedStatement ps = Connect.getConnection().prepareStatement(SessionSql.GET_SESSION_BY_NAME_QUERY);
            ps.setString(1, name);
            if(!extractData(ps.executeQuery()).isEmpty()) {
                return extractData(ps.executeQuery()).get(0);
            } else {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }



    @Override
    public void startStatus(Integer id) {

        try {
            PreparedStatement ps = Connect.getConnection().prepareStatement(SessionSql.UPDATE_SESSION_STATUS_QUERY);
            ps.setBoolean(1, true);
            ps.setInt(2, id);
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void closeStatus(Integer id) {

        try {
            PreparedStatement ps = Connect.getConnection().prepareStatement(SessionSql.UPDATE_SESSION_STATUS_QUERY);
            ps.setBoolean(1, false);
            ps.setInt(2, id);
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Session> extractData(ResultSet rs) {
        List<Session> sessions = new ArrayList<>();
        try {
            while(rs.next()) {
                Session session = new Session();
                session.setId(rs.getInt(SessionSql.PARAM_SESSION_ID));
                session.setSessionName(SessionSql.PARAM_SESSION_NAME);
                session.setPassword(SessionSql.PARAM_SESSION_PASSWORD);
                session.setReady(rs.getBoolean(SessionSql.PARAM_SESSION_IS_READY));

                sessions.add(session);
            }
            return sessions;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
