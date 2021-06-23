package ndk.ccetv_group8.shc.wrappers;

import ndk.ccetv_group8.shc.constants.ApplicationConstants;
import ndk.ccetv_group8.shc.to_utils.RESTGETTaskUtils;

public class RESTGETTaskUtilsWrapper extends RESTGETTaskUtils {

    @Override
    public String configureApplicationName() {
        return ApplicationConstants.APPLICATION_NAME;
    }
}
