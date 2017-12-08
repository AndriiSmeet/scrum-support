package com.example.smeet.scrum_support.model;

import java.util.Date;

/**
 * Created by Smeet on 05.12.2017.
 */

public class Story {

    private Integer id;
    private String title;
    private Session session;
    private String describe;
    private Date saveDate;
    private Boolean isActive;

    public Story(){}

    public Story(Integer id, String title, Session session, String describe, Date saveDate, Boolean isActive) {
        this.id = id;
        this.title = title;
        this.session = session;
        this.describe = describe;
        this.saveDate = saveDate;
        this.isActive = isActive;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public Date getSaveDate() {
        return saveDate;
    }

    public void setSaveDate(Date saveDate) {
        this.saveDate = saveDate;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }
}
