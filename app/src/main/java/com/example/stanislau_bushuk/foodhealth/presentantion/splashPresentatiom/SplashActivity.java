package com.example.stanislau_bushuk.foodhealth.presentantion.splashPresentatiom;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.stanislau_bushuk.foodhealth.ActivityManager;

public class SplashActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityManager.startMainActivity(this);
        finish();
    }
}
