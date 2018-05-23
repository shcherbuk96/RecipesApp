package com.example.stanislau_bushuk.foodhealth.presentantion.loginPresentation;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.example.stanislau_bushuk.foodhealth.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import timber.log.Timber;

public class LoginActivity extends MvpAppCompatActivity implements LoginView{

    @BindView(R.id.login_sign_in_button)
    Button input;

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
                loginPresenter.signInUser(email.getText().toString(),password.getText().toString());
            }
        });
    }


    @Override
    public void getUser(final FirebaseUser firebaseUser) {
        Toast.makeText(this, firebaseUser.getEmail(),Toast.LENGTH_SHORT).show();
    }

    @Override
    public void error() {
        Toast.makeText(this,"Проверь данные", Toast.LENGTH_SHORT).show();
    }
}
