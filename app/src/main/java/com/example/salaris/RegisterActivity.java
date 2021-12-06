package com.example.salaris;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class RegisterActivity extends AppCompatActivity {
    private EditText etFirstName, etLastName, etAddressLine, etCity, etPostNo, etTaxNo, etEmail, etPassword, etConfirmPassword;
    private Button btnRegister;
    private TextView tvLogin;
    Intent intent;

    int LAUNCH_REGISTER_ACTIVITY = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        this.intent = getIntent();

        String test = this.intent.getStringExtra("argName");
        if(test.equals("value")) {
            Intent resultIntent = new Intent();

            resultIntent.putExtra("result", "TEST RESULT");
            startActivityForResult(intent, LAUNCH_REGISTER_ACTIVITY);
        }

        this.etFirstName = (EditText) findViewById(R.id.etFirstName);
        this.etLastName = (EditText) findViewById(R.id.etLastName);
        this.etAddressLine = (EditText) findViewById(R.id.etAddressLine);
        this.etCity = (EditText) findViewById(R.id.etCity);
        this.etPostNo = (EditText) findViewById(R.id.etPostNo);
        this.etTaxNo = (EditText) findViewById(R.id.etTaxNo);
        this.etEmail = (EditText) findViewById(R.id.etEmail);
        this.etPassword = (EditText) findViewById(R.id.etPassword);
        this.etConfirmPassword = (EditText) findViewById(R.id.etConfirmPassword);
        this.btnRegister = (Button) findViewById(R.id.btnRegister);
        this.tvLogin = (TextView) findViewById(R.id.tvLogin);

        this.tvLogin.setOnClickListener(view -> finish());
    }
}