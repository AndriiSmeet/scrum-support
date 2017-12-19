package com.example.smeet.scrum_support.async.story;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.view.Window;
import android.widget.TextView;
import android.widget.Toast;

import com.example.smeet.scrum_support.R;
import com.example.smeet.scrum_support.activity.MainActivity;
import com.example.smeet.scrum_support.dao.NumberDao;
import com.example.smeet.scrum_support.dao.StoryDao;
import com.example.smeet.scrum_support.dao.impl.NumberDaoImpl;
import com.example.smeet.scrum_support.dao.impl.StoryDaoImpl;
import com.example.smeet.scrum_support.fragment.MasterMainFragment;
import com.example.smeet.scrum_support.fragment.MasterStoriesFragment;

/**
 * Created by Smeet on 19.12.2017.
 */

public class AsyncDeleteStory extends AsyncTask<Integer, Integer, Void> {

    private Context context;
    private StoryDao storyDao;
    private NumberDao numberDao;
    private Integer id;
    private Integer deletedId;
    private ProgressDialog progressDialog;


    public AsyncDeleteStory(Context context, Integer id) {
        this.context = context;
        this.id = id;
        storyDao = new StoryDaoImpl();
        numberDao = new NumberDaoImpl();
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
        deletedId = storyDao.delete(id);
        if(deletedId != null) {
            numberDao.delete(id);


        }
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        if (deletedId == null) {
            Toast.makeText(context, "Does not create story", Toast.LENGTH_SHORT).show();
        } else {
            new AsyncGetAllStoryByIdSession(context, MasterMainFragment.sessionId, MasterStoriesFragment.listView).execute();
            Dialog dialog = new Dialog(context);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setContentView(R.layout.dialog_ok);
            ((TextView)dialog.findViewById(R.id.txtDialogMessage)).setText("Story successfully deleted");
            dialog.show();

        }
        progressDialog.dismiss();
        super.onPostExecute(aVoid);
    }

}


