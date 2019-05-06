package ndk.ccetv_group8.shc.activities;

import androidx.core.util.Pair;

public class LaunchersActivity extends ndk.utils_android17.LaunchersActivity {
    @Override
    protected Pair[] configureLaunchers() {
        return new Pair[]{new Pair<>("More Symptoms", DiseasePredictionFailureNeedMoreSymptomsActivity.class), new Pair<>("No Match", DiseasePredictionFailureNoMatchActivity.class), new Pair<>("Prediction Success", DiseasePredictionSuccessActivity.class)};
    }
}
