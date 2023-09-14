package com.novikov.mobilehealth.presentation.view;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.novikov.mobilehealth.R;

import java.util.Locale;

public class SettingsDialog extends DialogFragment {

    private Spinner spinnerLocalization;
    private EditText etRestTime;
    private Switch switchPushNotification;

    private Button btnSave;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_settings, null);
        builder.setView(view);

        init(view);

        setCurrentLocale();

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("settings", "Locale");
                switch(spinnerLocalization.getSelectedItemPosition()){
                    case 0:
                        setLocale("en", "GB");
                        break;
                    case 1:
                        setLocale("ru", "Ru");
                }
            }
        });

        return builder.create();
    }

    private void init(View view){
        spinnerLocalization = view.findViewById(R.id.spinnerLocalization);
        etRestTime = view.findViewById(R.id.etRestTime);
        switchPushNotification = view.findViewById(R.id.switchPushNotification);
        btnSave = view.findViewById(R.id.btnSettingsSave);
    }

    private void setLocale(String languageCode, String countryCode){
        Locale locale = new Locale(languageCode, countryCode);
        if(!locale.equals(Locale.getDefault())){
            Locale.setDefault(locale);
            Resources resources = getActivity().getResources();
            Configuration config = resources.getConfiguration();
            config.setLocale(locale);
            resources.updateConfiguration(config, getActivity().getResources().getDisplayMetrics());
            getActivity().recreate();
        }
    }
    private void setCurrentLocale(){
        Resources resources = getActivity().getResources();
        Configuration config = resources.getConfiguration();

        if (Locale.getDefault().toLanguageTag().equals("en-GB") || Locale.getDefault().toLanguageTag().equals("en-EN")){
            spinnerLocalization.setSelection(0);
        }
        if(Locale.getDefault().toLanguageTag().equals("ru-RU")){
            spinnerLocalization.setSelection(1);
        }
        Log.i("locales", Locale.getDefault().toLanguageTag());
    }
}
