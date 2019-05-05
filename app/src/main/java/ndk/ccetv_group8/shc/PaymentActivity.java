package ndk.ccetv_group8.shc;

import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;

import androidx.core.util.Pair;

import java.util.Random;

import ndk.utils_android14.ActivityUtils;
import ndk.utils_android14.ContextActivity;
import ndk.utils_android16.Alert_Dialog_Utils;
import ndk.utils_android16.Snackbar_Utils;

public class PaymentActivity extends ContextActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

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
                }, (dialog, which) -> {
                }).titled_OK_Dialogue(activity_context, "Your Transaction ID is " + transactionID + ", Please Keep it for further queries.", "Payment Success!", false);
                ndk.utils_android14.ActivityUtils.start_activity_with_string_extras_and_finish(activity_context, SubmitDetailsActivity.class, new Pair[]{new Pair<>("transactionID", String.valueOf(transactionID))});
            }
        }), "Success");

        wv.addJavascriptInterface(new WebAppInterface(this, () -> {
            Snackbar_Utils.display_Short_no_FAB_error_bottom_SnackBar(activity_context, "Failure");
            ActivityUtils.start_activity_with_string_extras_and_finish(activity_context, SlotConfirmationActivity.class, new Pair[]{new Pair<>("payment", "Failure")});
        }), "Failure");
    }
}
