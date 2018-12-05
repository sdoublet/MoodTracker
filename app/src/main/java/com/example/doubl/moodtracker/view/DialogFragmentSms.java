package com.example.doubl.moodtracker.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.telephony.SmsManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.doubl.moodtracker.R;
import com.example.doubl.moodtracker.controller.DialogFragment;
import com.example.doubl.moodtracker.controller.MainActivity;
import com.example.doubl.moodtracker.model.Mood;
import com.example.doubl.moodtracker.model.MoodEnum;

public class DialogFragmentSms extends DialogFragment {



    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.row_sms, container, false);

        final EditText phoneNumber = view.findViewById(R.id.phone_);
        Button sendSms = view.findViewById(R.id.send_sms);

        sendSms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String phone = phoneNumber.getText().toString();
                SmsManager.getDefault().sendTextMessage(phone, null, "You know what ? I am"  + " " +  MoodEnum.values()[0].toString(), null,null);
                dismiss();
            }
        });



        return view;
    }

}
