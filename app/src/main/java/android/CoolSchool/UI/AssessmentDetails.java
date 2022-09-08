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
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.RadioGroup;
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
    TextInputEditText dateText;
    DatePickerDialog.OnDateSetListener myDate;
    final Calendar myCalendar = Calendar.getInstance();
    SimpleDateFormat sdf;


    TextInputEditText editAssessmentIDTxt;
    TextInputEditText editAssessmentNameTxt;
    TextInputEditText editAssessmentDatePicker;
    Spinner editSpinner;
    TextInputEditText editAssessmentNote;

    int id;
    String name;
    String date;
    String type;
    String note;
    Repository repo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assessment_details);

        /**
         * assigns the variables I created to the IDs of the assessments details text input fields.
         * */
        editAssessmentIDTxt = findViewById(R.id.assessmentIDTxt);
        editAssessmentNameTxt = findViewById(R.id.assessmentNameTxt);
        editAssessmentDatePicker = findViewById(R.id.assessmentDatePicker);
        editSpinner = findViewById(R.id.typeSpinner);
        editAssessmentNote = findViewById(R.id.noteTxt);


        /**
         * assigns the keys of the adapter to the variables I created
         * */
        id = getIntent().getIntExtra("id", 0);
        name = getIntent().getStringExtra("name");
        date = getIntent().getStringExtra("date");
        type = getIntent().getStringExtra("type");
        note = getIntent().getStringExtra("notes");


        /**
         * assigns the keys of the adapter to the text fields.
         * */
        editAssessmentIDTxt.setText(Integer.toString(id));
        editAssessmentNameTxt.setText(name);
        editAssessmentDatePicker.setText(date);
       // editSpinner.setSelection(selectSpinnerItemByValue(typeSpinner, Integer.parseInt(type))); //find out how to properly set these when an item is selected
        editAssessmentNote.setText(note);

        repo = new Repository(getApplication());

        /**
         * building and assigning a calender object to the Edit text field in the app.
         * */
        dateText = findViewById(R.id.assessmentDatePicker);
        String myFormat = "MM/dd/yy";
        sdf = new SimpleDateFormat(myFormat, Locale.US);
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
         * This code sets the array from the value package string file to the spinner in assessment details.
         * */
        Spinner assessmentSpinner = (Spinner) findViewById(R.id.typeSpinner);
        ArrayAdapter<CharSequence> assessmentAdapter = ArrayAdapter.createFromResource(this, R.array.assessment_types_array, android.R.layout.simple_spinner_item);
        assessmentAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        assessmentSpinner.setAdapter(assessmentAdapter);

        // This is the end of the onCreate implementation method.
    }

    /**
     * This method is apart of the DatePicker set up
     */
    private void updateLabelStart() {
        String myFormat = "MM/dd/yy";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        dateText.setText(sdf.format(myCalendar.getTime()));
    }


    /**
     * inflates the menu and set items to the menu.
     */
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.assessment_menu, menu);
        return true;
    }

    /**
     * This is the code used to share notes from the share button
     */
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
            case R.id.shareNotes:
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, "Text from note field"); // how do i add notes here? DON'T FORGET TO ASK!
                sendIntent.putExtra(Intent.EXTRA_TITLE, "Notes");
                sendIntent.setType("text/plain");
                Intent shareIntent = Intent.createChooser(sendIntent, null);
                startActivity(shareIntent);
                return true;
            case R.id.notify:
                String dateFromScreen = dateText.getText().toString();
                Date myDate = null;
                try {
                    myDate = sdf.parse(dateFromScreen);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                Long trigger = myDate.getTime();
                Intent intent = new Intent(AssessmentDetails.this, MyReceiver.class);
                intent.putExtra("key", "message I want to send");
                PendingIntent sender = PendingIntent.getBroadcast(AssessmentDetails.this, MainActivity.numAlert++, intent, 0);
                AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
                alarmManager.set(AlarmManager.RTC_WAKEUP, trigger, sender);
                return true;
            case R.id.delete:
               /* for(Assessments a: repo.getAllAssessments()){
                    if (Courses.getAssessmentID() == editAssessmentIDTxt) currentAssessment = prod;
                }
                numAssessments = 0;
                for(Courses c: repo.getAllCourses()){
                    if(c.getAssessmentID() == editAssessmentIDTxt) ++numAssessments;
                }
                if(numAssessments == 0){
                    repo.delete(currentAssessment);
                    Toast.makeText(AssessmentsList.this, currentAssessment.getAssessmentName() + "was deleted", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(AssessmentsList.this, "Can't delete an Assessment with an associated course.", Toast.LENGTH_SHORT).show();
                }

                return true;*/
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
     * Need to add the date handeling code here
     * saving fields when save button is pressed, fix the spinners that need to be set, add delete functionality, refresh button, end date picker
     * */
}

