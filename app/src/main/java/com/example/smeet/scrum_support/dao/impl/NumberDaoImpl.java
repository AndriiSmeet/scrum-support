package com.example.smeet.scrum_support.dao.impl;

import com.example.smeet.scrum_support.config.Connect;
import com.example.smeet.scrum_support.dao.NumberDao;
import com.example.smeet.scrum_support.model.Number;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Smeet on 02.12.2017.
 */

public class NumberDaoImpl implements NumberDao<Number> {

    private SessionDaoImpl sessionDao = new SessionDaoImpl();

    @Override
    public List<Number> getAllOnSession(Integer id) {
        String getAllOnSessionSQL = "SELECT * \n" +
                "FROM number n\n" +
                "INNER JOIN session s\n" +
                "ON n.session_id_fk = s.id\n" +
                "WHERE s.id = ?;";
        try {
            PreparedStatement ps = Connect.getConnection().prepareStatement(getAllOnSessionSQL);
            ps.setInt(1, id);
            return extractData(ps.executeQuery());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Integer create(Integer value, Integer sessionId) {
        String createNumSql = "INSERT INTO number(value, session_id_fk) VALUES(?, ?) ";
        try {
            PreparedStatement ps = Connect.getConnection().prepareStatement(createNumSql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, value);
            ps.setInt(2, sessionId);
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
    public Integer update(Integer id, Integer value) {
        String updateSQL = "UPDATE number SET value = ? WHERE id = ?";
            try {
                PreparedStatement ps = Connect.getConnection().prepareStatement(updateSQL, Statement.RETURN_GENERATED_KEYS);
                ps.setInt(1, value);
                ps.setInt(2, id);
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

                number.setId(rs.getInt("id"));
                number.setSession(sessionDao.getById(rs.getInt("session_id_fk")));
                number.setValue(rs.getInt("value"));

                numbers.add(number);
            }
            return numbers;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}