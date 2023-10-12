package com.novikov.mobilehealth.presentation.viewmodels.factories;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.novikov.mobilehealth.presentation.viewmodels.StepsViewModel;

public class StepsViewModelFactory implements ViewModelProvider.Factory {

    private Context context;

    public StepsViewModelFactory(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new StepsViewModel(context);
    }
}
