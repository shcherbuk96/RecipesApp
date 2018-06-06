package com.example.stanislau_bushuk.foodhealth.presentantion.authPresentation;

import com.google.firebase.auth.AuthResult;


public interface CallBackLoginPresenter {
    void call(AuthResult authResult);

    void fail(Exception e);
}
