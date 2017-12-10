package com.example.smeet.scrum_support.service.impl;

import android.content.Context;

import com.example.smeet.scrum_support.async.number.AsyncNumberGetAll;
import com.example.smeet.scrum_support.dao.NumberDao;
import com.example.smeet.scrum_support.dao.impl.NumberDaoImpl;
import com.example.smeet.scrum_support.model.Number;
import com.example.smeet.scrum_support.service.NumberService;

/**
 * Created by Smeet on 08.12.2017.
 */

public class NumberServiceImpl implements NumberService {

    private Context context;
    private final NumberDao numberDao;
    private Integer storyId;

    public NumberServiceImpl(Context context, Integer storyId) {
        this.context = context;
        this.storyId = storyId;
        numberDao = new NumberDaoImpl();
    }

    @Override
    public void getAllNumberOnStory(Integer id) {
        new AsyncNumberGetAll(context, storyId).execute();
    }

    @Override
    public Integer update(Number number) {
        return numberDao.update(number);
    }
}
