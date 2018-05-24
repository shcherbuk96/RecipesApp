package com.example.stanislau_bushuk.foodhealth.presentantion.loginPresentation;

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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ButterKnife.bind(this);

        input.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                loginPresenter.signInUser(email.getText().toString(), password.getText().toString());
            }
        });

        anonymous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                loginPresenter.signInAnonymous();
            }
        });

    }


    @Override
    public void getUser(final FirebaseUser firebaseUser) {
        ActivityManager.startMainActivity(this);
    }

    @Override
    public void error() {
        Toast.makeText(this, R.string.login_error, Toast.LENGTH_SHORT).show();
    }
}
