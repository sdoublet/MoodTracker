package com.example.doubl.moodtracker.model;

import android.support.annotation.ColorRes;

import com.example.doubl.moodtracker.R;


public enum MoodEnum {

    SUPPER_HAPPY (R.drawable.smiley_super_happy, R.color.banana_yellow,5),
    HAPPY (R.drawable.smiley_happy, R.color.light_sage,4),
    NORMAL (R.drawable.smiley_normal, R.color.cornflower_blue_65,3),
    DISAPPOINTED(R.drawable.smiley_disappointed, R.color.warm_grey,2),
    SAD (R.drawable.smiley_sad, R.color.faded_red,1);

    private final int width;
    private int drawableIcon;
    private int colorBackground;




    MoodEnum(int drawableIcon, int colorBackground, int width) {
        this.width = width;
        this.drawableIcon = drawableIcon;
        this.colorBackground = colorBackground;

    }


    public int getWidth() {
        return width;
    }

    public int getDrawableIcon() {
        return drawableIcon;
    }

    @ColorRes
    public int getColorBackground() {
        return colorBackground;
    }



}
