package com.example.salaris;

import com.example.salaris.helper.GenericOnKeyListener;
import com.example.salaris.helper.GenericTextWatcher;
import com.example.salaris.models.User;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class JoinCompanyActivity extends AppCompatActivity  {
    private final int GET_NEW_COMPANY = 0;

    private User user;
    private Button btnJoin;
    private EditText etRefKey1, etRefKey2, etRefKey3, etRefKey4, etRefKey5, etRefKey6;
    private TextView tvCreate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join_company);

        initializeComponents();
    }

    private  void initializeComponents() {
        this.user = (User) getIntent().getSerializableExtra("user");
        this.btnJoin = (Button) findViewById(R.id.btnJoin);
        this.etRefKey1 = (EditText) findViewById(R.id.etRefKey1);
        this.etRefKey2 = (EditText) findViewById(R.id.etRefKey2);
        this.etRefKey3 = (EditText) findViewById(R.id.etRefKey3);
        this.etRefKey4 = (EditText) findViewById(R.id.etRefKey4);
        this.etRefKey5 = (EditText) findViewById(R.id.etRefKey5);
        this.etRefKey6 = (EditText) findViewById(R.id.etRefKey6);
        this.tvCreate = (TextView) findViewById(R.id.tvCreate);

        this.etRefKey1.setOnKeyListener(new GenericOnKeyListener(this.etRefKey1, this.etRefKey1));
        this.etRefKey2.setOnKeyListener(new GenericOnKeyListener(this.etRefKey1, this.etRefKey2));
        this.etRefKey3.setOnKeyListener(new GenericOnKeyListener(this.etRefKey2, this.etRefKey3));
        this.etRefKey4.setOnKeyListener(new GenericOnKeyListener(this.etRefKey3, this.etRefKey4));
        this.etRefKey5.setOnKeyListener(new GenericOnKeyListener(this.etRefKey4, this.etRefKey5));
        this.etRefKey6.setOnKeyListener(new GenericOnKeyListener(this.etRefKey5, this.etRefKey6));

        this.etRefKey1.addTextChangedListener(new GenericTextWatcher(this.etRefKey1, this.etRefKey2));
        this.etRefKey2.addTextChangedListener(new GenericTextWatcher(this.etRefKey2, this.etRefKey3));
        this.etRefKey3.addTextChangedListener(new GenericTextWatcher(this.etRefKey3, this.etRefKey4));
        this.etRefKey4.addTextChangedListener(new GenericTextWatcher(this.etRefKey4, this.etRefKey5));
        this.etRefKey5.addTextChangedListener(new GenericTextWatcher(this.etRefKey5, this.etRefKey6));
        this.etRefKey6.addTextChangedListener(new GenericTextWatcher(this.etRefKey6, this.etRefKey6));

        //this.etRefKey1.requestFocus();

        this.btnJoin.setOnClickListener(view -> joinCompany());
        this.tvCreate.setOnClickListener(view -> goToCreateCompanyActivity());
    }



    private void joinCompany() {
        // DO THE THING...

        finish();
    }

    private void goToCreateCompanyActivity() {
        Intent intent = new Intent(this, CreateCompanyActivity.class);
        intent.putExtra("user", this.user);
        startActivityForResult(intent, GET_NEW_COMPANY);
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