package com.example.stanislau_bushuk.foodhealth.cicerone;

import ru.terrakok.cicerone.commands.Command;

public class OwnBackToCommand implements Command {
    private final String screenKey;
    private final Object transitionData;

    /**
     * Creates a {@link OwnBackToCommand} navigation command.
     *
     * @param screenKey      screen key
     * @param transitionData initial data
     */

    public OwnBackToCommand(final String screenKey, final Object transitionData) {
        this.screenKey = screenKey;
        this.transitionData = transitionData;
    }

    public String getScreenKey() {
        return screenKey;
    }

    public Object getTransitionData(){
        return this.transitionData;
    }
}
