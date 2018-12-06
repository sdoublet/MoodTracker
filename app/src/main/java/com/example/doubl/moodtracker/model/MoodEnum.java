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
    private int drawableIcone;
    private int colorBakground;




    MoodEnum(int drawableIcone, int colorBakground, int width) {
        this.width = width;
        this.drawableIcone = drawableIcone;
        this.colorBakground = colorBakground;

    }


    public int getWidth() {
        return width;
    }

    public int getDrawableIcone() {
        return drawableIcone;
    }

    @ColorRes
    public int getColorB'akground() {
        return colorBakground;
    }

    // set MoodEnum with current page
  //  public static void  currentPageMood(int currentpage) {
//
  //      if (currentpage==0){
  //          MoodEnum.values()[0] = MoodEnum.SUPPER_HAPPY;
  //      }else if (currentpage==1){
  //          MoodEnum.values()[1] = MoodEnum.HAPPY;
  //      }else if (currentpage==2){
  //          MoodEnum.values()[2] = MoodEnum.NORMAL;
  //      }else if (currentpage==3){
  //          MoodEnum.values()[3]=MoodEnum.DISAPPOINTED;
  //      }else if (currentpage==4) {
  //          MoodEnum.values()[4] = MoodEnum.SAD;
  //      }


  //  }

}
