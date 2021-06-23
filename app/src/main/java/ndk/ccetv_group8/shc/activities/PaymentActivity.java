package ndk.ccetv_group8.shc.activities;

import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;

import androidx.core.util.Pair;

import java.util.Random;

import ndk.ccetv_group8.shc.R;
import ndk.ccetv_group8.shc.to_utils.FurtherActions;
import ndk.ccetv_group8.shc.to_utils.WebAppInterface;
import ndk.utils_android14.ActivityUtils;
import ndk.utils_android14.ContextActivity;
import ndk.utils_android16.Alert_Dialog_Utils;
import ndk.utils_android16.Snackbar_Utils;

public class PaymentActivity extends ContextActivity {

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

    String doctorDetails = "Details";
    String passedDoctorDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

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

        WebView wv = findViewById(R.id.webView);
        wv.loadUrl("file:///android_asset/paymentPage.html");

        WebSettings ws = wv.getSettings();
        ws.setJavaScriptEnabled(true);

        wv.addJavascriptInterface(new WebAppInterface(this, new FurtherActions() {
            @Override
            public void furtherActions() {
                Snackbar_Utils.display_Short_no_FAB_success_bottom_SnackBar(activity_context, "Success");
//                SharedPreferences settings = getSharedPreferences(ApplicationConstants.APPLICATION_NAME, Context.MODE_PRIVATE);
//                if (settings.getString("isLoggedIn", String.valueOf(false)).equals(String.valueOf(true))) {
//
//                } else {
//                    ndk.utils_android14.ActivityUtils.start_activity_with_finish(activity_context, LoginPageActivity.class, ApplicationConstants.APPLICATION_NAME);
//                }
                int transactionID = new Random().nextInt(1000);
                new Alert_Dialog_Utils((dialog, which) -> {
                    ndk.utils_android14.ActivityUtils.start_activity_with_string_extras_and_finish(activity_context, SubmitDetailsActivity.class, new Pair[]{new Pair<>("disease", passedDisease), new Pair<>("doctor", passedDoctor), new Pair<>("slot", passedSlot), new Pair<>("transactionID", String.valueOf(transactionID)), new Pair<>("doctor_id", passedDoctorId), new Pair<>("slot_id", passedSlotId), new Pair<>("doctor_details", passedDoctorDetails)});
                }, (dialog, which) -> {
                }).titled_OK_Dialogue(activity_context, "Your Transaction ID is " + transactionID + ", Please Keep it for further queries.", "Payment Success!", false);
            }
        }), "Success");

        wv.addJavascriptInterface(new WebAppInterface(this, () -> {
            Snackbar_Utils.display_Short_no_FAB_error_bottom_SnackBar(activity_context, "Failure");
            ActivityUtils.start_activity_with_string_extras_and_finish(activity_context, SlotConfirmationActivity.class, new Pair[]{new Pair<>("disease", passedDisease), new Pair<>("doctor", passedDoctor), new Pair<>("slot", passedSlot), new Pair<>("payment", "Failure"), new Pair<>("doctor_id", passedDoctorId), new Pair<>("slot_id", passedSlotId)});
        }), "Failure");
    }
}
