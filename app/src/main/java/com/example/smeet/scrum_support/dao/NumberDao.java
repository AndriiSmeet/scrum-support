package com.example.smeet.scrum_support.dao;

import java.util.List;

/**
 * Created by Smeet on 02.12.2017.
 */

public interface NumberDao<T> {
    List<T> getAllOnSession(Integer id);
    Integer create(Integer value, Integer sessionId);
    Integer update(Integer id, Integer value);

}
