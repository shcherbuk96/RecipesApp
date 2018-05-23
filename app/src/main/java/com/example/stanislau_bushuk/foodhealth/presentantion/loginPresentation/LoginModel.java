package com.example.stanislau_bushuk.foodhealth.presentantion.loginPresentation;

import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.kelvinapps.rxfirebase.RxFirebaseAuth;

import rx.Observable;

public class LoginModel {

    private FirebaseAuth mAuth;
    private CallBackLoginPresenter callBackLoginPresenter;

    public LoginModel() {
        mAuth = FirebaseAuth.getInstance();
    }

    public void setCallBack(final LoginPresenter loginPresenter) {
        callBackLoginPresenter = loginPresenter;
    }

    public void signIn(String email, String password) {
        Observable<AuthResult> observable = RxFirebaseAuth.signInWithEmailAndPassword(mAuth, email, password)
                .asObservable();
        callBackLoginPresenter.call(observable);

    }
}
