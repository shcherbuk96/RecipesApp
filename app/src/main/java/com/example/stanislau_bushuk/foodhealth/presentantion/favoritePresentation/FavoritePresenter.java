package com.example.stanislau_bushuk.foodhealth.presentantion.favoritePresentation;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.example.stanislau_bushuk.foodhealth.App;
import com.example.stanislau_bushuk.foodhealth.cicerone.OwnRouter;
import com.example.stanislau_bushuk.foodhealth.model.RealmModel;
import com.example.stanislau_bushuk.foodhealth.model.pojo.Recipe;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.realm.RealmResults;
import ru.terrakok.cicerone.NavigatorHolder;
import timber.log.Timber;

@InjectViewState
public class FavoritePresenter extends MvpPresenter<FavoriteView> implements CallBackRealmData {

    @Inject
    RealmModel realmModel;

    @Inject
    OwnRouter router;

    FavoritePresenter() {
        App.getAppComponent().inject(this);
        realmModel.setCallBack(this);
    }

    public void dataRealm() {
        realmModel.getData();
    }

    @Override
    public void getDataRealm(final Observable<RealmResults<Recipe>> observable) {
        observable
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<RealmResults<Recipe>>() {
                    @Override
                    public void onSubscribe(final Disposable d) {
                    }

                    @Override
                    public void onNext(final RealmResults<Recipe> recipes) {
                        if (recipes != null) {
                            getViewState().showList(recipes);
                        }
                    }

                    @Override
                    public void onError(final Throwable e) {
                        Timber.e(e);
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }

    public void deleteFromFavorite(final Recipe recipe) {
        realmModel.deleteFromFavorite(recipe);
    }

    public void goTo(final String screenKey,final String uri){
        router.navigateTo(screenKey, uri);
    }
}
