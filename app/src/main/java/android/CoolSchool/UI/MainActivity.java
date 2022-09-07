package android.CoolSchool.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.CoolSchool.Database.Repository;
import android.CoolSchool.Entity.Assessments;
import android.CoolSchool.Entity.Courses;
import android.CoolSchool.Entity.Terms;
import android.CoolSchool.R;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    public static int numAlert;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void enterButton(View view) {
        Intent intent = new Intent(MainActivity.this, SelectScreen.class);
        startActivity(intent);
        Repository repo = new Repository(getApplication());

        Assessments assessments = new Assessments(1, "Assessment", "10/22/2022", "Objective","");
        repo.insert(assessments);

        Courses courses = new Courses(1,"Course","10/22/2022", "10/22/2022","Name","512-647-3381","falcon@falcon.com", 78, "Completed","note" );
        repo.insert(courses);

        Terms terms = new Terms(1,"Term","10/22/2022","10/22/2022", 55, "note" );
        repo.insert(terms);
    }

}