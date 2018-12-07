package com.example.doubl.moodtracker.controller;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.media.MediaPlayer;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SnapHelper;
import android.util.Log;
import android.view.View;

import com.example.doubl.moodtracker.R;
import com.example.doubl.moodtracker.model.MoodEnum;
import com.example.doubl.moodtracker.view.DialogFragmentSms;
import com.example.doubl.moodtracker.view.DialogueFragment;
import com.example.doubl.moodtracker.view.RecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;

import static android.support.v7.widget.RecyclerView.SCROLL_STATE_DRAGGING;
import static android.support.v7.widget.RecyclerView.SCROLL_STATE_IDLE;

public class MainActivity extends AppCompatActivity implements RecyclerViewAdapter.OnMoodClickedCallBack {

    private MediaPlayer mediaPlayer1;


    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mediaPlayer1 = MediaPlayer.create(this, R.raw.musique);
        final RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // ArrayList Smiley and Background
        List<MoodEnum> happyList = new ArrayList<>();
        happyList.add(MoodEnum.SUPPER_HAPPY);
        happyList.add(MoodEnum.HAPPY);
        happyList.add(MoodEnum.NORMAL);
        happyList.add(MoodEnum.DISAPPOINTED);
        happyList.add(MoodEnum.SAD);

        final RecyclerViewAdapter recyclerViewAdapter = new RecyclerViewAdapter(happyList, this);
        recyclerView.setAdapter(recyclerViewAdapter);

        // to scroll to happy mood position
        recyclerView.scrollToPosition(1);

        //to fix scroll item
        SnapHelper snapHelper = new LinearSnapHelper();
        snapHelper.attachToRecyclerView(recyclerView);

        // Listener for RecyclerView
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                Log.d("rv", "onScrollStateChanged() called with: , newState = [" + newState + "]");
                if (newState == SCROLL_STATE_DRAGGING) {
                    Log.e("state", "you have started scrolling now");
                } else if (newState == SCROLL_STATE_IDLE) {
                    Log.e("state", "you have stopped scrolling");

                }
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                Log.d("onScrolled", "onScrolled() called with: , dx = [" + dx + "], dy = [" + dy + "]");

                DataBaseManager dataBaseManager = new DataBaseManager(recyclerView.getContext());
                int offset = recyclerView.computeVerticalScrollOffset();
                int currentPage;

                //To have the height of RecyclerView only if items are equals
                currentPage = offset / recyclerView.getHeight();

                //dataBaseManager.insertNewMood();

                dataBaseManager.insertMood("", MoodEnum.values()[currentPage]);
                dataBaseManager.updateNewMood("", MoodEnum.values()[currentPage]);


                String str = Integer.toString(currentPage);
                Log.i("moodEnum", str);
            }
        });
    }

    public void sms(View view) {
        DialogFragmentSms dialogFragment = new DialogFragmentSms();
        dialogFragment.show(getSupportFragmentManager(), "sms");
    }

    public void comment(View view) {
        DialogueFragment dialogFragment = new DialogueFragment();
        dialogFragment.show(getSupportFragmentManager(), "dialog");
    }

    public void history(View view) {
        Intent intent = new Intent(this, HistoryActivity.class);
        startActivity(intent);

    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        mediaPlayer1.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mediaPlayer1.pause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        DataBaseManager dataBaseManager = new DataBaseManager(this);
        dataBaseManager.deleteOldMood();
    }

    @Override
    public void onMoodClicked(MoodEnum position) {
    }
}
