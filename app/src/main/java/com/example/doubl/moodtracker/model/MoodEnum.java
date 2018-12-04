package com.example.doubl.moodtracker.model;

import com.example.doubl.moodtracker.R;

public enum MoodEnum {

    SUPPER_HAPPY (R.drawable.smiley_super_happy, R.color.banana_yellow,5),
    HAPPY (R.drawable.smiley_happy, R.color.light_sage,4),
    NORMAL (R.drawable.smiley_normal, R.color.cornflower_blue_65,3),
    DISAPPOINTED(R.drawable.smiley_disappointed, R.color.warm_grey,2),
    SAD (R.drawable.smiley_sad, R.color.faded_red,1);

    private final int Width;
    private int DrawableIcone;
    private int ColorBakground;



    MoodEnum( int drawableIcone, int colorBakground, int width) {
        this.Width = width;
        this.DrawableIcone = drawableIcone;
        this.ColorBakground = colorBakground;
    }

    public int getWidth() {
        return Width;
    }

    public int getDrawableIcone() {
        return DrawableIcone;
    }

    public int getColorBakground() {
        return ColorBakground;
    }
}
