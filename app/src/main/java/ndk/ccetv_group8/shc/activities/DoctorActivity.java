package ndk.ccetv_group8.shc.activities;

import android.app.SearchManager;
import android.graphics.Color;
import android.os.Bundle;
import android.text.InputFilter;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.util.Pair;
import androidx.core.view.MenuItemCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import ndk.ccetv_group8.shc.R;
import ndk.ccetv_group8.shc.adaptors.DoctorRecyclerViewAdapter;
import ndk.ccetv_group8.shc.models.DoctorModel;
import ndk.utils_android14.ContextActivity;

public class DoctorActivity extends ContextActivity {

    private RecyclerView recyclerView;
    private Toolbar toolbar;

    String disease = "XYZ";
    String passedDisease;

    private DoctorRecyclerViewAdapter mAdapter;
    private ArrayList<DoctorModel> modelList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor);

        passedDisease = getIntent().getStringExtra("disease");
        if (passedDisease == null) {
            passedDisease = disease;
        }

        TextView textViewDisease = findViewById(R.id.textViewDisease);
        textViewDisease.setText("Disease : " + passedDisease);

        findViews();
        setSupportActionBar(toolbar);
        setAdapter();
    }

    private void findViews() {
        toolbar = findViewById(R.id.toolbar);
        recyclerView = findViewById(R.id.recyclerView);
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
        searchEdit.setHint("Search Doctors");

        InputFilter[] fArray = new InputFilter[2];
        fArray[0] = new InputFilter.LengthFilter(40);
        fArray[1] = (source, start, end, dest, dstart, dend) -> {
            for (int i = start; i < end; i++) {
                if (!Character.isLetterOrDigit(source.charAt(i)))
                    return "";
            }
            return null;
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
                ArrayList<DoctorModel> filterList = new ArrayList<DoctorModel>();
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

//        modelList.add(new AbstractModel("Android", "Hello " + " Android"));
//        modelList.add(new AbstractModel("Beta", "Hello " + " Beta"));
//        modelList.add(new AbstractModel("Cupcake", "Hello " + " Cupcake"));
//        modelList.add(new AbstractModel("Donut", "Hello " + " Donut"));
//        modelList.add(new AbstractModel("Eclair", "Hello " + " Eclair"));
//        modelList.add(new AbstractModel("Froyo", "Hello " + " Froyo"));
//        modelList.add(new AbstractModel("Gingerbread", "Hello " + " Gingerbread"));
//        modelList.add(new AbstractModel("Honeycomb", "Hello " + " Honeycomb"));
//        modelList.add(new AbstractModel("Ice Cream Sandwich", "Hello " + " Ice Cream Sandwich"));
//        modelList.add(new AbstractModel("Jelly Bean", "Hello " + " Jelly Bean"));
//        modelList.add(new AbstractModel("KitKat", "Hello " + " KitKat"));
//        modelList.add(new AbstractModel("Lollipop", "Hello " + " Lollipop"));
//        modelList.add(new AbstractModel("Marshmallow", "Hello " + " Marshmallow"));
//        modelList.add(new AbstractModel("Nougat", "Hello " + " Nougat"));
//        modelList.add(new AbstractModel("Android O", "Hello " + " Android O"));

        modelList.add(new DoctorModel(1, "DoctorModel 1", "DoctorModel 1 Address", "DoctorModel 1 Designation", "DoctorModel 1 Working Hospital", "DoctorModel 1 Certificate ID", "DoctorModel 1 Working Clinic", "9 AM", "4 PM", 500.0));

        modelList.add(new DoctorModel(2, "DoctorModel 2", "DoctorModel 2 Address", "DoctorModel 2 Designation", "DoctorModel 2 Working Hospital", "DoctorModel 2 Certificate ID", "DoctorModel 2 Working Clinic", "11 AM", "4 PM", 500.0));

        modelList.add(new DoctorModel(3, "DoctorModel 3", "DoctorModel 3 Address", "DoctorModel 3 Designation", "DoctorModel 3 Working Hospital", "DoctorModel 3 Certificate ID", "DoctorModel 3 Working Clinic", "2 PM", "4 PM", 500.0));

        mAdapter = new DoctorRecyclerViewAdapter(DoctorActivity.this, modelList, "Doctors");

        recyclerView.setHasFixedSize(true);

        // use a linear layout manager
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(mAdapter);

        mAdapter.SetOnItemClickListener((view, position, model) -> {
            //handle item click events here
//            Toast.makeText(DoctorActivity.this, "Hey " + model.getName(), Toast.LENGTH_SHORT).show();
            ndk.utils_android14.ActivityUtils.start_activity_with_string_extras(activity_context, SlotActivity.class, new Pair[]{new Pair<>("disease", passedDisease), new Pair<>("doctor", model.getName())}, false, 0);
        });

        mAdapter.SetOnHeaderClickListener((view, headerTitle) -> {
            //handle item click events here
        });
    }
}
