package com.example.stanislau_bushuk.foodhealth.modul;

import com.example.stanislau_bushuk.foodhealth.model.FirebaseModel;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class FirebaseModul {

    @Provides
    @Singleton
    FirebaseModel firebaseModel(){
        return new FirebaseModel();
    }
}
