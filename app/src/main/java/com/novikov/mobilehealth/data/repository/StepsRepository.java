package com.novikov.mobilehealth.data.repository;

import android.content.Context;

import com.google.gson.Gson;
import com.novikov.mobilehealth.domain.models.PFCModel;
import com.novikov.mobilehealth.domain.models.StepModel;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class StepsRepository {
    private static final String FILE_NAME = "steps.json";

    public static StepModel getStepsInfo(Context context){

        try (FileInputStream inputStream = context.openFileInput(FILE_NAME);
             InputStreamReader streamReader = new InputStreamReader(inputStream)) {

            Gson gson = new Gson();
            StepModel stepInfo = gson.fromJson(streamReader, StepModel.class);

            return stepInfo;

        } catch (IOException e) {

            e.printStackTrace();
            return null;

        }
    }
    public static void saveStepInfo(Context context, StepModel stepInfo){

        Gson gson = new Gson();
        String json = gson.toJson(stepInfo);

        try(FileOutputStream outputStream = context.openFileOutput(FILE_NAME, Context.MODE_PRIVATE)){

            outputStream.write(json.getBytes());

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
