package com.example.smeet.scrum_support.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.smeet.scrum_support.R;
import com.example.smeet.scrum_support.model.Story;

import java.util.ArrayList;

/**
 * Created by Smeet on 31.10.2017.
 */

public class UserActiveStories extends BaseAdapter {

    private Context context;
    private LayoutInflater lInflater;
    private ArrayList<Story> activeStories;

    public UserActiveStories(Context context, ArrayList<Story> activeStories) {
        this.context = context;
        this.activeStories = activeStories;
        lInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return activeStories.size();
    }

    @Override
    public Story getItem(int position) {
        return activeStories.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = convertView;
        if (view == null) {
            view = lInflater.inflate(R.layout.element_story, parent, false);
        }

        Story story = getItem(position);
        ((TextView) view.findViewById(R.id.txtTitle)).setText(story.getTitle());
        ((TextView) view.findViewById(R.id.txtDescription)).setText(story.getDescribe());

        return view;
    }

}
