package com.example.doubl.moodtracker.view;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.doubl.moodtracker.R;
import com.example.doubl.moodtracker.model.Mood;


import java.util.ArrayList;


public class HistoryListAdapter extends ArrayAdapter<Mood> {
    private ArrayList<Mood> moodsList;
    private Context context;


    HistoryListAdapter(Context context, int layoutResourceId, ArrayList<Mood> historyMoodArrayList) {
        super(context, layoutResourceId, historyMoodArrayList);
        this.moodsList = historyMoodArrayList;
        this.context = context;

    }

    @Override
    public int getCount() {
        return super.getCount();
    }


    @Override
    public Mood getItem(int position) {
        return moodsList.get(position);
    }


    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        TextView textViewDay;
        ImageView imageButtonComment;
        DisplayMetrics displayMetrics;
        displayMetrics = context.getResources().getDisplayMetrics();
        int width = displayMetrics.widthPixels / 5;
        final Mood mood = moodsList.get(position);


        if (convertView == null) {
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            convertView = inflater.inflate(R.layout.row_history_listview, parent, false);
        }

        textViewDay = convertView.findViewById(R.id.row_history_input);
        imageButtonComment = convertView.findViewById(R.id.row_history_button);

        if (mood.getComment() != null && !mood.getComment().equals("")) {
            imageButtonComment.setVisibility(View.VISIBLE);
        } else imageButtonComment.setVisibility(View.INVISIBLE);
        imageButtonComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, mood.getComment(), Toast.LENGTH_SHORT).show();
            }
        });

        textViewDay.setText(mood.textviewSetText());
        setBackgroundColor(convertView, width, mood);

        return convertView;
    }

    private void setBackgroundColor(View convertView, int width, Mood mood) {

        if (mood.getMood_().contains("SUPPER_HAPPY")) {
            convertView.setBackgroundColor(convertView.getResources().getColor(R.color.banana_yellow));
        } else if (mood.getMood_().contains("HAPPY")) {
            convertView.getLayoutParams().width = width * 4;
            convertView.setBackgroundColor(convertView.getResources().getColor(R.color.light_sage));
        } else if (mood.getMood_().contains("NORMAL")) {
            convertView.getLayoutParams().width = width * 3;
            convertView.setBackgroundColor(convertView.getResources().getColor(R.color.cornflower_blue_65));
        } else if (mood.getMood_().contains("DISAPPOINTED")) {
            convertView.getLayoutParams().width = width * 2;
            convertView.setBackgroundColor(convertView.getResources().getColor(R.color.warm_grey));
        } else if (mood.getMood_().contains("SAD")) {
            convertView.getLayoutParams().width = width;
            convertView.setBackgroundColor(convertView.getResources().getColor(R.color.faded_red));

        }
    }
}

