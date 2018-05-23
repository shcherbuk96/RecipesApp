package com.example.stanislau_bushuk.foodhealth.modul;

import android.content.Context;

import com.example.stanislau_bushuk.foodhealth.NavigationUtil;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class NavigationUtilModul {

    private Context context;

    public NavigationUtilModul(final Context context){
        this.context=context;
    }

    @Provides
    @Singleton
    NavigationUtil navigationUtil(){
        return new NavigationUtil(context);
    }
}
