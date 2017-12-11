package com.example.smeet.scrum_support.service.impl;

import android.content.Context;

import com.example.smeet.scrum_support.async.number.AsyncNumberGetAll;
import com.example.smeet.scrum_support.dao.NumberDao;
import com.example.smeet.scrum_support.dao.impl.NumberDaoImpl;
import com.example.smeet.scrum_support.model.Number;
import com.example.smeet.scrum_support.service.NumberService;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.formatter.IValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.utils.ViewPortHandler;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Smeet on 08.12.2017.
 */

public class NumberServiceImpl implements NumberService {

    private Context context;
    private final NumberDao numberDao;
    private Integer storyId;

    public NumberServiceImpl(Context context) {
        this.context = context;
        numberDao = new NumberDaoImpl();
    }

    @Override
    public void getAllNumberOnStory(Integer id, BarChart barChart) {
        new AsyncNumberGetAll(context, id, barChart).execute();
    }

    @Override
    public Integer update(Number number) {
        return numberDao.update(number);
    }

    public void showStats(List<Number> numbers, BarChart barChart) {

        List<Integer> numbersInt = new ArrayList<>();
        Collections.sort(numbersInt);
        HashMap<Integer, Integer> numberAndCount = new HashMap<>();
        ArrayList<BarEntry> entries = new ArrayList<>();

        for (Number number : numbers) {
            numbersInt.add(number.getValue());
        }


        for(Integer integer : numbersInt) {
            int counter = Collections.frequency(numbersInt, integer);
            numberAndCount.put(integer, counter);

        }

        int count = 0;
        for(Map.Entry pair : numberAndCount.entrySet()) {
            count++;
            entries.add(new BarEntry(count, (Integer) pair.getValue(), "Number " + pair.getKey()));
        }

        BarDataSet dataSet = new BarDataSet(entries, "Calls");
        dataSet.setValueFormatter(new IValueFormatter() {
            @Override
            public String getFormattedValue(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) {
                return entry.getData().toString();
            }
        });
        dataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        BarData data = new BarData(dataSet);

        barChart.animateY(3000);
        barChart.setData(data);

    }
}
