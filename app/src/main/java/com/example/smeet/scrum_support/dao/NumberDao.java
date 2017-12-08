package com.example.smeet.scrum_support.dao;

import java.util.List;
import com.example.smeet.scrum_support.model.Number;

/**
 * Created by Smeet on 02.12.2017.
 */

public interface NumberDao extends CrudDao<Number> {
    List<Number> getAllOnSession(Integer id);
    Integer create(Integer value, Integer sessionId);
    Integer update(Integer id, Integer value);

}
