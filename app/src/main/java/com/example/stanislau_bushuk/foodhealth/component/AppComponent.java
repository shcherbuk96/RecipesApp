package com.example.stanislau_bushuk.foodhealth.component;


import com.example.stanislau_bushuk.foodhealth.model.NetWorkModel;
import com.example.stanislau_bushuk.foodhealth.modul.Api;
import com.example.stanislau_bushuk.foodhealth.modul.NetWorkModul;
import com.example.stanislau_bushuk.foodhealth.modul.ResourceManagerModul;
import com.example.stanislau_bushuk.foodhealth.presentantion.searchPresentation.presenters.SearchPresenter;

import javax.inject.Singleton;

import dagger.Component;


@Singleton
@Component(modules = {Api.class, NetWorkModul.class, ResourceManagerModul.class})
public interface AppComponent {

    void inject(SearchPresenter searchPresenter);

    void inject(NetWorkModel netWorkModel);
}
