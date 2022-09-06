package android.CoolSchool.UI;



import android.CoolSchool.Entity.Courses;

import android.CoolSchool.R;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CoursesAdapter extends RecyclerView.Adapter<CoursesAdapter.CoursesViewHolder> {
    class CoursesViewHolder extends RecyclerView.ViewHolder {
        private final TextView courseItemView;

        private CoursesViewHolder(View itemView) {
            super(itemView);
            courseItemView = itemView.findViewById(R.id.textView3);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    final Courses current = mCourses.get(position);
                    Intent intent = new Intent(context,CourseDetails.class);
                    intent.putExtra("id", current.getCoursesID());
                    intent.putExtra("name", current.getCourseName());
                    intent.putExtra("start", current.getStart());
                    intent.putExtra("end", current.getEnd());
                    intent.putExtra("ciname", current.getCIName());
                    intent.putExtra("cinumber", current.getCIPhoneNumber());
                    intent.putExtra("ciemail", current.getCIEMail());
                    context.startActivity(intent);

                }
            });

        }
    }

    /**
     *        this.coursesID = coursesID;
     *         this.courseName = courseName;
     *         this.start = start;
     *         this.end = end;
     *         this.cIName = cIName;
     *         this.cIPhoneNumber = cIPhoneNumber;
     *         this.cIEMail = cIEMail;
     * */

    private List<Courses> mCourses;
    private final Context context;
    private final LayoutInflater mInflater;
    public CoursesAdapter(Context context){
        mInflater = LayoutInflater.from(context);
        this.context = context;
    }

    @NonNull
    @Override
    public CoursesAdapter.CoursesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.courses_list_item, parent, false);
        return new CoursesViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CoursesAdapter.CoursesViewHolder holder, int position) {
        if(mCourses != null){
            Courses current = mCourses.get(position);
            String name = current.getCourseName();
            holder.courseItemView.setText(name);
        }
        else{
            holder.courseItemView.setText("No course name");
        }
    }

    public void setCourses(List<Courses> courses){
        mCourses = courses;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if(mCourses != null){
            return mCourses.size();
        }
        else return 0;
    }
}