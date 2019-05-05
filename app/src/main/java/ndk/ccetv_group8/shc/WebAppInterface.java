package ndk.ccetv_group8.shc;

import android.content.Context;
import android.webkit.JavascriptInterface;

interface FurtherActions {
    void furtherActions();
}

public class WebAppInterface {

    private Context mContext;
    private FurtherActions mfurtherActions;

    /**
     * Instantiate the interface and set the context
     */
    WebAppInterface(Context context, FurtherActions furtherActions) {
        mContext = context;
        mfurtherActions = furtherActions;
    }

    /**
     * Show a toast from the web page
     */
    @JavascriptInterface
    public void doActions() {
        mfurtherActions.furtherActions();
    }
}