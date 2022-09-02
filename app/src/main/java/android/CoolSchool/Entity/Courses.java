package android.CoolSchool.Entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.time.LocalDate;


@Entity(tableName = "Courses")
public class Courses {
    @PrimaryKey(autoGenerate = true)
    private int coursesID;

    private String courseName;
    private LocalDate start;
    private LocalDate end;
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

    public Courses(int coursesID, String courseName, LocalDate start, LocalDate end, String cIName, String cIPhoneNumber, String cIEMail) {
        this.coursesID = coursesID;
        this.courseName = courseName;
        this.start = start;
        this.end = end;
        this.cIName = cIName;
        this.cIPhoneNumber = cIPhoneNumber;
        this.cIEMail = cIEMail;
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


    public String getCIName() {
        return cIName;
    }

    public void setCIName(String cIName) {
        this.cIName = cIName;
    }


    public String getCIPhoneNumber() {
        return cIPhoneNumber;
    }

    public void setCIPhoneNumber(String cIPhoneNumber) {
        this.cIPhoneNumber = cIPhoneNumber;
    }


    public String getCIEMail() {
        return cIEMail;
    }

    public void setCIEMail(String cIEMail) {
        this.cIEMail = cIEMail;
    }
}
