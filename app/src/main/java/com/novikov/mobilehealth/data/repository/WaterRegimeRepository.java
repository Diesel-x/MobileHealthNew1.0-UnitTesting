package com.novikov.mobilehealth.data.repository;

import android.content.Context;

import com.google.gson.Gson;
import com.novikov.mobilehealth.domain.models.PFCModel;
import com.novikov.mobilehealth.domain.models.WaterRegimeModel;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class WaterRegimeRepository {

        private static final String FILE_NAME = "water_regime.json";

    public static WaterRegimeModel getWaterRegimeInfo(Context context){

        try (FileInputStream inputStream = context.openFileInput(FILE_NAME);
             InputStreamReader streamReader = new InputStreamReader(inputStream)) {

            Gson gson = new Gson();
            WaterRegimeModel waterRegimeInfo = gson.fromJson(streamReader, WaterRegimeModel.class);

            return waterRegimeInfo;

        } catch (IOException e) {

            e.printStackTrace();
            return null;

        }
    }
    public static void saveWaterRegimeInfo(Context context, WaterRegimeModel waterRegimeModel){

        Gson gson = new Gson();
        String json = gson.toJson(waterRegimeModel);

        try(FileOutputStream outputStream = context.openFileOutput(FILE_NAME, Context.MODE_PRIVATE)){

            outputStream.write(json.getBytes());

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
