package com.example.smeet.scrum_support.model;

/**
 * Created by Smeet on 02.12.2017.
 */

public class Session {
    private Integer id;
    private String sessionName;
    private String password;
    private Boolean isReady;

    public Session() {}

    public Session(Integer id, String sessionName, String password, Boolean isReady) {
        this.id = id;
        this.sessionName = sessionName;
        this.password = password;
        this.isReady = isReady;
    }

    public Session(String sessionName, String password) {
        this.sessionName = sessionName;
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSessionName() {
        return sessionName;
    }

    public void setSessionName(String sessionName) {
        this.sessionName = sessionName;
    }

    public Boolean getReady() {
        return isReady;
    }

    public void setReady(Boolean ready) {
        isReady = ready;
    }
}
