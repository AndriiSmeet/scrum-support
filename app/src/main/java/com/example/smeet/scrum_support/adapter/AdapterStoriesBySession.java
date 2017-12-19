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

    private Context ctx;
    private List<Story> stories;
    private LayoutInflater inflater;
    private ListView lv;
    private Integer idListView;

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

        TextView txtTitle = v.findViewById(R.id.txtTitle);
        txtTitle.setText(story.getTitle());

        TextView tvDate = v.findViewById(R.id.txtDate);
        tvDate.setText( story.getSaveDate()!= null ? story.getSaveDate().toString() : "None");

        TextView tvDescr = v.findViewById(R.id.txtDescription);
        tvDescr.setText(story.getDescribe());

        TextView textStoryStatus = v.findViewById(R.id.textStoryStatus);
        System.out.println("6");
        textStoryStatus.setText(story.getActive() ? "open" : "close");

        lv = v.findViewById(idListView);

        return v;

    }

    public ListView getListView(){
        return lv;
    }
}
