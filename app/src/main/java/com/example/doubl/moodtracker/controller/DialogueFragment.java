package com.example.doubl.moodtracker.controller;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;


import com.example.doubl.moodtracker.R;

public class DialogueFragment extends DialogFragment {

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.dialog_fragment, container, false);

        final EditText editText = view.findViewById(R.id.dialog_input);
        Button saveComment = view.findViewById(R.id.validate_dialog);


        saveComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = editText.getText().toString();
                DataBaseManager dataBaseManager = new DataBaseManager(getContext());
                dataBaseManager.updateComment(text);
                dismiss();
            }
        });

        return view;
    }
}
