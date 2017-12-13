package com.example.smeet.scrum_support.async.story;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.view.Window;
import android.widget.TextView;
import android.widget.Toast;

import com.example.smeet.scrum_support.R;
import com.example.smeet.scrum_support.dao.StoryDao;
import com.example.smeet.scrum_support.dao.impl.StoryDaoImpl;
import com.example.smeet.scrum_support.fragment.MasterMainFragment;
import com.example.smeet.scrum_support.model.Story;

/**
 * Created by Andrii on 09.12.2017.
 */

public class AsyncCreateStory extends AsyncTask<Integer, Integer, Void> {

    private Story story;
    private Context context;
    private StoryDao storyDao;
    private Integer id;
    private ProgressDialog progressDialog;


    public AsyncCreateStory(Story story, Context context) {
        this.story = story;
        this.context = context;
        storyDao = new StoryDaoImpl();
    }

    @Override
    protected void onPreExecute() {
        progressDialog = new ProgressDialog(context);
        progressDialog.setCancelable(false);
        progressDialog.show();
        progressDialog.setContentView(R.layout.progress_bar);
    }

    @Override
    protected Void doInBackground(Integer... integers) {
        id = storyDao.create(story);
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        if (id == null) {
            Toast.makeText(context, "Does not create story", Toast.LENGTH_SHORT).show();
        } else {
            Dialog dialog = new Dialog(context);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setContentView(R.layout.dialog_ok);
            ((TextView)dialog.findViewById(R.id.txtDialogMessage)).setText("Story successfully created");
            dialog.show();
        }
        progressDialog.dismiss();
        super.onPostExecute(aVoid);
    }

}

