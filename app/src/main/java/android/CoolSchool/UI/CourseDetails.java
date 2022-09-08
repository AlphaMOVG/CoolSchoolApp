package android.CoolSchool.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.CoolSchool.Database.Repository;
import android.CoolSchool.Entity.Assessments;
import android.CoolSchool.R;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class CourseDetails extends AppCompatActivity {

    TextInputEditText dateText;
    DatePickerDialog.OnDateSetListener myDate;
    final Calendar myCalendar = Calendar.getInstance();

    TextInputEditText editID;
    TextInputEditText editName;
    TextInputEditText editStart;
    TextInputEditText editEnd;
    TextInputEditText editCIName;
    TextInputEditText editCIPhone;
    TextInputEditText editCIMail;
    Spinner editAssessmentsSpin;
    Spinner editStatusSpin;
    TextInputEditText editNote;

    int id;
    String name;
    String start;
    String end;
    String cIName;
    String cINumber;
    String cIEM;
    int assessmentID;
    String status;
    String note;
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
        editAssessmentsSpin = findViewById(R.id.assessmentsSpinner);
        editStatusSpin = findViewById(R.id.statusSpinner);
        editNote = findViewById(R.id.notesTxt);

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
        assessmentID = getIntent().getIntExtra("assessid", 0);
        status = getIntent().getStringExtra("status");
        note = getIntent().getStringExtra("notes");

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
        editAssessmentsSpin.setSelection(assessmentID); //find out how to properly set these when an item is selected
       // editStatusSpin.setSelection(status);// find out how to properly set these when an item is selected
        editNote.setText(note);

        repo = new Repository(getApplication());

        /**
         * building and assigning a calender object to the Edit text field in the app. also ask how to assign th date picker to another edit text field
         * */
        dateText = findViewById(R.id.startDatePicker);
        String myFormat = "MM/dd/yy";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        String currentDate = sdf.format(new Date());
        dateText.setText(currentDate);
        dateText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Date date;
                String info = dateText.getText().toString();
                try {
                    myCalendar.setTime(sdf.parse(info));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                new DatePickerDialog(CourseDetails.this, myDate, myCalendar.get(Calendar.YEAR),
                        myCalendar.get(Calendar.MONTH), myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
        myDate = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }
        };

        /**
         * This is where the spinner is populated with information from the added assessments
         * */
        Spinner assessmentSpinner = (Spinner) findViewById(R.id.assessmentsSpinner);
        ArrayList<Assessments> myAssessments = new ArrayList<>();
        myAssessments.add(new Assessments(1, "assessment", "10/22/2022", "Performance", "note"));
        ArrayAdapter<Assessments> assessmentAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, myAssessments);
        assessmentAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        assessmentSpinner.setAdapter(assessmentAdapter);

        assessmentSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(CourseDetails.this,myAssessments.get(i).toString(),Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                // another interface callback
            }

        });


        /**
         * This code sets the array from the value package string file to the spinner in assessment details.
         * */
        Spinner progressSpinner = (Spinner) findViewById(R.id.statusSpinner);
        ArrayAdapter<CharSequence> progressAdapter = ArrayAdapter.createFromResource(this, R.array.course_progress_array, android.R.layout.simple_spinner_item);
        assessmentAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        progressSpinner.setAdapter(progressAdapter);
    }

    /**
     * This method is apart of the DatePicker set up
     * */
    private void updateLabel(){
        String myFormat = "MM/dd/yy";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        dateText.setText(sdf.format(myCalendar.getTime()));
    }

    /**
     * Index selection
     * */
    public static void selectSpinnerItemByValue(Spinner spnr, int value){
        SimpleCursorAdapter adapter = (SimpleCursorAdapter) spnr.getAdapter();
        for(int postition = 0; postition < adapter.getCount(); postition++){
            if(adapter.getItemId(postition) == value){
                spnr.setSelection(postition);
                return;
            }
        }
    }





    /**
     * Need to add the date handeling code here
     * Need to add the save, notes and send code
     * figure out how to join assessments.
     * */
}