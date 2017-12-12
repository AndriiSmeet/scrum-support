package com.example.smeet.scrum_support.fragment;


import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.smeet.scrum_support.R;
import com.example.smeet.scrum_support.activity.MainActivity;
import com.example.smeet.scrum_support.dao.SessionDao;
import com.example.smeet.scrum_support.dao.StoryDao;
import com.example.smeet.scrum_support.dao.impl.SessionDaoImpl;
import com.example.smeet.scrum_support.dao.impl.StoryDaoImpl;
import com.example.smeet.scrum_support.model.Story;
import com.example.smeet.scrum_support.service.NumberService;
import com.example.smeet.scrum_support.service.SessionService;
import com.example.smeet.scrum_support.service.StoryService;
import com.example.smeet.scrum_support.service.impl.NumberServiceImpl;
import com.example.smeet.scrum_support.service.impl.SessionServiceImpl;
import com.example.smeet.scrum_support.service.impl.StoryServiceImpl;
import com.github.mikephil.charting.charts.BarChart;

import java.util.Date;


public class MasterMainFragment extends Fragment {

    private SessionService sessionService = new SessionServiceImpl(getContext());
    private StoryService storyService;
    private NumberService numberService;
    private Dialog dialog;

    private BarChart barChart;
    public static Integer sessionId;
    public static Integer storyId;
    private Button btnCreateStory;
    private Button btnSaveStory;
    private Button btnDialogCreateStory;
    private Button btnSelectStory;
    private Button btnRefresh;


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


        if(storyId!= null) {
            numberService.getAllNumberOnStory(storyId, barChart);
        }
    }


    private void initResource() {
        btnCreateStory = getActivity().findViewById(R.id.btnCreateStory);
        btnSaveStory = getActivity().findViewById(R.id.btnSaveStory);
        barChart = getActivity().findViewById(R.id.barchart);
        btnSelectStory = getActivity().findViewById(R.id.btnSelectStory);
        btnRefresh = getActivity().findViewById(R.id.btnRefresh);

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

                final Dialog dialog = new Dialog(getContext());
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.dialog_create_story);
                dialog.show();

                btnDialogCreateStory = dialog.findViewById(R.id.btnDialogCreate);

                final SessionService sessionService = new SessionServiceImpl(getContext());
                final StoryService storyService = new StoryServiceImpl(getContext());

                btnDialogCreateStory.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        String dialogTitle = ((EditText)dialog.findViewById(R.id.editDialogTitle)).getText().toString();
                        String dialogDesc =((EditText)dialog.findViewById(R.id.editDialogDescription)).getText().toString();


                        storyService.create(new Story(dialogTitle, sessionService.getById(sessionId), dialogDesc, new Date(), true));
                        dialog.dismiss();
                    }

                });

            }
        });

        btnSelectStory.setOnClickListener(new View.OnClickListener() {
            final StoryService storyService = new StoryServiceImpl(getContext());
            @Override
            public void onClick(View view) {
                storyService.getAllStoryByIsActive(true, sessionId);
            }
        });



        btnSaveStory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("in fragment " + storyId);
            }
        });

        btnRefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("refresh on " + storyId);
                if(storyId!= null) {
                    numberService.getAllNumberOnStory(storyId, barChart);
                }
            }
        });
    }




}
