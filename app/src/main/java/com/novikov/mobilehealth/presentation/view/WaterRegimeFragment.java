package com.novikov.mobilehealth.presentation.view;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.novikov.mobilehealth.R;
import com.novikov.mobilehealth.presentation.viewmodels.WaterRegimeViewModel;
import com.novikov.mobilehealth.presentation.viewmodels.factories.WaterRegimeViewModelFactory;

import java.text.MessageFormat;

public class WaterRegimeFragment extends Fragment {

    private TextView tvWaterCount;
    private TextView tvGoalRowDays;

    private SeekBar sbDrunkWater;

    private ProgressBar pbWater;

    private Button btnAdd;
    private ImageButton btnBack;

    private WaterRegimeViewModel vm;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_water_regime, container, false);

        init(view);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity)requireActivity()).navController.navigate(R.id.mainFragment);
            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vm.addAction(sbDrunkWater.getProgress());
            }
        });

        vm.waterGoalCount.observe(requireActivity(), new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                tvWaterCount.setText(MessageFormat.format("{0}/{1}ml", vm.waterCurrentCount.getValue(), integer));
            }
        });

        vm.waterCurrentCount.observe(requireActivity(), new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                tvWaterCount.setText(MessageFormat.format("{0}/{1}ml", integer, vm.waterGoalCount.getValue()));
            }
        });

        return view;
    }

    private void init(View view){

        tvWaterCount = view.findViewById(R.id.tvWaterRegimeProgress);
        tvGoalRowDays = view.findViewById(R.id.tvWaterRegimeGoalAchievedDays);

        pbWater = view.findViewById(R.id.pbWaterRegimeProgress);

        sbDrunkWater = view.findViewById(R.id.sbDrunkWater);

        btnBack = view.findViewById(R.id.btnBack);
        btnAdd = view.findViewById(R.id.btnAdd);

        vm = new ViewModelProvider(requireActivity(), new WaterRegimeViewModelFactory(requireContext()))
                .get(WaterRegimeViewModel.class);
    }
}