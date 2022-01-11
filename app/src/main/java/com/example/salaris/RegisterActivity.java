package com.example.salaris;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.salaris.models.User;

public class RegisterActivity extends AppCompatActivity {
    private EditText etFirstName, etLastName, etAddressLine, etCity, etPostNo, etTaxNo, etEmail, etPassword, etConfirmPassword;
    private Button btnRegister;
    private TextView tvLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        this.etFirstName = findViewById(R.id.etFirstName);
        this.etLastName = findViewById(R.id.etLastName);
        this.etAddressLine = findViewById(R.id.etAddressLine);
        this.etCity = findViewById(R.id.etCity);
        this.etPostNo = findViewById(R.id.etPostNo);
        this.etTaxNo = findViewById(R.id.etTaxNo);
        this.etEmail = findViewById(R.id.etEmail);
        this.etPassword = findViewById(R.id.etPassword);
        this.etConfirmPassword = findViewById(R.id.etConfirmPassword);
        this.btnRegister = findViewById(R.id.btnRegister);
        this.tvLogin = findViewById(R.id.tvLogin);

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
        if (isEmpty(this.etFirstName) || isEmpty(this.etLastName) || isEmpty(this.etAddressLine) || isEmpty(this.etCity) || isEmpty(this.etPostNo) || isEmpty(this.etTaxNo) || isEmpty(this.etTaxNo) || isEmpty(this.etEmail) || isEmpty(this.etPassword) || isEmpty(this.etConfirmPassword)) {
            Toast.makeText(RegisterActivity.this, "Please fill all required fields.", Toast.LENGTH_SHORT).show();
        } else goToCompanyActivity();
    }

    private boolean isEmpty(EditText et) {
        return et.getText().toString().trim().length() == 0;
    }

}
