package com.example.stanislau_bushuk.foodhealth.modul;

import com.example.stanislau_bushuk.foodhealth.presentantion.deepSearchPresentation.NetWorkModelDeepSearch;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by logoped583st on 20.4.18.
 */

@Module
public class NetworkDeepSearchModul {

    @Provides
    @Singleton
    public NetWorkModelDeepSearch netWorkModelDeepSearch(){
        return new NetWorkModelDeepSearch();
    }
}
