package com.example.smeet.scrum_support.dao;

import com.example.smeet.scrum_support.model.Session;

import java.util.List;

/**
 * Created by Smeet on 02.12.2017.
 */

public interface SessionDao extends CrudDao<Session> {

    Session connectSession(String name, String password);
    Session getByName(String name);

    void startStatus(Integer id);
    void closeStatus(Integer id);
    void resetSession(Integer id);

}
