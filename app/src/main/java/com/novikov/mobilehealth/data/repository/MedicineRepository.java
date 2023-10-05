package com.novikov.mobilehealth.data.repository;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.novikov.mobilehealth.domain.models.MedicineModel;
import com.novikov.mobilehealth.domain.models.PFCModel;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class MedicineRepository {

    private static final String FILE_NAME = "medicine.json";

    public static List<MedicineModel> getMedicineInfo(Context context){

        try (FileInputStream inputStream = context.openFileInput(FILE_NAME);
             InputStreamReader streamReader = new InputStreamReader(inputStream)) {

            Gson gson = new Gson();
            List<MedicineModel> medicineInfo = gson.fromJson(streamReader, new TypeToken<List<MedicineModel>>(){}.getType());

            return medicineInfo;

        } catch (IOException e) {

            e.printStackTrace();
            return null;

        }
    }
    public static void saveMedicineInfo(Context context, List<MedicineModel> medicineInfo){

        Gson gson = new Gson();
        String json = gson.toJson(medicineInfo);

        try(FileOutputStream outputStream = context.openFileOutput(FILE_NAME, Context.MODE_PRIVATE)){

            outputStream.write(json.getBytes());

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
