package com.example.symptomate;

import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.support.v4.util.Pair;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import java.util.Objects;

import ndk.utils.Activity_Utils;
import ndk.utils.DisplayHelper;

public class Activity_Launcher_Collection extends AppCompatActivity {

    //TODO : Abstract the class - add button, buttons parameter
    //TODO : Fix Margins of buttons - left, right, top : first & others, bottom : first & others
    //TODO : Stroke for button
    //TODO : Typography for button
    //TODO : Fix Height of button
    //TODO : Clean code
    //TODO : Make SHC Activity using Contexted Activity
    //TODO : Extend SHC Activity

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launcher_collection2);

//        LinearLayout linearLayout = findViewById(R.id.rootContainer);
//
//        // Create Button Dynamically
//        Button btnShow = new Button(this);
//        btnShow.setText("Test");
//        btnShow.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
//        btnShow.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(Activity_Launcher_Collection.this, "Test Clicked", Toast.LENGTH_LONG).show();
//            }
//        });
//
//        // Add Button to LinearLayout
//        if (linearLayout != null) {
//            linearLayout.addView(btnShow);
//        }

        ConstraintLayout constraintLayout = findViewById(R.id.constraintLayout);

//        // Create Button Dynamically
//        Button btnShow = new Button(this);
//        btnShow.setText("Test");
//        btnShow.setId(View.generateViewId());
//
//        ConstraintLayout.LayoutParams clpcontactUs = new ConstraintLayout.LayoutParams(
//                ConstraintLayout.LayoutParams.MATCH_CONSTRAINT, ConstraintLayout.LayoutParams.WRAP_CONTENT);
//        btnShow.setLayoutParams(clpcontactUs);
//
////        btnShow.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
//
//        btnShow.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(Activity_Launcher_Collection.this, "Test Clicked", Toast.LENGTH_LONG).show();
//            }
//        });
//
//        // Add Button to LinearLayout
//        if (constraintLayout != null) {
//            constraintLayout.addView(btnShow);
//        }
//
//        ConstraintSet constraintSet = new ConstraintSet();
//        constraintSet.clone(constraintLayout);
//
//        constraintSet.connect(btnShow.getId(), ConstraintSet.TOP, constraintLayout.getId(), ConstraintSet.TOP, 16);
//        constraintSet.connect(btnShow.getId(), ConstraintSet.LEFT, constraintLayout.getId(), ConstraintSet.LEFT, 16);
//        constraintSet.connect(btnShow.getId(), ConstraintSet.RIGHT, constraintLayout.getId(), ConstraintSet.RIGHT, 16);
//
//        constraintSet.applyTo(constraintLayout);
        add_buttons(constraintLayout, new Pair[]{new Pair<>("inter3", INTERVIEW3.class), new Pair<>("inter2", INTERVIEW2.class), new Pair<>("Inter", INTERVIEW.class), new Pair<>("doctor Home", DoctorshomeActivity.class), new Pair<>("disease 3", DISEASE3.class), new Pair<>("disease 2", DISEASE2.class), new Pair<>("disease1", disease1.class), new Pair<>("D5", D5.class), new Pair<>("D4", D4.class), new Pair<>("Appointments", AppointmentlstActivity.class), new Pair<>("Add Leave", AddleaveActivity.class), new Pair<>("Main", MainActivity.class), new Pair<>("Main2", Main2Activity.class), new Pair<>("Terms", TERMS.class), new Pair<>("Pat", PATIENT1.class), new Pair<>("Pat2", PATIENT2.class), new Pair<>("Pat3", PATIENT3.class), new Pair<>("Point", POINT.class), new Pair<>("Predic", PREDICTING.class), new Pair<>("Re", Re.class), new Pair<>("Region", REGIONS.class), new Pair<>("Sear", SEAR.class), new Pair<>("Symp", SYMPTOMS.class)});
    }

    private void add_buttons(ConstraintLayout constraintLayout, Pair[] buttons) {

        int i = 0;
        Button previous_button = null;
        for (final Pair button_item : buttons) {

            // Create Button Dynamically
            Button button = new Button(this);
            button.setText(Objects.requireNonNull(button_item.first).toString());
            button.setId(View.generateViewId());

            ConstraintLayout.LayoutParams layoutParams = new ConstraintLayout.LayoutParams(
                    ConstraintLayout.LayoutParams.MATCH_CONSTRAINT, DisplayHelper.dpToPixel(48, getApplicationContext()));
            button.setLayoutParams(layoutParams);

            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    Toast.makeText(Activity_Launcher_Collection.this, Objects.requireNonNull(button_item.second).toString(), Toast.LENGTH_LONG).show();
                    Activity_Utils.start_activity(Activity_Launcher_Collection.this, (Class) button_item.second);
                }
            });

            // Add Button to LinearLayout
            constraintLayout.addView(button);

            ConstraintSet constraintSet = new ConstraintSet();
            constraintSet.clone(Objects.requireNonNull(constraintLayout));

            if (i == 0) {
                constraintSet.connect(button.getId(), ConstraintSet.TOP, constraintLayout.getId(), ConstraintSet.TOP, 16);
            } else {
                constraintSet.connect(button.getId(), ConstraintSet.TOP, previous_button.getId(), ConstraintSet.BOTTOM, 16);
            }
            constraintSet.connect(button.getId(), ConstraintSet.LEFT, constraintLayout.getId(), ConstraintSet.LEFT, 16);
            constraintSet.connect(button.getId(), ConstraintSet.RIGHT, constraintLayout.getId(), ConstraintSet.RIGHT, 16);

            constraintSet.applyTo(constraintLayout);
            previous_button = button;
            i++;
        }


//        btnShow.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));


    }
}
