package com.example.stanislau_bushuk.foodhealth.presentantion.loginPresentation;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.example.stanislau_bushuk.foodhealth.App;
import com.google.firebase.auth.AuthResult;

import java.sql.Time;

import javax.inject.Inject;


import io.reactivex.android.schedulers.AndroidSchedulers;
import rx.Observable;
import rx.Observer;
import rx.schedulers.Schedulers;
import timber.log.Timber;

@InjectViewState
public class LoginPresenter extends MvpPresenter<LoginView> implements CallBackLoginPresenter{

    @Inject
    LoginModel loginModel;

    public LoginPresenter(){
        App.getAppComponent().inject(this);
        loginModel.setCallBack(this);
    }

    public void signInUser(String email,String password){
        loginModel.signIn(email,password);
    }

    @Override
    public void call(final Observable<AuthResult> observable) {
        observable
                .subscribe(new Observer<AuthResult>() {
                    @Override
                    public void onCompleted() {
                        Timber.e("onCompleted");
                    }

                    @Override
                    public void onError(final Throwable e) {
                        Timber.e("onError");
                        getViewState().error();
                    }

                    @Override
                    public void onNext(final AuthResult authResult) {
                        Timber.e("onNext");
                        if(authResult!=null){
                            Timber.e(authResult.getUser().toString());
                            getViewState().getUser(authResult.getUser());
                        }
                    }
                });
    }
}
