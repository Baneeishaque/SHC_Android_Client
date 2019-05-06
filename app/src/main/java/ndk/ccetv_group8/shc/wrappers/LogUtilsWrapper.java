package ndk.ccetv_group8.shc.wrappers;

import ndk.ccetv_group8.shc.BuildConfig;
import ndk.ccetv_group8.shc.constants.ApplicationConstants;
import ndk.utils_android16.Log_Utils;

public class LogUtilsWrapper {

    //TODO : Make *Utils to abstract class
    //TODO : Make a composite abstract class - if needed

    public static void debug(String message) {
        Log_Utils.debug(ApplicationConstants.APPLICATION_NAME, message, BuildConfig.DEBUG);
    }
}
