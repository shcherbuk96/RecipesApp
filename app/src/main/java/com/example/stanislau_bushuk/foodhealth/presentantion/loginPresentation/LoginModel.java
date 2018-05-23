package com.example.stanislau_bushuk.foodhealth.presentantion.loginPresentation;

import android.app.Activity;
import android.support.annotation.NonNull;

import com.example.stanislau_bushuk.foodhealth.presentantion.favoritePresentation.FavoritePresenter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.kelvinapps.rxfirebase.RxFirebaseAuth;


import io.reactivex.android.schedulers.AndroidSchedulers;
import rx.Observable;
import timber.log.Timber;

public class LoginModel {

    private FirebaseAuth mAuth;
    private CallBackLoginPresenter callBackLoginPresenter;

    public LoginModel(){
        mAuth = FirebaseAuth.getInstance();
    }

    public void setCallBack(final LoginPresenter loginPresenter) {
        callBackLoginPresenter = loginPresenter;
    }
    private void signIn(String email, String password){

        Observable<AuthResult> observable= RxFirebaseAuth.signInWithEmailAndPassword(mAuth,email,password)
                .asObservable();
        callBackLoginPresenter.call(observable);
       /* mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                    }
                });*/
    }
}
