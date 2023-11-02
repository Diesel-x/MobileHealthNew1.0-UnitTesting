package com.novikov.mobilehealth.presentation.view;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.novikov.mobilehealth.R;
import com.novikov.mobilehealth.presentation.viewmodels.WaterRegimeViewModel;
import com.novikov.mobilehealth.presentation.viewmodels.factories.WaterRegimeViewModelFactory;

import java.text.MessageFormat;

public class WaterRegimeFragment extends Fragment {

    private TextView tvWaterCount, tvAddCount, tvGoalRowDays;

    private SeekBar sbDrunkWater;

    private ProgressBar pbWater;

    private Button btnAdd;
    private ImageButton btnBack;
    private ImageView ivGlass;

    private WaterRegimeViewModel vm;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_water_regime, container, false);

        init(view);

        vm.setWaterGoal();

        tvAddCount.setText(String.valueOf(sbDrunkWater.getProgress()));

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
                pbWater.setMax(integer);
                Log.i("max", String.valueOf(pbWater.getMax()));
            }
        });

        vm.waterCurrentCount.observe(requireActivity(), new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                tvWaterCount.setText(MessageFormat.format("{0}/{1}ml", integer, vm.waterGoalCount.getValue()));
                pbWater.setProgress(integer);
                Log.i("current", String.valueOf(pbWater.getProgress()));
            }
        });

        sbDrunkWater.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

                tvAddCount.setText(String.valueOf(i) + getString(R.string.ml));

                if(i <= seekBar.getMax()*0.25){
                    ivGlass.setImageResource(R.drawable.glass_25);
                }
                else if(i > seekBar.getMax()*0.25 && i <= seekBar.getMax()*0.50){
                    ivGlass.setImageResource(R.drawable.glass_50);
                }
                else if(i > seekBar.getMax()*0.50 && i <= seekBar.getMax()*0.75){
                    ivGlass.setImageResource(R.drawable.glass_75);
                }
                else{
                    ivGlass.setImageResource(R.drawable.glass_full);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        return view;
    }

    private void init(View view){

        tvWaterCount = view.findViewById(R.id.tvWaterRegimeProgress);
        tvGoalRowDays = view.findViewById(R.id.tvWaterRegimeGoalAchievedDays);
        tvAddCount = view.findViewById(R.id.tvAddCount);

        pbWater = view.findViewById(R.id.pbWaterRegimeProgress);

        sbDrunkWater = view.findViewById(R.id.sbDrunkWater);
        ivGlass = view.findViewById(R.id.ivGlass);

        btnBack = view.findViewById(R.id.btnBack);
        btnAdd = view.findViewById(R.id.btnAdd);

        vm = new ViewModelProvider(requireActivity(), new WaterRegimeViewModelFactory(requireContext()))
                .get(WaterRegimeViewModel.class);
    }
}