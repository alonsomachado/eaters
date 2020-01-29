package com.example.eaters.Activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.eaters.R;

public class LoginActivity extends AppCompatActivity {

    private Button btn_login, btn_sign_up;
    private EditText name, pass ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btn_login = findViewById(R.id.btn_login);
        btn_sign_up= findViewById(R.id.btn_go_to_sign_up);
        name = findViewById(R.id.name_login);
        pass= findViewById(R.id.pass_login);

        btn_sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);

                startActivity(intent);
            }
        });

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                attemptSignIn(name.getText().toString(), pass.getText().toString());
            }
        });
    }

    public void attemptSignIn(String nEmail, String nPass) {

        name.setError(null);
        pass.setError(null);

        boolean cancel = false;
        View focusView = null;

        if (TextUtils.isEmpty(nPass)) {
            pass.setError("Campo Obrigatorio");
            focusView = pass;
            cancel = true;
        } else if (!isPasswordValid(nPass)) {
            pass.setError("Password Demasiado Pequena");
            focusView = pass;
            cancel = true;
        }

        if (TextUtils.isEmpty(nEmail)) {
            name.setError("Nome Obrigatorio");
            focusView = name;
            cancel = true;
        } else if (!isEmailValid(nEmail)) {
            name.setError("Nome Invalido");
            focusView = name;
            cancel = true;
        }


        if (cancel) {

            focusView.requestFocus();

        } else {

            Toast.makeText(LoginActivity.this, "Bem vindo ", Toast.LENGTH_LONG).show();

            sign_in(nEmail, nPass);
        }
    }


    private boolean isPasswordValid(String password) {
        return password.length() > 5;
    }

    private boolean isEmailValid(String email) {

        return email.length()>4;
    }

    public void sign_in(String name, String password) {

        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(this);
        String person_name = settings.getString("name", "");

        Intent intent = new Intent(LoginActivity.this, MainActivity.class);

        startActivity(intent);
    }
}
