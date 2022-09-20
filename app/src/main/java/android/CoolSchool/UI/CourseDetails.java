package android.CoolSchool.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.CoolSchool.Database.Repository;
import android.CoolSchool.Entity.Assessments;
import android.CoolSchool.Entity.Courses;
import android.CoolSchool.Entity.Terms;
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

    TextInputEditText startDateText;
    TextInputEditText endDateText;
    DatePickerDialog.OnDateSetListener myDate;
    final Calendar myCalendar = Calendar.getInstance();
    SimpleDateFormat sdf;

    DatePickerDialog.OnDateSetListener myDateEnd;
    final Calendar myCalendarEnd = Calendar.getInstance();
    SimpleDateFormat sdfEnd;

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

        editCIName.setText(cIName);
        editCIPhone.setText(cINumber);
        editCIMail.setText(cIEM);
        editNote.setText(note);

        repo = new Repository(getApplication());

        /**
         * building and assigning a calender object to the Edit text field in the app. also ask how to assign the date picker to another edit text field
         * */
        startDateText = findViewById(R.id.startDatePicker);
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
                updateLabelStart();
            }
        };


        /**
         * building and assigning a calender object to the Edit text field in the app. also ask how to set the edit text field to the saved date of the selected item.
         * */
        endDateText = findViewById(R.id.endDatePicker);
        myFormat = "MM/dd/yy";
        sdfEnd = new SimpleDateFormat(myFormat, Locale.US);
        String currentDateEnd = null;
        if(end != null){
            currentDateEnd = end;
        }
        else{
            currentDateEnd = sdfEnd.format(new Date());
        }
        endDateText.setText(currentDateEnd);
        endDateText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Date date;
                String info = endDateText.getText().toString();
                try {
                    myCalendarEnd.setTime(sdfEnd.parse(info));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                new DatePickerDialog(CourseDetails.this, myDateEnd, myCalendarEnd.get(Calendar.YEAR),
                        myCalendarEnd.get(Calendar.MONTH), myCalendarEnd.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
        myDateEnd = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {
                myCalendarEnd.set(Calendar.YEAR, year);
                myCalendarEnd.set(Calendar.MONTH, monthOfYear);
                myCalendarEnd.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabelEnd();
            }
        };

        /**
         * This is where the spinner is populated with information from the added assessments
         * */
        Spinner termSpinner = (Spinner) findViewById(R.id.termsSpinner);
        ArrayList<Terms> myTerms = new ArrayList<>();
        myTerms.addAll(repo.getAllTerms());
        ArrayAdapter<Terms> termAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, myTerms);
        termAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        editTermsSpin.setAdapter(termAdapter);
        selectSpinnerItemByValue(editTermsSpin, -1);
        for(int i  = 0 ; i < myTerms.size(); i++) {
            if (myTerms.get(i).getTermsID() == termsID) editTermsSpin.setSelection(i);
        }


        editTermsSpin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(CourseDetails.this,myTerms.get(i).toString(),Toast.LENGTH_LONG).show();
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
        termAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        progressSpinner.setAdapter(progressAdapter);
        editStatusSpin.setSelection(status);



        // this is the end of the onCreate Method
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
    private void updateLabelEnd() {
        String myFormatEnd = "MM/dd/yy";
        SimpleDateFormat sdfEnd = new SimpleDateFormat(myFormatEnd, Locale.US);
        endDateText.setText(sdfEnd.format(myCalendarEnd.getTime()));
    }

    /**
     * Index selection
     * */
    public static void selectSpinnerItemByValue(Spinner spnr, int value){
        ArrayAdapter adapter = (ArrayAdapter) spnr.getAdapter();
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
                sendIntent.putExtra(Intent.EXTRA_TEXT, editNote.getText().toString());
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
                    myEndDate = sdfEnd.parse(endDateFromScreen);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                Long trigger = myDate.getTime();
                Long secondTrigger = myEndDate.getTime();
                Intent intent = new Intent(CourseDetails.this, MyReceiver.class);
                intent.putExtra("key", editName.getText() + " " + " starts today");
                intent.putExtra("key", editName.getText() + " "+ " ends today");
                PendingIntent sender = PendingIntent.getBroadcast(CourseDetails.this, MainActivity.numAlert++, intent, PendingIntent.FLAG_IMMUTABLE);
                PendingIntent senderEnd = PendingIntent.getBroadcast(CourseDetails.this, MainActivity.numAlert++, intent, PendingIntent.FLAG_IMMUTABLE); //
                AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
                AlarmManager alarmManagerEnd = (AlarmManager) getSystemService(Context.ALARM_SERVICE); //
                alarmManager.set(AlarmManager.RTC_WAKEUP, trigger, sender);
                alarmManagerEnd.set(AlarmManager.RTC_WAKEUP, secondTrigger, senderEnd);
                Toast.makeText(CourseDetails.this, "Alarm notifications for" + " " +  editName.getText() + " " + "have been set.", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.delete:

                Courses currentCourse = null;
                int numAssessments;

                for (Courses c : repo.getAllCourses()){
                    if(c.getCoursesID() == Integer.parseInt(editID.getText().toString())) currentCourse = c;
                }
                numAssessments = 0;
                for(Assessments a : repo.getAllAssessments()){
                    if (a.getCourseID() == Integer.parseInt(editID.getText().toString())) ++numAssessments;
                }

                if (numAssessments == 0) {
                    repo.delete(currentCourse);
                    Toast.makeText(CourseDetails.this,      editName.getText() + " " + "has been deleted.", Toast.LENGTH_SHORT).show();
                } else{
                    Toast.makeText(CourseDetails.this, "Cannot delete" + " " +  editName.getText() + " " + "since it is associated.", Toast.LENGTH_SHORT).show();
                }
                return true;

        }
        return super.onOptionsItemSelected(item);
    }


    /**
     * onClick action for the associated assessments button this also uses a hand shake to pass the id data to the associated assessments activity.
     * */
    public void associatedAssessments(View view) {
        Intent intent = new Intent(CourseDetails.this, AssociatedAssessments.class);
        intent.putExtra("id", id);
        startActivity(intent);
    }

    public void save(View view) {
        Courses courses;
        if(id == -1){
            int newID = repo.getAllCourses().get(repo.getAllCourses().size() - 1).getCoursesID() + 1;
            courses = new Courses(newID, editName.getText().toString(), startDateText.getText().toString(), endDateText.getText().toString(), editCIName.getText().toString(), editCIPhone.getText().toString(), editCIMail.getText().toString(), Integer.parseInt(editTermsSpin.getSelectedItem().toString()), Integer.parseInt(editStatusSpin.getSelectedItem().toString()), editNote.getText().toString());
            repo.insert(courses);
            Toast.makeText(CourseDetails.this, "Course with the name" + " " +  editName.getText() + " " + "has been saved.", Toast.LENGTH_SHORT).show();
        } else{
            courses = new Courses(id, editName.getText().toString(), startDateText.getText().toString(), endDateText.getText().toString(), editCIName.getText().toString(), editCIPhone.getText().toString(), editCIMail.getText().toString(), Integer.parseInt(editTermsSpin.getSelectedItem().toString()), Integer.parseInt(editStatusSpin.getSelectedItem().toString()), editNote.getText().toString() );
            repo.update(courses);
            Toast.makeText(CourseDetails.this, "Course with the name" + " " +  editName.getText() + " " + "has been updated.", Toast.LENGTH_SHORT).show();
        }
    }

}