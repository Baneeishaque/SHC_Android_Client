package ndk.ccetv_group8.shc;

import androidx.core.util.Pair;

import static ndk.ccetv_group8.shc.ButtonUtils.getBackButtonEvent;
import static ndk.ccetv_group8.shc.ButtonUtils.getEmptyButtonEvent;

public class DiseasePredictionFailureNeedMoreSymptoms extends TextWithButtonsActivity {
    @Override
    protected String configureTextViewFirstText() {
        return getResources().getString(R.string.more_symptoms);
    }

    @Override
    protected Pair[] configureButtonsWithClickEvents() {
        return new Pair[]{new Pair<>(getResources().getString(R.string.proceed), getBackButtonEvent(this)), new Pair<>(getResources().getString(R.string.no_thanks), getEmptyButtonEvent())};
    }

}
