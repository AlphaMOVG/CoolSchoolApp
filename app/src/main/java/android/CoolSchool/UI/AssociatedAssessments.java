package android.CoolSchool.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.CoolSchool.Database.Repository;
import android.CoolSchool.Entity.Assessments;
import android.CoolSchool.R;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import java.util.List;

public class AssociatedAssessments extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_associated_assessments);
        RecyclerView recyclerView = findViewById(R.id.associatedAssessmentRecycler);
        Repository repo = new Repository(getApplication());
       // List<Assessments> assessments = repo.getAllAssessments();
        final AssessmentsAdapter adapter = new AssessmentsAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
       // adapter.setAssessments(assessments);
    }

    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_termlist, menu);
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case android.R.id.home:
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}