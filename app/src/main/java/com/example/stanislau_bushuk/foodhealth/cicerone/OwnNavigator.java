package com.example.stanislau_bushuk.foodhealth.cicerone;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.example.stanislau_bushuk.foodhealth.App;
import com.example.stanislau_bushuk.foodhealth.FragmentCreater;
import com.example.stanislau_bushuk.foodhealth.R;

import java.util.LinkedList;

import javax.inject.Inject;

import ru.terrakok.cicerone.Navigator;
import ru.terrakok.cicerone.android.SupportAppNavigator;
import ru.terrakok.cicerone.commands.Command;
import timber.log.Timber;

public abstract class OwnNavigator extends SupportAppNavigator implements Navigator {

    private final FragmentManager fragmentManager;
    private final FragmentActivity fragmentActivity;

    @Inject
    FragmentCreater fragmentCreater;

    public OwnNavigator(final FragmentActivity activity, final int containerId) {
        super(activity, containerId);
        App.getAppComponent().inject(this);
        this.fragmentManager = activity.getSupportFragmentManager();
        this.fragmentActivity = activity;
    }

    @Override
    protected void applyCommand(final Command command) {
        if (command instanceof OwnBackToCommand) {
            backToOwnCommand((OwnBackToCommand) command);
        } else if (command != null) {
            super.applyCommand(command);
        }
    }

    public void copyStackToLocal() {
        localStackCopy = new LinkedList<>();

        final int stackSize = fragmentManager.getBackStackEntryCount();
        for (int i = 0; i < stackSize; i++) {
            localStackCopy.add(fragmentManager.getBackStackEntryAt(i).getName());
        }
    }

    public void backToOwnCommand(final OwnBackToCommand command) {

        final String key = command.getScreenKey();
        Timber.e(String.valueOf(fragmentCreater.getSearchFragment()));
        if (fragmentManager.getFragments().contains(fragmentCreater.getSearchFragment()) &&
                (int) command.getTransitionData() == -1) {
            fragmentActivity.finish();
        } else {
            final Fragment fragment = createFragment(key, command.getTransitionData());

            final FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

            setupFragmentTransactionAnimation(
                    command,
                    fragmentManager.findFragmentById(R.id.main_contener_frame_layout),
                    fragment,
                    fragmentTransaction
            );

            fragmentTransaction
                    .replace(R.id.main_contener_frame_layout, fragment)
                    .addToBackStack(command.getScreenKey())
                    .commit();

            localStackCopy.clear();
            localStackCopy.add(command.getScreenKey());
        }
    }
}
