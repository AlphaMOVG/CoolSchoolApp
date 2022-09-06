package android.CoolSchool.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.CoolSchool.Database.Repository;
import android.CoolSchool.R;
import android.os.Bundle;

import com.google.android.material.textfield.TextInputEditText;

public class TermDetails extends AppCompatActivity {
    TextInputEditText editID;
    TextInputEditText editName;
    TextInputEditText editStart;
    TextInputEditText editEnd;

    int id;
    String name;
    String start;
    String end;
    Repository repo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_term_details);

        /**
         * assigns the variables I created to the IDs of the term details text input fields.
         * */
        editID = findViewById(R.id.termIDTxt);
        editName = findViewById(R.id.termNameTxt);
        editStart = findViewById(R.id.startDatePicker);
        editEnd = findViewById(R.id.endDatePicker);

        /**
         * assigns the keys of the adapter to the variables I created
         * */
        id = getIntent().getIntExtra("id", 0);
        name = getIntent().getStringExtra("name");
        start = getIntent().getStringExtra("start");
        end = getIntent().getStringExtra("end");

        /**
         * assigns the keys of the adapter to the text fields.
         * */
        editID.setText(Integer.toString(id));
        editName.setText(name);
        editStart.setText(start);
        editEnd.setText(end);
        repo = new Repository(getApplication());
    }

    /**
     * Need to add the date handeling code here
     *    Need to add the save, notes and send code
     *    figure out how to join Courses.
     * */
}