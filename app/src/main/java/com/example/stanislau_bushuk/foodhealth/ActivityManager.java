package com.example.stanislau_bushuk.foodhealth;

import android.content.Context;
import android.content.Intent;

import com.example.stanislau_bushuk.foodhealth.presentantion.cardPresentation.CardActivity;
import com.example.stanislau_bushuk.foodhealth.presentantion.deepSearchPresentation.DeepSearchActivity;
import com.example.stanislau_bushuk.foodhealth.presentantion.loginPresentation.LoginActivity;

public class ActivityManager {

    public static void startMainActivity(final Context context) {
        final Intent intent = new Intent(context, MainActivity.class);
        context.startActivity(intent);
    }

    public static void startCardActivity(final Context context, final String uri) {
        final Intent intent = new Intent(context, CardActivity.class);
        intent.putExtra(Constants.RECIPE_INTENT_KEY, uri);
        context.startActivity(intent);
    }

    public static void startDeepSearchActivity(final Context context) {
        final Intent intent = new Intent(context, DeepSearchActivity.class);
        context.startActivity(intent);
    }

    public static void startLoginActivity(final Context context){
        final Intent intent = new Intent(context, LoginActivity.class);
        context.startActivity(intent);
    }

}
