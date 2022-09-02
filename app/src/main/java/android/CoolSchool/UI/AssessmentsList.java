package android.CoolSchool.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.CoolSchool.R;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class AssessmentsList extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assessmentslist);
    }


    public void goToAssessmentDetails(View view) {
        Intent intent = new Intent(AssessmentsList.this, AssessmentDetails.class);
        startActivity(intent);
    }
}