package com.example.stanislau_bushuk.foodhealth.presentantion.authPresentation;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.widget.Button;
import android.widget.EditText;

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

public class RegistrationActivity extends MvpAppCompatActivity implements LoginView {

    @BindView(R.id.registration_email_editText)
    EditText email;

    @BindView(R.id.registration_password1_editText)
    EditText password;

    @BindView(R.id.registration_password2_editText)
    EditText confirm_password;

    @BindView(R.id.registration_sign_in_button)
    Button registration;

    @BindView(R.id.registration_toolbar)
    Toolbar toolbar;

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
        setContentView(R.layout.activity_registation);

        ButterKnife.bind(this);

        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            toolbar.setTitle(R.string.registration_toolbar);
        }

    }

    @OnClick(R.id.registration_sign_in_button)
    public void clickRegistration() {
        loginPresenter.registrationUser(email.getText().toString(),
                password.getText().toString(),
                confirm_password.getText().toString());
    }

    @Override
    public void user(final FirebaseUser firebaseUser) {
        loginPresenter.replace(Constants.MAIN_ACTIVITY);
    }

    @Override
    public void error(final Exception e) {
        Snackbar.make(findViewById(R.id.registration_email_editText), e.getMessage(), Snackbar.LENGTH_LONG).show();
//        Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void checkPassword() {
//        Toast.makeText(this, R.string.registration_check_password, Toast.LENGTH_SHORT).show();
        Snackbar.make(findViewById(R.id.registration_email_editText), R.string.registration_check_password, Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void checkEmptyLine() {
        Snackbar.make(findViewById(R.id.registration_email_editText), R.string.registration_check_empty_line, Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void onBackPressed() {
        loginPresenter.exit();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
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
