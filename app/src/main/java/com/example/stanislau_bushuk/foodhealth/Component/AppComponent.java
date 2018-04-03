package com.example.stanislau_bushuk.foodhealth.Component;

import com.example.stanislau_bushuk.foodhealth.MainActivity;
import com.example.stanislau_bushuk.foodhealth.Modul.Api;
import com.example.stanislau_bushuk.foodhealth.Presenters.SearchPresenter;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = Api.class)
public interface AppComponent {//main component dagger
    void inject (SearchPresenter searchPresenter);
    void inject (MainActivity mainActivity);
}
