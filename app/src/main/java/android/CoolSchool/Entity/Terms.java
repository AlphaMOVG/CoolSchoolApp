package android.CoolSchool.Entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;



@Entity(tableName = "Terms")
public class Terms {
    @PrimaryKey(autoGenerate = true)
    private int termsID;

    private String termName;
    private String start;
    private String end;
    private int courseID;
    private String notes;



    public Terms(int termsID, String termName, String start, String end, int courseID, String notes) {
        this.termsID = termsID;
        this.termName = termName;
        this.start = start;
        this.end = end;
        this.courseID = courseID;
        this.notes = notes;
    }

    @Override
    public String toString() {
        return  this.termName;
    }

    public int getTermsID() {
        return termsID;
    }

    public void setTermsID(int termsID) {
        this.termsID = termsID;
    }

    public String getTermName() {
        return termName;
    }

    public void setTermName(String termName) {
        this.termName = termName;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public int getCourseID() {
        return courseID;
    }

    public void setCourseID(int courseID) {
        this.courseID = courseID;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
