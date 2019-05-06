package ndk.ccetv_group8.shc;

import androidx.core.util.Pair;


public class DiseasePredictionFailureNoMatch extends TextWithButtonsActivity {
    @Override
    protected String configureTextViewFirstText() {
        return getResources().getString(R.string.disease_prediction_failure_no_match);
    }

    @Override
    protected Pair[] configureButtonsWithClickEvents() {
        return new Pair[]{new Pair<>(getResources().getString(R.string.proceed), ButtonUtils.getStartActivityButtonEvent(activity_context, DoctorActivity.class)), new Pair<>(getResources().getString(R.string.try_again), ButtonUtils.getBackButtonEvent(this)), new Pair<>(getResources().getString(R.string.no_thanks), ButtonUtils.getButtonEvent(() -> ActivityUtils.toHomeActivityWithConfirmation(activity_context)))};
    }
}
