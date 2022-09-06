package android.CoolSchool.UI;


import android.CoolSchool.Entity.Terms;
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

public class TermsAdapter extends RecyclerView.Adapter<TermsAdapter.TermsViewHolder> {
    class TermsViewHolder extends RecyclerView.ViewHolder {
        private final TextView termItemView;

        private TermsViewHolder(View itemView) {
            super(itemView);
            termItemView = itemView.findViewById(R.id.textView4);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    final Terms current = mTerms.get(position);
                    Intent intent = new Intent(context,TermDetails.class);
                    intent.putExtra("id", current.getTermsID());
                    intent.putExtra("name", current.getTermName());
                    intent.putExtra("start", current.getStart());
                    intent.putExtra("end", current.getEnd());

                    context.startActivity(intent);

                }
            });

        }
    }


    private List<Terms> mTerms;
    private final Context context;
    private final LayoutInflater mInflater;
    public TermsAdapter(Context context){
        mInflater = LayoutInflater.from(context);
        this.context = context;
    }

    @NonNull
    @Override
    public TermsAdapter.TermsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.terms_list_item, parent, false);
        return new TermsViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull TermsAdapter.TermsViewHolder holder, int position) {
        if(mTerms != null){
            Terms current = mTerms.get(position);
            String name = current.getTermName();
            holder.termItemView.setText(name);
        }
        else{
            holder.termItemView.setText("No term name");
        }
    }

    public void setTerms(List<Terms> terms){
        mTerms = terms;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if(mTerms != null){
            return mTerms.size();
        }
        else return 0;
    }
}
