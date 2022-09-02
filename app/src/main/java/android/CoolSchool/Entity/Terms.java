package android.CoolSchool.Entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity(tableName = "Terms")
public class Terms {
    @PrimaryKey(autoGenerate = true)
    private int termsID;

    private String termName;
    private LocalDate start;
    private LocalDate end;

    @Override
    public String toString() {
        return "Terms{" +
                "termsID=" + termsID +
                ", termName='" + termName + '\'' +
                '}';
    }

    public Terms(int termsID, String termName, LocalDate start, LocalDate end) {
        this.termsID = termsID;
        this.termName = termName;
        this.start = start;
        this.end = end;
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

    public LocalDate getStart() {
        return start;
    }

    public void setStart(LocalDate start) {
        this.start = start;
    }

    public LocalDate getEnd() {
        return end;
    }

    public void setEnd(LocalDate end) {
        this.end = end;
    }


}
