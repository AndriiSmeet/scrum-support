package com.example.smeet.scrum_support.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.example.smeet.scrum_support.R;
import com.example.smeet.scrum_support.dao.SessionDao;
import com.example.smeet.scrum_support.dao.StoryDao;
import com.example.smeet.scrum_support.dao.impl.SessionDaoImpl;
import com.example.smeet.scrum_support.dao.impl.StoryDaoImpl;
import com.example.smeet.scrum_support.model.Session;
import com.example.smeet.scrum_support.model.Story;

import java.util.List;


public class MasterMainFragment extends Fragment {

    private SessionDao sessionDao = new SessionDaoImpl();
    private StoryDao storyDao = new StoryDaoImpl();

    private Integer sessionId;
    private Button btnCreateStory;
    private Button btnSaveStory;
    private Spinner storiesSpinner;


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

//        List<Session> sessions = sessionDao.getAll();
//        ArrayAdapter<Session> adapter = new ArrayAdapter<Session>(getActivity().getApplicationContext(), R.layout.spinner_item, sessions);
//
//        storiesSpinner.setAdapter(adapter);

    }



    private void initResource() {
        btnCreateStory = getActivity().findViewById(R.id.btnReady);
        btnSaveStory = getActivity().findViewById(R.id.btnEnd);

        sessionId = getActivity().getIntent().getExtras().getInt("sessionId");

    }

    private void doButtonLogic() {
        btnCreateStory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                storyDao.create(new Story("MyStory", sessionDao.getById(sessionId), "describe", true));
            }
        });

        btnSaveStory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

}
