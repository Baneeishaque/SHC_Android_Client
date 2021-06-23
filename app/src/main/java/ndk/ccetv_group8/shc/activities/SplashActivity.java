package ndk.ccetv_group8.shc.activities;

public class SplashActivity extends ndk.utils_android14.SplashActivity {

    @Override
    protected Class configureNextClass() {

//        //TODO : To First run Utils - take parameters, first run class, skipped class, logged class, first run actions, skipped actions, logged actions & their composite functions
//        //TODO : Intro Slides
//        SharedPreference_Utils.First_Run_Actions first_run_actions = new SharedPreference_Utils.First_Run_Actions() {
//            @Override
//            public void on_first_run() {
//
//            }
//        };
//
//        if (SharedPreference_Utils.is_first_run(this, ApplicationConstants.APPLICATION_NAME, first_run_actions)) {
//            return IntroductionSlide1.class;
//        } else {
//            SharedPreferences settings = getSharedPreferences(ApplicationConstants.APPLICATION_NAME, MODE_PRIVATE);
//            if (settings.getString("is_skipped_login", String.valueOf(false)).equals(String.valueOf(true))) {
//                //TODO : Skipped Actions
//                return ActivitySkippedFurther.class;
//            } else if (settings.getString("is_logged", String.valueOf(false)).equals(String.valueOf(true))) {
//                //TODO : Logged in Actions
//                return ActivityLoggedInFurther.class;
//            }
//            return ActivityLogin.class;
//        }

        return SymptomsActivity.class;
    }

}
