package android.CoolSchool.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.CoolSchool.R;
import android.os.Bundle;
import android.widget.RadioGroup;

import com.google.android.material.textfield.TextInputEditText;

public class AssessmentDetails extends AppCompatActivity {
    TextInputEditText assessmentIDTxt;
    TextInputEditText assessmentNameTxt;
    TextInputEditText assessmentDatePicker;
    RadioGroup radioBtnGroup;
    int id;
    String name;
    String date;
    String type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assessment_details);
        assessmentIDTxt = findViewById(R.id.assessmentIDTxt);
        assessmentNameTxt = findViewById(R.id.assessmentNameTxt);
        assessmentDatePicker = findViewById(R.id.assessmentDatePicker);
        radioBtnGroup = findViewById(R.id.radioBtnGroup);
        id = getIntent().getIntExtra("id", -1);
        name = getIntent().getStringExtra("name");
        date = getIntent().getStringExtra("date");
        type = getIntent().getStringExtra("type");
    }

    /**
     * Need to add the date handeling code here
     * */
}