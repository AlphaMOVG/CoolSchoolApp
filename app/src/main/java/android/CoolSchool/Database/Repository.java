package android.CoolSchool.Database;

import android.CoolSchool.DAO.AssessmentsDAO;
import android.CoolSchool.DAO.CoursesDAO;
import android.CoolSchool.DAO.TermsDAO;
import android.CoolSchool.Entity.Assessments;
import android.CoolSchool.Entity.Courses;
import android.CoolSchool.Entity.Terms;
import android.app.Application;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Repository {

    /**
     * There are the threads
     */
    private AssessmentsDAO mAssessmentsDAO;
    private CoursesDAO mCoursesDAO;
    private TermsDAO mTermsDAO;
    private List<Assessments> mAllAssessments;
    private List<Courses> mAllCourses;
    private List<Terms> mAllTerms;
    private List<Assessments> filteredAssessments;
    private List<Courses> filteredCourses;
    private static int NUMBER_OF_THREADS = 6;
    static final ExecutorService databaseExecutors = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public Repository(Application application) {
        CoolSchoolDatabaseBuilder db = CoolSchoolDatabaseBuilder.getDatabase(application);
        mAssessmentsDAO = db.assessmentsDAO();
        mCoursesDAO = db.coursesDAO();
        mTermsDAO = db.termsDAO();
    }

    public List<Courses> getAllCoursesByTermID(int termID){
        databaseExecutors.execute(()->{
            filteredCourses = mCoursesDAO.getAllCoursesByTermID(termID);
        });

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return filteredCourses;
    }

    public  List<Assessments> getAllAssessmentsByCourseID(int courseID){
        databaseExecutors.execute(()->{
            filteredAssessments = mAssessmentsDAO.getAllAssessmentsByCourseID(courseID);
        });

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return filteredAssessments;
    }

    /**
     * Assessments
     * */
    public List<Assessments> getAllAssessments(){
        databaseExecutors.execute(()->{
            mAllAssessments = mAssessmentsDAO.getAllAssessments();
        });

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return mAllAssessments;
    }

    public void insert(Assessments assessments) {
        databaseExecutors.execute(() -> {
            mAssessmentsDAO.insert(assessments);
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void update(Assessments assessments) {
        databaseExecutors.execute(() -> {
            mAssessmentsDAO.update(assessments);
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void delete(Assessments assessments) {
        databaseExecutors.execute(() -> {
            mAssessmentsDAO.delete(assessments);
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

/**
 * Courses
 * */
    public List<Courses> getAllCourses(){
        databaseExecutors.execute(()->{
            mAllCourses = mCoursesDAO.getAllCourses();
        });

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return mAllCourses;
    }

    public void insert(Courses courses) {
        databaseExecutors.execute(() -> {
            mCoursesDAO.insert(courses);
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void update(Courses courses) {
        databaseExecutors.execute(() -> {
            mCoursesDAO.update(courses);
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void delete(Courses courses) {
        databaseExecutors.execute(() -> {
            mCoursesDAO.delete(courses);
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

        /**
         * Terms
         * */
    public List<Terms> getAllTerms(){
        databaseExecutors.execute(()->{
            mAllTerms = mTermsDAO.getAllTerms();
        });

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return mAllTerms;
    }

    public void insert(Terms terms) {
        databaseExecutors.execute(() -> {
            mTermsDAO.insert(terms);
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void update(Terms terms) {
        databaseExecutors.execute(() -> {
            mTermsDAO.update(terms);
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void delete(Terms terms) {
        databaseExecutors.execute(() -> {
            mTermsDAO.delete(terms);
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
