package com.example.stanislau_bushuk.foodhealth.presentantion.favoritePresentation;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.example.stanislau_bushuk.foodhealth.App;
import com.example.stanislau_bushuk.foodhealth.model.RealmModel;
import com.example.stanislau_bushuk.foodhealth.model.pojo.Recipe;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.realm.RealmResults;
import timber.log.Timber;

@InjectViewState
public class FavoritePresenter extends MvpPresenter<FavoriteView>{
    @Inject
    RealmModel realmModel;

    FavoritePresenter(){
        App.getAppComponent().inject(this);
        call(realmModel.getData());

    }

    private void call(final Observable<RealmResults<Recipe>> observable){
        observable
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<RealmResults<Recipe>>() {
                    @Override
                    public void onSubscribe(final Disposable d) {
                        Timber.e("onSubscribe");
                    }

                    @Override
                    public void onNext(final RealmResults<Recipe> recipes) {
                        Timber.e("onNext");
                        if(recipes!=null){
                            getViewState().showList(recipes);
                            Timber.e("getViewState");
                        }
                    }

                    @Override
                    public void onError(final Throwable e) {
                        Timber.e(e);
                    }

                    @Override
                    public void onComplete() {
                        Timber.e("onComplete");
                    }
                });
    }
}
