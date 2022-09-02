package android.CoolSchool.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.CoolSchool.R;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class CoursesList extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_courseslist);
    }

    public void goToCourseDetails(View view) {
        Intent intent = new Intent(CoursesList.this, CourseDetails.class);
        startActivity(intent);
    }
}