package com.novikov.mobilehealth.presentation.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.novikov.mobilehealth.R;
import com.novikov.mobilehealth.presentation.viewmodels.ProfileViewModel;
import com.novikov.mobilehealth.presentation.viewmodels.factories.ProfileViewModelFactory;

import java.time.Instant;
import java.time.ZoneId;
import java.util.GregorianCalendar;
import java.util.Objects;

public class ProfileFragment extends Fragment {

    private ImageButton btnSettings;

    private EditText etGrowth, etWeight, etBirthDate;
    private CalendarView cvBirthDate;

    private Button btnSaveInfo;

    private ProfileViewModel vm;

    private FragmentManager fragmentManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        init(view);

        vm.getInfo();

        btnSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SettingsDialog settings = new SettingsDialog();
                settings.show(fragmentManager, "settings");
            }
        });

        cvBirthDate.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int i, int i1, int i2) {
                vm.birthDate.setValue(new GregorianCalendar(i, i1, i2).getTimeInMillis());
            }
        });

        btnSaveInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(etGrowth.getText().toString().isEmpty() || etWeight.getText().toString().isEmpty()
                || !(etBirthDate.getText().toString().matches("\\d{4}-\\d{2}-\\d{2}"))) {
                    Toast.makeText(requireContext(), "Неправильное заполнение полей", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(Integer.valueOf(etBirthDate.getText().toString().substring(0,4)) < 2015 ||
                        Integer.valueOf(etBirthDate.getText().toString().substring(5,7)) < 12 ||
                        Integer.valueOf(etBirthDate.getText().toString().substring(5,7)) < 12){
                    vm.growth.setValue(Integer.valueOf(etGrowth.getText().toString()));
                    vm.weight.setValue(Integer.valueOf(etWeight.getText().toString()));
                    vm.birthDate.setValue(new GregorianCalendar(Integer.valueOf(etBirthDate.getText().toString().substring(0,4)),
                            Integer.valueOf(etBirthDate.getText().toString().substring(5,7)),
                            Integer.valueOf(etBirthDate.getText().toString().substring(8,10))).getTimeInMillis());
                    vm.saveInfo();
                    Log.i("year", etBirthDate.getText().toString().substring(0,4));
                    Log.i("month", etBirthDate.getText().toString().substring(5,7));
                    Log.i("day", etBirthDate.getText().toString().substring(8,10));
                }
                else{
                    Toast.makeText(requireContext(), "Неправильное заполнение полей", Toast.LENGTH_SHORT).show();
                }

            }
        });

        vm.birthDate.observe(requireActivity(), new Observer<Long>() {
            @Override
            public void onChanged(Long aLong) {
                etBirthDate.setText(String.valueOf(Instant.ofEpochMilli(aLong)
                        .atZone(ZoneId.systemDefault()).toLocalDate()));

                cvBirthDate.setDate(aLong);

            }
        });

        vm.growth.observe(requireActivity(), new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                etGrowth.setText(String.valueOf(integer));
            }
        });

        vm.weight.observe(requireActivity(), new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                etWeight.setText(String.valueOf(integer));
            }
        });

        return view;
    }

    private void init(View view){

        btnSettings = view.findViewById(R.id.btnSettings);
        btnSaveInfo = view.findViewById(R.id.btnSaveProfile);

        etGrowth = view.findViewById(R.id.etGrowth);
        etWeight = view.findViewById(R.id.etWeight);
        etBirthDate = view.findViewById(R.id.etBirthDate);

        cvBirthDate = view.findViewById(R.id.cvBirthDate);

        vm = new ViewModelProvider(requireActivity(), new ProfileViewModelFactory(requireContext())).get(ProfileViewModel.class);

        fragmentManager = getActivity().getSupportFragmentManager();

    }
}