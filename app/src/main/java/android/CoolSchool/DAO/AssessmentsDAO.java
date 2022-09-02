package android.CoolSchool.DAO;

import android.CoolSchool.Entity.Assessments;

import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

public interface AssessmentsDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Assessments assessment);

    @Update
    void update(Assessments assessment);

    @Delete
    void delete(Assessments assessment);

    @Query("SELECT * FROM Assessments ORDER BY assessmentsID ASC")
    List<Assessments> getAllAssessments();
}
