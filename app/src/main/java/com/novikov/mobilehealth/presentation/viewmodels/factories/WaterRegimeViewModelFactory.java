package com.novikov.mobilehealth.presentation.viewmodels.factories;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.novikov.mobilehealth.presentation.viewmodels.WaterRegimeViewModel;

public class WaterRegimeViewModelFactory implements ViewModelProvider.Factory {

    private Context context;

    public WaterRegimeViewModelFactory(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new WaterRegimeViewModel(context);
    }
}
