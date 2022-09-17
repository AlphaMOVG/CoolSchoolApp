package android.CoolSchool.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.CoolSchool.Database.Repository;
import android.CoolSchool.Entity.Assessments;
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

public class CourseDetails extends AppCompatActivity {

    TextInputEditText dateText;
    DatePickerDialog.OnDateSetListener myDate;
    final Calendar myCalendar = Calendar.getInstance();
    SimpleDateFormat sdf;
    String myFormat;
    String currentDate;


    TextInputEditText editID;
    TextInputEditText editName;
    TextInputEditText editStart;
    TextInputEditText editEnd;
    TextInputEditText editCIName;
    TextInputEditText editCIPhone;
    TextInputEditText editCIMail;
    Spinner editTermsSpin;
    Spinner editStatusSpin;
    TextInputEditText editNote;

    int id;
    String name;
    String start;
    String end;
    String cIName;
    String cINumber;
    String cIEM;
    int termsID;
    int status;
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
        editTermsSpin = findViewById(R.id.termsSpinner);
        editStatusSpin = findViewById(R.id.statusSpinner);
        editNote = findViewById(R.id.notesTxt);

        /**
         * assigns the keys of the adapter to the variables I created
         * */
        id = getIntent().getIntExtra("id", -1);
        name = getIntent().getStringExtra("name");
        start = getIntent().getStringExtra("start");
        end = getIntent().getStringExtra("end");
        cIName = getIntent().getStringExtra("ciname");
        cINumber = getIntent().getStringExtra("cinumber");
        cIEM = getIntent().getStringExtra("ciemail");
        termsID = getIntent().getIntExtra("termid", -1);
        status = getIntent().getIntExtra("status", -1);
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
        //find out how to properly set these when an item is selected
        editNote.setText(note);

        repo = new Repository(getApplication());

        /**
         * building and assigning a calender object to the Edit text field in the app. also ask how to assign th date picker to another edit text field
         * */
        dateText = findViewById(R.id.startDatePicker);
        myFormat = "MM/dd/yy";
        sdf = new SimpleDateFormat(myFormat, Locale.US);
        currentDate = sdf.format(new Date());
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
         * building and assigning a calender object to the Edit text field in the app. also ask how to set the edit text field to the saved date of the selected item.
         * */
        dateText = findViewById(R.id.endDatePicker);
        myFormat = "MM/dd/yy";
        sdf = new SimpleDateFormat(myFormat, Locale.US);
        currentDate = sdf.format(new Date());
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
        Spinner assessmentSpinner = (Spinner) findViewById(R.id.termsSpinner);
        ArrayList<Assessments> myAssessments = new ArrayList<>();
        myAssessments.add(new Assessments(-1, "None", "", "", 1, "", 1));
        ArrayAdapter<Assessments> assessmentAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, myAssessments);
        assessmentAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        assessmentSpinner.setAdapter(assessmentAdapter);
        selectSpinnerItemByValue(editTermsSpin, termsID);


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
        editStatusSpin.setSelection(status);



        // this is the end of the onCreate Method
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
     * inflates the menu and set items to the menu.
     */
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.courses_menu, menu);
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
                sendIntent.putExtra(Intent.EXTRA_TEXT, editNote.getText()); // how do i add notes here? DON'T FORGET TO ASK!
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
                Intent intent = new Intent(CourseDetails.this, MyReceiver.class);
                intent.putExtra("key", "message I want to send");
                PendingIntent sender = PendingIntent.getBroadcast(CourseDetails.this, MainActivity.numAlert++, intent, PendingIntent.FLAG_IMMUTABLE);
                AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
                alarmManager.set(AlarmManager.RTC_WAKEUP, trigger, sender);
                return true;
            case R.id.delete:



        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * Need to add the date handeling code here
     * Need to add the save, notes and send code
     * figure out how to join assessments.
     * */
}