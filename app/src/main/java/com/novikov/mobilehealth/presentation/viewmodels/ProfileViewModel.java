package com.novikov.mobilehealth.presentation.viewmodels;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.novikov.mobilehealth.data.repository.ProfileRepository;
import com.novikov.mobilehealth.domain.models.ProfileInfoModel;

import java.util.Objects;

public class ProfileViewModel extends ViewModel {

    public MutableLiveData<Integer> growth = new MutableLiveData<>(0);
    public MutableLiveData<Integer> weight = new MutableLiveData<>(0);
    public MutableLiveData<Long> birthDate = new MutableLiveData<>();
    private Context context;

    public ProfileViewModel(@NonNull Context context) {
        this.context = context;
    }

    public void saveInfo(){
        if (growth != null && weight != null) {
            ProfileRepository.saveProfileInfo(context,
                    new ProfileInfoModel(growth.getValue(), weight.getValue(), birthDate.getValue()));
            Toast.makeText(context, "Данные успешно сохранены", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(context, "Неправильное заполнение полей", Toast.LENGTH_SHORT).show();
        }
    }

    public void getInfo(){

        ProfileInfoModel profileInfo = ProfileRepository.getProfileInfo(context);

        if(profileInfo != null){
            growth.setValue(profileInfo.getHeight());
            weight.setValue(profileInfo.getWeight());
            birthDate.setValue(profileInfo.getBirthDate());
        }

    }
}
