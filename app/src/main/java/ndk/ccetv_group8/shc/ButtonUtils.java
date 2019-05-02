package ndk.ccetv_group8.shc;

import android.content.Context;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import ndk.utils_android14.ActivityUtils;

public class ButtonUtils {

    public static View.OnClickListener getStartActivityButtonEvent(Context activityContext, Class targetClass) {
        return v -> ActivityUtils.start_activity(activityContext, targetClass);
    }

    public static View.OnClickListener getStartActivityWithFinishButtonEvent(Context activityContext, Class targetClass, String applicationName) {
        return v -> ActivityUtils.start_activity_with_finish(activityContext, targetClass, applicationName);
    }

    public static View.OnClickListener getEmptyButtonEvent() {
        return v -> {
        };
    }

    public static View.OnClickListener getBackButtonEvent(AppCompatActivity appCompatActivity) {
        return v -> (appCompatActivity).onBackPressed();
    }
}
