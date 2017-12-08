package com.example.smeet.scrum_support.dao;

import java.sql.ResultSet;
import java.util.List;

/**
 * Created by Smeet on 08.12.2017.
 */

public interface CrudDao<T> {
    List<T> getAll();
    T getById(Integer id);
    Integer create(T t);
    Integer delete(Integer id);
    List<T> extractData(ResultSet resultSet);
}
