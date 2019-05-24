package ndk.ccetv_group8.shc.activities;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.core.util.Pair;

import ndk.ccetv_group8.shc.R;
import ndk.ccetv_group8.shc.to_utils.ActivityUtils;
import ndk.ccetv_group8.shc.to_utils.ButtonUtils;
import ndk.utils_android14.ContextActivity;
import ndk.utils_android16.Alert_Dialog_Utils;

public class SlotConfirmationActivity extends ContextActivity {

    String doctor = "XYZ";
    String passedDoctor;

    String disease = "XYZ";
    String passedDisease;

    String slot = "5 AM to 6 AM";
    String passedSlot;

    String slotId = "6";
    String passedSlotId;

    String doctorId = "1";
    String passedDoctorId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slot_confirmation);

        passedDisease = getIntent().getStringExtra("disease");
        if (passedDisease == null) {
            passedDisease = disease;
        }

        passedDoctor = getIntent().getStringExtra("doctor");
        if (passedDoctor == null) {
            passedDoctor = doctor;
        }

        passedSlot = getIntent().getStringExtra("slot");
        if (passedSlot == null) {
            passedSlot = slot;
        }

        passedSlotId = getIntent().getStringExtra("slot_id");
        if (passedSlotId == null) {
            passedSlotId = slotId;
        }

        passedDoctorId = getIntent().getStringExtra("doctor_id");
        if (passedDoctorId == null) {
            passedDoctorId = doctorId;
        }

        if (getIntent().getExtras() != null) {
            if (getIntent().getStringExtra("payment") != null && getIntent().getStringExtra("payment").equals("Failure")) {
                new Alert_Dialog_Utils((dialog, which) -> {
                }, (dialog, which) -> {
                }).titled_OK_Dialogue(activity_context, "Try Again...", "Payment Failure!", false);
            }
        }

        TextView textViewDisease = findViewById(R.id.textViewDisease);
        textViewDisease.setText("Disease : " + passedDisease);

        TextView textViewDoctor = findViewById(R.id.textViewDoctor);
        textViewDoctor.setText("Doctor : " + passedDoctor);

        TextView textViewSlot = findViewById(R.id.textViewSlot);
        textViewSlot.setText("Slot : " + passedSlot);

        Button buttonPayment = findViewById(R.id.buttonPayment);
        buttonPayment.setOnClickListener(ButtonUtils.getButtonEvent(() -> ndk.utils_android14.ActivityUtils.start_activity_with_string_extras_and_finish(activity_context, PaymentActivity.class, new Pair[]{new Pair<>("disease", passedDisease), new Pair<>("doctor", passedDoctor), new Pair<>("slot", passedSlot), new Pair<>("doctor_id", passedDoctorId), new Pair<>("slot_id", passedSlotId)})));

        Button buttonSlots = findViewById(R.id.buttonSlots);
        buttonSlots.setOnClickListener(ButtonUtils.getBackButtonEvent(this));

        Button buttonExit = findViewById(R.id.buttonExit);
        buttonExit.setOnClickListener(ButtonUtils.getButtonEvent(() -> ActivityUtils.toHomeActivityWithConfirmation(activity_context)));

    }
}
