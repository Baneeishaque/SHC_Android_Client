package ndk.ccetv_group8.shc.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.core.util.Pair;

import java.util.Objects;

import ndk.ccetv_group8.shc.R;
import ndk.ccetv_group8.shc.constants.ApplicationConstants;
import ndk.ccetv_group8.shc.to_utils.ButtonUtils;
import ndk.ccetv_group8.shc.to_utils.StringUtils;
import ndk.ccetv_group8.shc.wrappers.APIUtilsWrapper;
import ndk.ccetv_group8.shc.wrappers.LogUtilsWrapper;
import ndk.ccetv_group8.shc.wrappers.RESTGETTaskUtilsWrapper;
import ndk.utils_android14.ContextActivity;
import ndk.utils_android16.Alert_Dialog_Utils;
import ndk.utils_android16.Date_Utils;
import ndk.utils_android16.Toast_Utils;
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

    String slotId = "6";
    String passedSlotId;

    String doctorId = "1";
    String passedDoctorId;

    String doctorDetails = "Details";
    String passedDoctorDetails;

    ProgressBar progressBar;

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

        passedSlotId = getIntent().getStringExtra("slot_id");
        if (passedSlotId == null) {
            passedSlotId = slotId;
        }

        passedDoctorId = getIntent().getStringExtra("doctor_id");
        if (passedDoctorId == null) {
            passedDoctorId = doctorId;
        }

        passedDoctorDetails = getIntent().getStringExtra("doctor_details");
        if (passedDoctorDetails == null) {
            passedDoctorDetails = doctorDetails;
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

        progressBar = findViewById(R.id.progressBar);

        Button buttonSubmit = findViewById(R.id.buttonSubmit);
        buttonSubmit.setOnClickListener(ButtonUtils.getButtonEvent(() -> {
            boolean result = Objects.requireNonNull(Validation_Utils.empty_check(new Pair[]{new Pair<>(editTextName, "Enter Name..."), new Pair<>(editTextAddress, "Enter Address..."), new Pair<>(editTextContactNumber, "Enter Contact Number...")}).first);
            if (!result) {
                new Alert_Dialog_Utils((dialog, which) -> {
                    buttonSubmit.setVisibility(View.INVISIBLE);

                    new RESTGETTaskUtilsWrapper().execute(APIUtilsWrapper.getHTTPAPI2("" + editTextName.getText().toString() + "/" + editTextAddress.getText().toString() + "/" + editTextContactNumber.getText().toString() + "/" + passedDisease + "/" + passedDoctor + "/" + passedSlot + "/" + passedTransactionID + "/" + passedDoctorId + "/" + passedSlotId + "/" + Date_Utils.get_current_date_string_in_mysql_format(), "add_slot"), activity_context, progressBar, response -> {

                        LogUtilsWrapper.debug(response);
                        if (response.equals("exception")) {
                            //TODO : Make scenarios for no match & More Symptoms & incorporate them
                            LogUtilsWrapper.debug("Error...");
                        } else {
                            if (StringUtils.removeQuotations(response).equals("ok")) {
                                Toast_Utils.longToast(getApplicationContext(), "Success...");
                                new Alert_Dialog_Utils((dialog1, which1) -> ndk.utils_android14.ActivityUtils.start_activity_with_finish(activity_context, SymptomsActivity.class, ApplicationConstants.APPLICATION_NAME), (dialog12, which12) -> {
                                }).titled_OK_Dialogue(activity_context, "Name : " + editTextName.getText().toString() + "\n" + "Address : " + editTextAddress.getText().toString() + "\n" + "Contact Number : " + editTextContactNumber.getText().toString() + "\n\n" + "Disease : " + passedDisease + "\n" + "Doctor : " + passedDoctor + "," + passedDoctorDetails + "\n" + "Slot : " + passedSlot + "\n" + "TransactionID : " + passedTransactionID + "\n\n" + "Please Keep These Things...", "Caution", false);
                            } else {
                                Toast_Utils.longToast(getApplicationContext(), "Error...");
                            }
                        }
                    });
                }, (dialog, which) -> {
                }).titled_Yes_No_Dialogue(activity_context, "Name : " + editTextName.getText().toString() + "\n" + "Address : " + editTextAddress.getText().toString() + "\n" + "Contact Number : " + editTextContactNumber.getText().toString() + "\n\n" + "Disease : " + passedDisease + "\n" + "Doctor : " + passedDoctor + ", " + passedDoctorDetails + "\n" + "Slot : " + passedSlot + "\n" + "TransactionID : " + passedTransactionID, "Confirmation", true);
            }
        }));
    }
}
