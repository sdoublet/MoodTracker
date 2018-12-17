package com.example.doubl.moodtracker.controller;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.doubl.moodtracker.model.Mood;
import com.example.doubl.moodtracker.model.MoodEnum;


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
        String sqlFilTable = " CREATE TABLE " + DATA_TABLE + "( dayOfYear INTEGER PRIMARY KEY ," +
                " mood TEXT,  comment TEXT);";
        db.execSQL(sqlFilTable);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    // insert mood in table

    void insertMood( MoodEnum moodEnum) {
        Mood mood = new Mood();
        int date = mood.getDayOfYear();

        ContentValues values = new ContentValues();
        values.put("mood", moodEnum.name());
        values.put("dayOfYear", date);

        this.getWritableDatabase().insert(DATA_TABLE, null, values);
    }
    void insertNow (){
        ContentValues values = new ContentValues();
        values.put("mood", MoodEnum.SAD.name());
        values.put("dayOfYear", "2018342");
        values.put("comment", "");
        this.getWritableDatabase().insert(DATA_TABLE, null, values);
    }

    // update new mood for the same date

    void updateNewMood( MoodEnum moodEnum) {
        Mood mood = new Mood();
        int date = mood.getDayOfYear();
        ContentValues values = new ContentValues();
        values.put("mood", moodEnum.name());
        values.put("dayOfYear", date);

        this.getWritableDatabase().update(DATA_TABLE, values, "dayOfYear=" + date, null);


    }

    // update the table with the comment DialogFragment
    public void updateComment(String comment) {
        Mood mood = new Mood();
        ContentValues values = new ContentValues();
        values.put("comment", comment);
        this.getWritableDatabase().update(DATA_TABLE, values, "dayOfYear=" + mood.getDayOfYear(), null);


    }


    // delete on the table if date equal 0 to have only one value per date
    void deleteOldMood() {

        String sql = " DELETE FROM " + DATA_TABLE + " WHERE dayOfYear NOT IN ( SELECT dayOfYear FROM MOOD ORDER BY dayOfYear DESC LIMIT 8)";
        this.getWritableDatabase().execSQL(sql);
    }


    // read the table in history
    List<Mood> readForWeek() {
        List<Mood> moods = new ArrayList<>();

        String selectMood = " SELECT * FROM " + DATA_TABLE + " ORDER BY dayOfYear  LIMIT 8";

        Cursor cursor = getReadableDatabase().rawQuery(selectMood, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            MoodEnum moodEnum = MoodEnum.valueOf(cursor.getString(cursor.getColumnIndex("mood")));
            Mood mood = new Mood(cursor.getString(cursor.getColumnIndex("comment")), moodEnum,
                    cursor.getInt(cursor.getColumnIndex("dayOfYear")));
            moods.add(mood);
            cursor.moveToNext();
        }
        cursor.close();
        return moods;
    }

    public Mood getLastMood() {
        Mood mood = new Mood();
        Cursor cursor = getReadableDatabase().query(DATA_TABLE, null, "dayOfYear = " + mood.getDayOfYear(), null, null, null, null);
        if (cursor.moveToFirst()) {
            MoodEnum moodEnum = MoodEnum.valueOf(cursor.getString(cursor.getColumnIndex("mood")));
            mood = new Mood(cursor.getString(cursor.getColumnIndex("comment")), moodEnum,
                    cursor.getInt(cursor.getColumnIndex("dayOfYear")));
        }
        cursor.close();
        return mood;
    }

}
