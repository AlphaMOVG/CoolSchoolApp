package android.CoolSchool.UI;

import androidx.appcompat.app.AppCompatActivity;


import android.CoolSchool.R;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class SelectScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_screen);
    }

    public void termsButton(View view) {
        Intent intent = new Intent(SelectScreen.this, TermsList.class);
        startActivity(intent);

    }

    public void coursesButton(View view) {
        Intent intent = new Intent(SelectScreen.this, CoursesList.class);
        startActivity(intent);
    }

    public void assessmentsButton(View view) {
        Intent intent = new Intent(SelectScreen.this, AssessmentsList.class);
        startActivity(intent);
    }
}