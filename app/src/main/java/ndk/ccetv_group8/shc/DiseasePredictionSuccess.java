package ndk.ccetv_group8.shc;

import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.util.Pair;

import ndk.utils_android16.ProgressBar_Utils;
import ndk.utils_android16.Snackbar_Utils;

import static android.Manifest.permission.ACCESS_FINE_LOCATION;

public class DiseasePredictionSuccess extends TextWithButtonsActivity {

    private static final int PERMISSION_REQUEST_CODE = 0;

    //TODO : Centralise Layout

    //TODO : Read Bundle Value, substitute if it is null
    String disease = "XYZ";
    String predictedDisease;

    @Override
    protected String configureTextViewFirstText() {

        //TODO : To Bundle Utils with default value
        predictedDisease = getIntent().getStringExtra("passedDisease");
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
//                startNextActivityWithLocation();
                ndk.utils_android14.ActivityUtils.start_activity_with_string_extras(activity_context, DoctorActivity.class, new Pair[]{new Pair<>("disease", predictedDisease)}, false, 0);

            }
        })), new Pair<>(getResources().getString(R.string.try_again), ButtonUtils.getBackButtonEvent(this)), new Pair<>(getResources().getString(R.string.no_thanks), ButtonUtils.getButtonEvent(() -> ActivityUtils.toHomeActivityWithConfirmation(activity_context)))};
    }

    private void startNextActivityWithLocation() {
        if (ContextCompat.checkSelfPermission(getApplicationContext(), ACCESS_FINE_LOCATION) != 0) {
            ActivityCompat.requestPermissions((AppCompatActivity) activity_context, new String[]{ACCESS_FINE_LOCATION}, PERMISSION_REQUEST_CODE);
        } else {
            ProgressBar_Utils.showProgress(true, activity_context, progressBar, scrollView);
            LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, new LocationListener() {
                @Override
                public void onLocationChanged(Location location) {
                    ProgressBar_Utils.showProgress(false, activity_context, progressBar, scrollView);
                    ndk.utils_android14.ActivityUtils.start_activity_with_string_extras(activity_context, DoctorActivity.class, new Pair[]{new Pair<>("disease", predictedDisease), new Pair<>("longitude", location.getLongitude()), new Pair<>("latitude", location.getLatitude())}, false, 0);
                }

                @Override
                public void onStatusChanged(String provider, int status, Bundle extras) {

                }

                @Override
                public void onProviderEnabled(String provider) {

                }

                @Override
                public void onProviderDisabled(String provider) {
//                    Snackbar_Utils.display_Short_no_FAB_error_bottom_SnackBar(activity_context, "Please allow GPS for better doctor search results.");
                }
            });
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {

        if (requestCode == PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0) {
                boolean locationAccepted = grantResults[0] == PackageManager.PERMISSION_GRANTED;

                if (locationAccepted) {
                    Snackbar_Utils.display_Short_no_FAB_success_bottom_SnackBar(activity_context, "Permission Granted, Thanks.");
//                    startNextActivityWithLocation();
                    ndk.utils_android14.ActivityUtils.start_activity_with_string_extras(activity_context, DoctorActivity.class, new Pair[]{new Pair<>("disease", predictedDisease)}, false, 0);

                } else {
                    Snackbar_Utils.display_Short_no_FAB_error_bottom_SnackBar(activity_context, "Please allow location access for better doctor search results.");
                }
            }
        }
    }

}
