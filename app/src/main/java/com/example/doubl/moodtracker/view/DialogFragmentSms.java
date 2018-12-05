package com.example.doubl.moodtracker.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.doubl.moodtracker.R;
import com.example.doubl.moodtracker.controller.DialogFragment;

public class DialogFragmentSms extends DialogFragment {



    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.row_sms, container, false);



        return view;
    }
}
