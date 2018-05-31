package com.example.stanislau_bushuk.foodhealth.presentantion.authPresentation;

import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.kelvinapps.rxfirebase.RxFirebaseAuth;

import rx.Observable;

public class LoginModel {

    private final FirebaseAuth mAuth;
    private CallBackLoginPresenter callBackLoginPresenter;

    public LoginModel() {
        mAuth = FirebaseAuth.getInstance();
    }

    public void setCallBack(final LoginPresenter loginPresenter) {
        callBackLoginPresenter = loginPresenter;
    }

    public void signIn(final String email, final String password) {
        final Observable<AuthResult> observable = RxFirebaseAuth.signInWithEmailAndPassword(mAuth, email, password)
                .asObservable();
        callBackLoginPresenter.call(observable);
    }

    public void signInAnonymous() {
        final Observable<AuthResult> observable = RxFirebaseAuth.signInAnonymously(mAuth).asObservable();
        callBackLoginPresenter.call(observable);
    }

    public void registrationUser(final String email, final String password) {
        final Observable<AuthResult> observable = RxFirebaseAuth.createUserWithEmailAndPassword(mAuth, email, password)
                .asObservable();
        callBackLoginPresenter.call(observable);
    }

    public FirebaseAuth getAuth() {
        return mAuth;
    }
}
