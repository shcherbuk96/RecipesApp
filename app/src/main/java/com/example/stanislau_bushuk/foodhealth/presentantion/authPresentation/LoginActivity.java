package com.example.stanislau_bushuk.foodhealth.presentantion.authPresentation;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.example.stanislau_bushuk.foodhealth.App;
import com.example.stanislau_bushuk.foodhealth.Constants;
import com.example.stanislau_bushuk.foodhealth.NavigationUtil;
import com.example.stanislau_bushuk.foodhealth.R;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.firebase.auth.FirebaseUser;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ru.terrakok.cicerone.NavigatorHolder;

public class LoginActivity extends MvpAppCompatActivity implements LoginView {

    @BindView(R.id.login_sign_in_button)
    Button input;

    @BindView(R.id.login_anonymous_button)
    Button anonymous;

    @BindView(R.id.login_sign_up_button)
    Button signUp;

    @BindView(R.id.login_email_editText)
    EditText email;

    @BindView(R.id.login_password_editText)
    EditText password;

    @InjectPresenter
    LoginPresenter loginPresenter;

    @Inject
    NavigatorHolder navigatorHolder;

    ProgressDialog progressDialog;

    private String keyScreen;

    @Inject
    NavigationUtil navigationUtil() {
        return new NavigationUtil(this, R.id.main_contener_frame_layout);
    }

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        App.getAppComponent().inject(this);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        isGooglePlayServicesAvailable();
        keyScreen = getIntent().getStringExtra(Constants.KEY_FRAGMENT);
        ButterKnife.bind(this);
        loginPresenter.setViewVisibility(keyScreen);
        progressDialog=new ProgressDialog(this);
        progressDialog.setMessage("Loading");
    }

    @OnClick(R.id.login_sign_in_button)
    public void clickSignIn(final View view) {
        progressDialog.show();
        loginPresenter.signInUser(email.getText().toString(), password.getText().toString());
    }

    @OnClick(R.id.login_sign_up_button)
    public void clickSignUp(final View view) {
        loginPresenter.goTo(Constants.REGISTRATION_ACTIVITY);
    }

    @OnClick(R.id.login_anonymous_button)
    public void clickAnonymous(final View view) {
        progressDialog.show();
        loginPresenter.signInAnonymous();
    }

    @Override
    public void user(final FirebaseUser firebaseUser) {
        progressDialog.hide();

        if (keyScreen != null) {
            loginPresenter.goTo(keyScreen);
        } else {
            loginPresenter.goTo(Constants.MAIN_ACTIVITY);
        }
    }

    @Override
    public void error(final Exception e) {
        progressDialog.hide();
        Snackbar.make(findViewById(R.id.login_email_editText), e.getMessage(), Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void checkPassword() {

    }

    @Override
    public void checkEmptyLine() {
        progressDialog.hide();
        Snackbar.make(findViewById(R.id.login_email_editText), R.string.registration_check_empty_line, Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void setViewVisibility(final int visibility) {
        anonymous.setVisibility(visibility);
        signUp.setVisibility(visibility);
    }

    @Override
    protected void onResume() {
        super.onResume();

        navigatorHolder.setNavigator(navigationUtil());
    }

    @Override
    protected void onPause() {
        super.onPause();

        navigatorHolder.removeNavigator();
    }

    private void isGooglePlayServicesAvailable() {
        final GoogleApiAvailability googleApiAvailability = GoogleApiAvailability.getInstance();
        final int resultCode = googleApiAvailability.isGooglePlayServicesAvailable(this);

        if (resultCode != ConnectionResult.SUCCESS) {
            final Dialog dialog = googleApiAvailability.getErrorDialog(this, resultCode, 0);

            if (dialog != null) {
                dialog.show();
            }

        }

    }
}
