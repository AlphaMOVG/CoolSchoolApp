package android.CoolSchool.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.CoolSchool.Database.Repository;
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

public class TermDetails extends AppCompatActivity {
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
    Spinner editCourseID;
    TextInputEditText editNote;

    int id;
    String name;
    String start;
    String end;
    int courseSpin;
    String note;
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
        editCourseID = findViewById(R.id.courseSpinner);
        editNote = findViewById(R.id.notesTxt);

        /**
         * assigns the keys of the adapter to the variables I created
         * */
        id = getIntent().getIntExtra("id", 0);
        name = getIntent().getStringExtra("name");
        start = getIntent().getStringExtra("start");
        end = getIntent().getStringExtra("end");
        courseSpin = getIntent().getIntExtra("course", 0); //find out how to properly set these when an item is selected
        note = getIntent().getStringExtra("notes");

        /**
         * assigns the keys of the adapter to the text fields.
         * */
        editID.setText(Integer.toString(id));
        editName.setText(name);
        editStart.setText(start);
        editEnd.setText(end);
        editCourseID.setSelection(courseSpin);
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
                new DatePickerDialog(TermDetails.this, myDate, myCalendar.get(Calendar.YEAR),
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
         * building and assigning a calender object to the Edit text field in the app. also ask how to assign th date picker to another edit text field
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
                new DatePickerDialog(TermDetails.this, myDate, myCalendar.get(Calendar.YEAR),
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
        Spinner courseSpinner = (Spinner) findViewById(R.id.courseSpinner);
        ArrayList<Courses> myCourses = new ArrayList<>();
        myCourses.add(new Courses(1, "Red", "10/22/2022", "10/22/2022", "Bob", "512-777-7777", "falcon@falcon.com", 45, "In progress", "note"));
        ArrayAdapter<Courses> courseAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, myCourses);
        courseAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        courseSpinner.setAdapter(courseAdapter);

        courseSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(TermDetails.this,myCourses.get(i).toString(),Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                // another interface callback
            }
        });
    }

    /**
     * This method is apart of the DatePicker set up
     * */
    private void updateLabel(){
        String myFormat = "MM/dd/yy";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        dateText.setText(sdf.format(myCalendar.getTime()));
    }

    public static void selectSpinnerItemByValue(Spinner spnr, long value){
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
                Intent intent = new Intent(TermDetails.this, MyReceiver.class);
                intent.putExtra("key", "message I want to send");
                PendingIntent sender = PendingIntent.getBroadcast(TermDetails.this, MainActivity.numAlert++, intent, 0);
                AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
                alarmManager.set(AlarmManager.RTC_WAKEUP, trigger, sender);
                return true;
            case R.id.delete:

        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * Need to add the date handeling code here
     *    Need to add the save, notes and send code
     *    figure out how to join Courses.
     * */
}