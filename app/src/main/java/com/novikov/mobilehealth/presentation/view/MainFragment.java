package com.novikov.mobilehealth.presentation.view;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.novikov.mobilehealth.R;
import com.novikov.mobilehealth.adapters.MainAdapter;
import com.novikov.mobilehealth.domain.interfaces.IOnMainRVItemClick;
import com.novikov.mobilehealth.domain.models.MainRVItemModel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainFragment extends Fragment {

    private RecyclerView rvMain;
    private List<MainRVItemModel> sections = new ArrayList<>();

    private NavController navController;

    public MainFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);

        init(view);

        sections = initList();

        IOnMainRVItemClick itemClickInterface = new IOnMainRVItemClick() {
            @Override
            public void onClick(MainRVItemModel item, int position) {
                switch(position){
                    case 0:
                        ((MainActivity)requireActivity()).navController.navigate(R.id.action_mainFragment_to_PFCFragment);
                        break;
                    case 1:
                        ((MainActivity)requireActivity()).navController.navigate(R.id.action_mainFragment_to_waterRegimeFragment);
                        break;
                    case 2:
                        ((MainActivity)requireActivity()).navController.navigate(R.id.action_mainFragment_to_stepsFragment);
                        break;
                    case 3:
                        ((MainActivity)requireActivity()).navController.navigate(R.id.action_mainFragment_to_medicineFragment);
                        break;
                    case 4:
                        ((MainActivity)requireActivity()).navController.navigate(R.id.action_mainFragment_to_workoutFragment);
                        break;
                    case 5:
                        ((MainActivity)requireActivity()).navController.navigate(R.id.action_mainFragment_to_breathTechnicFragment);
                }
            }
        };

        rvMain.setAdapter(new MainAdapter(getContext(), sections, itemClickInterface));

        return view;
    }

    private void init(View view){
        rvMain = view.findViewById(R.id.rvMain);
        navController = ((MainActivity)requireActivity()).navController;
    }

    private List<MainRVItemModel> initList(){
        List<MainRVItemModel> list =
                Arrays.asList(new MainRVItemModel(R.drawable.pfc_icon, getString(R.string.pfc), getResources().getColor(R.color.pfc)),
                        new MainRVItemModel(R.drawable.water_regime_icon, getString(R.string.water_regime), getResources().getColor(R.color.water_regime)),
                        new MainRVItemModel(R.drawable.steps_icon, getString(R.string.steps), getResources().getColor(R.color.steps)),
                        new MainRVItemModel(R.drawable.medicine_icon, getString(R.string.medicines), getResources().getColor(R.color.medicines)),
                        new MainRVItemModel(R.drawable.workout_icon, getString(R.string.workouts), getResources().getColor(R.color.workouts)),
                        new MainRVItemModel(R.drawable.breath_icon, getString(R.string.breath_technics), getResources().getColor(R.color.breath_technics)));
        return list;
    }
}