package com.novikov.mobilehealth.presentation.viewmodels;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.novikov.mobilehealth.R;
import com.novikov.mobilehealth.data.repository.PFCRepository;
import com.novikov.mobilehealth.data.repository.ProfileRepository;
import com.novikov.mobilehealth.domain.models.PFCModel;
import com.novikov.mobilehealth.domain.models.ProfileInfoModel;

import java.time.LocalDate;

public class PFCViewModel extends ViewModel {

    private Context context;

    public MutableLiveData<Integer> proteinGoal = new MutableLiveData<>(0);
    public MutableLiveData<Integer> fatGoal = new MutableLiveData<>(0);
    public MutableLiveData<Integer> carbGoal = new MutableLiveData<>(0);

    public MutableLiveData<Integer> proteinCount = new MutableLiveData<>(0);
    public MutableLiveData<Integer> fatCount = new MutableLiveData<>(0);
    public MutableLiveData<Integer> carbCount = new MutableLiveData<>(0);

    public PFCViewModel(Context context) {
        this.context = context;

        getCurrentData();
    }

    public void setGoal(){

        ProfileInfoModel profileInfo = ProfileRepository.getProfileInfo(context);

        if(profileInfo != null){

            proteinGoal.setValue((int)Math.round(profileInfo.getWeight()*1.5));
            fatGoal.setValue((int)Math.round(profileInfo.getWeight()*1.2));
            carbGoal.setValue((int)Math.round(profileInfo.getWeight()*3));

        }

        else{
            proteinGoal.setValue(0);
            fatGoal.setValue(0);
            carbGoal.setValue(0);
            Toast.makeText(context, context.getString(R.string.put_your_weight), Toast.LENGTH_SHORT).show();
        }
    }

    public void addAction(int protein, int fat, int carb){

        proteinCount.setValue(proteinCount.getValue() + protein);
        fatCount.setValue(fatCount.getValue() + fat);
        carbCount.setValue(carbCount.getValue() + carb);

        saveCurrentData();

    }

    public void getCurrentData(){
        PFCModel pfcModel = PFCRepository.getPFCInfo(context);

        if(pfcModel != null) {

            if(!pfcModel.getDate().equals(LocalDate.now().toString())){
                proteinCount.setValue(0);
                fatCount.setValue(0);
                carbCount.setValue(0);

                Log.i("getCurrentDataIF", "yes");

                Log.i("localdate", LocalDate.now().toString());
            }
            else{
                proteinCount.setValue(pfcModel.getProtein());
                fatCount.setValue(pfcModel.getFats());
                carbCount.setValue(pfcModel.getCarbs());

                Log.i("getCurrentDataIF", "no");
            }

            Log.i("getCurrentData", "yes");
        }

        Log.i("getCurrentData", "end");
    }

    public void saveCurrentData(){
        PFCModel pfcModel = new PFCModel(proteinCount.getValue(), fatCount.getValue(), carbCount.getValue(), LocalDate.now().toString());
        PFCRepository.savePFCInfo(context, pfcModel);

        Log.i("saveCurrentData", "yes");
    }
}
