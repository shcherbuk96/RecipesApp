package com.example.stanislau_bushuk.foodhealth.modul;

import com.example.stanislau_bushuk.foodhealth.presentantion.registratinPresentation.RegistrationModel;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class RegistrationModul {
    @Provides
    @Singleton
    public RegistrationModel registrationModel() {
        return new RegistrationModel();
    }
}
