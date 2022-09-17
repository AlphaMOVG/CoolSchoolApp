package android.CoolSchool.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.CoolSchool.Database.Repository;
import android.CoolSchool.Entity.Courses;
import android.CoolSchool.R;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import java.util.List;

public class AssociatedCourses extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_associated_courses);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        RecyclerView recyclerView = findViewById(R.id.associatedCourseRecycler);
        Repository repo = new Repository(getApplication());
      //  List<Courses> courses = repo.getAllCourses();
        final CoursesAdapter adapter = new CoursesAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
       // adapter.setCourses(courses);
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
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}