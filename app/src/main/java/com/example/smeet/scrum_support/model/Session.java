package com.example.smeet.scrum_support.model;

/**
 * Created by Smeet on 02.12.2017.
 */

public class Session {
    private Integer id;
    private String sessionName;
    private Boolean isReady;

    public Session() {}

    public Session(Integer id, String sessionName, Boolean isReady) {
        this.id = id;
        this.sessionName = sessionName;
        this.isReady = isReady;
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
