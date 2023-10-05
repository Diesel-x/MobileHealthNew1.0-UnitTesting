package com.novikov.mobilehealth.presentation.view;

import android.app.Dialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;

import com.novikov.mobilehealth.R;
import com.novikov.mobilehealth.adapters.MedicineAdapter;
import com.novikov.mobilehealth.domain.models.MedicineModel;
import com.novikov.mobilehealth.presentation.viewmodels.MedicineViewModel;
import com.novikov.mobilehealth.presentation.viewmodels.PFCViewModel;
import com.novikov.mobilehealth.presentation.viewmodels.factories.MedicineViewModelFactory;
import com.novikov.mobilehealth.presentation.viewmodels.factories.PFCViewModelFactory;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class MedicineFragment extends Fragment {

    private RecyclerView rvMedicines;

    private Button btnMedicineAdding;

    private ImageButton btnBack;

    private FragmentManager fragmentManager;

    private MedicineViewModel vm;

    //Dialog
    private EditText etName, etTime, etDuration, etStartDate;

    private Spinner spinnerType;
    private Button btnDialogAdd;

    private MedicineAdapter adapter;

    private List<MedicineModel> medicineList = new ArrayList<>();

    Dialog dialog;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_medicine, container, false);

        init(view);

        vm.getCurrentData();

        medicineList = vm.medicineList.getValue();

        adapter = new MedicineAdapter(medicineList, requireContext());

        rvMedicines.setAdapter(adapter);

        btnMedicineAdding.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dialog = new Dialog(requireActivity());
                dialog.setContentView(R.layout.dialog_medicine_adding);
                dialog.show();
//                dialog.show(fragmentManager, "add_medicine");

                    btnDialogAdd = dialog.findViewById(R.id.btnAddMedicine);
                    etName = dialog.findViewById(R.id.etAddMedicineName);
                    etDuration = dialog.findViewById(R.id.etAddMedicineCourseDuration);
                    etStartDate = dialog.findViewById(R.id.etAddMedicineStartDate);
                    etTime = dialog.findViewById(R.id.etAddMedicineReceptionTime);
                    spinnerType = dialog.findViewById(R.id.spinnerAddMedicineType);

                    btnDialogAdd.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

//                            vm.addMedicine(new MedicineModel(etName.getText().toString(),
//                                    LocalTime.parse(etTime.getText().toString()),
//                                    Integer.parseInt(etDuration.getText().toString()),
//                                    spinnerType.getSelectedItem().toString(),
//                                    LocalDate.parse(etStartDate.getText(), DateTimeFormatter.ofPattern("dd.MM.yyyy"))));
                            medicineList.add(new MedicineModel(etName.getText().toString(),
                                    etTime.getText().toString(),
                                    Integer.parseInt(etDuration.getText().toString()),
                                    spinnerType.getSelectedItem().toString(),
                                    etStartDate.getText().toString()));

                            rvMedicines.getAdapter().notifyDataSetChanged();

                            vm.medicineList.setValue(medicineList);

                            vm.saveCurrentData();

                            Log.i("dialog", "complete");
                            dialog.dismiss();

                        }
                    });
                Log.i("fragment", "complete");
                }
        });
        vm.medicineList.observe(requireActivity(), new Observer<List<MedicineModel>>() {
            @Override
            public void onChanged(List<MedicineModel> medicineModels) {

                medicineList = medicineModels;
                rvMedicines.getAdapter().notifyDataSetChanged();
                Log.i("medicineList", "complete");
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity)getActivity()).navController.navigate(R.id.mainFragment);
            }
        });

        return view;
    }

    private void init(View view){
        btnMedicineAdding = view.findViewById(R.id.btnMedicineAdding);
        btnBack = view.findViewById(R.id.btnBack);
        rvMedicines = view.findViewById(R.id.rvMedicines);
        fragmentManager = getActivity().getSupportFragmentManager();

        vm = new ViewModelProvider(requireActivity(), new MedicineViewModelFactory(requireContext()))
                .get(MedicineViewModel.class);
    }
}