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
public interface CoursesDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Courses courses);

    @Update
    void update(Courses courses);

    @Delete
    void delete(Courses courses);

    @Query("SELECT * FROM Courses ORDER BY coursesID ASC")
    List<Assessments> getAllCourses();
}
