package com.example.doubl.moodtracker.controller;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.doubl.moodtracker.model.Mood;
import com.example.doubl.moodtracker.model.MoodEnum;

import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.List;

public class DataBaseManager extends SQLiteOpenHelper {

    private static final String DB_NAME = "mood.db";
    private static final int DB_VERSION = 1;
    private static final String DATA_TABLE = "MOOD";


    public DataBaseManager(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sqlFilTable = " CREATE TABLE " + DATA_TABLE + "( id INTEGER PRIMARY KEY," +
                " mood TEXT, dayOfYear NUMERIC, comment TEXT);";
        db.execSQL(sqlFilTable);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


    // insert mood in table
    public void insertMood(String comment, MoodEnum moodEnum) {
        Mood mood = new Mood();
        int date = mood.getDayOfYear();
        ContentValues values = new ContentValues();
        values.put("comment", comment);
        values.put("mood", moodEnum.name());
        values.put("dayOfYear", date);

        this.getWritableDatabase().insert(DATA_TABLE, null, values);


    }

    // update the table with the comment DialogFragment
    void updateComment(String comment_) {

        ContentValues values = new ContentValues();
        values.put("comment", comment_);
        this.getWritableDatabase().update(DATA_TABLE, values, "dayOfYear=" + DateTime.now().getDayOfYear(), null);


    }

    // update the last entry if the date is the same of today and change value's date
    public void insertNewMood() {
        Mood mood = new Mood();
        int date = 0;
        ContentValues values = new ContentValues();
        values.put("dayOfYear", date);
        this.getWritableDatabase().update(DATA_TABLE, values, "dayOfYear=" + mood.getDayOfYear(), null);
    }

    // delete on the table if date equal 0 to have only one value per date
    public void deleteOldMood() {
        int date = 0;
        String sql = " DELETE FROM " + DATA_TABLE + " WHERE dayOfYear=" + date;
        this.getWritableDatabase().execSQL(sql);
    }

    // public void deleteOldMood_() {
    //     int date = DateTime.now().getDayOfYear() - 180;
    //     String sql = " DELETE FROM " + DATA_TABLE + " WHERE dayOfYear=" + date;
    //     this.getWritableDatabase().execSQL(sql);
    // }


    // read the table in history
    public List<Mood> readForWeek() {
        List<Mood> moods = new ArrayList<>();

        String selectMood = " SELECT * FROM " + DATA_TABLE + " ORDER BY dayOfYear  ";

        Cursor cursor = getReadableDatabase().rawQuery(selectMood, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Mood mood = new Mood(cursor.getString(cursor.getColumnIndex("comment")), cursor.getString(cursor.getColumnIndex("mood")),
                    cursor.getInt(cursor.getColumnIndex("dayOfYear")));
            moods.add(mood);
            cursor.moveToNext();
        }
        cursor.close();
        return moods;
    }

}
