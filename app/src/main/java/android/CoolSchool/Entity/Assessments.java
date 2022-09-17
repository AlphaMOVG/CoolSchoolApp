package android.CoolSchool.Entity;

import android.widget.DatePicker;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.time.LocalDate;
import java.util.Date;

@Entity(tableName = "Assessments")
public class Assessments {
    @PrimaryKey(autoGenerate = true)
    private int assessmentsID;

    private String assessmentName;
    private String assessmentStartDate;
    private String assessmentEndDate;
    private int assessmentType;
    private String notes;
    private int courseID;



    public Assessments(int assessmentsID, String assessmentName, String assessmentStartDate, String assessmentEndDate, int assessmentType, String notes, int courseID) {
        this.assessmentsID = assessmentsID;
        this.assessmentName = assessmentName;
        this.assessmentStartDate = assessmentStartDate;
        this.assessmentEndDate = assessmentEndDate;
        this.assessmentType = assessmentType;
        this.notes = notes;
        this.courseID = courseID;
    }

    @Override
    public String toString() {
        return  this.assessmentName;
    }



    public int getAssessmentsID() {

        return assessmentsID;
    }

    public void setAssessmentsID(int assessmentsID) {

        this.assessmentsID = assessmentsID;
    }

    public String getAssessmentName() {

        return assessmentName;
    }

    public void setAssessmentName(String assessmentName) {

        this.assessmentName = assessmentName;
    }

    public String getAssessmentStartDate() {
        return assessmentStartDate;
    }

    public void setAssessmentStartDate(String assessmentStartDate) {
        this.assessmentStartDate = assessmentStartDate;
    }

    public String getAssessmentEndDate() {
        return assessmentEndDate;
    }

    public void setAssessmentEndDate(String assessmentEndDate) {
        this.assessmentEndDate = assessmentEndDate;
    }
    public int getAssessmentType() {

        return assessmentType;
    }

    public void setAssessmentType(int assessmentType) {

        this.assessmentType = assessmentType;
    }

    public String getNotes() {

        return notes;
    }

    public void setNotes(String notes) {

        this.notes = notes;
    }

    public int getCourseID() {
        return courseID;
    }

    public void setCourseID(int courseID) {
        this.courseID = courseID;
    }
}
