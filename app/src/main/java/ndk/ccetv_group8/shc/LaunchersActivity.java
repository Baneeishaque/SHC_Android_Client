package ndk.ccetv_group8.shc;

import androidx.core.util.Pair;

public class LaunchersActivity extends ndk.utils_android17.LaunchersActivity {
    @Override
    protected Pair[] configureLaunchers() {
        return new Pair[]{new Pair<>("More Symptoms", DiseasePredictionFailureNeedMoreSymptoms.class), new Pair<>("No Match", DiseasePredictionFailureNoMatch.class), new Pair<>("Doctor Home", DoctorHomeActivity.class), new Pair<>("Prediction Success", DiseasePredictionSuccess.class), new Pair<>("First Page", FirstPageActivity.class), new Pair<>("Login", LoginPageActivity.class), new Pair<>("Main2", Main2Activity.class), new Pair<>("Main", MainActivity.class), new Pair<>("inter3", INTERVIEW3.class), new Pair<>("inter2", INTERVIEW2.class), new Pair<>("Inter", INTERVIEW.class), new Pair<>("doctor Home", DoctorshomeActivity.class), new Pair<>("disease 3", DISEASE3.class), new Pair<>("disease 2", DISEASE2.class), new Pair<>("disease1", disease1.class), new Pair<>("D5", D5.class), new Pair<>("D4", D4.class), new Pair<>("Appointments", AppointmentlstActivity.class), new Pair<>("Add Leave", AddleaveActivity.class), new Pair<>("Introduction", IntroductionActivity.class), new Pair<>("Terms", TERMS.class), new Pair<>("Pat", PATIENT1.class), new Pair<>("Pat2", PATIENT2.class), new Pair<>("Pat3", PATIENT3.class), new Pair<>("Point", POINT.class), new Pair<>("Predic", PREDICTING.class), new Pair<>("Re", Re.class), new Pair<>("Region", REGIONS.class), new Pair<>("Sear", SEAR.class), new Pair<>("Symp", SYMPTOMS.class)};
    }
}
