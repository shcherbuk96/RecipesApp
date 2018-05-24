package com.example.stanislau_bushuk.foodhealth.modul;

import com.example.stanislau_bushuk.foodhealth.cicerone.OwnRouter;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import ru.terrakok.cicerone.Cicerone;
import ru.terrakok.cicerone.NavigatorHolder;

@Module
public class CiceroneModul {

    private Cicerone<OwnRouter> cicerone;

    public CiceroneModul() {
        cicerone = create();
    }

    @Provides
    @Singleton
    OwnRouter provideRouter() {
        return cicerone.getRouter();
    }

    public Cicerone<OwnRouter> create() {
        return Cicerone.create(new OwnRouter());
    }


    @Provides
    @Singleton
    NavigatorHolder provideNavigatorHolder() {
        return cicerone.getNavigatorHolder();
    }
}
