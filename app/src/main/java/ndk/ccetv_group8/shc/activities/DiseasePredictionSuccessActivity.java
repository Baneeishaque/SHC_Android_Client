package ndk.ccetv_group8.shc.activities;

import android.content.pm.PackageManager;
import android.location.Location;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.util.Pair;

import ndk.ccetv_group8.shc.R;
import ndk.ccetv_group8.shc.to_utils.ActivityUtils;
import ndk.ccetv_group8.shc.to_utils.ButtonUtils;
import ndk.ccetv_group8.shc.to_utils.LocationUtils;
import ndk.ccetv_group8.shc.to_utils.RESTGETTask;
import ndk.ccetv_group8.shc.to_utils.StringUtils;
import ndk.ccetv_group8.shc.to_utils.activities.TextWithButtonsActivity;
import ndk.ccetv_group8.shc.wrappers.APIUtilsWrapper;
import ndk.ccetv_group8.shc.wrappers.LogUtilsWrapper;
import ndk.ccetv_group8.shc.wrappers.RESTGETTaskUtilsWrapper;
import ndk.utils_android16.ProgressBar_Utils;
import ndk.utils_android16.Snackbar_Utils;
import ndk.utils_android16.Toast_Utils;

import static android.Manifest.permission.ACCESS_FINE_LOCATION;

public class DiseasePredictionSuccessActivity extends TextWithButtonsActivity {

    private static final int PERMISSION_REQUEST_CODE = 0;

    //TODO : Centralise Layout

    //TODO : Read Bundle Value, substitute if it is null
    String disease = "XYZ";
    String passedDisease;

    boolean myLocationFlag = false;
    LocationUtils locationUtils = new LocationUtils();

    @Override
    protected String configureTextViewFirstText() {

        //TODO : To Bundle Utils with default value
        passedDisease = getIntent().getStringExtra("disease");
        if (passedDisease == null) {
            passedDisease = disease;
        }

        return "Your Disease is " + passedDisease;
    }

    @Override
    protected Pair[] configureButtonsWithClickEvents() {
        return new Pair[]{new Pair<>(getResources().getString(R.string.proceed), ButtonUtils.getButtonEvent(() -> {

            if (ContextCompat.checkSelfPermission(getApplicationContext(), ACCESS_FINE_LOCATION) != 0) {
                ActivityCompat.requestPermissions((AppCompatActivity) activity_context, new String[]{ACCESS_FINE_LOCATION}, PERMISSION_REQUEST_CODE);
            } else {
                LocationUtils.LocationResult locationResult = new LocationUtils.LocationResult() {
                    @Override
                    public void gotLocation(Location location) {
                        //Got the location!
                        LogUtilsWrapper.debug(location.toString());
                        ProgressBar_Utils.showProgress(false, activity_context, progressBar, scrollView);

                        new RESTGETTaskUtilsWrapper().execute(APIUtilsWrapper.getHTTPAPI2("" + passedDisease, "get_doctor"), activity_context, progressBar, scrollView, (RESTGETTask.Async_Response) response -> {

                            LogUtilsWrapper.debug(response);
                            if (response.equals("exception")) {
                                //TODO : Make scenarios for no match & More Symptoms & incorporate them
                                LogUtilsWrapper.debug("Error...");
                            } else {
                                if (StringUtils.removeQuotations(response).equals("[]")) {
                                    Toast_Utils.longToast(getApplicationContext(), "Sorry No Doctors Available...");
                                } else {
                                    ndk.utils_android14.ActivityUtils.start_activity_with_string_extras(activity_context, DoctorActivity.class, new Pair[]{new Pair<>("disease", passedDisease), new Pair<>("doctors", StringUtils.removeHyphens(StringUtils.removeFirstAndLastCharacters(response))), new Pair<>("longitude", location.getLongitude()), new Pair<>("latitude", location.getLatitude())}, false, 0);
                                }
                            }
                        });
//                        ndk.utils_android14.ActivityUtils.start_activity_with_string_extras(activity_context, DoctorActivity.class, new Pair[]{new Pair<>("disease", passedDisease), new Pair<>("longitude", location.getLongitude()), new Pair<>("latitude", location.getLatitude())}, false, 0);
                    }
                };
                if (locationUtils.getLocation(activity_context, locationResult)) {
                    myLocationFlag = true;
                    ProgressBar_Utils.showProgress(true, activity_context, progressBar, scrollView);
                }

            }
        })), new Pair<>(getResources().getString(R.string.try_again), ButtonUtils.getBackButtonEvent(this)), new Pair<>(getResources().getString(R.string.no_thanks), ButtonUtils.getButtonEvent(() -> ActivityUtils.toHomeActivityWithConfirmation(activity_context)))};
    }

    @Override
    protected void onPause() {
        if (myLocationFlag) {
            locationUtils.cancelTimer();
        }
        super.onPause();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {

        if (requestCode == PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0) {
                boolean locationAccepted = grantResults[0] == PackageManager.PERMISSION_GRANTED;

                if (locationAccepted) {
                    Snackbar_Utils.display_Short_no_FAB_success_bottom_SnackBar(activity_context, "Permission Granted, Thanks.");
                } else {
                    Snackbar_Utils.display_Short_no_FAB_error_bottom_SnackBar(activity_context, "Please allow location access for better doctor search results.");
                }
            }
        }
    }
}
