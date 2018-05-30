package com.example.stanislau_bushuk.foodhealth.presentantion.cardPresentation;

import android.net.Uri;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.example.stanislau_bushuk.foodhealth.App;
import com.example.stanislau_bushuk.foodhealth.model.CallBackCardPresenter;
import com.example.stanislau_bushuk.foodhealth.model.CardNetWorkModel;
import com.example.stanislau_bushuk.foodhealth.model.FirebaseModel;
import com.example.stanislau_bushuk.foodhealth.model.pojo.Comment;
import com.example.stanislau_bushuk.foodhealth.model.pojo.Recipe;
import com.google.firebase.database.DataSnapshot;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import ru.terrakok.cicerone.Router;
import rx.functions.Func1;
import timber.log.Timber;


@InjectViewState
public class CardPresenter extends MvpPresenter<CardView> implements CallBackCardPresenter {

    @Inject
    CardNetWorkModel netWorkModel;

    @Inject
    FirebaseModel firebaseModel;

    @Inject
    Router router;

    CardPresenter() {
        App.getAppComponent().inject(this);
        netWorkModel.setCallBackCard(this);
        firebaseModel.setCallBack(this);
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
    public void call(final io.reactivex.Observable<Recipe> observable) {
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
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }

    @Override
    public void callFirebase(final rx.Observable<DataSnapshot> observable) {
        observable.map(new Func1<DataSnapshot, List<Comment>>() {
            @Override
            public List<Comment> call(final DataSnapshot dataSnapshot) {
                final List<Comment> listComments = new ArrayList<>();
                for (final DataSnapshot ds : dataSnapshot.getChildren()) {
                    listComments.add(new Comment(ds));
                }
                Collections.reverse(listComments);
                return listComments;
            }
        }).subscribe(new rx.Observer<List<Comment>>() {
            @Override
            public void onCompleted() {
                Timber.e("onComplete2");
            }

            @Override
            public void onError(final Throwable e) {
                Timber.e("onError2");
                Timber.e(e);
            }

            @Override
            public void onNext(final List<Comment> comments) {
                Timber.e("onNext2");
                getViewState().showComments(comments);
            }
        });
      /*  observable.map(new Function<DataSnapshot, List<Comment>>() {
            @Override
            public List<Comment> apply(final DataSnapshot dataSnapshot) throws Exception {
                final List<Comment> listComments = new ArrayList<>();
                for (final DataSnapshot ds : dataSnapshot.getChildren()) {
                    listComments.add(ds.getValue(Comment.class));
                }
                return listComments;
            }
        }).subscribe(new Observer<List<Comment>>() {
            @Override
            public void onSubscribe(final Disposable d) {
                Timber.e("onSubscribe2");
            }

            @Override
            public void onNext(final List<Comment> listComments) {
                Timber.e("onNext2");
                getViewState().showComments(listComments);
            }

            @Override
            public void onError(final Throwable e) {
                Timber.e("onError2");
            }

            @Override
            public void onComplete() {
                Timber.e("onComplete2");
            }
        });*/
    }

    public void addComment(final String email, final String text, final String nameRecipe, final Uri photoUri) {
        if (photoUri == null) {
            firebaseModel.addComment(email, text, nameRecipe, "null");
        } else {
            firebaseModel.addComment(email, text, nameRecipe, photoUri.toString());
        }

    }

    public void back() {
        router.exit();
    }
}
