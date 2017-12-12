package com.example.smeet.scrum_support.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.smeet.scrum_support.R;
import com.example.smeet.scrum_support.async.story.AsyncCreateStory;
import com.example.smeet.scrum_support.service.StoryService;
import com.example.smeet.scrum_support.service.impl.StoryServiceImpl;


public class MasterStoriesFragment extends Fragment  {

    public ListView listView;
    public StoryService storyService;

    public MasterStoriesFragment() {}


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_master_stories, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        listView = getActivity().findViewById(R.id.listStory);
        storyService = new StoryServiceImpl(getContext());
        storyService.getAllStoryByIdSession(1, listView);



        //Фрагмент должен содержать всю листу историй в данной сесии,
        //Нужно вальнуть адаптер и сделать вывод листы на фрагмент, юзай обычный ViewList
        //Копата что то на фронте делал, елемент для листы - element_story
    }
}
