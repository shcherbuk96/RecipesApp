package com.example.stanislau_bushuk.foodhealth;

import android.content.Context;
import android.content.Intent;

import com.example.stanislau_bushuk.foodhealth.model.pojo.Recipe;
import com.example.stanislau_bushuk.foodhealth.presentantion.cardPresentation.CardActivity;

public class ActivityManager {

    public static void startMainActivity(final Context context) {
        final Intent intent = new Intent(context, MainActivity.class);
        context.startActivity(intent);
    }

    public static void startCardActivity(final Context context, String uri) {
        final Intent intent = new Intent(context, CardActivity.class);
        intent.putExtra("recipe",uri);
        context.startActivity(intent);
    }

}
