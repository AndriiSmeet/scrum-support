package com.example.smeet.scrum_support.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.smeet.scrum_support.R;
import com.example.smeet.scrum_support.model.Story;

import java.util.List;

/**
 * Created by Andrii on 10.12.2017.
 */

public class AdapterStoriesBySession extends BaseAdapter{

    Context ctx;
    List<Story> stories;
    LayoutInflater inflater;
    ListView lv;
    Integer idListView;

    public AdapterStoriesBySession(Context ctx, List<Story> stories, Integer id) {
        this.ctx = ctx;
        this.stories = stories;
        this.idListView = id;
        inflater = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return stories.size();
    }

    @Override
    public Object getItem(int i) {
        return stories.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View v = view;
        if (v == null){
            v = inflater.inflate(R.layout.element_story, viewGroup, false);
        }

        Story story = (Story) getItem(i);

        TextView tvSession = v.findViewById(R.id.txtNameStory);
        tvSession.setText(story.getSession().getSessionName());

        TextView tvDate = v.findViewById(R.id.txtDate);
        tvDate.setText(story.getSaveDate().toString());


        TextView tvDescr = v.findViewById(R.id.txtDescription);
        tvDescr.setText(story.getDescribe());

        lv = v.findViewById(idListView);

        return v;
    }

    public ListView getListView(){
        return lv;
    }
}
