package com.example.stanislau_bushuk.foodhealth.modul;

import com.example.stanislau_bushuk.foodhealth.model.NetWorkModel;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class NetWorkModul {
    
    @Provides
    @Singleton
    public NetWorkModel netWorkModel() {
        return new NetWorkModel();
    }
}
