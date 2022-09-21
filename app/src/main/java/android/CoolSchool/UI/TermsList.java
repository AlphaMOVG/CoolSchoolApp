package android.CoolSchool.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.CoolSchool.Database.Repository;
import android.CoolSchool.Entity.Terms;
import android.CoolSchool.R;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.List;

public class TermsList extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_termslist);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        RecyclerView recyclerView = findViewById(R.id.termRecycler);
        Repository repo = new Repository(getApplication());
        List<Terms> terms = repo.getAllTerms();
        final TermsAdapter adapter = new TermsAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter.setTerms(terms);
    }
        public boolean onCreateOptionsMenu(Menu menu){
            getMenuInflater().inflate(R.menu.refresh_terms_menu, menu);
            return true;
        }
        public boolean onOptionsItemSelected(MenuItem item){
            switch (item.getItemId()){
                case android.R.id.home:
                    this.finish();
                    return true;
                case R.id.refresh:
                    RecyclerView recyclerView = findViewById(R.id.termRecycler);
                    Repository repo = new Repository(getApplication());
                    List<Terms> terms = repo.getAllTerms();
                    final TermsAdapter adapter = new TermsAdapter(this);
                    recyclerView.setAdapter(adapter);
                    recyclerView.setLayoutManager(new LinearLayoutManager(this));
                    adapter.setTerms(terms);
                    return true;
            }
                return super.onOptionsItemSelected(item);
        }

    public void goToTermsDetails(View view) {
        Intent intent = new Intent(TermsList.this, TermDetails.class);
        startActivity(intent);
    }


}
