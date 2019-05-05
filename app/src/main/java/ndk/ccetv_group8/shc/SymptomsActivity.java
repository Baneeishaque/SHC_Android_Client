package ndk.ccetv_group8.shc;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.ScrollView;

import androidx.core.util.Pair;

import com.cunoraz.tagview.Tag;
import com.cunoraz.tagview.TagView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import cn.refactor.kmpautotextview.KMPAutoComplTextView;
import ndk.ccetv_group8.shc.models.TagClass;
import ndk.utils_android14.ActivityUtils;
import ndk.utils_android14.ContextActivity;
import ndk.utils_android16.Alert_Dialog_Utils;
import ndk.utils_android16.Toast_Utils;

public class SymptomsActivity extends ContextActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_symptoms);

        ScrollView scrollView = findViewById(R.id.scrollView);
        ProgressBar progressBar = findViewById(R.id.progressBar);

        //TODO : To file utils - Read Text file to string

        //Reading json file from assets folder
        StringBuilder stringBuilder = new StringBuilder();
        BufferedReader bufferedReader = null;
        try {
            //TODO : Features to string array
            bufferedReader = new BufferedReader(new InputStreamReader(getAssets().open(
                    "features.json")));
            String tempString;
            while ((tempString = bufferedReader.readLine()) != null)
                stringBuilder.append(tempString);
        } catch (IOException e) {
            ErrorUtilsWrapper.displayException(getApplicationContext(), e);
        } finally {
            try {
                if (bufferedReader != null) {
                    //stop reading
                    bufferedReader.close();
                }
            } catch (IOException e) {
                ErrorUtilsWrapper.displayException(getApplicationContext(), e);
            }
        }
        //TODO : from json file json keys to android string array
        String featuresJSON = stringBuilder.toString();

        //TODO : - Read keys of json file
        JSONObject jsonObject;
        List<Object> data = new ArrayList<>();
        try {
            jsonObject = new JSONObject(featuresJSON);
            Iterator<?> keys = jsonObject.keys();
            while (keys.hasNext()) {
                // TODO : To JSON Utils - Read Keys of json string - key further processing interface
                String key = (String) keys.next();
                LogUtilsWrapper.debug("Key : " + key);
                // TODO : To JSON Utils - Read Keys of json string to string list
                // TODO : To JSON Utils - Read Keys of json file to string list
                data.add(key);
            }
        } catch (JSONException e) {
            ErrorUtilsWrapper.displayException(getApplicationContext(), e);
        }

//        List<String> data = new ArrayList<String>();
//        data.add("Red roses for wedding");
//        data.add("Bouquet with red roses");
//        data.add("Single red rose flower");

        TagView tagGroup = findViewById(R.id.tag_group);

        Button buttonAddSymptom = findViewById(R.id.buttonAddSymptom);
        //TODO : To UI Utils - multi enable & multi disable
        buttonAddSymptom.setEnabled(false);

        Button buttonEvaluateSymptoms = findViewById(R.id.buttonEvaluateSymptoms);
        buttonEvaluateSymptoms.setEnabled(false);

        KMPAutoComplTextView editTextSymptom = findViewById(R.id.editTextSymptom);
        editTextSymptom.setDatas(data);

        editTextSymptom.setOnPopupItemClickListener((item, charSequence) -> {
            //TODO : Make Toast Activity
            //TODO : Make Toast Composition Activity
            //TODO : Short Toast
            Toast_Utils.longToast(getApplicationContext(), getResources().getString(R.string.symptom) + charSequence.toString());
            buttonAddSymptom.setEnabled(true);
        });

        buttonAddSymptom.setOnClickListener(v -> {
            String symptom = editTextSymptom.getText().toString();
            if (!symptom.isEmpty()) {
                //TODO : Validate symptom
                Toast_Utils.longToast(getApplicationContext(), getResources().getString(R.string.symptom) + symptom);
//                addTagWithRefreshList(tagGroup,symptom,data,editTextSymptom, buttonAddSymptom, buttonEvaluateSymptoms);
//                addTag(tagGroup, symptom);
                addTagWithEvaluation(tagGroup, symptom, data, editTextSymptom, buttonAddSymptom, buttonEvaluateSymptoms);
            }
        });

        //TODO : To tagview Utils - add tag via name
        //You can add one tag
//        Tag tag=new Tag("tag");
//        tagGroup.addTag(tag);

//        addTag(tagGroup, "tagName");

        //TODO : To tagview Utils - add tag via array list
        //You can add multiple tag via ArrayList
//        ArrayList<Tag> tagArrayList = new ArrayList<>();
//        tagArrayList.add(new Tag("TA1"));
//        tagArrayList.add(new Tag("TA2"));
//        tagArrayList.add(new Tag("TA3"));
//        tagGroup.addTags(tagArrayList);

        //TODO : To tagview Utils - add tag via string array
        //Via string array
//        tagGroup.addTags(new String[]{"t1", "t2"});

        //set click listener
//        tagGroup.setOnTagClickListener(new TagView.OnTagClickListener() {
//            @Override
//            public void onTagClick(Tag tag, int position) {
//            }
//        });

        //set delete listener
        tagGroup.setOnTagDeleteListener((view, tag1, position) -> {

            // TODO : Alert * Utils - Static methods
            // TODO : Alert * Utils - Default No Action Dialogues
            // TODO : Alert * Utils - Cancelable & Un cancelable Dialogues

            new Alert_Dialog_Utils((dialog, which) -> {
//                removeTagWithRefreshList(view, position, tag1, tagGroup, buttonEvaluateSymptoms, data, editTextSymptom);
                removeTagWithEvaluation(view, position, tag1, tagGroup, buttonEvaluateSymptoms, data, editTextSymptom);
            }, (dialog, which) -> {
            }).titled_Yes_No_Dialogue(activity_context, "\"" + tag1.text + getResources().getString(R.string.delete_confirmation), getResources().getString(R.string.caution), true);
        });

        //set long click listener
