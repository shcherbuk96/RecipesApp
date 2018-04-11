package com.example.stanislau_bushuk.foodhealth;

import android.app.Application;

import com.example.stanislau_bushuk.foodhealth.component.AppComponent;
import com.example.stanislau_bushuk.foodhealth.component.DaggerAppComponent;
import com.example.stanislau_bushuk.foodhealth.modul.Api;
import com.example.stanislau_bushuk.foodhealth.modul.NetWorkModul;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import timber.log.Timber;

public class App extends Application {

    private static AppComponent appComponent;

    public static AppComponent getAppComponent() {
        return appComponent;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        setRealm();
        Timber.plant(new Timber.DebugTree());
        appComponent = buildComponent();
    }

    public void setRealm() {
        Realm.init(this);
        final RealmConfiguration realmConfig = new RealmConfiguration.Builder()
                .deleteRealmIfMigrationNeeded()
                .name("realm.realm")
                .build();
        Realm.setDefaultConfiguration(realmConfig);
    }

    public AppComponent buildComponent() {
        return DaggerAppComponent.builder()
                .netWorkModul(new NetWorkModul())
                .api(new Api())
                .build();
    }
}
