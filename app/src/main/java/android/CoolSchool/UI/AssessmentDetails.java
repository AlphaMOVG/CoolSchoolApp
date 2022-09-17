package android.CoolSchool.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.CoolSchool.Database.Repository;
import android.CoolSchool.Entity.Assessments;
import android.CoolSchool.Entity.Courses;
import android.CoolSchool.R;
import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class AssessmentDetails extends AppCompatActivity {
    TextInputEditText startDateText;
    TextInputEditText endDateText;
    DatePickerDialog.OnDateSetListener myDate;
    final Calendar myCalendar = Calendar.getInstance();
    SimpleDateFormat sdf;


    TextInputEditText editAssessmentIDTxt;
    TextInputEditText editAssessmentNameTxt;
    TextInputEditText editAssessmentStartDatePicker;
    TextInputEditText editAssessmentEndDatePicker;
    Spinner editSpinner;
    TextInputEditText editAssessmentNote;
    Spinner editCourseSpinner;

    int id;
    String name;
    String startDate;
    String endDate;
    int type;
    String note;
    Repository repo;
    int course;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assessment_details);

        /**
         * assigns the variables I created to the IDs of the assessments details text input fields.
         * */
        editAssessmentIDTxt = findViewById(R.id.assessmentIDTxt);
        editAssessmentNameTxt = findViewById(R.id.assessmentNameTxt);
        editAssessmentStartDatePicker = findViewById(R.id.StartDatePicker);
        editAssessmentEndDatePicker = findViewById(R.id.endDatePicker);
        editSpinner = findViewById(R.id.typeSpinner);
        editAssessmentNote = findViewById(R.id.noteTxt);
        editCourseSpinner = findViewById(R.id.courseSpinner);



        /**
         * assigns the keys of the adapter to the variables I created
         * */
        id = getIntent().getIntExtra("id", -1);
        name = getIntent().getStringExtra("name");
        startDate = getIntent().getStringExtra("startdate");
        endDate = getIntent().getStringExtra("enddate");
        type = getIntent().getIntExtra("type", -1);
        note = getIntent().getStringExtra("notes");
        course = getIntent().getIntExtra("courseid", -1);


        /**
         * assigns the keys of the adapter to the text fields.
         * */
        editAssessmentIDTxt.setText(Integer.toString(id));
        editAssessmentNameTxt.setText(name);
        editAssessmentStartDatePicker.setText(startDate);
        editAssessmentEndDatePicker.setText(endDate);
        editAssessmentNote.setText(note);
        repo = new Repository(getApplication());


        /**
         * building and assigning a calender object to the Edit text field in the app. need logic to determine modifying or adding an                assessment.
         * */

        startDateText = findViewById(R.id.StartDatePicker);
        String myFormat = "MM/dd/yy";
        sdf = new SimpleDateFormat(myFormat, Locale.US);
        String currentDate = null;
        if(startDate != null){
            currentDate = startDate;
        }
        else{
            currentDate = sdf.format(new Date());
        }
        startDateText.setText(currentDate);
        startDateText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Date date;
                String info = startDateText.getText().toString();
                try {
                    myCalendar.setTime(sdf.parse(info));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                new DatePickerDialog(AssessmentDetails.this, myDate, myCalendar.get(Calendar.YEAR),
                        myCalendar.get(Calendar.MONTH), myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
        myDate = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabelStart();
            }
        };

        /**
         * building and assigning a calender object to the Edit text field in the app. also ask how to set the edit text field to the saved date of the selected item.
         * */
        endDateText = findViewById(R.id.EndDatePicker);
        myFormat = "MM/dd/yy";
        sdf = new SimpleDateFormat(myFormat, Locale.US);
        String currentDateEnd = null;
        if(endDate != null){
            currentDateEnd = endDate;
        }
        else{
            currentDateEnd = sdf.format(new Date());
        }
        endDateText.setText(currentDateEnd);
        endDateText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Date date;
                String info = endDateText.getText().toString();
                try {
                    myCalendar.setTime(sdf.parse(info));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                new DatePickerDialog(AssessmentDetails.this, myDate, myCalendar.get(Calendar.YEAR),
                        myCalendar.get(Calendar.MONTH), myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
        myDate = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabelEnd();
            }
        };

        /**
         * This is where the spinner is populated with information from the added assessments
         * */
        Spinner courseSpinner = (Spinner) findViewById(R.id.courseSpinner);
        ArrayList<Courses> myCourses = new ArrayList<>();
        myCourses.add(new Courses(-1, "None", "", "", "", "", "", -1, 0, ""));
        ArrayAdapter<Courses> courseAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, myCourses);
        courseAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        courseSpinner.setAdapter(courseAdapter);
        selectSpinnerItemByValue(editCourseSpinner, course);

        courseSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(AssessmentDetails.this,myCourses.get(i).toString(),Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                // another interface callback
            }
        });


        /**
         * This code sets the array from the value package string file to the spinner in assessment details.
         * */
        Spinner assessmentSpinner = (Spinner) findViewById(R.id.typeSpinner);
        ArrayAdapter<CharSequence> assessmentAdapter = ArrayAdapter.createFromResource(this, R.array.assessment_types_array,           android.R.layout.simple_spinner_item);
        assessmentAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        assessmentSpinner.setAdapter(assessmentAdapter);
        editSpinner.setSelection(type);


        // This is the end of the onCreate implementation method.
    }

    /**
     * This method is apart of the DatePicker set up
     */
    private void updateLabelStart() {
        String myFormat = "MM/dd/yy";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        startDateText.setText(sdf.format(myCalendar.getTime()));
    }

    /**
     * This method is apart of the DatePicker set up
     */
    private void updateLabelEnd() {
        String myFormat = "MM/dd/yy";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        endDateText.setText(sdf.format(myCalendar.getTime()));
    }


    /**
     * inflates the menu and set items to the menu.
     */
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.assessments_menu, menu);
        return true;
    }

    /**
     * This is the code used to share notes from the share button, notify a user on the day of their assessment, or delete an assessment
     */
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
            case R.id.shareNotes:
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, editAssessmentNote.getText());
                sendIntent.putExtra(Intent.EXTRA_TITLE, "Notes");
                sendIntent.setType("text/plain");
                Intent shareIntent = Intent.createChooser(sendIntent, null);
                startActivity(shareIntent);
                return true;
            case R.id.notify:
                String dateFromScreen = startDateText.getText().toString();
                String endDateFromScreen = endDateText.getText().toString();
                Date myDate = null;
                Date myEndDate = null;
                try {
                    myDate = sdf.parse(dateFromScreen);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                try {
                    myEndDate = sdf.parse(endDateFromScreen);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                Long trigger = myDate.getTime();
                Long secondTrigger = myEndDate.getTime();
                Intent intent = new Intent(AssessmentDetails.this, MyReceiver.class);
                intent.putExtra("key", editAssessmentNameTxt.getText() + " " + " starts today");
                intent.putExtra("key", editAssessmentNameTxt.getText() + " "+ " ends today");
                PendingIntent sender = PendingIntent.getBroadcast(AssessmentDetails.this, MainActivity.numAlert++, intent, PendingIntent.FLAG_IMMUTABLE);
                AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
                alarmManager.set(AlarmManager.RTC_WAKEUP, trigger, sender);
                alarmManager.set(AlarmManager.RTC_WAKEUP, secondTrigger, sender);
                return true;
            case R.id.delete:
                Assessments currentAssessment = null;
                for(Assessments a: repo.getAllAssessments()){
                    if (a.getAssessmentsID() == Integer.parseInt(editAssessmentIDTxt.getText().toString())) currentAssessment = a;
                }

                    repo.delete(currentAssessment);
                    if(currentAssessment != null){
                        Toast.makeText(AssessmentDetails.this, currentAssessment.getAssessmentName() + "was deleted", Toast.LENGTH_SHORT).show();
                    }

                return true;
        }
        return super.onOptionsItemSelected(item);
    }


   /* public void save(View view) {
        Assessments assessments;
        if(id == -1){
            int newID = repo.getAllAssessments().get(repo.getAllAssessments().size() - 1).getAssessmentsID() + 1;
            assessments = new Assessments(newID, editAssessmentNameTxt.getText().toString(), editAssessmentDatePicker.getText().toString());
            repo.insert(assessments);
        } else{
            assessments = new Assessments(id, editAssessmentNameTxt.getText().toString(), editAssessmentDatePicker.getText().toString());
            repo.update(assessments);
        }
    }*/


    public static void selectSpinnerItemByValue(Spinner spnr, int value) {
        SimpleCursorAdapter adapter = (SimpleCursorAdapter) spnr.getAdapter();
        for (int position = 0; position < adapter.getCount(); position++) {
            if (adapter.getItemId(position) == value) {
                spnr.setSelection(position);
                return;
            }
        }
    }

    public void save(View view) {
    }

    /**
     *
     * saving fields when save button is pressed, , refresh button
     * */
}

