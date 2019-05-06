package ndk.ccetv_group8.shc.activities;

import android.app.SearchManager;
import android.graphics.Color;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.Spanned;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.MenuItemCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import ndk.ccetv_group8.shc.R;
import ndk.ccetv_group8.shc.adaptors.AppointmentRecyclerViewAdapter;
import ndk.ccetv_group8.shc.models.AppointmentModel;
import ndk.utils_android14.ContextActivity;

public class AppointmentsActivity extends ContextActivity {

    private RecyclerView recyclerView;
    private Toolbar toolbar;

    private AppointmentRecyclerViewAdapter mAdapter;

    private ArrayList<AppointmentModel> modelList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointments);

//        Spinner spinnerDoctor = findViewById(R.id.spinnerDoctor);
//        ArrayAdapter<String> spinner_adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, Arrays.asList(new String[]{"DoctorModel 1", "DoctorModel 2", "DoctorModel 3"}));
//        spinner_adapter.setDropDownViewResource(androidx.appcompat.R.layout.support_simple_spinner_dropdown_item);
//        spinnerDoctor.setAdapter(spinner_adapter);

//        if (getIntent().getExtras() != null && getIntent().getIntExtra("doctorPosition", 0) != 0) {
//            spinnerDoctor.setSelection(getIntent().getIntExtra("doctorPosition", 0));
//        }
//        spinnerDoctor.setOnItemClickListener((parent, view, position, id) -> ndk.utils_android14.ActivityUtils.start_activity_with_integer_extras_and_finish(activity_context, AppointmentsActivity.class, new Pair[]{new Pair<>("doctorPosition", spinnerDoctor.getSelectedItemPosition())}));

        findViews();
        setSupportActionBar(toolbar);
        setAdapter();
    }

    private void findViews() {
        toolbar = findViewById(R.id.toolbar);
        recyclerView = findViewById(R.id.recycler_view);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);

        getMenuInflater().inflate(R.menu.menu_search, menu);

        // Retrieve the SearchView and plug it into SearchManager
        final SearchView searchView = (SearchView) MenuItemCompat
                .getActionView(menu.findItem(R.id.action_search));

        SearchManager searchManager = (SearchManager) this.getSystemService(SEARCH_SERVICE);
        searchView.setSearchableInfo(searchManager.getSearchableInfo(this.getComponentName()));

        //changing editText color
        EditText searchEdit = searchView.findViewById(androidx.appcompat.R.id.search_src_text);
        searchEdit.setTextColor(Color.WHITE);
        searchEdit.setHintTextColor(Color.WHITE);
        searchEdit.setBackgroundColor(Color.TRANSPARENT);
        searchEdit.setHint("Search Appointments");

        InputFilter[] fArray = new InputFilter[2];
        fArray[0] = new InputFilter.LengthFilter(40);
        fArray[1] = new InputFilter() {
            @Override
            public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {

                for (int i = start; i < end; i++) {

                    if (!Character.isLetterOrDigit(source.charAt(i)))
                        return "";
                }
                return null;
            }
        };
        searchEdit.setFilters(fArray);
        View v = searchView.findViewById(androidx.appcompat.R.id.search_plate);
        v.setBackgroundColor(Color.TRANSPARENT);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                ArrayList<AppointmentModel> filterList = new ArrayList<AppointmentModel>();
                if (s.length() > 0) {
                    for (int i = 0; i < modelList.size(); i++) {
                        if (modelList.get(i).getName().toLowerCase().contains(s.toLowerCase())) {
                            filterList.add(modelList.get(i));
                            mAdapter.updateList(filterList);
                        }
                    }
                } else {
                    mAdapter.updateList(modelList);
                }
                return false;
            }
        });
        return true;
    }

    private void setAdapter() {

        modelList.add(new AppointmentModel("Patient 1", "Patient 1 Address", "9999999999", "2 PM to 3 PM", "X"));
        modelList.add(new AppointmentModel("Patient 2", "Patient 2 Address", "9999999999", "3 PM to 4 PM", "Y"));
        modelList.add(new AppointmentModel("Patient 3", "Patient 3 Address", "9999999999", "4 PM to 5 PM", "Z"));

        mAdapter = new AppointmentRecyclerViewAdapter(AppointmentsActivity.this, modelList);
        recyclerView.setHasFixedSize(true);

        // use a linear layout manager
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(mAdapter);

        mAdapter.SetOnItemClickListener((view, position, model) -> {
            //handle item click events here
            Toast.makeText(activity_context, model.getName() + ", " + model.getAddress(), Toast.LENGTH_SHORT).show();
        });
    }
}