package com.example.doubl.moodtracker.model;

import android.util.Log;

import org.joda.time.DateTime;

public class Mood {

    private String comment;
    private String mood_;
    private int date;

    public Mood(String comment, String mood_, int date) {
        this.comment = comment;
        this.mood_ = mood_;
        this.date = date;
    }

    public Mood() {
    }

    public String getComment() {
        return comment;
    }


    public String getMood_() {
        return mood_;
    }


    public int getDate() {
        return date;
    }

    public void setDate(int date) {
        this.date = date;
    }



    public int getDayOfYear() {
        DateTime dateTime1 = new DateTime();
        int days = dateTime1.getDayOfYear();
        String str = Integer.toString(days);
        Log.i("date", str);
        return days;
    }


    public String textviewSetText(){
        int i=date;
        int dayAgo = DateTime.now().getDayOfYear() - i;
        String str = Integer.toString(dayAgo);
        Log.i("day", str);
        if (i==getDayOfYear()){
            return "Today";
        }
        else if(i == getDayOfYear()-1){
            return "Yesterday";

        }else if (i== getDayOfYear()-7){
            return "A week ago";
        }
        else return  str + " days ago";

    }
}
