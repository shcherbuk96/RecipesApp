package com.example.stanislau_bushuk.foodhealth.modul;

import com.example.stanislau_bushuk.foodhealth.model.CardNetWorkModel;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class CardNetWorkModul {
    @Provides
    @Singleton
    public CardNetWorkModel cardNetWorkModel() {
        return new CardNetWorkModel();
    }
}
