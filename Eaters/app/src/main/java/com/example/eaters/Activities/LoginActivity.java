package com.example.eaters.Activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.eaters.R;

public class LoginActivity extends AppCompatActivity {

    private String person_name, person_pass;
    private Button btn_login, btn_sign_up;
    private EditText name, pass ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences settings2 = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor2 = settings2.edit();
        Boolean isLog = settings2.getBoolean("logado", false);

        setContentView(R.layout.activity_login);

        btn_login = findViewById(R.id.btn_login);
        btn_sign_up= findViewById(R.id.btn_go_to_sign_up);
        name = findViewById(R.id.name_login);
        pass= findViewById(R.id.pass_login);

        if(isLog == true){
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
        }else {
            String name = settings2.getString("name", ""); //Não criou um Utilizador ainda
            if(name == "") {
                Toast.makeText(LoginActivity.this, "Crie um Utilizador", Toast.LENGTH_LONG).show();
            }
        }

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

    public void attemptSignIn(String nName, String nPass) {

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

        if (TextUtils.isEmpty(nName)) {
            name.setError("Nome Obrigatorio");
            focusView = name;
            cancel = true;
        } else if (!isUsernameValid(nName)) {
            name.setError("Nome Invalido");
            focusView = name;
            cancel = true;
        }


        if (cancel) {

            focusView.requestFocus();

        } else {
            sign_in(nName, nPass);
        }
    }


    private boolean isPasswordValid(String password) {
        return password.length() >= 3;
    }

    private boolean isUsernameValid(String user) {

        return user.length()>=3;
    }

    public void sign_in(String name, String password) {

        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = settings.edit();

        person_name = settings.getString("name", "");
        person_pass = settings.getString("pass", "");

        if ( name.equalsIgnoreCase(person_name)  && password.equalsIgnoreCase(person_pass) ) {

            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            editor.putBoolean("logado", true);
            startActivity(intent);
            Toast.makeText(LoginActivity.this, "Bem vindo ao Eaters", Toast.LENGTH_LONG).show();
            Toast.makeText(LoginActivity.this, "Aplicação Portifólio Alonso Machado", Toast.LENGTH_LONG).show();
        }else {
            Toast.makeText(LoginActivity.this, "Confira Username e Password! ", Toast.LENGTH_SHORT).show();
        }
    }
}
