package com.example.stanislau_bushuk.foodhealth.component;


import com.example.stanislau_bushuk.foodhealth.MainActivity;
import com.example.stanislau_bushuk.foodhealth.MainActivityPresenter;
import com.example.stanislau_bushuk.foodhealth.NavigationUtil;
import com.example.stanislau_bushuk.foodhealth.cicerone.OwnNavigator;
import com.example.stanislau_bushuk.foodhealth.model.CardNetWorkModel;
import com.example.stanislau_bushuk.foodhealth.model.FirebaseModel;
import com.example.stanislau_bushuk.foodhealth.model.NetWorkModel;
import com.example.stanislau_bushuk.foodhealth.modul.Api;
import com.example.stanislau_bushuk.foodhealth.modul.CardNetWorkModul;
import com.example.stanislau_bushuk.foodhealth.modul.CiceroneModul;
import com.example.stanislau_bushuk.foodhealth.modul.DeepSearchModul;
import com.example.stanislau_bushuk.foodhealth.modul.FirebaseModul;
import com.example.stanislau_bushuk.foodhealth.modul.FragmentCreaterModul;
import com.example.stanislau_bushuk.foodhealth.modul.LoginModul;
import com.example.stanislau_bushuk.foodhealth.modul.NetWorkModul;
import com.example.stanislau_bushuk.foodhealth.modul.NetworkDeepSearchModul;
import com.example.stanislau_bushuk.foodhealth.modul.RealmModul;
import com.example.stanislau_bushuk.foodhealth.modul.ResourceManagerModul;
import com.example.stanislau_bushuk.foodhealth.presentantion.addOwnRecipe.AddOwnRecipeActivity;
import com.example.stanislau_bushuk.foodhealth.presentantion.addOwnRecipe.presenters.AddOwnRecipePresenter;
import com.example.stanislau_bushuk.foodhealth.presentantion.authPresentation.LoginActivity;
import com.example.stanislau_bushuk.foodhealth.presentantion.authPresentation.LoginPresenter;
import com.example.stanislau_bushuk.foodhealth.presentantion.authPresentation.RegistrationActivity;
import com.example.stanislau_bushuk.foodhealth.presentantion.cardOwnRecipePresentation.CardOwnRecipeActivity;
import com.example.stanislau_bushuk.foodhealth.presentantion.cardOwnRecipePresentation.CardOwnRecipePresenter;
import com.example.stanislau_bushuk.foodhealth.presentantion.cardPresentation.CardActivity;
import com.example.stanislau_bushuk.foodhealth.presentantion.cardPresentation.CardPresenter;
import com.example.stanislau_bushuk.foodhealth.presentantion.deepSearchPresentation.DeepSearchActivity;
import com.example.stanislau_bushuk.foodhealth.presentantion.deepSearchPresentation.model.NetWorkModelDeepSearch;
import com.example.stanislau_bushuk.foodhealth.presentantion.deepSearchPresentation.presenters.DeepSearchPresenter;
import com.example.stanislau_bushuk.foodhealth.presentantion.favoritePresentation.FavoriteFragment;
import com.example.stanislau_bushuk.foodhealth.presentantion.favoritePresentation.FavoritePresenter;
import com.example.stanislau_bushuk.foodhealth.presentantion.ownRecipesPresentation.presenters.OwnRecipesPresenter;
import com.example.stanislau_bushuk.foodhealth.presentantion.profilePresentation.ProfileFragment;
import com.example.stanislau_bushuk.foodhealth.presentantion.profilePresentation.presenters.ProfilePresenter;
import com.example.stanislau_bushuk.foodhealth.presentantion.searchPresentation.SearchFragment;
import com.example.stanislau_bushuk.foodhealth.presentantion.searchPresentation.presenters.SearchPresenter;
import com.example.stanislau_bushuk.foodhealth.presentantion.splashPresentatiom.SplashActivity;
import com.example.stanislau_bushuk.foodhealth.presentantion.splashPresentatiom.SplashActivityPresenter;

import javax.inject.Singleton;

import dagger.Component;


@Singleton
@Component(modules = {Api.class, NetWorkModul.class, CardNetWorkModul.class,
        ResourceManagerModul.class, DeepSearchModul.class, NetworkDeepSearchModul.class,
        RealmModul.class, LoginModul.class, CiceroneModul.class,
        FirebaseModul.class, FragmentCreaterModul.class, FragmentCreaterModul.class})


public interface AppComponent {

    void inject(SearchPresenter searchPresenter);

    void inject(SearchFragment searchFragment);

    void inject(CardPresenter cardPresenter);

    void inject(CardActivity cardActivity);

    void inject(MainActivityPresenter mainActivityPresenter);

    void inject(NetWorkModel netWorkModel);

    void inject(FavoritePresenter favoritePresenter);

    void inject(DeepSearchPresenter deepSearchPresenter);

    void inject(NetWorkModelDeepSearch netWorkModelDeepSearch);

    void inject(DeepSearchActivity deepSearchActivity);

    void inject(MainActivity mainActivity);

    void inject(NavigationUtil navigationUtil);

    void inject(SplashActivity splashActivity);

    void inject(OwnNavigator ownNavigator);

    void inject(FavoriteFragment favoriteFragment);

    void inject(LoginPresenter loginPresenter);

    void inject(FirebaseModel firebaseModel);

    void inject(SplashActivityPresenter splashActivityPresenter);

    void inject(LoginActivity loginActivity);

    void inject(RegistrationActivity registrationActivity);

    void inject(ProfilePresenter profilePresenter);

    void inject(ProfileFragment profileFragment);

    void inject(OwnRecipesPresenter ownRecipesPresenter);

    void inject(CardNetWorkModel cardNetWorkModel);

    void inject(AddOwnRecipeActivity addOwnRecipeActivity);

    void inject(AddOwnRecipePresenter addOwnRecipePresenter);

    void inject(CardOwnRecipePresenter cardOwnRecipePresenter);

    void inject(CardOwnRecipeActivity cardOwnRecipeActivity);
}