//        tagGroup.setOnTagLongClickListener(new TagView.OnTagLongClickListener() {
//            @Override
//            public void onTagLongClick(Tag tag, int position) {
//            }
//        });

        buttonEvaluateSymptoms.setOnClickListener(v -> {
            //TODO : To tagview Utils - tags to comma separated string
            StringBuilder symptoms = new StringBuilder();
            List<Tag> symptomTags = tagGroup.getTags();
            for (int i = 0; i < symptomTags.size(); i++) {
                if (i == 0) {
                    symptoms.append(symptomTags.get(i).text);
                } else {
                    symptoms.append(",").append(symptomTags.get(i).text);
                }
            }

            new RESTGETTaskUtilsWrapper().execute(APIUtilsWrapper.getHTTPAPI("" + symptoms), activity_context, progressBar, scrollView, (RESTGETTask.Async_Response) response -> {
                String predictedDisease = StringUtils.removeQuotations(response);
                if (predictedDisease.equals("exception")) {
                    //TODO : Make scenarios for no match & More Symptoms & incorporate them
                    ActivityUtils.start_activity(activity_context, DiseasePredictionFailureNoMatch.class);
                } else {
                    Toast_Utils.longToast(getApplicationContext(), "Disease : " + StringUtils.removeQuotations(response));
                    ActivityUtils.start_activity_with_string_extras(activity_context, DiseasePredictionSuccess.class, new Pair[]{new Pair<>("passedDoctor", predictedDisease)}, false, 0);
                }
            });
        });
    }

    //TODO : To tagview Utils - removeTagWithEvaluation of components
    private void removeTagWithEvaluation(TagView view, int position, Tag tag1, TagView tagGroup, Button buttonEvaluateSymptoms, List<Object> data, KMPAutoComplTextView complTextView) {
        view.remove(position);
        Toast_Utils.longToast(getApplicationContext(), "\"" + tag1.text + getResources().getString(R.string.deleted));
        checkEvaluateSymptomsButton(buttonEvaluateSymptoms, tagGroup);
//        data.add(tag1.text);
//        complTextView.setDatas(data);
    }

    //TODO : To tagview Utils - addTagWithEvaluation of components
    private void addTagWithEvaluation(TagView tagGroup, String symptom, List<Object> data, KMPAutoComplTextView complTextView, Button buttonAddSymptom, Button buttonEvaluateSymptoms) {

        if (!checkTagsForExistence(tagGroup, symptom)) {
            addTag(tagGroup, symptom);
            checkEvaluateSymptomsButton(buttonEvaluateSymptoms, tagGroup);
        }
//        TODO : Bug
//        data.remove(symptom);
        complTextView.setText("");
//        TODO : Bug
//        complTextView.setDatas(data);
        //TODO : Check Editor Events
        buttonAddSymptom.setEnabled(false);
    }

    //TODO : To tagview Utils - checkTagsForExistence of given string
    private boolean checkTagsForExistence(TagView tagGroup, String symptom) {
        List<Tag> symptomTags = tagGroup.getTags();
        for (int i = 0; i < symptomTags.size(); i++) {
            if (symptomTags.get(i).text.equals(symptom)) {
                return true;
            }
        }
        return false;
    }

    //TODO : To tagview Utils - removeTagWith removal & Refresh List of string
    private void removeTagWithRefreshList(TagView view, int position, Tag tag1, TagView tagGroup, Button buttonEvaluateSymptoms, List<Object> data, KMPAutoComplTextView complTextView) {
        view.remove(position);
        Toast_Utils.longToast(getApplicationContext(), "\"" + tag1.text + getResources().getString(R.string.deleted));
        checkEvaluateSymptomsButton(buttonEvaluateSymptoms, tagGroup);
        data.add(tag1.text);
        complTextView.setDatas(data);
    }

    //TODO : To tagview Utils - addTagWith addition & Refresh List of string
    private void addTagWithRefreshList(TagView tagGroup, String symptom, List<Object> data, KMPAutoComplTextView complTextView, Button buttonAddSymptom, Button buttonEvaluateSymptoms) {
        addTag(tagGroup, symptom);
        data.remove(symptom);
//        TODO : Bug
//        complTextView.setText("");
        complTextView.setDatas(data);
        buttonAddSymptom.setEnabled(false);
        checkEvaluateSymptomsButton(buttonEvaluateSymptoms, tagGroup);
    }

    //TODO : To tagview Utils - enableDisable based on tag counts
    private void checkEvaluateSymptomsButton(Button buttonEvaluateSymptoms, TagView tagGroup) {
        LogUtilsWrapper.debug("tagGroup Count : " + tagGroup.getChildCount());
        LogUtilsWrapper.debug("tagGroup Count : " + tagGroup.getTags().size());
        //TODO : tagview Lib - method for tag counts
        buttonEvaluateSymptoms.setEnabled(tagGroup.getChildCount() >= 4);
    }

    //TODO : To tagview Utils - add a tag
    private void addTag(TagView tagGroup, String tagName) {
        TagClass tagClass = new TagClass(tagName);
        Tag tag = new Tag(tagClass.getName());
        tag.radius = 10f;
        tag.layoutColor = Color.parseColor(tagClass.getColor());
        tag.isDeletable = true;
        tagGroup.addTag(tag);
    }
}
