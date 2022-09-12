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
    private String assessmentDate;
    private int assessmentType;
    private String notes;




    public Assessments(int assessmentsID, String assessmentName, String assessmentDate, int assessmentType, String notes) {
        this.assessmentsID = assessmentsID;
        this.assessmentName = assessmentName;
        this.assessmentDate = assessmentDate;
        this.assessmentType = assessmentType;
        this.notes = notes;
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

    public String getAssessmentDate() {
        return assessmentDate;
    }

    public void setAssessmentDate(String assessmentDate) {
        this.assessmentDate = assessmentDate;
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
}
