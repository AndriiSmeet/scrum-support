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
        String selectAllSession = "SELECT * FROM session";
        try{
            PreparedStatement pr = Connect.getConnection().prepareStatement(selectAllSession);
            return extractData(pr.executeQuery());
        }catch (Exception e){
            e.printStackTrace();
        }
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
    public Integer create(Session session) {
        String createSessionSql = "INSERT INTO session(session_name, password, is_ready) VALUES(?, ?, ?) ";
        try {
            PreparedStatement ps = Connect.getConnection().prepareStatement(createSessionSql, Statement.RETURN_GENERATED_KEYS);
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
    public Integer delete(Integer id) {
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
