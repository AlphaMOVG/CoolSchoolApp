package android.CoolSchool.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.CoolSchool.Database.Repository;
import android.CoolSchool.Entity.Assessments;
import android.CoolSchool.R;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
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
    Repository repo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assessment_details);
        editAssessmentIDTxt = findViewById(R.id.assessmentIDTxt);
        editAssessmentNameTxt = findViewById(R.id.assessmentNameTxt);
        editAssessmentDatePicker = findViewById(R.id.assessmentDatePicker);
        editRadioBtnGroup = findViewById(R.id.radioBtnGroup);
        id = getIntent().getIntExtra("id", 0);
        name = getIntent().getStringExtra("name");
        date = getIntent().getStringExtra("date");
        type = getIntent().getStringExtra("type");
        editAssessmentIDTxt.setText(Integer.toString(id));
        editAssessmentNameTxt.setText(name);
        editAssessmentDatePicker.setText(date);
        //editRadioBtnGroup.setOnCheckedChangeListener(CompoundButton.setOnCheckedChangeListener());


    }

    public void saveButton(View view) {
        Assessments assessments;
        if(id == -1){
            int newID = repo.getAllAssessments().get(repo.getAllAssessments().size() - 1).getAssessmentsID() + 1;
            assessments = new Assessments(newID, editAssessmentNameTxt.getText().toString(), editAssessmentDatePicker.getText().toString(), editRadioBtnGroup);
            repo.insert(assessments);
        } else{
            assessments = new Assessments(id, editAssessmentNameTxt.getText().toString(), editAssessmentDatePicker.getText().toString(), editRadioBtnGroup);
            repo.update(assessments);
        }
    }

    /**
     * Need to add the date handeling code here
     * */
}