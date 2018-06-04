package com.example.stanislau_bushuk.foodhealth;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;

import com.example.stanislau_bushuk.foodhealth.cicerone.OwnNavigator;
import com.example.stanislau_bushuk.foodhealth.presentantion.authPresentation.LoginActivity;
import com.example.stanislau_bushuk.foodhealth.presentantion.authPresentation.RegistrationActivity;
import com.example.stanislau_bushuk.foodhealth.presentantion.cardPresentation.CardActivity;
import com.example.stanislau_bushuk.foodhealth.presentantion.deepSearchPresentation.DeepSearchActivity;

import javax.inject.Inject;

import ru.terrakok.cicerone.commands.Command;


public class NavigationUtil extends OwnNavigator {

    @Inject
    FragmentCreater fragmentCreater;

    public NavigationUtil(final Context context, final int id) {
        super((FragmentActivity) context, id);
        App.getAppComponent().inject(this);
    }

    @Override
    public void applyCommands(final Command[] commands) {
        super.copyStackToLocal();

        for (final Command command : commands) {
            super.applyCommand(command);
        }

    }

    @Override
    protected Intent createActivityIntent(final Context context, final String screenKey, final Object data) {
        final Intent intent;

        switch (screenKey) {

            case Constants.MAIN_ACTIVITY: {
                intent = new Intent(context, MainActivity.class);
                return intent;
            }

            case Constants.DEEP_SEARCH_ACTIVITY: {
                intent = new Intent(context, DeepSearchActivity.class);
                return intent;
            }

            case Constants.CARD_ACTIVITY: {
                intent = new Intent(context, CardActivity.class);
                intent.putExtra(Constants.RECIPE_INTENT_KEY, (String) data);
                return intent;
            }

            case Constants.LOGIN_ACTIVITY: {
                intent = new Intent(context, LoginActivity.class);
                intent.putExtra(Constants.KEY_FRAGMENT, (String) data);
                return intent;
            }

            case Constants.REGISTRATION_ACTIVITY: {
                intent = new Intent(context, RegistrationActivity.class);
                intent.putExtra(Constants.KEY_FRAGMENT, (String) data);
                return intent;
            }

            default: {
                return null;
            }

        }
    }

    @Override
    protected Fragment createFragment(final String screenKey, final Object data) {
        return fragmentCreater.getNewInstanceFragment(screenKey, (int) data);
    }

}









