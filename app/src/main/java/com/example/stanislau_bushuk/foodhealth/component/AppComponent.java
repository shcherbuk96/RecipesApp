package com.example.stanislau_bushuk.foodhealth.component;


import com.example.stanislau_bushuk.foodhealth.model.CardNetWorkModel;
import com.example.stanislau_bushuk.foodhealth.model.NetWorkModel;
import com.example.stanislau_bushuk.foodhealth.modul.Api;
import com.example.stanislau_bushuk.foodhealth.modul.CardNetWorkModul;
import com.example.stanislau_bushuk.foodhealth.modul.NetWorkModul;
import com.example.stanislau_bushuk.foodhealth.modul.RealmModul;
import com.example.stanislau_bushuk.foodhealth.modul.ResourceManagerModul;
import com.example.stanislau_bushuk.foodhealth.presentantion.cardPresentation.CardPresenter;
import com.example.stanislau_bushuk.foodhealth.presentantion.favoritePresentation.FavoriteAdapter;
import com.example.stanislau_bushuk.foodhealth.presentantion.favoritePresentation.FavoritePresenter;
import com.example.stanislau_bushuk.foodhealth.presentantion.searchPresentation.adapter.RecyclerAdapter;
import com.example.stanislau_bushuk.foodhealth.presentantion.searchPresentation.presenters.SearchPresenter;

import javax.inject.Singleton;

import dagger.Component;


@Singleton
@Component(modules = {Api.class, NetWorkModul.class, CardNetWorkModul.class, ResourceManagerModul.class, RealmModul.class})
public interface AppComponent {

    void inject(SearchPresenter searchPresenter);

    void inject(CardPresenter cardPresenter);

    void inject(NetWorkModel netWorkModel);

    void inject(CardNetWorkModel cardNetWorkModel);

    void inject(FavoritePresenter favoritePresenter);

    void inject(FavoriteAdapter favoriteAdapter);

    void inject(RecyclerAdapter recyclerAdapter);
}
