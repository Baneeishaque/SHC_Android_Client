package ndk.ccetv_group8.shc;

import androidx.core.util.Pair;

public class DoctorHomeActivity extends ndk.utils_android17.LaunchersActivity {
    @Override
    protected Pair[] configureLaunchers() {
        return new Pair[]{new Pair<>("Leaves", AddleaveActivity.class), new Pair<>("AppointmentsActivity", AppointmentlstActivity.class), new Pair<>("Change Credentials", D4.class)};
    }
}
