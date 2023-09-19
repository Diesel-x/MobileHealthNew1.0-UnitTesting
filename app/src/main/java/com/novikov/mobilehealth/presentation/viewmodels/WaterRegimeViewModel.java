package com.novikov.mobilehealth.presentation.viewmodels;

import android.content.Context;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.novikov.mobilehealth.R;
import com.novikov.mobilehealth.data.repository.ProfileRepository;
import com.novikov.mobilehealth.data.repository.WaterRegimeRepository;
import com.novikov.mobilehealth.domain.models.ProfileInfoModel;
import com.novikov.mobilehealth.domain.models.WaterRegimeModel;

import java.time.LocalDate;
import java.util.Objects;

public class WaterRegimeViewModel extends ViewModel {

    private Context context;
    public MutableLiveData<Integer> waterCurrentCount = new MutableLiveData<>(0);
    public MutableLiveData<Integer> waterGoalCount = new MutableLiveData<>(0);
    public MutableLiveData<Integer> rowGoal = new MutableLiveData<>(0);

    private WaterRegimeModel waterRegimeModel;

    public WaterRegimeViewModel(Context context) {

        this.context = context;

        getCurrentData();
        setWaterGoal();

    }

    public void addAction(int waterCount){
        waterCurrentCount.setValue(waterCurrentCount.getValue()+waterCount);
        saveCurrentData();
    }

    public void getCurrentData(){
        waterRegimeModel = WaterRegimeRepository.getWaterRegimeInfo(context);

        if (waterRegimeModel != null){

            if(!Objects.equals(waterRegimeModel.getDate(), LocalDate.now().toString())){

                if (waterCurrentCount.getValue() <= waterGoalCount.getValue()){
                    rowGoal.setValue(0);
                }

                waterCurrentCount.setValue(0);
                return;
            }
            else{
                waterCurrentCount.setValue(waterRegimeModel.getWaterCurrentCount());
            }
            rowGoal.setValue(waterRegimeModel.getGoalRow());

        }
    }

    public void saveCurrentData(){

        WaterRegimeRepository.saveWaterRegimeInfo(context, new WaterRegimeModel(
                waterCurrentCount.getValue(),
                rowGoal.getValue(),
                LocalDate.now().toString()));
    }

    public void setWaterGoal(){

        ProfileInfoModel profileInfoModel = ProfileRepository.getProfileInfo(context);

        if(profileInfoModel != null)
            waterGoalCount.setValue(profileInfoModel.getWeight()*30);
        else{
            waterGoalCount.setValue(0);
            Toast.makeText(context, context.getString(R.string.put_your_weight), Toast.LENGTH_SHORT).show();
        }

    }

}
