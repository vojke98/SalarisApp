package com.example.salaris;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.salaris.models.User;

public class LoginActivity extends AppCompatActivity {
    private EditText etEmail, etPassword;
    private Button btnLogin;
    private TextView tvRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        this.etEmail = (EditText) findViewById(R.id.etEmail);
        this.etPassword = (EditText) findViewById(R.id.etPassword);
        this.btnLogin = (Button) findViewById(R.id.btnLogin);
        this.tvRegister = (TextView) findViewById(R.id.tvRegister);

        this.tvRegister.setOnClickListener(view -> goToRegisterActivity());

        this.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (TextUtils.isEmpty(etEmail.getText().toString()) || TextUtils.isEmpty(etPassword.getText().toString())){
                    Toast.makeText(LoginActivity.this, "Prosimo, popolnite vsa polja.", Toast.LENGTH_SHORT).show();
                }
                else goToCompanyActivity();

            }
        });


    }

    private void goToRegisterActivity() {
        Intent intent = new Intent(this, RegisterActivity.class);
        intent.putExtra("argName", "value");
        startActivity(intent);
    }

    private void goToCompanyActivity() {
        Intent intent = new Intent(this, CompanyActivity.class);
        User user = new User("Dejan", "Vojinović", "Gerbičeva Ulica 51a", "Ljubljana", "1000", "12345678", "dejanvojinovic@yahoo.com", "pass");
        intent.putExtra("user", user);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }

    private boolean checkLogin() {

        return true;
    }
}