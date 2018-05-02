package com.example.stanislau_bushuk.foodhealth.modul;

import com.example.stanislau_bushuk.foodhealth.presentantion.deepSearchPresentation.model.DeepSearchModel;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class DeepSearchModul {

    @Provides
    @Singleton
    public DeepSearchModel deepSearchModel(){
        return new DeepSearchModel();
    }
}
