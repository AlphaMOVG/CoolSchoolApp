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
    private String assessmentType;




    public Assessments(int assessmentsID, String assessmentName, String assessmentDate, String assessmentType) {
        this.assessmentsID = assessmentsID;
        this.assessmentName = assessmentName;
        this.assessmentDate = assessmentDate;
        this.assessmentType = assessmentType;
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

    public String getAssessmentType() {
        return assessmentType;
    }

    public void setAssessmentType(String assessmentType) {
        this.assessmentType = assessmentType;
    }




}
