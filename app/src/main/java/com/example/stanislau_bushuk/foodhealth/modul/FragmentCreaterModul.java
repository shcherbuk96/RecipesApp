package com.example.stanislau_bushuk.foodhealth.modul;

import com.example.stanislau_bushuk.foodhealth.FragmentCreater;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class FragmentCreaterModul {

    @Provides
    @Singleton
    FragmentCreater fragmentCreater(){
        return new FragmentCreater();
    }
}
