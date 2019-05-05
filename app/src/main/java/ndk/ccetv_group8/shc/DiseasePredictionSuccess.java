package ndk.ccetv_group8.shc;

import android.content.pm.PackageManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.util.Pair;

import ndk.utils_android16.Snackbar_Utils;

import static android.Manifest.permission.ACCESS_FINE_LOCATION;

public class DiseasePredictionSuccess extends TextWithButtonsActivity {
    private static final int PERMISSION_REQUEST_CODE = 0;

    //TODO : Centralise Layout

    //TODO : Read Bundle Value, substitute if it is null
    String disease = "XYZ";

    @Override
    protected String configureTextViewFirstText() {

        //TODO : To Bundle Utils with default value
        String predictedDisease = getIntent().getStringExtra("predictedDisease");
        if (predictedDisease == null) {
            predictedDisease = disease;
        }

        return "Your Disease is " + predictedDisease;
    }

    @Override
    protected Pair[] configureButtonsWithClickEvents() {
        return new Pair[]{new Pair<>(getResources().getString(R.string.proceed), ButtonUtils.getButtonEvent(new ButtonUtils.FurtherActions() {
            @Override
            public void configureFurtherActions() {
                if (ContextCompat.checkSelfPermission(getApplicationContext(), ACCESS_FINE_LOCATION) != 0) {
                    ActivityCompat.requestPermissions((AppCompatActivity) activity_context, new String[]{ACCESS_FINE_LOCATION}, PERMISSION_REQUEST_CODE);
                } else {
                    ndk.utils_android1.ActivityUtils.start_activity(activity_context, D5.class);
                }
            }
        })), new Pair<>(getResources().getString(R.string.try_again), ButtonUtils.getBackButtonEvent(this)), new Pair<>(getResources().getString(R.string.no_thanks), ButtonUtils.getButtonEvent(() -> ActivityUtils.toHomeActivityWithConfirmation(activity_context)))};
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {

        if (requestCode == PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0) {
                boolean locationAccepted = grantResults[0] == PackageManager.PERMISSION_GRANTED;

                if (locationAccepted) {
                    Snackbar_Utils.display_Short_no_FAB_success_bottom_SnackBar(activity_context, "Permission Granted, Thanks.");
                    ndk.utils_android1.ActivityUtils.start_activity(activity_context, D5.class);
                } else {
                    Snackbar_Utils.display_Short_no_FAB_error_bottom_SnackBar(activity_context, "Please allow location access for better doctor search results.");
                }
            }
        }
    }

}
