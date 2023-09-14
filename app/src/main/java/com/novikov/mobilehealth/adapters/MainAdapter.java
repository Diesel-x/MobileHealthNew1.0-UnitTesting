package com.novikov.mobilehealth.adapters;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.ColorRes;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.novikov.mobilehealth.R;
import com.novikov.mobilehealth.domain.interfaces.IOnMainRVItemClick;
import com.novikov.mobilehealth.domain.models.MainRVItemModel;

import java.util.List;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder> {

    private List<MainRVItemModel> list;

    private LayoutInflater inflater;

    private IOnMainRVItemClick itemClickInterface;

    public MainAdapter(Context context, List<MainRVItemModel> list, IOnMainRVItemClick itemClickInterface) {
        this.list = list;
        this.inflater = LayoutInflater.from(context);
        this.itemClickInterface = itemClickInterface;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.rv_main_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        MainRVItemModel item = list.get(position);
        holder.ivMain.setImageResource(item.getImgRes());
        holder.tvMain.setText(item.getName());
        holder.clMain.setBackgroundColor(item.getColorRes());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                itemClickInterface.onClick(item, position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private ImageView ivMain;
        private TextView tvMain;

        private ConstraintLayout clMain;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            ivMain = itemView.findViewById(R.id.ivMain);
            tvMain = itemView.findViewById(R.id.tvMain);
            clMain = itemView.findViewById(R.id.clMain);
        }
    }
}
