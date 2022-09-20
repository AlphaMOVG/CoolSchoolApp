package android.CoolSchool.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.CoolSchool.Database.Repository;
import android.CoolSchool.Entity.Assessments;
import android.CoolSchool.R;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.List;

public class AssociatedAssessments extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_associated_assessments);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        RecyclerView recyclerView = findViewById(R.id.associatedAssessmentRecycler);
        Repository repo = new Repository(getApplication());
        int courseID = getIntent().getIntExtra("id", -1);
        List<Assessments> assessments = repo.getAllAssessmentsByCourseID(courseID);
        final AssessmentsAdapter adapter = new AssessmentsAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter.setAssessments(assessments);
    }

    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.refresh_menu, menu);
        return true;
    }

    /**
     * sets a menu item of refresh and and give functionality to the refresh button.
     * */
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case android.R.id.home:
                this.finish();
                return true;
            case R.id.refresh:
                RecyclerView recyclerView = findViewById(R.id.associatedAssessmentRecycler);
                Repository repo = new Repository(getApplication());
                int courseID = getIntent().getIntExtra("id", -1);
                List<Assessments> assessments = repo.getAllAssessmentsByCourseID(courseID);
                final AssessmentsAdapter adapter = new AssessmentsAdapter(this);
                recyclerView.setAdapter(adapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(this));
                adapter.setAssessments(assessments);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void addAssessment(View view) {
        Intent intent = new Intent(AssociatedAssessments.this, AssessmentDetails.class);
        startActivity(intent);
    }
}