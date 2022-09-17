package android.CoolSchool.DAO;

import android.CoolSchool.Entity.Assessments;
import android.CoolSchool.Entity.Courses;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface AssessmentsDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Assessments assessment);

    @Update
    void update(Assessments assessment);

    @Delete
    void delete(Assessments assessment);

    @Query("SELECT * FROM Assessments ORDER BY assessmentsID ASC")
    List<Assessments> getAllAssessments();

    @Query("SELECT * FROM Assessments WHERE courseID = :courseID")
    List<Assessments> getAllAssessmentsByCourseID(int courseID);

}
