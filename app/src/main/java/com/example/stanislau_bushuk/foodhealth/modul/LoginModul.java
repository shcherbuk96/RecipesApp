package com.example.stanislau_bushuk.foodhealth.modul;

import com.example.stanislau_bushuk.foodhealth.presentantion.loginPresentation.LoginModel;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class LoginModul {
    @Provides
    @Singleton
    public LoginModel loginModel(){
        return new LoginModel();
    }
}
