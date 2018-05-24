package com.example.stanislau_bushuk.foodhealth.presentantion.splashPresentatiom;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;


import com.example.stanislau_bushuk.foodhealth.App;
import com.example.stanislau_bushuk.foodhealth.Constants;
import com.example.stanislau_bushuk.foodhealth.MainActivity;
import com.example.stanislau_bushuk.foodhealth.NavigationUtil;
import com.example.stanislau_bushuk.foodhealth.cicerone.OwnRouter;

import javax.inject.Inject;

import ru.terrakok.cicerone.NavigatorHolder;


public class SplashActivity extends AppCompatActivity {


    @Inject
    OwnRouter router;

    @Inject
    NavigatorHolder navigatorHolder;

    @Inject
    NavigationUtil navigationUtil(){
        return new NavigationUtil(this);
    }


    @Override
    protected void onCreate(@Nullable final Bundle savedInstanceState) {
        App.getAppComponent().inject(this);

        super.onCreate(savedInstanceState);

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                router.replaceScreen(Constants.MAIN_ACTIVITY);
                finish();
            }
        }, Constants.WAIT);
    }

    @Override
    protected void onResume() {
        super.onResume();

        navigatorHolder.setNavigator(navigationUtil());
    }

    @Override
    protected void onPause() {
        super.onPause();

        navigatorHolder.removeNavigator();
    }
}
