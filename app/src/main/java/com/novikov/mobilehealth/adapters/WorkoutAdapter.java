package com.novikov.mobilehealth.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.novikov.mobilehealth.R;
import com.novikov.mobilehealth.domain.models.WorkoutModel;

import java.util.List;

public class WorkoutAdapter extends RecyclerView.Adapter<WorkoutAdapter.ViewHolder> {

    private LayoutInflater inflater;
    private List<WorkoutModel> workoutModels;

    public WorkoutAdapter(Context context, List<WorkoutModel> workoutModels) {
        this.workoutModels = workoutModels;
        this.inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.rv_workouts_item, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        WorkoutModel workoutModel = workoutModels.get(position);

        holder.tvTitle.setText(workoutModel.getTitle());
        holder.ivWorkoutImage.setImageResource(workoutModel.getImgRes());
        holder.tvDescription.setText(workoutModel.getDescription());
    }

    @Override
    public int getItemCount() {
        return workoutModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tvTitle;
        private ImageView ivWorkoutImage;
        private TextView tvDescription;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvTitle = itemView.findViewById(R.id.tvWorkoutTitle);
            ivWorkoutImage = itemView.findViewById(R.id.ivWorkoutItem);
            tvDescription = itemView.findViewById(R.id.tvWorkoutDescription);

        }
    }
}
