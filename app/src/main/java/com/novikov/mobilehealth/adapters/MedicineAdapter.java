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
import com.novikov.mobilehealth.domain.models.MedicineModel;

import java.util.ArrayList;
import java.util.List;

public class MedicineAdapter extends RecyclerView.Adapter<MedicineAdapter.ViewHolder> {

    private List<MedicineModel> medicineList;
    private LayoutInflater inflater;

    public MedicineAdapter(List<MedicineModel> medicineList, Context context) {
        this.medicineList = medicineList;
        inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.rv_medicines_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        MedicineModel medicineModel = medicineList.get(position);

        holder.tvName.setText(medicineModel.getName());
        holder.tvDuration.setText(String.valueOf(medicineModel.getDuration()));
        holder.tvTime.setText(medicineModel.getTime().toString());
        holder.tvStartDate.setText(medicineModel.getStartDate().toString());

    }

    @Override
    public int getItemCount() {
        return medicineList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView ivType;
        private TextView tvName, tvDuration, tvStartDate, tvTime;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            ivType = itemView.findViewById(R.id.ivMedicineImage);

            tvName = itemView.findViewById(R.id.tvMedicineName);
            tvDuration = itemView.findViewById(R.id.tvMedicineDuration);
            tvTime = itemView.findViewById(R.id.tvMedicineReceptionTime);
            tvStartDate = itemView.findViewById(R.id.tvMedicineStartDate);

        }
    }
}
