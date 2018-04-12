package com.example.stanislau_bushuk.foodhealth.modul;

import android.content.Context;

import com.example.stanislau_bushuk.foodhealth.ResourceManager;

import javax.inject.Inject;

import dagger.Module;
import dagger.Provides;

@Module
public class ResourceManagerModul {
    private Context context;

    public ResourceManagerModul(final Context context){
        this.context=context;
    }

    @Provides
    @Inject
    public ResourceManager resourceManager(){
        return new ResourceManager(context);
    }
}
