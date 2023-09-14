package com.novikov.mobilehealth.data.repository;

import android.content.Context;

import com.google.gson.Gson;
import com.novikov.mobilehealth.domain.models.PFCModel;
import com.novikov.mobilehealth.domain.models.ProfileInfoModel;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class PFCRepository {

    private static final String FILE_NAME = "pfc.json";

    public static PFCModel getPFCInfo(Context context){

        try (FileInputStream inputStream = context.openFileInput(FILE_NAME);
             InputStreamReader streamReader = new InputStreamReader(inputStream)) {

            Gson gson = new Gson();
            PFCModel pfcInfo = gson.fromJson(streamReader, PFCModel.class);

            return pfcInfo;

        } catch (IOException e) {

            e.printStackTrace();
            return null;

        }
    }
    public static void savePFCInfo(Context context, PFCModel pfcInfo){

        Gson gson = new Gson();
        String json = gson.toJson(pfcInfo);

        try(FileOutputStream outputStream = context.openFileOutput(FILE_NAME, Context.MODE_PRIVATE)){

            outputStream.write(json.getBytes());

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
