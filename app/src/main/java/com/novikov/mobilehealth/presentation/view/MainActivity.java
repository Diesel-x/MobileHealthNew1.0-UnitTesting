package com.novikov.mobilehealth.presentation.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.novikov.mobilehealth.R;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView bnvMenu;
    public NavController navController;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(getSupportActionBar() != null)
            getSupportActionBar().hide();

        init();

        bnvMenu.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if(item.getItemId() == R.id.homeItem){
                    navController.navigate(R.id.mainFragment);
                    return true;
                }
                else if (item.getItemId() == R.id.profileItem) {
                    navController.navigate(R.id.profileFragment);
                    return true;
                }
                return false;
            }
        });

    }

    private void init(){

        bnvMenu = findViewById(R.id.bnvMain);
        navController = Navigation.findNavController(this, R.id.nav_host);
        Log.e("navcontroller", Navigation.findNavController(this, R.id.nav_host).toString());
    }
}