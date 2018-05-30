package com.example.stanislau_bushuk.foodhealth.presentantion.splashPresentatiom;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.example.stanislau_bushuk.foodhealth.App;
import com.example.stanislau_bushuk.foodhealth.Constants;
import com.example.stanislau_bushuk.foodhealth.NavigationUtil;
import com.google.firebase.auth.FirebaseAuth;

import javax.inject.Inject;

import ru.terrakok.cicerone.NavigatorHolder;
import timber.log.Timber;


public class SplashActivity extends MvpAppCompatActivity implements MvpView {

    @Inject
    NavigatorHolder navigatorHolder;

    @InjectPresenter
    SplashActivityPresenter presenter;

    @Inject
    NavigationUtil navigationUtil() {
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

                if (FirebaseAuth.getInstance().getCurrentUser() == null) {
                    presenter.goTo(Constants.LOGIN_ACTIVITY);
                } else {
                    presenter.goTo(Constants.MAIN_ACTIVITY);

                }

            }
        }, Constants.WAIT);
    }


    @Override
    protected void onResume() {
        super.onResume();
        Timber.e("RESUME SPlASH");
        navigatorHolder.setNavigator(navigationUtil());
    }

    @Override
    protected void onPause() {
        super.onPause();
        Timber.e("Pause SPlASH");
        navigatorHolder.removeNavigator();
    }
}
