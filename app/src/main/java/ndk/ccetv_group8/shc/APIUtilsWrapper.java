package ndk.ccetv_group8.shc;

import ndk.utils_android16.API_Utils;

public class APIUtilsWrapper {

    //TODO : No Method name methods
    //TODO : No File extension name methods
    //TODO : Make APIUtils to abstract class
    //TODO : Make a composite abstract class - if needed
    //TODO : Add parameter to calls

    public static String getHTTPAPI(String methodName) {
        return API_Utils.get_http_API(methodName, APIConstants.SERVER_IP_ADDRESS, APIConstants.HTTP_API_FOLDER, APIConstants.FILE_EXTENSION);
    }
}
