package ndk.ccetv_group8.shc.activities;

import android.app.SearchManager;
import android.graphics.Color;
import android.os.Bundle;
import android.text.InputFilter;
import android.view.Menu;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
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
import ndk.ccetv_group8.shc.adaptors.ConsultationSlotRecyclerViewAdapter;
import ndk.ccetv_group8.shc.models.ConsultationSlotModel;
import ndk.ccetv_group8.shc.to_utils.ButtonUtils;
import ndk.utils_android14.ContextActivity;

public class SlotActivity extends ContextActivity {

    String disease = "XYZ";
    String passedDisease;
    String doctor = "XYZ";
    String passedDoctor;

    TextView textViewSelectedConsultationSlot;
    private RecyclerView recyclerView;
    private Toolbar toolbar;

    private ConsultationSlotRecyclerViewAdapter mAdapter;
    private ArrayList<ConsultationSlotModel> modelList = new ArrayList<>();

    String selectedSlot;
    Button buttonSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultation_slot);

        textViewSelectedConsultationSlot = findViewById(R.id.textViewSelectedConsultationSlot);

        passedDisease = getIntent().getStringExtra("disease");
        if (passedDisease == null) {
            passedDisease = disease;
        }

        passedDoctor = getIntent().getStringExtra("doctor");
        if (passedDoctor == null) {
            passedDoctor = doctor;
        }

        TextView textViewDisease = findViewById(R.id.textViewDisease);
        textViewDisease.setText("Disease : " + passedDisease);

        TextView textViewDoctor = findViewById(R.id.textViewDoctor);
        textViewDoctor.setText("DoctorModel : " + passedDoctor);

        findViews();
        setSupportActionBar(toolbar);
        setAdapter();

        buttonSubmit = findViewById(R.id.buttonSubmit);
        buttonSubmit.setEnabled(false);

        Button buttonDoctors = findViewById(R.id.buttonDoctors);

        buttonDoctors.setOnClickListener(ButtonUtils.getBackButtonEvent(this));

        buttonSubmit.setOnClickListener(ButtonUtils.getButtonEvent(() -> ndk.utils_android14.ActivityUtils.start_activity_with_string_extras(activity_context, SlotConfirmationActivity.class, new Pair[]{new Pair<>("disease", passedDisease), new Pair<>("doctor", passedDoctor), new Pair<>("slot", selectedSlot)}, false, 0)));
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
        searchEdit.setInputType(EditorInfo.TYPE_CLASS_NUMBER);
        searchEdit.setHintTextColor(Color.WHITE);
        searchEdit.setBackgroundColor(Color.TRANSPARENT);
        searchEdit.setHint("Search Slots");

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
                ArrayList<ConsultationSlotModel> filterList = new ArrayList<ConsultationSlotModel>();
                if (s.length() > 0) {
                    for (int i = 0; i < modelList.size(); i++) {
                        if (String.valueOf(modelList.get(i).getSlotStart()).toLowerCase().contains(s.toLowerCase()) || String.valueOf(modelList.get(i).getSlotEnd()).toLowerCase().contains(s.toLowerCase())) {
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

//        modelList.add(new DoctorModel(1, "DoctorModel", "Address", "Designation", "Working Hospital", "Certificate ID", "Working Clinic", new Time(0), new Time(0), 500.0));

        modelList.add(new ConsultationSlotModel("9 AM", "10 AM"));
        modelList.add(new ConsultationSlotModel("11 AM", "12 PM"));
        modelList.add(new ConsultationSlotModel("3 PM", "4 PM"));
        mAdapter = new ConsultationSlotRecyclerViewAdapter(activity_context, modelList, "Time Slots");

        recyclerView.setHasFixedSize(true);

        // use a linear layout manager
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(mAdapter);

        mAdapter.SetOnItemClickListener((view, position, model) -> {
            //handle item click events here
//            Toast.makeText(SlotActivity.this, "Hey " + model.getSlotStart(), Toast.LENGTH_SHORT).show();
            textViewSelectedConsultationSlot.setText("You are selected : " + model.getSlotStart() + " to " + model.getSlotEnd());
            String textViewSelectedConsultationSlotData = textViewSelectedConsultationSlot.getText().toString();
            selectedSlot = textViewSelectedConsultationSlotData.substring(textViewSelectedConsultationSlotData.lastIndexOf(":") + 2);
            buttonSubmit.setEnabled(true);
        });

        mAdapter.SetOnHeaderClickListener((view, headerTitle) -> {
            //handle item click events here
        });
    }
}
