package com.novikov.mobilehealth.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.novikov.mobilehealth.R;
import com.novikov.mobilehealth.domain.models.ExerciseModel;

import java.util.List;

public class ExerciseAdapter extends RecyclerView.Adapter<ExerciseAdapter.ViewHolder> {

    private LayoutInflater inflater;
    private List<ExerciseModel> models;

    public ExerciseAdapter(Context context, List<ExerciseModel> models) {
        this.models = models;
        inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.rv_exercises_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ExerciseModel exerciseModel = models.get(position);

        holder.tvTitle.setText(exerciseModel.getTitle());
        holder.tvCount.setText("x" + String.valueOf(exerciseModel.getCount()));
    }

    @Override
    public int getItemCount() {
        return models.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tvTitle, tvCount;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvTitle = itemView.findViewById(R.id.tvExerciseTitle);
            tvCount = itemView.findViewById(R.id.tvExerciseCount);
        }
    }
}
