package com.example.doubl.moodtracker.controller;


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
import com.example.doubl.moodtracker.view.History;
import com.example.doubl.moodtracker.view.RecyclerviewAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements RecyclerviewAdapter.OnMoodClickedCallBack {

    private MediaPlayer mediaPlayer1;


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

        final RecyclerviewAdapter recyclerViewAdapter = new RecyclerviewAdapter(happyList, this);
        recyclerView.setAdapter(recyclerViewAdapter);

        //computeRecyclerviewHeight(recyclerView);
        // to scroll to happy mood position
        recyclerView.scrollToPosition(1);


        //to fix scroll item
        SnapHelper snapHelper = new LinearSnapHelper();
        snapHelper.attachToRecyclerView(recyclerView);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                Log.d("rv", "onScrollStateChanged() called with: , newState = [" + newState + "]");
                if (newState==1){
                    Log.e("state", "you have started scrolling now");
                }else if (newState == 0){
                    Log.e("state", "you have stopped scrolling");
                }
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                Log.d("onscrolled", "onScrolled() called with: , dx = [" + dx + "], dy = [" + dy + "]");

                DataBaseManager dataBaseManager = new DataBaseManager(recyclerView.getContext());
                int offset =recyclerView.computeVerticalScrollOffset();
                int currentpage;

                //To have the height of Recyclerview only if items are equals
                currentpage = offset/recyclerView.getHeight();

                if (currentpage==0){
                    // MoodEnum.values()[0] = MoodEnum.SUPPER_HAPPY;
                    dataBaseManager.insertNewMood();// change it
                    dataBaseManager.insertMood("", MoodEnum.SUPPER_HAPPY);
                }else if (currentpage==1){
                    // MoodEnum.values()[1] = MoodEnum.HAPPY;
                    dataBaseManager.insertNewMood();
                    dataBaseManager.insertMood("", MoodEnum.HAPPY);
                }else if (currentpage==2){
                    //MoodEnum.values()[2] = MoodEnum.NORMAL;
                    dataBaseManager.insertNewMood();
                    dataBaseManager.insertMood("", MoodEnum.NORMAL);
                }else if (currentpage==3){
                    // MoodEnum.values()[3]=MoodEnum.DISAPPOINTED;
                    dataBaseManager.insertNewMood();
                    dataBaseManager.insertMood("", MoodEnum.DISAPPOINTED);
                }else if (currentpage==4){
                    //MoodEnum.values()[4]=MoodEnum.SAD;
                    dataBaseManager.insertNewMood();
                    dataBaseManager.insertMood("", MoodEnum.SAD);}


                String str = Integer.toString( currentpage);
                Log.i("moodenum",str );
            }
        });

    }


    public void comment(View view) {
        DialogFragment dialogFragment = new DialogFragment();
        dialogFragment.show(getSupportFragmentManager(), "dialog");
    }

    public void history(View view) {
        Intent intent = new Intent(this, History.class);
        startActivity(intent);
        DataBaseManager dataBaseManager= new DataBaseManager(this);
        dataBaseManager.deleteOldMood();
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

    }
    @Override
    public void onMoodClicked(MoodEnum position) {

    }
}
