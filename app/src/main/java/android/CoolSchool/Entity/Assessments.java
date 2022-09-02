package android.CoolSchool.Entity;

import android.widget.DatePicker;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Assessments")
public class Assessments {
    @PrimaryKey(autoGenerate = true)
    private int assessmentsID;

    private String assessmentName;
    private DatePicker assessmentDate;
    private String assessmentType;

    @Override
    public String toString() {
        return "Assessments{" +
                "assessmentsID=" + assessmentsID +
                ", assessmentName='" + assessmentName + '\'' +
                '}';

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

    public DatePicker getAssessmentDate() {
        return assessmentDate;
    }

    public void setAssessmentDate(DatePicker assessmentDate) {
        this.assessmentDate = assessmentDate;
    }

    public String getAssessmentType() {
        return assessmentType;
    }

    public void setAssessmentType(String assessmentType) {
        this.assessmentType = assessmentType;
    }



    public Assessments(int assessmentsID, String assessmentName, DatePicker assessmentDate, String assessmentType) {
        this.assessmentsID = assessmentsID;
        this.assessmentName = assessmentName;
        this.assessmentDate = assessmentDate;
        this.assessmentType = assessmentType;
    }
}
