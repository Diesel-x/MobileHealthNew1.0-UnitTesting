package com.novikov.mobilehealth.presentation.view;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import com.novikov.mobilehealth.R;

public class MedicineFragment extends Fragment {

    private RecyclerView rvMedicines;

    private Button btnMedicineAdding;

    private ImageButton btnBack;

    private FragmentManager fragmentManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_medicine, container, false);

        init(view);

        btnMedicineAdding.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MedicineDialog dialog = new MedicineDialog();
                dialog.show(fragmentManager, "add_medicine");
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
        fragmentManager = getActivity().getSupportFragmentManager();
    }
}