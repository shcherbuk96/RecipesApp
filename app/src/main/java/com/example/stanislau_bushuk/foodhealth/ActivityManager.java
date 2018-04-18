package com.example.stanislau_bushuk.foodhealth;

import android.content.Context;
import android.content.Intent;

public class ActivityManager {

    public static void startMainActivity(final Context context) {
        final Intent intent = new Intent(context, MainActivity.class);
        context.startActivity(intent);
    }
}
