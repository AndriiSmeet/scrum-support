package com.example.smeet.scrum_support.service.impl;

import android.content.Context;
import android.widget.CheckBox;
import android.widget.Toast;

import com.example.smeet.scrum_support.async.session.AsyncConnectSession;
import com.example.smeet.scrum_support.async.session.AsyncCreateSession;
import com.example.smeet.scrum_support.async.session.AsyncFindSessionById;
import com.example.smeet.scrum_support.dao.SessionDao;
import com.example.smeet.scrum_support.dao.impl.SessionDaoImpl;
import com.example.smeet.scrum_support.model.Session;
import com.example.smeet.scrum_support.service.SessionService;

import java.util.List;

/**
 * Created by Smeet on 08.12.2017.
 */

public class SessionServiceImpl implements SessionService {

    private final SessionDao sessionDao = new SessionDaoImpl();
    private Context context;
    private Session session;


    public SessionServiceImpl(Context context) {
        this.context = context;

    }

    @Override
    public List<Session> getAll() {
        return sessionDao.getAll();
    }

    @Override
    public Session connect(String name, String password, CheckBox isScrumMaster) {
        if(checkValidation(name, password)) {
            AsyncConnectSession asyncConnectSession = new AsyncConnectSession(context, name, password, isScrumMaster);
            asyncConnectSession.execute();
        }
        return null;
    }

    private boolean checkValidation(String name, String password) {
        if(name.length() > 1 && password.length() > 1) {
            return true;
        }
        Toast.makeText(context, "Fields must contains more than 1 character", Toast.LENGTH_SHORT).show();
        return false;
    }

    @Override
    public Integer create(Session session) {

        if(checkValidation(session.getSessionName(), session.getPassword())) {
            AsyncCreateSession asyncCreateSession = new AsyncCreateSession(context, session);
            asyncCreateSession.execute();
        }
        return null;
    }

    @Override
    public Session getById(Integer id) {
        return sessionDao.getById(id);

    }


    @Override
    public Session getByName(String name) {
        return sessionDao.getByName(name);
    }

    @Override
    public void startStatus(Integer id) {
        sessionDao.startStatus(id);

    }

    @Override
    public void closeStatus(Integer id) {
        sessionDao.closeStatus(id);

    }
}
