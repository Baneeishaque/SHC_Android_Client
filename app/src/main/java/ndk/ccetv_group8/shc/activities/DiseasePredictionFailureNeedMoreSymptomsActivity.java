package ndk.ccetv_group8.shc.activities;

import androidx.core.util.Pair;

import ndk.ccetv_group8.shc.R;
import ndk.ccetv_group8.shc.to_utils.ActivityUtils;
import ndk.ccetv_group8.shc.to_utils.ButtonUtils;
import ndk.ccetv_group8.shc.to_utils.activities.TextWithButtonsActivity;

import static ndk.ccetv_group8.shc.to_utils.ButtonUtils.getBackButtonEvent;

public class DiseasePredictionFailureNeedMoreSymptomsActivity extends TextWithButtonsActivity {
    @Override
    protected String configureTextViewFirstText() {
        return getResources().getString(R.string.more_symptoms);
    }

    @Override
    protected Pair[] configureButtonsWithClickEvents() {
        return new Pair[]{new Pair<>(getResources().getString(R.string.proceed), getBackButtonEvent(this)), new Pair<>(getResources().getString(R.string.no_thanks), ButtonUtils.getButtonEvent(() -> ActivityUtils.toHomeActivityWithConfirmation(activity_context)))};
    }

}
