package com.novikov.mobilehealth.presentation.view;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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

    private SensorManager sensorManager;
    private Sensor sensor;
    private SensorEventListener listener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_steps, container, false);

        listener = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent sensorEvent) {
                tvStepsToday.setText(String.valueOf((int)sensorEvent.values[0]));
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int i) {

            }
        };

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
        tvStepsToday = view.findViewById(R.id.tvStepsToday);
        tvStepsTodayGoal = view.findViewById(R.id.tvStepsGoal);

        sensorManager = (SensorManager) requireActivity().getSystemService(Context.SENSOR_SERVICE);
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
        sensorManager.registerListener(listener, sensor, SensorManager.SENSOR_DELAY_UI);
    }


}