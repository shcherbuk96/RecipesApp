package com.example.stanislau_bushuk.foodhealth.presentantion.authPresentation;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.example.stanislau_bushuk.foodhealth.ActivityManager;
import com.example.stanislau_bushuk.foodhealth.R;
import com.google.firebase.auth.FirebaseUser;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

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

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
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
        ActivityManager.startRegistrationActivity(this);
        finish();
    }

    @OnClick(R.id.login_anonymous_button)
    public void clickAnonymous(final View view) {
        loginPresenter.signInAnonymous();
    }

    @Override
    public void user(final FirebaseUser firebaseUser) {
        ActivityManager.startMainActivity(this);
        finish();
    }

    @Override
    public void error(final Throwable e) {
        Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void checkPassword() {
        Toast.makeText(this, R.string.registration_check_password, Toast.LENGTH_SHORT).show();
    }


}
