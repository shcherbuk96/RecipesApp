package com.example.stanislau_bushuk.foodhealth.Modul;

import com.example.stanislau_bushuk.foodhealth.API.IAPI;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class Api {

    @Provides
    @Singleton
    public IAPI getIAPI() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.edamam.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        IAPI iapi = retrofit.create(IAPI.class);
        return iapi;
    }
}
