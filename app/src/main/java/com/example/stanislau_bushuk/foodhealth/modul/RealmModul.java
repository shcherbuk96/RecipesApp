package com.example.stanislau_bushuk.foodhealth.modul;

import com.example.stanislau_bushuk.foodhealth.model.RealmModel;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class RealmModul {
    @Provides
    @Singleton
    public RealmModel realmModel() {
        return new RealmModel();
    }
}
