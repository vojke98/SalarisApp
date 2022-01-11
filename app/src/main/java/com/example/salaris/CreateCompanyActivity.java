package com.example.salaris;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.salaris.models.Company;
import com.example.salaris.models.Role;
import com.example.salaris.models.User;

public class CreateCompanyActivity extends AppCompatActivity {

    private final int GET_NEW_COMPANY = 0;

    private User user;
    private TextView tvJoin;
    private EditText etCompanyName, etAddressLine, etCity, etPostNo, etTaxNo, etAbout;
    private Button btnCreate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_company);

        initializeComponents();
        setComponentListeners();
    }

    private void initializeComponents() {
        this.user = (User) getIntent().getSerializableExtra("user");
        this.tvJoin = (TextView) findViewById(R.id.tvJoin);
        this.etCompanyName = (EditText) findViewById(R.id.etCompanyName);
        this.etAddressLine = (EditText) findViewById(R.id.etAddressLine);
        this.etCity = (EditText) findViewById(R.id.etCity);
        this.etPostNo = (EditText) findViewById(R.id.etPostNo);
        this.etTaxNo = (EditText) findViewById(R.id.etTaxNo);
        this.etAbout = (EditText) findViewById(R.id.etAbout);
        this.btnCreate = (Button) findViewById(R.id.btnCreate);
    }

    private void setComponentListeners() {
        this.tvJoin.setOnClickListener(view -> goToJoinCompanyActivity());
        this.btnCreate.setOnClickListener(view -> createCompany());
    }

    private void goToJoinCompanyActivity() {
        Intent intent = new Intent(this, JoinCompanyActivity.class);
        intent.putExtra("user", this.user);
        startActivityForResult(intent, GET_NEW_COMPANY);
    }

    private void createCompany() {
        // DO THE THING
        //Role role = new Role("CEO", 15.0);
        Company company = new Company(this.etCompanyName.getText().toString(), this.etAddressLine.getText().toString(), this.etCity.getText().toString(), this.etPostNo.getText().toString(), this.etTaxNo.getText().toString(), this.etAbout.getText().toString(), this.user);

        /*this.user.setCompany(company);
        this.user.setRole(role);*/

        Intent intent = new Intent();
        intent.putExtra("company", company);
        setResult(Activity.RESULT_OK, intent);

        finish();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        if (requestCode == GET_NEW_COMPANY) {
            if (resultCode == Activity.RESULT_OK) {
                setResult(Activity.RESULT_OK, intent);
            } else if (resultCode == Activity.RESULT_CANCELED) {
                // something went wrong
            }

            finish();
        }
    }
}