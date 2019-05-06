package ndk.ccetv_group8.shc.wrappers;

import android.content.Context;

import ndk.ccetv_group8.shc.BuildConfig;
import ndk.ccetv_group8.shc.constants.ApplicationConstants;
import ndk.utils_android16.Error_Utils;

public class ErrorUtilsWrapper {

    //TODO : Make *Utils to abstract class
    //TODO : Make a composite abstract class - if needed
    //TODO : Modify toast based on debug flag
    //TODO : Use applicationContext - if possible - check this

    public static void displayException(Context application_context, Exception exception) {
        Error_Utils.display_Exception(application_context, exception, ApplicationConstants.APPLICATION_NAME, BuildConfig.DEBUG);
    }
}
