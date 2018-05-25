package com.example.stanislau_bushuk.foodhealth.presentantion.authPresentation;

import android.os.Bundle;
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

public class RegistrationActivity extends MvpAppCompatActivity implements LoginView {

    @BindView(R.id.registration_email_editText)
    EditText email;

    @BindView(R.id.registration_password1_editText)
    EditText password;

    @BindView(R.id.registration_password2_editText)
    EditText confirm_password;

    @BindView(R.id.registration_sign_in_button)
    Button registration;

    @InjectPresenter
    LoginPresenter loginPresenter;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registation);

        ButterKnife.bind(this);
    }

    @OnClick(R.id.registration_sign_in_button)
    public void clickRegistration() {
        loginPresenter.registrationUser(email.getText().toString(),
                password.getText().toString(),
                confirm_password.getText().toString());
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

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        ActivityManager.startLoginActivity(this);
        finish();
    }
}
