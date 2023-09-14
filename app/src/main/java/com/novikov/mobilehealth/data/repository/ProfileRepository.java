package com.novikov.mobilehealth.data.repository;

import android.content.Context;

import com.google.gson.Gson;
import com.novikov.mobilehealth.domain.models.ProfileInfoModel;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class ProfileRepository {

    private static final String FILE_NAME = "profile.json";

    public static ProfileInfoModel getProfileInfo(Context context){

        try (FileInputStream inputStream = context.openFileInput(FILE_NAME);
             InputStreamReader streamReader = new InputStreamReader(inputStream)) {

            Gson gson = new Gson();
            ProfileInfoModel profileInfo = gson.fromJson(streamReader, ProfileInfoModel.class);

            return profileInfo;

        } catch (IOException e) {

            e.printStackTrace();
            return null;

        }
    }
    public static void saveProfileInfo(Context context, ProfileInfoModel profileInfo){

        Gson gson = new Gson();
        String json = gson.toJson(profileInfo);

        try(FileOutputStream outputStream = context.openFileOutput(FILE_NAME, Context.MODE_PRIVATE)){

            outputStream.write(json.getBytes());

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
