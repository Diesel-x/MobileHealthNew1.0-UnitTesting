package com.novikov.mobilehealth.presentation.view;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.novikov.mobilehealth.R;
import com.novikov.mobilehealth.adapters.WorkoutAdapter;
import com.novikov.mobilehealth.domain.models.WorkoutModel;

import java.util.ArrayList;
import java.util.List;

public class WorkoutFragment extends Fragment {

    private RecyclerView rvWorkout;
    private List<WorkoutModel> workoutModels = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_workout, container, false);

        init(view);

        workoutModels = initList();

        rvWorkout.setAdapter(new WorkoutAdapter(getContext(), workoutModels));

        Log.i("listsize", String.valueOf(workoutModels.size()));

        return view;
    }
    private void init(View view){
        rvWorkout = view.findViewById(R.id.rvWorkout);
    }

    private List<WorkoutModel> initList(){
        List<WorkoutModel> list = new ArrayList<>();

        list.add(new WorkoutModel(getString(R.string.breast), R.drawable.breast_img, "da"));
        list.add(new WorkoutModel(getString(R.string.breast), R.drawable.breast_img, "da"));
        list.add(new WorkoutModel(getString(R.string.breast), R.drawable.breast_img, "da"));
        list.add(new WorkoutModel(getString(R.string.breast), R.drawable.breast_img, "da"));
        list.add(new WorkoutModel(getString(R.string.breast), R.drawable.breast_img, "da"));

        return list;
    }
}