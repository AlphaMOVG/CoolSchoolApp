package android.CoolSchool.Entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.time.LocalDateTime;

@Entity(tableName = "Terms")
public class Terms {
    @PrimaryKey(autoGenerate = true)
    private int termsID;

    private String termName;
    private LocalDateTime start;
    private LocalDateTime end;

    @Override
    public String toString() {
        return "Terms{" +
                "termsID=" + termsID +
                ", termName='" + termName + '\'' +
                '}';
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

    public LocalDateTime getStart() {
        return start;
    }

    public void setStart(LocalDateTime start) {
        this.start = start;
    }

    public LocalDateTime getEnd() {
        return end;
    }

    public void setEnd(LocalDateTime end) {
        this.end = end;
    }

    public Terms(int termsID, String termName, LocalDateTime start, LocalDateTime end) {
        this.termsID = termsID;
        this.termName = termName;
        this.start = start;
        this.end = end;
    }
}
