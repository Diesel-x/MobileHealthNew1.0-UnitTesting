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
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.novikov.mobilehealth.R;
import com.novikov.mobilehealth.presentation.viewmodels.PFCViewModel;
import com.novikov.mobilehealth.presentation.viewmodels.ProfileViewModel;
import com.novikov.mobilehealth.presentation.viewmodels.factories.PFCViewModelFactory;
import com.novikov.mobilehealth.presentation.viewmodels.factories.ProfileViewModelFactory;

import java.text.MessageFormat;

public class PFCFragment extends Fragment {

    private TextView tvProteinsCurrentCount, tvProteinsTotalCount;
    private TextView tvFatsCurrentCount, tvFatsTotalCount;
    private TextView tvCarbsCurrentCount, tvCarbsTotalCount;

    private ProgressBar pbProteins, pbFats, pbCarbs;

    private EditText etAddProteins, etAddFats, etAddCarbs;

    private Button btnAdd;

    private ImageButton btnBack;

    private PFCViewModel vm;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_pfc, container, false);

        init(view);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("back", "pressed");
                ((MainActivity)requireActivity()).navController.navigate(R.id.mainFragment);
            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                vm.addAction(Integer.parseInt(etAddProteins.getText().toString()),
                        Integer.parseInt(etAddFats.getText().toString()),
                        Integer.parseInt(etAddCarbs.getText().toString()));

            }
        });

        setData();

        vm.proteinGoal.observe(requireActivity(), new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                tvProteinsCurrentCount.setText(MessageFormat.format("{0}/{1}", vm.proteinCount.getValue(), integer));
                pbProteins.setMax(integer);
            }
        });

        vm.proteinCount.observe(requireActivity(), new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                tvProteinsCurrentCount.setText(MessageFormat.format("{0}/{1}", integer, vm.proteinGoal.getValue()));
                pbProteins.setProgress(integer, true);
            }
        });

        vm.carbGoal.observe(requireActivity(), new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                tvCarbsCurrentCount.setText(MessageFormat.format("{0}/{1}", vm.carbCount.getValue(), integer));
                pbCarbs.setMax(integer);
            }
        });

        vm.carbCount.observe(requireActivity(), new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                tvCarbsCurrentCount.setText(MessageFormat.format("{0}/{1}", integer, vm.carbGoal.getValue()));
                pbCarbs.setProgress(integer, true);
            }
        });

        vm.fatGoal.observe(requireActivity(), new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                tvFatsCurrentCount.setText(MessageFormat.format("{0}/{1}", vm.fatCount.getValue(), integer));
                pbFats.setMax(integer);
            }
        });

        vm.fatCount.observe(requireActivity(), new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                tvFatsCurrentCount.setText(MessageFormat.format("{0}/{1}", integer, vm.fatGoal.getValue()));
                pbFats.setProgress(integer, true);
            }
        });

        return view;
    }

    private void init(View view){

        btnBack = view.findViewById(R.id.btnBack);
        btnAdd = view.findViewById(R.id.btnAddPfc);

        tvProteinsCurrentCount = view.findViewById(R.id.tvProteinProgress);
        tvCarbsCurrentCount = view.findViewById(R.id.tvCarbProgress);
        tvFatsCurrentCount = view.findViewById(R.id.tvFatProgress);

        pbProteins = view.findViewById(R.id.pbProtein);
        pbCarbs = view.findViewById(R.id.pbCarb);
        pbFats = view.findViewById(R.id.pbFat);

        etAddProteins = view.findViewById(R.id.etProtein);
        etAddCarbs = view.findViewById(R.id.etCarb);
        etAddFats = view.findViewById(R.id.etFat);

        vm = new ViewModelProvider(requireActivity(), new PFCViewModelFactory(requireContext()))
                .get(PFCViewModel.class);
    }

    private void setData(){
        vm.setGoal();
    }
}