package ndk.ccetv_group8.shc.to_utils;

import android.content.Context;
import android.widget.Toast;

public class Toast_Utils extends ndk.utils_android16.Toast_Utils {

    public static void shortToast(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }
}
