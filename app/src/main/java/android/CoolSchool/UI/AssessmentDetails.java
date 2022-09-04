package android.CoolSchool.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.CoolSchool.R;
import android.os.Bundle;
import android.widget.RadioGroup;

import com.google.android.material.textfield.TextInputEditText;

public class AssessmentDetails extends AppCompatActivity {
    TextInputEditText editAssessmentIDTxt;
    TextInputEditText editAssessmentNameTxt;
    TextInputEditText editAssessmentDatePicker;
    RadioGroup editRadioBtnGroup;
    int id;
    String name;
    String date;
    String type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assessment_details);
        editAssessmentIDTxt = findViewById(R.id.assessmentIDTxt);
        editAssessmentNameTxt = findViewById(R.id.assessmentNameTxt);
        editAssessmentDatePicker = findViewById(R.id.assessmentDatePicker);
        editRadioBtnGroup = findViewById(R.id.radioBtnGroup);
        id = getIntent().getIntExtra("id", -1);
        name = getIntent().getStringExtra("name");
        date = getIntent().getStringExtra("date");
        type = getIntent().getStringExtra("type");
        editAssessmentIDTxt.setText(Integer.toString(id));
        editAssessmentNameTxt.setText(name);
        editAssessmentDatePicker.setText(date);
        //editRadioBtnGroup.set;


    }

    /**
     * Need to add the date handeling code here
     * */
}