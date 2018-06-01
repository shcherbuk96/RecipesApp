package com.example.stanislau_bushuk.foodhealth.presentantion.cardPresentation;

import android.net.Uri;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.example.stanislau_bushuk.foodhealth.App;
import com.example.stanislau_bushuk.foodhealth.cicerone.OwnRouter;
import com.example.stanislau_bushuk.foodhealth.model.CallBackCardPresenter;
import com.example.stanislau_bushuk.foodhealth.model.CardNetWorkModel;
import com.example.stanislau_bushuk.foodhealth.model.FirebaseModel;
import com.example.stanislau_bushuk.foodhealth.model.RealmModel;
import com.example.stanislau_bushuk.foodhealth.model.pojo.Comment;
import com.example.stanislau_bushuk.foodhealth.model.pojo.Recipe;
import com.google.firebase.database.DataSnapshot;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;


@InjectViewState
public class CardPresenter extends MvpPresenter<CardView> implements CallBackCardPresenter {

    @Inject
    CardNetWorkModel netWorkModel;

    @Inject
    FirebaseModel firebaseModel;

    @Inject
    RealmModel realmModel;

    @Inject
    OwnRouter router;

    CardPresenter() {
        App.getAppComponent().inject(this);
        netWorkModel.setCallBackCard(this);
        firebaseModel.setCardPresenterCallBack(this);
    }


    public void getRecipeFromRealmUri(final String uri) {
        netWorkModel.getRecipeFromRealmUri(uri);
    }

    public void getEditData(final float number, final Data data) {
        final EditData editData = EditData.newBuilder()
                .setCalories(data.getCalories() * data.getYield() / number)
                .setENERC_KCAL(data.getENERC_KCAL().getQuantity() * data.getYield() / number)
                .setChocdf(data.getChocdf().getQuantity() * data.getYield() / number)
                .setFat(data.getFat().getQuantity() * data.getYield() / number)
                .setProt(data.getProt().getQuantity() * data.getYield() / number)
                .setYield(data.getYield())
                .build();
        getViewState().showEditData(editData);
    }

    @Override
    public void call(final Observable<Recipe> observable) {
        observable.subscribeOn(Schedulers.io())
                .map(new Function<Recipe, Data>() {
                    @Override
                    public Data apply(final Recipe recipe) {

                        return Data.newBuilder()
                                .setFat(recipe.getTotalNutrients())
                                .setProt(recipe.getTotalNutrients())
                                .setChocdf(recipe.getTotalNutrients())
                                .setCalories(recipe)
                                .setYield(recipe)
                                .setENERC_KCAL(recipe.getTotalDaily())
                                .setLabel(recipe)
                                .setImage(recipe)
                                .setIngridients(recipe)
                                .build();
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Data>() {
                    @Override
                    public void onSubscribe(final Disposable d) {
                    }

                    @Override
                    public void onNext(final Data data) {
                        Timber.e("onNext");
                        firebaseModel.getComments(data.getLabel());
                        getViewState().showList(data);
                    }

                    @Override
                    public void onError(final Throwable e) {
                        e.printStackTrace();
                        getViewState().showError();
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }


    @Override
    public void callList(final Observable<List<Recipe>> observable) {
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(new Function<List<Recipe>, Data>() {
                    @Override
                    public Data apply(final List<Recipe> recipes) {
                        final Recipe recipe = recipes.get(0);
                        realmModel.addToRealm(recipes.get(0));
                        return Data.newBuilder()
                                .setFat(recipe.getTotalNutrients())
                                .setProt(recipe.getTotalNutrients())
                                .setChocdf(recipe.getTotalNutrients())
                                .setCalories(recipe)
                                .setYield(recipe)
                                .setENERC_KCAL(recipe.getTotalDaily())
                                .setLabel(recipe)
                                .setImage(recipe)
                                .setIngridients(recipe)
                                .build();
                    }
                })
                .subscribe(new Observer<Data>() {
                    @Override
                    public void onSubscribe(final Disposable d) {
                    }

                    @Override
                    public void onNext(final Data data) {
                        getViewState().showList(data);
                    }

                    @Override
                    public void onError(final Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }

    @Override
    public void callFirebase(final DataSnapshot dataSnapshot) {
        if (dataSnapshot != null) {
            final List<Comment> listComments = new ArrayList<>();

            for (final DataSnapshot ds : dataSnapshot.getChildren()) {
                listComments.add(new Comment(ds));
            }

            Collections.reverse(listComments);

            if (listComments.size() != 0) {
                getViewState().showComments(listComments);
            }
        } else {
            getViewState().showError();
        }
    }

    public void addComment(final String email, final String text, final String nameRecipe, final Uri photoUri) {
        if (email.isEmpty()) {
            getViewState().showAnonymous();
        } else {

            if (photoUri == null) {
                firebaseModel.addComment(email, text, nameRecipe, "null");
            } else {
                firebaseModel.addComment(email, text, nameRecipe, photoUri.toString());
            }
        }
    }

    public void back() {
        router.exit();
    }

}
