package com.example.smeet.scrum_support.service;

import com.github.mikephil.charting.charts.BarChart;

/**
 * Created by Smeet on 08.12.2017.
 */

public interface NumberService {

    void getAllNumberOnStory(Integer id, BarChart barChart);
    Integer update(Integer value, Integer storyId);
    Integer create(Integer value, Integer storyId);

}
