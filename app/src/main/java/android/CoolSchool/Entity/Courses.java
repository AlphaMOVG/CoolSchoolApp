package android.CoolSchool.Entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.time.LocalDateTime;

@Entity(tableName = "Courses")
public class Courses {
    @PrimaryKey(autoGenerate = true)
    private int coursesID;

    private String courseName;
    private LocalDateTime start;
    private LocalDateTime end;
    private String cIName;
    private String cIPhoneNumber;
    private String cIEMail;

    @Override
    public String toString() {
        return "Courses{" +
                "coursesID=" + coursesID +
                ", courseName='" + courseName + '\'' +
                '}';
    }

    public int getCoursesID() {
        return coursesID;
    }

    public void setCoursesID(int coursesID) {
        this.coursesID = coursesID;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
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

    public String getcIName() {
        return cIName;
    }

    public void setcIName(String cIName) {
        this.cIName = cIName;
    }

    public String getcIPhoneNumber() {
        return cIPhoneNumber;
    }

    public void setcIPhoneNumber(String cIPhoneNumber) {
        this.cIPhoneNumber = cIPhoneNumber;
    }

    public String getcIEMail() {
        return cIEMail;
    }

    public void setcIEMail(String cIEMail) {
        this.cIEMail = cIEMail;
    }
}
