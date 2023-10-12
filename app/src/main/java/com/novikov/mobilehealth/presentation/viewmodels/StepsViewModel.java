package com.novikov.mobilehealth.presentation.viewmodels;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.novikov.mobilehealth.data.repository.StepsRepository;
import com.novikov.mobilehealth.domain.models.StepModel;

public class StepsViewModel extends ViewModel {

    private Context context;

    public MutableLiveData<Integer> stepToday = new MutableLiveData<>();

    private StepModel stepModel;

    public StepsViewModel(Context context) {
        this.context = context;
    }

    public void saveStepsData(StepModel stepModel){
        StepsRepository.saveStepInfo(context, stepModel);
    }

    public void getStepsData(){
        stepModel = StepsRepository.getStepsInfo(context);
    }
}
