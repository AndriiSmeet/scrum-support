package com.example.smeet.scrum_support.service.impl;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.example.smeet.scrum_support.async.number.AsyncNumberGetAllOnStory;
import com.example.smeet.scrum_support.async.story.AsyncDeleteStory;
import com.example.smeet.scrum_support.model.Number;
import android.widget.Toast;

import com.example.smeet.scrum_support.R;
import com.example.smeet.scrum_support.activity.UserActivity;
import com.example.smeet.scrum_support.adapter.AdapterStoriesBySession;
import com.example.smeet.scrum_support.adapter.UserActiveStories;
import com.example.smeet.scrum_support.async.story.AsyncCreateStory;
import com.example.smeet.scrum_support.async.story.AsyncGetAllStoryByIdSession;
import com.example.smeet.scrum_support.async.story.AsyncGetAllStoryByIsActive;
import com.example.smeet.scrum_support.async.story.AsyncGetByIdStory;
import com.example.smeet.scrum_support.fragment.MasterMainFragment;
import com.example.smeet.scrum_support.model.Story;
import com.example.smeet.scrum_support.service.StoryService;
import com.github.mikephil.charting.charts.PieChart;

import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Smeet on 08.12.2017.
 */

public class StoryServiceImpl implements StoryService {

    private Context context;

    public StoryServiceImpl(Context context) {
        this.context = context;
    }

    @Override
    public Story getById(Integer id) {
        AsyncGetByIdStory asyncTask = new AsyncGetByIdStory(id, context);
        asyncTask.execute();
        return null;
    }

    @Override
    public Integer create(Story story) {
        AsyncCreateStory asyncTask = new AsyncCreateStory(story, context);
        asyncTask.execute();
        return null;
    }

    @Override
    public List<Story> getAll() {
        return null;
    }

    @Override
    public List<Story> getAllStoryByIdSession(Integer idSession, ListView listView) {
        AsyncGetAllStoryByIdSession asyncTask = new AsyncGetAllStoryByIdSession(context, idSession, listView);
        asyncTask.execute();
        return null;
    }

    @Override
    public List<Story> getAllStoryByIsActive(boolean arg, Integer sessionId, UserActivity userActivity) {
        AsyncGetAllStoryByIsActive asyncTask = new AsyncGetAllStoryByIsActive(context, arg, sessionId, userActivity);
        asyncTask.execute();
        return null;
    }

    @Override
    public List<Story> getAllStoryByIsActive(boolean arg, Integer sessionId, MasterMainFragment masterMainFragment) {
        AsyncGetAllStoryByIsActive asyncTask = new AsyncGetAllStoryByIsActive(context, arg, sessionId, masterMainFragment);
        asyncTask.execute();
        return null;
    }

    @Override
    public void showStoriesBySession(final List<Story> stories){

        System.out.println("load story be session");
        AdapterStoriesBySession adapter = new AdapterStoriesBySession(context, new ArrayList<Story>(stories), R.id.listStory);
        ListView lv = adapter.getListView();

        lv.setAdapter(adapter);

    }

    public void setStoryList(final ArrayList<Story> stories, ListView listView) {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Story story = stories.get(i);
                System.out.println("get item " + story.getId());

                new AsyncNumberGetAllOnStory(context, story.getId()).execute();
                //TODO
            }
        });
    }

    public void showDialogWithStatsByStory(List<Number> numbers, final Integer storyId) {

        final Dialog dialogWrap = new Dialog(context);
        dialogWrap.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialogWrap.setContentView(R.layout.dialog_statistic);

        dialogWrap.findViewById(R.id.deleteStatistics).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Dialog dialog = new Dialog(context);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.dialog_attention);
                dialog.show();

               final Button btnYes;
               final Button btnNo;

               btnYes = dialog.findViewById(R.id.btnOk);
               btnNo = dialog.findViewById(R.id.btnNo);

               btnYes.setOnClickListener(new View.OnClickListener() {
                   @Override
                   public void onClick(View v) {
                       new AsyncDeleteStory(context, storyId).execute();
                       dialogWrap.dismiss();
                       dialog.dismiss();
                   }
               });

               btnNo.setOnClickListener(new View.OnClickListener() {
                   @Override
                   public void onClick(View v) {
                       dialog.dismiss();
                   }
               });
            }
        });


        List<Integer> numbersInt = new ArrayList<>();
        Collections.sort(numbersInt);
        HashMap<Integer, Integer> numberAndCount = new HashMap<>();
        ArrayList<PieEntry> entries = new ArrayList<>();

        PieChart pieChart = dialogWrap.findViewById(R.id.pieChart);
        pieChart.animateXY(3000, 3000);
        pieChart.setUsePercentValues(true);
        pieChart.setHoleRadius(30);
        pieChart.setTransparentCircleRadius(30);
        pieChart.setHoleColor(Color.TRANSPARENT);


        for (Number number : numbers) {
            numbersInt.add(number.getValue());
        }


        for(Integer integer : numbersInt) {
            int counter = Collections.frequency(numbersInt, integer);
            numberAndCount.put(integer, counter);

        }

        int sum = 0;
        int count = 0;
        for(Map.Entry pair : numberAndCount.entrySet()) {
            sum += (Integer) pair.getKey();
            count += (Integer) pair.getValue();
            entries.add(new PieEntry((Integer) pair.getValue(), " " + (Integer) pair.getKey()));
        }

        Double average = (double) sum/count;
        String resultAverage = String.format("%.2f", average);
        pieChart.setCenterText(resultAverage);
        pieChart.setCenterTextSize(22);

        PieDataSet dataSet = new PieDataSet(entries, "calls");
        dataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        dataSet.setHighlightEnabled(true);
        PieData pieData = new PieData(dataSet);
        pieData.setDrawValues(false);
        pieChart.setData(pieData);
        dialogWrap.show();
    }

    @Override
    public void showDialogWithActiveStories(final List<Story> stories, final UserActivity userActivity) {

        Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_stories_list);

        ListView lv = dialog.findViewById(R.id.lv);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Story story = stories.get(i);
                System.out.println(story.getId());
                MasterMainFragment.storyId = story.getId();
                UserActivity.storyId = story.getId();
                Toast.makeText(context, "Story with id: " + story.getId() + " selected", Toast.LENGTH_SHORT).show();
                userActivity.setStoryTitle(story.getTitle());

            }
        });

        UserActiveStories adapter = new UserActiveStories(context, new ArrayList<Story>(stories));
        lv.setAdapter(adapter);

        dialog.show();
    }

    @Override
    public void showDialogWithActiveStories(final List<Story> stories, final MasterMainFragment masterMainFragment) {

        Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_stories_list);

        ListView lv = dialog.findViewById(R.id.lv);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Story story = stories.get(i);
                System.out.println(story.getId());
                MasterMainFragment.storyId = story.getId();
                UserActivity.storyId = story.getId();
                Toast.makeText(context, "Story with id: " + story.getId() + " selected", Toast.LENGTH_SHORT).show();
                masterMainFragment.setTextStoryTitle(story.getTitle());
            }
        });

        UserActiveStories adapter = new UserActiveStories(context, new ArrayList<Story>(stories));
        lv.setAdapter(adapter);

        dialog.show();
    }
}
