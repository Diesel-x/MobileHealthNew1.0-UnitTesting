package com.novikov.mobilehealth.presentation.view;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.GlobalValues;
import com.novikov.mobilehealth.R;
import com.novikov.mobilehealth.adapters.ExerciseAdapter;
import com.novikov.mobilehealth.adapters.WorkoutAdapter;
import com.novikov.mobilehealth.domain.models.ExerciseModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ExercisesFragment extends Fragment {

    private Button btnBack;
    private RecyclerView rvExercises;

    private List<ExerciseModel> exerciseModels = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_exercises, container, false);
        init(view);

        if(Objects.equals(GlobalValues.workoutType, "Breast") || Objects.equals(GlobalValues.workoutType, "Грудь")){
            exerciseModels.add(new ExerciseModel(getString(R.string.classic_push_ups), 20));
            exerciseModels.add(new ExerciseModel(getString(R.string.diamond_push_ups), 12));
            exerciseModels.add(new ExerciseModel(getString(R.string.wide_push_ups), 16));
            exerciseModels.add(new ExerciseModel(getString(R.string.classic_push_ups), 14));
            exerciseModels.add(new ExerciseModel(getString(R.string.diamond_push_ups), 8));
            exerciseModels.add(new ExerciseModel(getString(R.string.wide_push_ups), 12));
        }
        if(Objects.equals(GlobalValues.workoutType, "Back") || Objects.equals(GlobalValues.workoutType, "Спина")){
            exerciseModels.add(new ExerciseModel(getString(R.string.classic_pull_ups), 10));
            exerciseModels.add(new ExerciseModel(getString(R.string.wide_pull_ups), 7));
            exerciseModels.add(new ExerciseModel(getString(R.string.superman), 20));
            exerciseModels.add(new ExerciseModel(getString(R.string.classic_pull_ups), 8));
            exerciseModels.add(new ExerciseModel(getString(R.string.wide_pull_ups), 6));
            exerciseModels.add(new ExerciseModel(getString(R.string.superman), 16));
        }
        if(Objects.equals(GlobalValues.workoutType, "Legs") || Objects.equals(GlobalValues.workoutType, "Ноги")){
            exerciseModels.add(new ExerciseModel(getString(R.string.squats), 24));
            exerciseModels.add(new ExerciseModel(getString(R.string.crossfit_squats), 20));
            exerciseModels.add(new ExerciseModel(getString(R.string.lunges), 24));
            exerciseModels.add(new ExerciseModel(getString(R.string.toe_up), 30));
            exerciseModels.add(new ExerciseModel(getString(R.string.squats), 18));
            exerciseModels.add(new ExerciseModel(getString(R.string.lunges), 24));
        }
        if(Objects.equals(GlobalValues.workoutType, "ABS") || Objects.equals(GlobalValues.workoutType, "Кор")){
            exerciseModels.add(new ExerciseModel(getString(R.string.twists), 20));
            exerciseModels.add(new ExerciseModel(getString(R.string.half_twists), 20));
            exerciseModels.add(new ExerciseModel(getString(R.string.alpinist), 20));
            exerciseModels.add(new ExerciseModel(getString(R.string.twists), 18));
            exerciseModels.add(new ExerciseModel(getString(R.string.half_twists), 18));
            exerciseModels.add(new ExerciseModel(getString(R.string.alpinist), 18));
        }

        rvExercises.setAdapter(new ExerciseAdapter(requireContext(), exerciseModels));

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity)requireActivity()).navController.navigate(R.id.workoutFragment);
            }
        });

        return view;
    }

    private void init(View view){
        btnBack = view.findViewById(R.id.btnBack);
        rvExercises = view.findViewById(R.id.rvExercises);
    }
}