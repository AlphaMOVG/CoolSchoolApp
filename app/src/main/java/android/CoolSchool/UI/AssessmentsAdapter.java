package android.CoolSchool.UI;

import android.CoolSchool.Entity.Assessments;
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

public class AssessmentsAdapter extends RecyclerView.Adapter<AssessmentsAdapter.AssessmentsViewHolder> {
    class AssessmentsViewHolder extends RecyclerView.ViewHolder {
        private final TextView assessmentItemView;

        private AssessmentsViewHolder(View itemView) {
            super(itemView);
            assessmentItemView = itemView.findViewById(R.id.textView2);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    final Assessments current = mAssessments.get(position);
                    Intent intent = new Intent(context,AssessmentDetails.class);
                    intent.putExtra("id", current.getAssessmentsID());
                    intent.putExtra("name", current.getAssessmentName());
                    intent.putExtra("date", current.getAssessmentDate());
                    intent.putExtra("type", current.getAssessmentType());
                    intent.putExtra("notes", current.getNotes());
                    context.startActivity(intent);

                }
            });

        }
    }

    private List<Assessments> mAssessments;
    private final Context context;
    private final LayoutInflater mInflater;

    public AssessmentsAdapter(Context context){
        mInflater = LayoutInflater.from(context);
        this.context = context;
    }

    @NonNull
    @Override
    public AssessmentsAdapter.AssessmentsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View itemView = mInflater.inflate(R.layout.assessment_list_item, parent, false);
       return new AssessmentsViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull AssessmentsAdapter.AssessmentsViewHolder holder, int position) {
        if(mAssessments != null){
            Assessments current = mAssessments.get(position);
            String name = current.getAssessmentName();
            holder.assessmentItemView.setText(name);
        }
        else{
            holder.assessmentItemView.setText("No assessment name");
        }
    }

    public void setAssessments(List<Assessments> assessments){
        mAssessments = assessments;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if(mAssessments != null){
            return mAssessments.size();
        }
        else return 0;
    }
}
