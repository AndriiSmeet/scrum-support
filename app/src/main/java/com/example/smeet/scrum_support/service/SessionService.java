package com.example.smeet.scrum_support.service;

import android.widget.CheckBox;

import com.example.smeet.scrum_support.model.Session;

import java.util.List;

/**
 * Created by Smeet on 08.12.2017.
 */

public interface SessionService {

    List<Session> getAll();
    Session connect(String name, String password, CheckBox isScrumMaster);
    Integer create(Session session);
    Session getById(Integer id);
    Session getByName(String name);
    void startStatus(Integer id);
    void closeStatus(Integer id);
}
