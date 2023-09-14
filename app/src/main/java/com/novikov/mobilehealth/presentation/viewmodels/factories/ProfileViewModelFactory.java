package com.novikov.mobilehealth.presentation.viewmodels.factories;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.novikov.mobilehealth.presentation.viewmodels.ProfileViewModel;

public class ProfileViewModelFactory implements ViewModelProvider.Factory{

    private Context context;

    public ProfileViewModelFactory(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new ProfileViewModel(context);
    }
}
