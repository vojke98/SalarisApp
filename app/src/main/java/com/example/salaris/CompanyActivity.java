package com.example.salaris;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.salaris.models.Company;
import com.example.salaris.models.Role;
import com.example.salaris.models.User;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CompanyActivity extends AppCompatActivity {
    private User user;
    private Company company;
    private Context context;

    private FloatingActionButton fabAdd, fabCreate, fabJoin;
    private TextView tvCreate, tvJoin, tvCompanyName, tvCompanyCEO, tvRole, tvHourlyRate, tvDateJoined, tvDescription;
    private Toolbar tbToolbar;
    private CardView cardCompany, cardAlert;
    private ImageView ivLeave;

    private boolean fabClicked = false;
    private final int GET_NEW_COMPANY = 0;

    private Animation rotateOpen, rotateClose, fromBottom, toBottom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company);

        initializeComponents();
        setEventListeners();
        checkLocationService();
    }

    private void initializeComponents() {
        this.user = (User) getIntent().getSerializableExtra("user");
        this.company = fetchUserCompany();
        this.context = this;

        this.fabAdd = (FloatingActionButton) findViewById(R.id.fabAdd);
        this.fabCreate = (FloatingActionButton) findViewById(R.id.fabCreate);
        this.fabJoin = (FloatingActionButton) findViewById(R.id.fabJoin);
        this.tvCreate = (TextView) findViewById(R.id.tvCreate);
        this.tvJoin = (TextView) findViewById(R.id.tvJoin);
        this.tbToolbar = (Toolbar) findViewById(R.id.tbToolbar);
        this.cardCompany = (CardView) findViewById(R.id.cardCompany);
        this.cardAlert = (CardView) findViewById(R.id.cardAlert);
        this.ivLeave = (ImageView) findViewById(R.id.ivLeave);

        this.tvCompanyName = (TextView) findViewById(R.id.tvCompanyName);
        this.tvCompanyCEO = (TextView) findViewById(R.id.tvCompanyCEO);
        this.tvRole = (TextView) findViewById(R.id.tvRole);
        this.tvHourlyRate = (TextView) findViewById(R.id.tvHourlyRate);
        this.tvDateJoined = (TextView) findViewById(R.id.tvDateJoined);
        this.tvDescription = (TextView) findViewById(R.id.tvDescription);

        this.rotateOpen = AnimationUtils.loadAnimation(this, R.anim.rotate_open_anim);
        this.rotateClose = AnimationUtils.loadAnimation(this, R.anim.rotate_close_anim);
        this.fromBottom = AnimationUtils.loadAnimation(this, R.anim.from_bottom_anim);
        this.toBottom = AnimationUtils.loadAnimation(this, R.anim.to_bottom_anim);

        setSupportActionBar(this.tbToolbar);
        onCompanyUpdate();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.toolbar_menu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.itLogOut) {
            goToLoginActivity();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        if (requestCode == GET_NEW_COMPANY) {
            if (resultCode == Activity.RESULT_OK) {
                this.company = (Company) intent.getSerializableExtra("company");
                onCompanyUpdate();
            } else if (resultCode == Activity.RESULT_CANCELED) {
                // something went wrong
            }
        }
    }

    private void setEventListeners() {
        this.fabAdd.setOnClickListener(view -> fabToggle(!this.fabClicked));
        this.fabCreate.setOnClickListener(view -> goToCreateCompanyActivity());
        this.fabJoin.setOnClickListener(view -> goToJoinCompanyActivity());

        this.cardCompany.setOnClickListener(view -> goToStaffActivity());
        this.ivLeave.setOnClickListener(view -> leaveCompany());
    }

    private void checkLocationService() {
        LocationManager lm = (LocationManager)context.getSystemService(Context.LOCATION_SERVICE);
        boolean gps_enabled = false;
        boolean network_enabled = false;

        try {
            gps_enabled = lm.isProviderEnabled(LocationManager.GPS_PROVIDER);
        } catch(Exception ignored) {}

        try {
            network_enabled = lm.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
        } catch(Exception ignored) {}

        if(!gps_enabled && !network_enabled) {
            // notify user
            new AlertDialog.Builder(context)
                    .setMessage("GPS not enabled")
                    .setPositiveButton("Open gps settings", (paramDialogInterface, paramInt) -> context.startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)))
                    .setNegativeButton("Cancel",null)
                    .show();
        }
    }

    private void fabToggle(boolean clicked) {
        //if(this.fabClicked == clicked) return;

        setFabAnimation(clicked);
        setFabVisibility(clicked);

        this.fabClicked = clicked;
    }

    private void setFabAnimation(boolean clicked) {
        if(clicked) {
            this.fabAdd.startAnimation(this.rotateOpen);
            this.fabCreate.startAnimation(this.fromBottom);
            this.fabJoin.startAnimation(this.fromBottom);
            this.tvCreate.startAnimation(this.fromBottom);
            this.tvJoin.startAnimation(this.fromBottom);
        } else {
            this.fabAdd.startAnimation(this.rotateClose);
            this.fabCreate.startAnimation(this.toBottom);
            this.fabJoin.startAnimation(this.toBottom);
            this.tvCreate.startAnimation(this.toBottom);
            this.tvJoin.startAnimation(this.toBottom);
        }
    }

    private void setFabVisibility(boolean clicked) {
        int visibility = clicked ? View.VISIBLE : View.GONE;

        this.tvJoin.setVisibility(visibility);
        this.fabJoin.setVisibility(visibility);
        this.tvCreate.setVisibility(visibility);
        this.fabCreate.setVisibility(visibility);
    }

    private void goToLoginActivity() {
        Intent intent = new Intent(this, LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }

    private void goToJoinCompanyActivity() {
        Intent intent = new Intent(this, JoinCompanyActivity.class);
        intent.putExtra("user", this.user);
        startActivityForResult(intent, GET_NEW_COMPANY);
    }

    private void goToCreateCompanyActivity() {
        Intent intent = new Intent(this, CreateCompanyActivity.class);
        intent.putExtra("user", this.user);
        startActivityForResult(intent, GET_NEW_COMPANY);
    }

    private void goToWorkhourActivity() {
        Intent intent = new Intent(this, WorkhoursActivity.class);
        intent.putExtra("user", this.user);
        intent.putExtra("company", this.company);
        startActivity(intent);
    }

    private void goToStaffActivity() {
        Intent intent = new Intent(this, StaffActivity.class);
        intent.putExtra("user", this.user);
        intent.putExtra("company", this.company);
        startActivity(intent);
    }

    private Company fetchUserCompany() {
        Role role = new Role("CEO", 15.0);
        this.user.setRole(role);
        this.user.setHourlyRate(20.0);
        this.user.setDateJoined(new Date());
        Company company = new Company("SALARIS", "Adress line", "City", "1000", "SL1234567", "About this company", this.user);

        this.user.setCompany(company);
        return company;
    }

    private void onCompanyUpdate() {
        this.fabClicked = false;
        fabToggle(false);

        if(this.company != null)  {
            //this.fabAdd.setVisibility(View.GONE);
            this.cardAlert.setVisibility(View.GONE);
            this.cardCompany.setVisibility(View.VISIBLE);

            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEE, DD.MM.YYYY");

            this.tvCompanyName.setText(this.company.getName());
            this.tvCompanyCEO.setText(this.company.getCEO().getFullName());
            this.tvRole.setText(this.user.getRole().toString());
            this.tvHourlyRate.setText(this.user.getHourlyRate() + "");
            this.tvDateJoined.setText(simpleDateFormat.format(this.user.getDateJoined()));
            this.tvDescription.setText(this.company.getAbout());
        }
        else {
            //this.fabAdd.setVisibility(View.VISIBLE);
            this.cardAlert.setVisibility(View.VISIBLE);
            this.cardCompany.setVisibility(View.GONE);
        }
    }

    private void leaveCompany() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Remove company");
        builder.setMessage("Are you sure?");
        builder.setPositiveButton("YES", (dialog, which) -> {
            this.company = null;

            onCompanyUpdate();

            dialog.dismiss();
        });
        builder.setNegativeButton("NO", (dialog, which) -> {
            // Do nothing

            dialog.dismiss();
        });
        AlertDialog alert = builder.create();
        alert.show();
    }
}