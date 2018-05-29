package com.example.stanislau_bushuk.foodhealth.cicerone;

import java.util.HashMap;

import ru.terrakok.cicerone.BaseRouter;
import ru.terrakok.cicerone.Navigator;
import ru.terrakok.cicerone.commands.Back;
import ru.terrakok.cicerone.commands.BackTo;
import ru.terrakok.cicerone.commands.Forward;
import ru.terrakok.cicerone.commands.Replace;
import ru.terrakok.cicerone.commands.SystemMessage;
import ru.terrakok.cicerone.result.ResultListener;

public class OwnRouter extends BaseRouter {
    private HashMap<Integer, ResultListener> resultListeners = new HashMap<>();

    public OwnRouter() {
        super();
    }

    /**
     * Subscribe to the screen result.<br>
     * <b>Note:</b> only one listener can subscribe to a unique resultCode!<br>
     * You must call a <b>removeResultListener()</b> to avoid a memory leak.
     *
     * @param resultCode key for filter results
     * @param listener   result listener
     */
    public void setResultListener(final Integer resultCode, final ResultListener listener) {
        resultListeners.put(resultCode, listener);
    }

    /**
     * Unsubscribe from the screen result.
     *
     * @param resultCode key for filter results
     */
    public void removeResultListener(final Integer resultCode) {
        resultListeners.remove(resultCode);
    }

    /**
     * Send result data to subscriber.
     *
     * @param resultCode result data key
     * @param result     result data
     * @return TRUE if listener was notified and FALSE otherwise
     */
    protected boolean sendResult(final Integer resultCode, final Object result) {
        final ResultListener resultListener = resultListeners.get(resultCode);
        if (resultListener != null) {
            resultListener.onResult(result);
            return true;
        }
        return false;
    }

    /**
     * Open new screen and add it to the screens chain.
     *
     * @param screenKey screen key
     */
    public void navigateTo(final String screenKey) {
        navigateTo(screenKey, null);
    }

    /**
     * Open new screen and add it to screens chain.
     *
     * @param screenKey screen key
     * @param data      initialisation parameters for the new screen
     */
    public void navigateTo(final String screenKey, final Object data) {
        executeCommands(new Forward(screenKey, data));
    }

    /**
     * Clear the current screens chain and start new one
     * by opening a new screen right after the root.
     *
     * @param screenKey screen key
     */
    public void newScreenChain(final String screenKey) {
        newScreenChain(screenKey, null);

    }
    /**
     * Clear the current screens chain and start new one
     * by opening a new screen right after the root.
     *
     * @param screenKey screen key
     * @param data      initialisation parameters for the new screen
     */
    public void newScreenChain(final String screenKey, final Object data) {
        executeCommands(
                new BackTo(null),
                new Forward(screenKey, data)
        );
    }

    /**
     * Clear all screens and open new one as root.
     *
     * @param screenKey screen key
     */
    public void newRootScreen(final String screenKey) {
        newRootScreen(screenKey, null);
    }

    /**
     * Clear all screens and open new one as root.
     *
     * @param screenKey screen key
     * @param data      initialisation parameters for the root
     */
    public void newRootScreen(final String screenKey, final Object data) {
        executeCommands(
                new BackTo(null),
                new Replace(screenKey, data)
        );
    }

    /**
     * Replace current screen.
     * By replacing the screen, you alters the backstack,
     * so by going back you will return to the previous screen
     * and not to the replaced one.
     *
     * @param screenKey screen key
     */
    public void replaceScreen(final String screenKey) {
        replaceScreen(screenKey, null);
    }

    /**
     * Replace current screen.
     * By replacing the screen, you alters the backstack,
     * so by going back you will return to the previous screen
     * and not to the replaced one.
     *
     * @param screenKey screen key
     * @param data      initialisation parameters for the new screen
     */
    public void replaceScreen(final String screenKey, final Object data) {
        executeCommands(new Replace(screenKey, data));
    }

    /**
     * Return back to the needed screen from the chain.
     * Behavior in the case when no needed screens found depends on
     * the processing of the {@link BackTo} command in a {@link Navigator} implementation.
     *
     * @param screenKey screen key
     */
    public void backTo(final String screenKey) {
        executeCommands(new BackTo(screenKey));
    }

    /**
     * Remove all screens from the chain and exit.
     * It's mostly used to finish the application or close a supplementary navigation chain.
     */
    public void finishChain() {
        executeCommands(
                new BackTo(null),
                new Back()
        );
    }

    /**
     * Return to the previous screen in the chain.
     * Behavior in the case when the current screen is the root depends on
     * the processing of the {@link Back} command in a {@link Navigator} implementation.
     */
    public void exit() {
        executeCommands(new Back());
    }

    /**
     * Return to the previous screen in the chain and send result data.
     *
     * @param resultCode result data key
     * @param result     result data
     */
    public void exitWithResult(final Integer resultCode, final Object result) {
        exit();
        sendResult(resultCode, result);
    }

    /**
     * Return to the previous screen in the chain and show system message.
     *
     * @param message message to show
     */
    public void exitWithMessage(final String message) {
        executeCommands(
                new Back(),
                new SystemMessage(message)
        );
    }

    /**
     * Show system message.
     *
     * @param message message to show
     */
    public void showSystemMessage(final String message) {
        executeCommands(new SystemMessage(message));
    }

    public void backToOwn(final String screenKey, final Object data){
        executeCommands(new OwnBackToCommand(screenKey,data));
    }
}
