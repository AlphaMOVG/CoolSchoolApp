package android.CoolSchool.Database;

import android.CoolSchool.Entity.Assessments;
import android.CoolSchool.Entity.Courses;
import android.CoolSchool.Entity.Terms;

import androidx.room.Database;

@Database(entities = {Assessments.class, Courses.class, Terms.class}, version = 2, exportSchema = false )

public class CoolSchoolDatabaseBuilder {
}
