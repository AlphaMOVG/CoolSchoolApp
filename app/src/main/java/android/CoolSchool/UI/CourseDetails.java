package android.CoolSchool.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.CoolSchool.Database.Repository;
import android.CoolSchool.R;
import android.os.Bundle;
import android.widget.RadioGroup;

import com.google.android.material.textfield.TextInputEditText;

public class CourseDetails extends AppCompatActivity {
    TextInputEditText editID;
    TextInputEditText editName;
    TextInputEditText editStart;
    TextInputEditText editEnd;
    TextInputEditText editCIName;
    TextInputEditText editCIPhone;
    TextInputEditText editCIMail;

    int id;
    String name;
    String start;
    String end;
    String cIName;
    String cINumber;
    String cIEM;
    Repository repo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_details);

        /**
         * assigns the variables I created to the IDs of the course details text input fields.
         * */
        editID = findViewById(R.id.courseIDTxt);
        editName = findViewById(R.id.courseNameTxt);
        editStart = findViewById(R.id.startDatePicker);
        editEnd = findViewById(R.id.endDatePicker);
        editCIName = findViewById(R.id.cINameTxt);
        editCIPhone = findViewById(R.id.cIPhoneTxt);
        editCIMail = findViewById(R.id.cIEmailTxt);

        /**
         * assigns the keys of the adapter to the variables I created
         * */
        id = getIntent().getIntExtra("id", 0);
        name = getIntent().getStringExtra("name");
        start = getIntent().getStringExtra("start");
        end = getIntent().getStringExtra("end");
        cIName = getIntent().getStringExtra("ciname");
        cINumber = getIntent().getStringExtra("cinumber");
        cIEM = getIntent().getStringExtra("ciemail");


        /**
         * assigns the keys of the adapter to the text fields.
         * */
        editID.setText(Integer.toString(id));
        editName.setText(name);
        editStart.setText(start);
        editEnd.setText(end);
        editCIName.setText(cIName);
        editCIPhone.setText(cINumber);
        editCIMail.setText(cIEM);


    }

    /**
     * Need to add the date handeling code here
     * Need to add the save, notes and send code
     * figure out how to join assessments.
     * */
}