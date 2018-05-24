package com.example.stanislau_bushuk.foodhealth.component;


import com.example.stanislau_bushuk.foodhealth.MainActivity;
import com.example.stanislau_bushuk.foodhealth.MainActivityPresenter;
import com.example.stanislau_bushuk.foodhealth.NavigationUtil;
import com.example.stanislau_bushuk.foodhealth.cicerone.OwnNavigator;
import com.example.stanislau_bushuk.foodhealth.model.CardNetWorkModel;
import com.example.stanislau_bushuk.foodhealth.model.NetWorkModel;
import com.example.stanislau_bushuk.foodhealth.modul.Api;
import com.example.stanislau_bushuk.foodhealth.modul.CardNetWorkModul;
import com.example.stanislau_bushuk.foodhealth.modul.CiceroneModul;
import com.example.stanislau_bushuk.foodhealth.modul.DeepSearchModul;
import com.example.stanislau_bushuk.foodhealth.modul.FragmentCreaterModul;
import com.example.stanislau_bushuk.foodhealth.modul.NetWorkModul;
import com.example.stanislau_bushuk.foodhealth.modul.NetworkDeepSearchModul;
import com.example.stanislau_bushuk.foodhealth.modul.RealmModul;
import com.example.stanislau_bushuk.foodhealth.modul.ResourceManagerModul;
import com.example.stanislau_bushuk.foodhealth.presentantion.cardPresentation.CardActivity;
import com.example.stanislau_bushuk.foodhealth.presentantion.cardPresentation.CardPresenter;
import com.example.stanislau_bushuk.foodhealth.presentantion.deepSearchPresentation.DeepSearchActivity;
import com.example.stanislau_bushuk.foodhealth.presentantion.deepSearchPresentation.model.NetWorkModelDeepSearch;
import com.example.stanislau_bushuk.foodhealth.presentantion.deepSearchPresentation.presenters.DeepSearchPresenter;
import com.example.stanislau_bushuk.foodhealth.presentantion.favoritePresentation.FavoriteAdapter;
import com.example.stanislau_bushuk.foodhealth.presentantion.favoritePresentation.FavoritePresenter;
import com.example.stanislau_bushuk.foodhealth.presentantion.searchPresentation.SearchFragment;
import com.example.stanislau_bushuk.foodhealth.presentantion.searchPresentation.adapter.RecyclerAdapter;
import com.example.stanislau_bushuk.foodhealth.presentantion.searchPresentation.presenters.SearchPresenter;
import com.example.stanislau_bushuk.foodhealth.presentantion.splashPresentatiom.SplashActivity;

import javax.inject.Singleton;

import dagger.Component;


@Singleton
@Component(modules = {Api.class, NetWorkModul.class, CardNetWorkModul.class,
        ResourceManagerModul.class, DeepSearchModul.class, NetworkDeepSearchModul.class,
        RealmModul.class, CiceroneModul.class, FragmentCreaterModul.class,})
public interface AppComponent {

    void inject(SearchPresenter searchPresenter);

    void inject(SearchFragment searchFragment);

    void inject(CardPresenter cardPresenter);

    void inject(CardActivity cardActivity);

    void inject(MainActivityPresenter mainActivityPresenter);

    void inject(NetWorkModel netWorkModel);

    void inject(CardNetWorkModel cardNetWorkModel);

    void inject(FavoritePresenter favoritePresenter);

    void inject(FavoriteAdapter favoriteAdapter);

    void inject(RecyclerAdapter recyclerAdapter);

    void inject(DeepSearchPresenter deepSearchPresenter);

    void inject(NetWorkModelDeepSearch netWorkModelDeepSearch);

    void inject(DeepSearchActivity deepSearchActivity);

    void inject(MainActivity mainActivity);

    void inject (NavigationUtil navigationUtil);

    void inject (SplashActivity splashActivity);

    void inject (OwnNavigator ownNavigator);

}
