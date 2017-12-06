package com.example.smeet.scrum_support.model;

/**
 * Created by Smeet on 02.12.2017.
 */

public class Number {

    private Integer id;
    private Integer value;
    private Session session;

    public Number(){}

    public Number(Integer id, Integer value, Session session) {
        this.id = id;
        this.value = value;
        this.session = session;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }
}
