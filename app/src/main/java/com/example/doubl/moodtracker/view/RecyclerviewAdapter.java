package com.example.doubl.moodtracker.view;

import android.media.MediaPlayer;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;

import com.example.doubl.moodtracker.R;
import com.example.doubl.moodtracker.model.MoodEnum;

import java.util.List;


public class RecyclerviewAdapter extends RecyclerView.Adapter<RecyclerviewAdapter.HappyViewHolder> {

    private List<MoodEnum> happyList;
    private OnMoodClickedCallBack callBack;


    public interface OnMoodClickedCallBack {
        void onMoodClicked(MoodEnum position);
    }

    public  class HappyViewHolder extends RecyclerView.ViewHolder {

    private ImageView ivSmiley;
    public View background;


    private HappyViewHolder(final View itemView) {
        super(itemView);

        ivSmiley = itemView.findViewById(R.id.smiley);
        background = itemView.findViewById(R.id.background);
        ivSmiley.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MediaPlayer mediaPlayer = MediaPlayer.create(v.getContext(), R.raw.sound3);
                mediaPlayer.start();
                RotateAnimation rotateAnimation = new RotateAnimation(0, 3600, RotateAnimation.RELATIVE_TO_SELF, 0.5f, RotateAnimation.RELATIVE_TO_SELF, 0.5f);
                rotateAnimation.setDuration(3700);
                v.startAnimation(rotateAnimation);

                onMoodSmileyClicked(getAdapterPosition());
            }
        });
    }


}public RecyclerviewAdapter(List<MoodEnum> happyList, OnMoodClickedCallBack callBack) {
    this.happyList = happyList;
    this.callBack = callBack;

}



    private void onMoodSmileyClicked(int adapterPosition) {
        callBack.onMoodClicked(happyList.get(adapterPosition));
    }


    @NonNull
    @Override
    public HappyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int position) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_recyclerview, parent, false);
        HappyViewHolder happyViewHolder;
        happyViewHolder = new HappyViewHolder(view);

        return happyViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull HappyViewHolder happyViewHolder, int position) {
        MoodEnum happy = happyList.get(position);
        happyViewHolder.ivSmiley.setImageResource(happy.getDrawableIcone());
        happyViewHolder.background.setBackgroundColor(happyViewHolder.itemView.getContext().getResources().getColor(happy.getColorBakground()));

    }

    @Override
    public int getItemCount() {
        return happyList.size();
    }


}
