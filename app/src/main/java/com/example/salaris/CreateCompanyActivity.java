package com.example.salaris;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class CreateCompanyActivity extends AppCompatActivity {
    private TextView tvJoin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_company);

        initializeComponents();
    }

    private void initializeComponents() {
        this.tvJoin = (TextView) findViewById(R.id.tvJoin);

        this.tvJoin.setOnClickListener(view -> goToJoinCompanyActivity());
    }

    private void goToJoinCompanyActivity() {
        Intent intent = new Intent(this, JoinCompanyActivity.class);
        startActivity(intent);
        finish();
    }
}