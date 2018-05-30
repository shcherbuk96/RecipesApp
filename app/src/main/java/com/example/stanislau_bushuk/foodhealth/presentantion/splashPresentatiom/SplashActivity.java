package com.example.stanislau_bushuk.foodhealth.presentantion.splashPresentatiom;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.stanislau_bushuk.foodhealth.ActivityManager;
import com.example.stanislau_bushuk.foodhealth.App;
import com.example.stanislau_bushuk.foodhealth.Constants;
import com.example.stanislau_bushuk.foodhealth.model.RealmModel;
import com.google.firebase.auth.FirebaseAuth;

import javax.inject.Inject;


public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable final Bundle savedInstanceState) {
        App.getAppComponent().inject(this);

        super.onCreate(savedInstanceState);

        final Context context = this;
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (FirebaseAuth.getInstance().getCurrentUser() == null) {
                    ActivityManager.startLoginActivity(context);
                    finish();
                } else {
                    ActivityManager.startMainActivity(context);
                    finish();
                }
            }
        }, Constants.WAIT);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    private void checkRealmInfo(){

    }

}
