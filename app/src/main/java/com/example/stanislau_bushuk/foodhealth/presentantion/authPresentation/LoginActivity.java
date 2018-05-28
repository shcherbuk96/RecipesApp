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
import timber.log.Timber;

public class LoginActivity extends MvpAppCompatActivity implements LoginView {

    @BindView(R.id.login_sign_in_button)
    Button input;

    @BindView(R.id.login_anonymous_button)
    Button anonymous;

    @BindView(R.id.login_email_editText)
    EditText email;

    @BindView(R.id.login_password_editText)
    EditText password;

    @InjectPresenter
    LoginPresenter loginPresenter;

    @Inject
    NavigatorHolder navigatorHolder;

    @Inject
    NavigationUtil navigationUtil() {
        return new NavigationUtil(this);
    }

    @Override
    protected void onCreate(final Bundle savedInstanceState) {

        App.getAppComponent().inject(this);


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ButterKnife.bind(this);
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
        loginPresenter.goTo(Constants.MAIN_ACTIVITY);
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
    protected void onResume() {
        super.onResume();
        Timber.e("RESUME LOGIN");
        navigatorHolder.setNavigator(navigationUtil());

    }

    @Override
    protected void onStop() {
        super.onStop();
        Timber.e("STOP LOGIN");
        navigatorHolder.removeNavigator();
    }

}
