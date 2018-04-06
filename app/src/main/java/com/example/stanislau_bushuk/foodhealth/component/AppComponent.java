package com.example.stanislau_bushuk.foodhealth.component;

import com.example.stanislau_bushuk.foodhealth.MainActivity;
import com.example.stanislau_bushuk.foodhealth.Model.NetWorkModel;
import com.example.stanislau_bushuk.foodhealth.modul.Api;
import com.example.stanislau_bushuk.foodhealth.modul.NetWorkModul;
import com.example.stanislau_bushuk.foodhealth.presentantion.searchPresentation.Presenters.SearchPresenter;

import javax.inject.Singleton;

import dagger.Component;


@Singleton
@Component(modules = {Api.class, NetWorkModul.class})
public interface AppComponent {//main component dagger

    void inject(SearchPresenter searchPresenter);

    void inject(MainActivity mainActivity);

    void inject(NetWorkModel netWorkModel);
}
