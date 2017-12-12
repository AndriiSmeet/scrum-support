package com.example.smeet.scrum_support.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Spinner;

import com.example.smeet.scrum_support.R;
import com.example.smeet.scrum_support.dao.SessionDao;
import com.example.smeet.scrum_support.dao.StoryDao;
import com.example.smeet.scrum_support.dao.impl.SessionDaoImpl;
import com.example.smeet.scrum_support.dao.impl.StoryDaoImpl;
import com.example.smeet.scrum_support.model.Story;
import com.example.smeet.scrum_support.service.NumberService;
import com.example.smeet.scrum_support.service.impl.NumberServiceImpl;
import com.github.mikephil.charting.charts.BarChart;


public class MasterMainFragment extends Fragment {

    private SessionDao sessionDao = new SessionDaoImpl();
    private StoryDao storyDao = new StoryDaoImpl();
    private NumberService numberService;

    private BarChart barChart;
    private Integer sessionId;
    private Integer storyId;
    private Button btnCreateStory;
    private Button btnSaveStory;


    public MasterMainFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_master, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initResource();
        doButtonLogic();


        numberService.getAllNumberOnStory(1, barChart);
//        List<Session> sessions = sessionDao.getAll();
//        ArrayAdapter<Session> adapter = new ArrayAdapter<Session>(getActivity().getApplicationContext(), R.layout.spinner_item, sessions);
//
//        storiesSpinner.setAdapter(adapter);

    }



    private void initResource() {
        btnCreateStory = getActivity().findViewById(R.id.btnReady);
        btnSaveStory = getActivity().findViewById(R.id.btnEnd);
        barChart = getActivity().findViewById(R.id.barchart);

        barChart.getAxisLeft().setDrawGridLines(false);
        barChart.setDrawGridBackground(false);
        barChart.getXAxis().setEnabled(false);
        barChart.getAxisLeft().setDrawAxisLine(false);
        barChart.getAxisRight().setDrawGridLines(false);
        barChart.setDescription(null);
        barChart.animateY(4000);

        numberService = new NumberServiceImpl(getContext());
        sessionId = getActivity().getIntent().getExtras().getInt("sessionId");

    }

    private void doButtonLogic() {
        btnCreateStory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                storyId = storyDao.create(new Story("MyStory", sessionDao.getById(sessionId), "describe", true));
            }
        });

        btnSaveStory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

}
