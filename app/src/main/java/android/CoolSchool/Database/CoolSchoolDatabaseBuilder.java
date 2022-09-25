package android.CoolSchool.Database;

import android.CoolSchool.DAO.AssessmentsDAO;
import android.CoolSchool.DAO.CoursesDAO;
import android.CoolSchool.DAO.TermsDAO;
import android.CoolSchool.Entity.Assessments;
import android.CoolSchool.Entity.Courses;
import android.CoolSchool.Entity.Terms;
import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;


/**
 * Code that builds the database for the application.
 * */
@Database(entities = {Assessments.class, Courses.class, Terms.class}, version = 20, exportSchema = false )
public abstract class CoolSchoolDatabaseBuilder extends RoomDatabase {
    public abstract AssessmentsDAO assessmentsDAO();
    public abstract CoursesDAO coursesDAO();
    public abstract TermsDAO termsDAO();

    public static volatile CoolSchoolDatabaseBuilder INSTANCE;

    static CoolSchoolDatabaseBuilder getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (CoolSchoolDatabaseBuilder.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), CoolSchoolDatabaseBuilder.class, "myCoolSchoolDatabase.db")
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
