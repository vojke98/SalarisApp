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

import com.example.salaris.models.Company;
import com.example.salaris.models.User;

public class RegisterActivity extends AppCompatActivity {
    private EditText etFirstName, etLastName, etAddressLine, etCity, etPostNo, etTaxNo, etEmail, etPassword, etConfirmPassword;
    private Button btnRegister;
    private TextView tvLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

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

        this.btnRegister.setOnClickListener(view -> checkRegister());
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

    private void checkRegister() {
        if (isEmpty(this.etFirstName) || isEmpty(this.etAddressLine) || isEmpty(this.etCity) || isEmpty(this.etFirstName) || isEmpty(this.etLastName) || isEmpty(this.etPostNo) || isEmpty(this.etTaxNo) || isEmpty(this.etConfirmPassword) || isEmpty(this.etPassword)) {
            Toast.makeText(RegisterActivity.this, "Please fill all required fields.", Toast.LENGTH_SHORT).show();
        } else goToCompanyActivity();
    }

    private boolean isEmpty(EditText et) {
        return et.getText().toString().trim().length() == 0;
    }

}
