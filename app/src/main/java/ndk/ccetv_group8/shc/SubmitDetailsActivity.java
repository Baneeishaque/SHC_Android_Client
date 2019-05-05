package ndk.ccetv_group8.shc;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.core.util.Pair;

import java.util.Objects;

import ndk.utils_android14.ContextActivity;
import ndk.utils_android16.Alert_Dialog_Utils;
import ndk.utils_android16.Validation_Utils;

public class SubmitDetailsActivity extends ContextActivity {

    String doctor = "XYZ";
    String passedDoctor;

    String disease = "XYZ";
    String passedDisease;

    String slot = "5 AM to 6 AM";
    String passedSlot;

    String transactionID = "121";
    String passedTransactionID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submit_details);

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

        TextView textViewTransactionID = null;
        if (getIntent().getExtras() != null) {
            if (getIntent().getStringExtra("transactionID") != null) {
                passedTransactionID = getIntent().getStringExtra("transactionID");
                textViewTransactionID = findViewById(R.id.textViewTransactionID);
                textViewTransactionID.setText("TransactionID : " + passedTransactionID);
            } else {
                passedTransactionID = transactionID;
            }
        }

        TextView textViewDisease = findViewById(R.id.textViewDisease);
        textViewDisease.setText("Disease : " + passedDisease);

        TextView textViewDoctor = findViewById(R.id.textViewDoctor);
        textViewDoctor.setText("Doctor : " + passedDoctor);

        TextView textViewSlot = findViewById(R.id.textViewSlot);
        textViewSlot.setText("Slot : " + passedSlot);

        EditText editTextName = findViewById(R.id.editTextName);
        EditText editTextAddress = findViewById(R.id.editTextAddress);
        EditText editTextContactNumber = findViewById(R.id.editTextContactNumber);

        Button buttonSubmit = findViewById(R.id.buttonSubmit);
        buttonSubmit.setOnClickListener(ButtonUtils.getButtonEvent(() -> {
            boolean result = Objects.requireNonNull(Validation_Utils.empty_check(new Pair[]{new Pair<>(editTextName, "Enter Name..."), new Pair<>(editTextAddress, "Enter Address..."), new Pair<>(editTextContactNumber, "Enter Contact Number...")}).first);
            if (!result) {
                new Alert_Dialog_Utils((dialog, which) -> {
                    buttonSubmit.setVisibility(View.INVISIBLE);
                    new Alert_Dialog_Utils((dialog1, which1) -> ndk.utils_android14.ActivityUtils.start_activity_with_finish(activity_context, SymptomsActivity.class, ApplicationConstants.APPLICATION_NAME), (dialog12, which12) -> {
                    }).titled_OK_Dialogue(activity_context, "Name : " + editTextName.getText().toString() + "\n" + "Address : " + editTextAddress.getText().toString() + "\n" + "Contact Number : " + editTextContactNumber.getText().toString() + "\n\n" + "Disease : " + passedDisease + "\n" + "Doctor : " + passedDoctor + "\n" + "Slot : " + passedSlot + "\n" + "TransactionID : " + passedTransactionID + "\n\n" + "Please Keep These Things...", "Caution", false);
                }, (dialog, which) -> {
                }).titled_Yes_No_Dialogue(activity_context, "Name : " + editTextName.getText().toString() + "\n" + "Address : " + editTextAddress.getText().toString() + "\n" + "Contact Number : " + editTextContactNumber.getText().toString() + "\n\n" + "Disease : " + passedDisease + "\n" + "Doctor : " + passedDoctor + "\n" + "Slot : " + passedSlot + "\n" + "TransactionID : " + passedTransactionID, "Confirmation", true);
            }
        }));
    }
}
