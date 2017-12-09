package com.example.smeet.scrum_support.dao;

import com.example.smeet.scrum_support.model.Number;

import java.util.List;

/**
 * Created by Smeet on 02.12.2017.
 */

public interface NumberDao extends CrudDao<Number> {

    List<Number> getAllNumberOnStory(Integer id);
    Integer update(Integer id, Integer value);

}
