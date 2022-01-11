package com.example.salaris;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.example.salaris.helper.API;
import com.example.salaris.helper.VolleyCallback;
import com.example.salaris.models.User;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity {
    private EditText etEmail, etPassword;
    private Button btnLogin;
    private TextView tvRegister;
    private API api;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        this.etEmail = (EditText) findViewById(R.id.etEmail);
        this.etPassword = (EditText) findViewById(R.id.etPassword);
        this.btnLogin = (Button) findViewById(R.id.btnLogin);
        this.tvRegister = (TextView) findViewById(R.id.tvRegister);

        this.tvRegister.setOnClickListener(view -> goToRegisterActivity());
        this.btnLogin.setOnClickListener(view -> checkLogin());

        this.api = new API(this);
    }

    private void goToRegisterActivity() {
        Intent intent = new Intent(this, RegisterActivity.class);
        intent.putExtra("argName", "value");
        startActivity(intent);
    }

    private void goToCompanyActivity(User user) {
        Intent intent = new Intent(this, CompanyActivity.class);
        intent.putExtra("user", user);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }

    private void checkLogin() {
        if (TextUtils.isEmpty(etEmail.getText().toString()) || TextUtils.isEmpty(etPassword.getText().toString())){
            Toast.makeText(LoginActivity.this, "Please fill all required fields.", Toast.LENGTH_SHORT).show();
        } else {
            fetchUser();
        }
    }

    private void fetchUser() {
        String email = this.etEmail.getText().toString();
        String password = this.etPassword.getText().toString();

        this.api.getUser(email, password, new VolleyCallback() {
            @Override
            public void onSuccess(String response) {
                try {
                    JSONArray jsonArray = new JSONArray(response);
                    JSONObject uo = jsonArray.getJSONObject(0);

                    Integer id = (Integer) uo.get("id");
                    String firstName = (String) uo.get("first_name");
                    String lastName = (String) uo.get("last_name");
                    String taxNo = (String) uo.get("tax_no");
                    String email = (String) uo.get("email");
                    String password = (String) uo.get("password");
                    Integer companyId = (Integer) uo.get("company");
                    Integer roleId = (Integer) uo.get("role");
                    Integer addressId = (Integer) uo.get("address");

                    User user = new User(id, firstName, lastName, taxNo, email, password, companyId, roleId, addressId);

                    goToCompanyActivity(user);
                } catch (JSONException err) {
                    Log.d("Error", err.toString());
                    Toast.makeText(LoginActivity.this, "Invalid login.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onError(VolleyError error) {
                VolleyLog.e("Error: ", error.getMessage());
            }
        });
    }
}