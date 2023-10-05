package com.novikov.mobilehealth.presentation.viewmodels;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.novikov.mobilehealth.data.repository.MedicineRepository;
import com.novikov.mobilehealth.domain.models.MedicineModel;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class MedicineViewModel extends ViewModel {

    private Context context;

    public MutableLiveData<List<MedicineModel>> medicineList = new MutableLiveData<>();

    public MedicineViewModel(Context context){

        this.context = context;
        medicineList.setValue(new ArrayList<>());

    }

    public void addMedicine(MedicineModel medicineModel){

        Log.i("vm", String.valueOf(medicineList.getValue().size()));
        Log.i("vm", "add");
    }

    public void getCurrentData(){
        List<MedicineModel> medicines = MedicineRepository.getMedicineInfo(context);

        if(medicines != null) {
            medicineList.setValue(medicines);
            Log.i("currentdataif", "complete");
        }

    }

    public void saveCurrentData(){
        MedicineRepository.saveMedicineInfo(context, medicineList.getValue());
    }

}
