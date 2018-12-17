package com.example.doubl.moodtracker.model;

import android.util.Log;

import org.joda.time.DateTime;


public class Mood {

    private String comment;
    private MoodEnum moodEnum;
    private int date;

    public Mood(String comment, MoodEnum moodEnum, int date) {
        this.comment = comment;
        this.moodEnum = moodEnum;
        this.date = date;
    }

    public Mood() {
    }

    public String getComment() {
        return comment;
    }





    public MoodEnum getMoodEnum() {
        return moodEnum;
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
       int year = dateTime1.getYear() *1000;
       int date = days + year;
       String str = Integer.toString(date);
       Log.i("date", str);

        return date;
    }


    public String textViewSetText(){
        int i=date;
        int dayAgo = getDayOfYear() - i;
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
