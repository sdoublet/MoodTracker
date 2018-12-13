package com.example.doubl.moodtracker.controller;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.doubl.moodtracker.R;
import com.example.doubl.moodtracker.model.Mood;
import com.example.doubl.moodtracker.view.HistoryListAdapter;

import java.util.ArrayList;
import java.util.List;

public class HistoryActivity extends AppCompatActivity {

    private ListView listView;
    private ArrayList<Mood> moodArrayList = new ArrayList<>();
    private DataBaseManager dataManager;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        listView= findViewById(R.id.list_item);

    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        dataManager = new DataBaseManager(this);
        history();

        listView.setAdapter(new HistoryListAdapter(this, R.layout.row_history_listview, moodArrayList));
        dataManager.close();
    }

    public void history(){
        List<Mood> moodList = dataManager.readForWeek();
        moodArrayList.addAll(moodList);

    }
}
