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
    TextInputEditText startDateText;
    TextInputEditText endDateText;
    DatePickerDialog.OnDateSetListener myDate;
    final Calendar myCalendar = Calendar.getInstance();
    SimpleDateFormat sdf;
    String myFormat;
    String currentDate;


    TextInputEditText editID;
    TextInputEditText editName;
    TextInputEditText editStart;
    TextInputEditText editEnd;
    TextInputEditText editNote;

    int id;
    String name;
    String start;
    String end;
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
        editNote = findViewById(R.id.notesTxt);

        /**
         * assigns the keys of the adapter to the variables I created
         * */
        id = getIntent().getIntExtra("id", 0);
        name = getIntent().getStringExtra("name");
        start = getIntent().getStringExtra("start");
        end = getIntent().getStringExtra("end");
        note = getIntent().getStringExtra("notes");

        /**
         * assigns the keys of the adapter to the text fields.
         * */
        editID.setText(Integer.toString(id));
        editName.setText(name);
        editStart.setText(start);
        editEnd.setText(end);

        editNote.setText(note);

        repo = new Repository(getApplication());

        /**
         * building and assigning a calender object to the Edit text field in the app. also ask how to set the edit text field to the saved date of the selected item.
         * */
        startDateText = findViewById(R.id.StartDatePicker);
        String myFormat = "MM/dd/yy";
        sdf = new SimpleDateFormat(myFormat, Locale.US);
        String currentDate = null;
        if(start != null){
            currentDate = start;
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
        if(end != null){
            currentDateEnd = end;
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
                updateLabelEnd();
            }
        };

// this is the end of the onCreate Method.
    }

    /**
     * This method is apart of the DatePicker set up
     * */
    private void updateLabelStart(){
        String myFormat = "MM/dd/yy";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        startDateText.setText(sdf.format(myCalendar.getTime()));
    }

    /**
     * This method is apart of the DatePicker set up
     * */
    private void updateLabelEnd(){
        String myFormat = "MM/dd/yy";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        endDateText.setText(sdf.format(myCalendar.getTime()));
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
                Intent intent = new Intent(TermDetails.this, MyReceiver.class);
                intent.putExtra("key", editName.getText() + " " + " starts today");
                intent.putExtra("key", editName.getText() + " "+ " ends today");
                PendingIntent sender = PendingIntent.getBroadcast(TermDetails.this, MainActivity.numAlert++, intent, PendingIntent.FLAG_IMMUTABLE);
                AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
                alarmManager.set(AlarmManager.RTC_WAKEUP, trigger, sender);
                alarmManager.set(AlarmManager.RTC_WAKEUP, secondTrigger, sender);
                return true;
            case R.id.delete:

        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * onClick action for the associated courses button
     * */
    public void associatedCourses(View view) {
        Intent intent = new Intent(TermDetails.this, AssociatedCourses.class);
        startActivity(intent);
    }

    /**
     * Need to add the date handeling code here
     *    Need to add the save, notes and send code
     *    figure out how to join Courses.
     * */
}