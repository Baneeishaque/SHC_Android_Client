package ndk.ccetv_group8.shc;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import ndk.utils_android14.ContextActivity;
import ndk.utils_android16.Alert_Dialog_Utils;

public class SlotConfirmationActivity extends ContextActivity {

    String doctor = "XYZ";
    String passedDoctor;

    String disease = "XYZ";
    String passedDisease;

    String slot = "5 AM to 6 AM";
    String passedSlot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slot_confirmation);

        passedDisease = getIntent().getStringExtra("Disease");
        if (passedDisease == null) {
            passedDisease = disease;
        }

        passedDoctor = getIntent().getStringExtra("Doctor");
        if (passedDoctor == null) {
            passedDoctor = doctor;
        }

        passedSlot = getIntent().getStringExtra("Slot");
        if (passedSlot == null) {
            passedSlot = slot;
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
        buttonPayment.setOnClickListener(ButtonUtils.getStartActivityButtonEvent(this, PaymentActivity.class));

        Button buttonSlots = findViewById(R.id.buttonSlots);
        buttonSlots.setOnClickListener(ButtonUtils.getBackButtonEvent(this));

        Button buttonExit = findViewById(R.id.buttonExit);
        buttonExit.setOnClickListener(ButtonUtils.getButtonEvent(new ButtonUtils.FurtherActions() {
            @Override
            public void configureFurtherActions() {
                ActivityUtils.toHomeActivityWithConfirmation(activity_context);
            }
        }));

    }
}
