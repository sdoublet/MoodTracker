package com.example.doubl.moodtracker.model;

import com.example.doubl.moodtracker.R;


public enum MoodEnum {

    SUPPER_HAPPY (R.drawable.smiley_super_happy, R.color.banana_yellow,5,0),
    HAPPY (R.drawable.smiley_happy, R.color.light_sage,4,1),
    NORMAL (R.drawable.smiley_normal, R.color.cornflower_blue_65,3,2),
    DISAPPOINTED(R.drawable.smiley_disappointed, R.color.warm_grey,2,3),
    SAD (R.drawable.smiley_sad, R.color.faded_red,1,4);

    private final int Width;
    private int DrawableIcone;
    private int ColorBakground;
    private int position;



    MoodEnum( int drawableIcone, int colorBakground, int width, int position) {
        this.Width = width;
        this.DrawableIcone = drawableIcone;
        this.ColorBakground = colorBakground;
        this.position = position;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
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

    // set MoodEnum with current page
    public static int  currentPageMood(int currentpage) {

        if (currentpage==0){
            MoodEnum.values()[0] = MoodEnum.SUPPER_HAPPY;
        }else if (currentpage==1){
            MoodEnum.values()[1] = MoodEnum.HAPPY;
        }else if (currentpage==2){
            MoodEnum.values()[2] = MoodEnum.NORMAL;
        }else if (currentpage==3){
            MoodEnum.values()[3]=MoodEnum.DISAPPOINTED;
        }else if (currentpage==4) {
            MoodEnum.values()[4] = MoodEnum.SAD;
        }
        return currentpage;
    }

}
