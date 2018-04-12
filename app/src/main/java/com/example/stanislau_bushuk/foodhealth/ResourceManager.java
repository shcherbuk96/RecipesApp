package com.example.stanislau_bushuk.foodhealth;

import android.content.Context;

import dagger.Module;

@Module
public class ResourceManager {

    private Context context;

    public ResourceManager(final Context context) {
        this.context = context;
    }

    public String getString(final int id) {
        return context.getResources().getString(id);
    }
}
