package com.novikov.mobilehealth.presentation.view;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.novikov.mobilehealth.R;
import com.novikov.mobilehealth.adapters.WorkoutAdapter;
import com.novikov.mobilehealth.domain.interfaces.IOnWorkoutRVItemClick;
import com.novikov.mobilehealth.domain.models.WorkoutModel;

import java.util.ArrayList;
import java.util.List;

public class WorkoutFragment extends Fragment {

    private RecyclerView rvWorkout;

    private ImageButton btnBack;
    private List<WorkoutModel> workoutModels = new ArrayList<>();

    private IOnWorkoutRVItemClick onWorkoutClick;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_workout, container, false);

        init(view);

        onWorkoutClick = new IOnWorkoutRVItemClick() {
            @Override
            public void onClick(WorkoutModel model, int position) {
                ((MainActivity)requireActivity()).navController.navigate(R.id.action_workoutFragment_to_exercisesFragment);
            }
        };

        workoutModels = initList();

        rvWorkout.setAdapter(new WorkoutAdapter(getContext(), workoutModels, onWorkoutClick));

        Log.i("listsize", String.valueOf(workoutModels.size()));

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity)requireActivity()).navController.navigate(R.id.mainFragment);
            }
        });

        return view;
    }
    private void init(View view){
        rvWorkout = view.findViewById(R.id.rvWorkout);
        btnBack = view.findViewById(R.id.btnBack);
    }

    private List<WorkoutModel> initList(){
        List<WorkoutModel> list = new ArrayList<>();

        list.add(new WorkoutModel(getString(R.string.breast), R.drawable.breast_img, getString(R.string.breast_description)));
        list.add(new WorkoutModel(getString(R.string.back), R.drawable.back_img, getString(R.string.back_description)));
        list.add(new WorkoutModel(getString(R.string.legs), R.drawable.legs_img, getString(R.string.legs_description)));
        list.add(new WorkoutModel(getString(R.string.abs), R.drawable.abs_img, getString(R.string.abs_description)));

        return list;
    }
}