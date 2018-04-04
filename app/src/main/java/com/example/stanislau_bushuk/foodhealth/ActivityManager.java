package com.example.stanislau_bushuk.foodhealth;

import android.content.Context;
import android.content.Intent;

public class ActivityManager {
    static void startMainActivity(Context context){
        Intent intent = new Intent(context,MainActivity.class);
        context.startActivity(intent);
    }
}
