package com.example.smeet.scrum_support.service;

import com.example.smeet.scrum_support.model.Number;
/**
 * Created by Smeet on 08.12.2017.
 */

public interface NumberService {

    void getAllNumberOnStory(Integer id);
    Integer update(Number number);

}
