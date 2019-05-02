package ndk.ccetv_group8.shc;

import androidx.core.util.Pair;

public class DiseasePredictionSuccess extends TextWithButtonsActivity {

    String disease = "XYZ";

    @Override
    protected String configureTextViewFirstText() {
        return "Your Disease is " + disease;
    }

    @Override
    protected Pair[] configureButtonsWithClickEvents() {
        return new Pair[]{new Pair<>(getResources().getString(R.string.proceed), ButtonUtils.getStartActivityButtonEvent(activity_context, D5.class)), new Pair<>(getResources().getString(R.string.try_again), ButtonUtils.getBackButtonEvent(this)), new Pair<>(getResources().getString(R.string.no_thanks), ButtonUtils.getEmptyButtonEvent())};
    }
}
