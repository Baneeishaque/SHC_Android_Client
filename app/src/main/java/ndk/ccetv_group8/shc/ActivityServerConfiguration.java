package ndk.ccetv_group8.shc;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.core.util.Pair;

import java.util.Objects;

import ndk.utils_android14.ActivityUtils;
import ndk.utils_android14.ContextActivity;
import ndk.utils_android16.SharedPreference_Utils;
import ndk.utils_android16.Validation_Utils;

public class ActivityServerConfiguration extends ContextActivity {

    //TODO : To Utils

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_server_configuration);

        Button buttonSubmit = findViewById(R.id.buttonSubmit);
        EditText editTextServerAddress = findViewById(R.id.editTextServerAddress);

        //TODO : To Utils - check existence of a preference
        SharedPreferences settings = getSharedPreferences(ApplicationConstants.APPLICATION_NAME, Context.MODE_PRIVATE);
        if (!Objects.equals(settings.getString("serverAddress", "http://192.168.43.89:5000"), "http://192.168.43.89:5000")) {
            editTextServerAddress.setText(settings.getString("serverAddress", "http://192.168.43.89:5000"));
        }

        buttonSubmit.setOnClickListener(arg0 -> {

            //TODO : To Utils - IP address check
            boolean validationResult = Objects.requireNonNull(Validation_Utils.empty_check(new Pair[]{new Pair<>(editTextServerAddress, "Please Enter Server Address.")}).first);
            if (!validationResult) {
                String serverAddress = editTextServerAddress.getText().toString();
                if (serverAddress.split("\\.").length == 4) {

                    //TODO : Implement This
//                    InetAddress address = null;
//                    try {
//                        address = InetAddress.getByName(serverAddress);
                    APIConstants.SERVER_IP_ADDRESS = serverAddress;
                    SharedPreference_Utils.commit_Shared_Preferences(activity_context, ApplicationConstants.APPLICATION_NAME, new Pair[]{new Pair<>("serverAddress", serverAddress)});
                    //TODO : DB of previous server addresses
                    ActivityUtils.start_activity_with_finish(activity_context, SplashActivity.class, ApplicationConstants.APPLICATION_NAME);
//                    } catch (UnknownHostException ex) {
//                        ErrorUtilsWrapper.displayException(activity_context, ex);
//                    }

                    //TODO : Implement This
//                    InetAddressValidator i = InetAddressValidator.getInstance();
//                    if (i.isValid(address.toString().substring(address.toString().indexOf("/") + 1))) {
//                        ip_address = ip.getText();
//                        new Voting().show();
//                        this.dispose();
//                    } else {
//                        JOptionPane.showMessageDialog(rootPane, "Invalid Server address", "Electronic Voting System", JOptionPane.ERROR_MESSAGE);
//                    }

                } else {
                    editTextServerAddress.setError("Invalid IP Address.");
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        //TODO : To Done - exit by Back presses
        ndk.ccetv_group8.shc.ActivityUtils.toHomeActivityWithConfirmation(activity_context);
    }
}
