package com.example.smeet.scrum_support.model;

/**
 * Created by Smeet on 05.12.2017.
 */

public class Story {

    private Integer id;
    private String title;
    private Session session;

    public Story(Integer id, String title, Session session) {
        this.id = id;
        this.title = title;
        this.session = session;
    }

    public Story(){}

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
}
