package com.example.smeet.scrum_support.model;

/**
 * Created by Smeet on 02.12.2017.
 */

public class Number {

    private Integer id;
    private Integer value;
    private Story story;

    public Number(){}

    public Number(Integer id, Integer value, Story story) {
        this.id = id;
        this.value = value;
        this.story = story;
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

    public Story getStory() {
        return story;
    }

    public void setStory(Story story) {
        this.story = story;
    }
}
