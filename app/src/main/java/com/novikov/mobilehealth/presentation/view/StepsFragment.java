package com.novikov.mobilehealth.presentation.view;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.novikov.mobilehealth.R;

public class StepsFragment extends Fragment {

    private TextView tvStepsToday, tvStepsTodayGoal;
    private TextView tvGoalRowDays;

    private ProgressBar pbSteps;

    private ImageButton btnBack;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_steps, container, false);

        init(view);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity)getActivity()).navController.navigate(R.id.mainFragment);
            }
        });

        return view;
    }

    private void init(View view) {
        btnBack = view.findViewById(R.id.btnBack);
    }
}