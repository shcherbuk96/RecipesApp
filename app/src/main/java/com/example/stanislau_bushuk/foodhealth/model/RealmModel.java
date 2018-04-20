package com.example.stanislau_bushuk.foodhealth.model;

import com.example.stanislau_bushuk.foodhealth.model.pojo.Recipe;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.realm.Realm;
import io.realm.RealmResults;
import timber.log.Timber;

public class RealmModel {

    private Realm realm;

    public RealmModel() {
        realm = Realm.getDefaultInstance();
    }

    public void addDataToRealm(final Recipe recipe) {
        realm.beginTransaction();
        realm.insertOrUpdate(recipe);
        realm.commitTransaction();
        Timber.e(String.valueOf(realm.where(Recipe.class).findAll()));
    }

    public void removeDataToRealm(final Recipe recipe) {
        final RealmResults<Recipe> students = realm.where(Recipe.class).findAll();

        final Recipe r = students.where().equalTo("uri", recipe.getUri()).findFirst();

        if (r != null) {
            realm.beginTransaction();
            r.deleteFromRealm();
            realm.commitTransaction();
        }

        Timber.e(String.valueOf(realm.where(Recipe.class).findAll()));
    }


    public Observable<RealmResults<Recipe>> getData() {
        final RealmResults<Recipe> recipes = realm.where(Recipe.class).findAll();

        if (recipes != null) {
            Timber.e("getData");
            final Observable<RealmResults<Recipe>> observable = recipes.asFlowable().toObservable();

            return observable.subscribeOn(AndroidSchedulers.mainThread());
        }

        Timber.e("getData null");

        return null;
    }

    public boolean checkFavorite(final Recipe recipe) {
        final RealmResults<Recipe> recipes = realm.where(Recipe.class).findAll();
        final Recipe r = recipes.where().equalTo("uri", recipe.getUri()).findFirst();

        if (r != null) {
            return true;
        }

        return false;
    }
}
