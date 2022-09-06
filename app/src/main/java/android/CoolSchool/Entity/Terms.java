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



    public Terms(int termsID, String termName, String start, String end) {
        this.termsID = termsID;
        this.termName = termName;
        this.start = start;
        this.end = end;
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


}
