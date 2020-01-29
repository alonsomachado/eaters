package com.example.eaters.Activities;

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

public class SignUpActivity extends AppCompatActivity {

    private Button btn_sign_up;
    private EditText name , pass, pass_confirm , address;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        name= findViewById(R.id.name_sign_up);
        pass=findViewById(R.id.pass_sign_up);
        pass_confirm= findViewById(R.id.pass_confirm_sign_up);
        address= findViewById(R.id.address_sign_up);

        btn_sign_up= findViewById(R.id.btn_sign_up);

        btn_sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                attemptSignup(name.getText().toString(),pass.getText().toString());
            }
        });


    }

    public void attemptSignup(String nEmail, String nPass) {

        name.setError(null);
        pass.setError(null);
        pass_confirm.setError(null);

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

        if (isEqualsPass()) {
            pass_confirm.setError("Passes diferentes");
            focusView = pass_confirm;
            cancel = true;
        }


        if (cancel) {

            focusView.requestFocus();

        } else {



            sign_up(nEmail, nPass);
        }
    }

    private boolean isPasswordValid(String password) {
        return password.length() > 5;
    }

    private boolean isEmailValid(String email) {

        return email.length()>4;
    }

    private boolean isEqualsPass () {

        return pass_confirm.getText().toString().equals(pass.getText().toString());
    }

    public void sign_up(String name, String password) {



        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString("name", name);
        editor.putString("pass", password);
        editor.putString("address", address.getText().toString());
        editor.commit();

    }

}
