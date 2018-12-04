package com.example.doubl.moodtracker.controller;

import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SnapHelper;
import android.view.View;

import com.example.doubl.moodtracker.R;
import com.example.doubl.moodtracker.model.MoodEnum;
import com.example.doubl.moodtracker.view.RecyclerviewAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements RecyclerviewAdapter.OnMoodClickedCallBack {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        final RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // ArrayList Smiley and Background
        List<MoodEnum> happyList = new ArrayList<>();
        happyList.add(MoodEnum.SUPPER_HAPPY);
        happyList.add(MoodEnum.HAPPY);
        happyList.add(MoodEnum.NORMAL);
        happyList.add(MoodEnum.DISAPPOINTED);
        happyList.add(MoodEnum.SAD);

        final RecyclerviewAdapter recyclerViewAdapter = new RecyclerviewAdapter(happyList, this);
        recyclerView.setAdapter(recyclerViewAdapter);

        //computeRecyclerviewHeight(recyclerView);
        // to scroll to happy mood position
        recyclerView.scrollToPosition(1);


        //to fix scroll item
        SnapHelper snapHelper = new LinearSnapHelper();
        snapHelper.attachToRecyclerView(recyclerView);


    }


    public void comment(View view) {
        DialogFragment dialogFragment = new DialogFragment();
        dialogFragment.show(getSupportFragmentManager(), "dialog");
    }
    @Override
    public void onMoodClicked(MoodEnum position) {

    }
}
