package ndk.ccetv_group8.shc;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

import com.cunoraz.tagview.Tag;
import com.cunoraz.tagview.TagView;

import java.util.ArrayList;
import java.util.List;

import cn.refactor.kmpautotextview.KMPAutoComplTextView;
import ndk.ccetv_group8.shc.models.TagClass;
import ndk.utils_android14.ContextActivity;

public class SymptomsActivity extends ContextActivity {

    private static final String SHC_TAG = "SH Consultation";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_symptoms_test);

        List<String> data = new ArrayList<String>();
        data.add("Red roses for wedding");
        data.add("Bouquet with red roses");
        data.add("Single red rose flower");

        TagView tagGroup = findViewById(R.id.tag_group);

        Button buttonAddSymptom = findViewById(R.id.buttonAddSymptom);
        buttonAddSymptom.setEnabled(false);

        Button buttonEvaluateSymptoms = findViewById(R.id.buttonEvaluateSymptom);
        buttonEvaluateSymptoms.setEnabled(false);

        KMPAutoComplTextView complTextView = findViewById(R.id.tvAutoCompl);
        complTextView.setDatas(data);
        complTextView.setOnPopupItemClickListener(charSequence -> {
            Toast.makeText(activity_context, charSequence.toString(), Toast.LENGTH_SHORT).show();
            buttonAddSymptom.setEnabled(true);
        });

        buttonAddSymptom.setOnClickListener(v -> {
            String symptom = complTextView.getText().toString();
            if (!symptom.isEmpty()) {
                Toast.makeText(getApplicationContext(), symptom, Toast.LENGTH_SHORT).show();
                addTag(tagGroup, symptom);
                complTextView.setText("");
                buttonAddSymptom.setEnabled(false);
                checkEvaluateSymptomsButton(buttonEvaluateSymptoms, tagGroup);
            }
        });

        //You can add one tag
//        Tag tag=new Tag("tag");
//        tagGroup.addTag(tag);

//        addTag(tagGroup, "tagName");

//        //You can add multiple tag via ArrayList
//        ArrayList<Tag> tagArrayList = new ArrayList<>();
//        tagArrayList.add(new Tag("TA1"));
//        tagArrayList.add(new Tag("TA2"));
//        tagArrayList.add(new Tag("TA3"));
//        tagGroup.addTags(tagArrayList);
//
//        //Via string array
//        tagGroup.addTags(new String[]{"t1", "t2"});

        //set click listener
//        tagGroup.setOnTagClickListener(new TagView.OnTagClickListener() {
//            @Override
//            public void onTagClick(Tag tag, int position) {
//            }
//        });

        //set delete listener
        tagGroup.setOnTagDeleteListener((view, tag1, position) -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(activity_context);
            builder.setMessage("\"" + tag1.text + "\" will be delete. Are you sure?");
            builder.setPositiveButton("Yes", (dialog, which) -> {
                view.remove(position);
                Toast.makeText(getApplicationContext(), "\"" + tag1.text + "\" deleted", Toast.LENGTH_SHORT).show();
                checkEvaluateSymptomsButton(buttonEvaluateSymptoms, tagGroup);
            });
            builder.setNegativeButton("No", null);
            builder.show();
        });

        //set long click listener
//        tagGroup.setOnTagLongClickListener(new TagView.OnTagLongClickListener() {
//            @Override
//            public void onTagLongClick(Tag tag, int position) {
//            }
//        });
    }

    private void checkEvaluateSymptomsButton(Button buttonEvaluateSymptoms, TagView tagGroup) {
        Log.d(SHC_TAG, "tagGroup Count : " + tagGroup.getChildCount());
        Log.d(SHC_TAG, "tagGroup Count : " + tagGroup.getTags().size());
        buttonEvaluateSymptoms.setEnabled(tagGroup.getChildCount() != 0);
    }

    private void addTag(TagView tagGroup, String tagName) {
        TagClass tagClass = new TagClass(tagName);
        Tag tag = new Tag(tagClass.getName());
        tag.radius = 10f;
        tag.layoutColor = Color.parseColor(tagClass.getColor());
        tag.isDeletable = true;
        tagGroup.addTag(tag);
    }
}
