package ndk.ccetv_group8.shc;

import androidx.core.util.Pair;

public class DiseasePredictionSuccess extends TextWithButtonsActivity {

    //TODO : Centralise Layout

    //TODO : Read Bundle Value, substitute if it is null
    String disease = "XYZ";

    @Override
    protected String configureTextViewFirstText() {

        //TODO : To Bundle Utils with default value
        String predictedDisease = getIntent().getStringExtra("predictedDisease");
        if (predictedDisease == null) {
            predictedDisease = disease;
        }

        return "Your Disease is " + predictedDisease;
    }

    @Override
    protected Pair[] configureButtonsWithClickEvents() {
        return new Pair[]{new Pair<>(getResources().getString(R.string.proceed), ButtonUtils.getStartActivityButtonEvent(activity_context, D5.class)), new Pair<>(getResources().getString(R.string.try_again), ButtonUtils.getBackButtonEvent(this)), new Pair<>(getResources().getString(R.string.no_thanks), ButtonUtils.getEmptyButtonEvent())};
    }
}
