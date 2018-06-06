package com.example.stanislau_bushuk.foodhealth.presentantion.authPresentation;

import android.support.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


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
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(final AuthResult authResult) {
                        callBackLoginPresenter.call(authResult);
                    }

                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull final Exception e) {
                        callBackLoginPresenter.fail(e);
                    }
                });
    }

    public void signInAnonymous() {
        mAuth.signInAnonymously()
                .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(final AuthResult authResult) {
                        callBackLoginPresenter.call(authResult);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull final Exception e) {
                        callBackLoginPresenter.fail(e);
                    }
                });

    }

    public void registrationUser(final String email, final String password) {
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(final AuthResult authResult) {
                        callBackLoginPresenter.call(authResult);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull final Exception e) {
                        callBackLoginPresenter.fail(e);
                    }
                });

    }

    public FirebaseAuth getAuth() {
        return mAuth;
    }

}
