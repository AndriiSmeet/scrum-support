package com.example.smeet.scrum_support.dao.impl;

import com.example.smeet.scrum_support.config.Connect;
import com.example.smeet.scrum_support.dao.SessionDao;
import com.example.smeet.scrum_support.model.Session;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Smeet on 02.12.2017.
 */

public class SessionDaoImpl implements SessionDao<Session> {

    @Override
    public List<Session> getAll() {
        return null;
    }

    @Override
    public Session connectSession(String name, String password) {
        String connectToSessionSql = "SELECT * FROM session WHERE session_name = ? AND password = ?";
        PreparedStatement ps = null;
        try {
            ps = Connect.getConnection().prepareStatement(connectToSessionSql);
            ps.setString(1, name);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Session session = new Session();
                session.setId(rs.getInt("id"));
                session.setSessionName(rs.getString("session_name"));
                return session;

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Boolean createSession(String name, String password) {
        String createSessionSql = "INSERT INTO session(session_name, password, is_ready) VALUES(?, ?, ?) ";
        PreparedStatement ps = null;
        try {
            ps = Connect.getConnection().prepareStatement(createSessionSql);ps.setString(1, name);
            ps.setString(2, password);
            ps.setBoolean(3, false);
            ps.execute();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Session getById(Integer id) {

        String selectSessionSQL = "SELECT * FROM session WHERE id = ?";
        try {
            PreparedStatement ps = Connect.getConnection().prepareStatement(selectSessionSQL);
            ps.setInt(1, id);
            return extractData(ps.executeQuery()).get(0);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Session getByName(String name) {
        String selectSessionSQL = "SELECT * FROM session WHERE session_name = ?";
        try {
            PreparedStatement ps = Connect.getConnection().prepareStatement(selectSessionSQL);
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
    public void update(Session session) {

    }

    @Override
    public void startStatus(Integer id) {
        String endSessionSQL = "UPDATE session SET is_ready = ? WHERE id = ?";
        PreparedStatement ps = null;
        try {
            ps = Connect.getConnection().prepareStatement(endSessionSQL);
            ps.setBoolean(1, true);
            ps.setInt(2, id);
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void closeStatus(Integer id) {
        String endSessionSQL = "UPDATE session SET is_ready = ? WHERE id = ?";
        PreparedStatement ps = null;
        try {
            ps = Connect.getConnection().prepareStatement(endSessionSQL);
            ps.setBoolean(1, false);
            ps.setInt(2, id);
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void resetSession(Integer id) {
        String resetSessionSQL = "DELETE FROM number WHERE session_id_fk = ?";
        PreparedStatement ps = null;
        try {
            ps = Connect.getConnection().prepareStatement(resetSessionSQL);
            ps.setInt(1, id);
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
                session.setId(rs.getInt("id"));
                session.setSessionName(rs.getString("session_name"));
                session.setReady(rs.getBoolean("is_ready"));

                sessions.add(session);
            }
            return sessions;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
