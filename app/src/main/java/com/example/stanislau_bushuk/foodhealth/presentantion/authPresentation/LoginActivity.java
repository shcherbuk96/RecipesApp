package com.example.stanislau_bushuk.foodhealth.presentantion.authPresentation;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.example.stanislau_bushuk.foodhealth.App;
import com.example.stanislau_bushuk.foodhealth.Constants;
import com.example.stanislau_bushuk.foodhealth.NavigationUtil;
import com.example.stanislau_bushuk.foodhealth.R;
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

    private String keyScreen;

    @Inject
    NavigationUtil navigationUtil() {
        return new NavigationUtil(this);
    }

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        App.getAppComponent().inject(this);
        setContentView(R.layout.activity_login);

        keyScreen = getIntent().getStringExtra(Constants.KEY_FRAGMENT);
        ButterKnife.bind(this);
        loginPresenter.setViewVisibility(keyScreen);
    }

    @OnClick(R.id.login_sign_in_button)
    public void clickSignIn(final View view) {
        loginPresenter.signInUser(email.getText().toString(), password.getText().toString());
    }

    @OnClick(R.id.login_sign_up_button)
    public void clickSignUp(final View view) {
        loginPresenter.goTo(Constants.REGISTRATION_ACTIVITY);
    }

    @OnClick(R.id.login_anonymous_button)
    public void clickAnonymous(final View view) {
        loginPresenter.signInAnonymous();
    }

    @Override
    public void user(final FirebaseUser firebaseUser) {

        if (keyScreen != null) {
            loginPresenter.goTo(keyScreen);
        } else {
            loginPresenter.goTo(Constants.MAIN_ACTIVITY);
        }
    }

    @Override
    public void error(final Throwable e) {
        Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void checkPassword() {
        Toast.makeText(this, R.string.registration_check_password, Toast.LENGTH_SHORT).show();
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

}
