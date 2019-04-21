package ndk.ccetv_group8.shc;

//TODO : Full screen splash

public class SplashActivity extends ndk.utils_android14.SplashActivity {

    @Override
    protected Class configureNextClass() {
//        return LaunchersActivity.class;
        return SymptomsActivity.class;
    }

}
