package com.example.smeet.scrum_support.dao;

import com.example.smeet.scrum_support.model.Session;

import java.util.List;

/**
 * Created by Smeet on 02.12.2017.
 */

public interface SessionDao<T> {

    List<T> getAll();
    Session connectSession(String name, String password);
    Boolean createSession(String name, String password);
    T getById(Integer id);
    T getByName(String name);
    void update(T t);
    void startStatus(Integer id);
    void closeStatus(Integer id);
    void resetSession(Integer id);

}
