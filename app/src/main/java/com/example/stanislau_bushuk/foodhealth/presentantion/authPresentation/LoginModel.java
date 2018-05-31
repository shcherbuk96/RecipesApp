package com.example.stanislau_bushuk.foodhealth.presentantion.authPresentation;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
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
        final Task<AuthResult> authResult = mAuth.signInWithEmailAndPassword( email, password);
        authResult.addOnSuccessListener(new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(final AuthResult authResult) {
                callBackLoginPresenter.call(authResult);
            }
        });

    }

    public void signInAnonymous() {
        final Task<AuthResult> authResult = mAuth.signInAnonymously().addOnSuccessListener(new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(final AuthResult authResult) {
                callBackLoginPresenter.call(authResult);
            }
        });

    }

    public void registrationUser(final String email, final String password) {
        final Task<AuthResult> authResult = mAuth.createUserWithEmailAndPassword(email, password)
                .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(final AuthResult authResult) {
                        callBackLoginPresenter.call(authResult);
                    }
                });

    }

    public FirebaseAuth getAuth() {
        return mAuth;
    }

}
