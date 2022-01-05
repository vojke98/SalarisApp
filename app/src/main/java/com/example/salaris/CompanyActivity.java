package com.example.salaris;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import android.content.Context;
import android.content.DialogInterface;
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
import com.example.salaris.models.User_Company;
import com.example.salaris.models.Workhour;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.ArrayList;

public class CompanyActivity extends AppCompatActivity {
    User user;
    Company company;
    Context context;

    FloatingActionButton fabAdd, fabCreate, fabJoin;
    TextView tvCreate, tvJoin;
    Toolbar tbToolbar;
    CardView cardCompany, cardAlert;
    ImageView ivLeave;

    private boolean fabVisible = false;

    private Animation fabOpenRotateAnim, fabCloseRotateAnim, fabExpandAnim, fabCollapseAnim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company);

        initializeComponents();
        setOnClickListeners();
        checkLocationService();
    }

    private void initializeComponents() {
        this.user = (User) getIntent().getSerializableExtra("user");
        this.company = fetchUserCompany(user);
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

        if(this.company != null)  cardAlert.setVisibility(View.GONE);
        else cardCompany.setVisibility(View.GONE);

        this.fabOpenRotateAnim = AnimationUtils.loadAnimation(this, R.anim.fab_open_rotate_anim);
        this.fabCloseRotateAnim = AnimationUtils.loadAnimation(this, R.anim.fab_close_rotate_anim);
        this.fabExpandAnim = AnimationUtils.loadAnimation(this, R.anim.fab_expand_anim);
        this.fabCollapseAnim = AnimationUtils.loadAnimation(this, R.anim.fab_collapse_anim);

        this.ivLeave.setOnClickListener(view -> leaveCompany());

        setSupportActionBar(tbToolbar);
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

    private void setOnClickListeners() {
        this.fabAdd.setOnClickListener(view -> fabToggle());
        this.fabCreate.setOnClickListener(view -> goToCreateCompanyActivity());
        this.fabJoin.setOnClickListener(view -> goToJoinCompanyActivity());

        this.cardCompany.setOnClickListener(view -> goToStaffActivity());
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

    private void fabToggle() {
        fabVisible = !fabVisible;
        if(fabVisible){
            fabCreate.setVisibility(View.VISIBLE);
            fabJoin.setVisibility(View.VISIBLE);
            tvCreate.setVisibility(View.VISIBLE);
            tvJoin.setVisibility(View.VISIBLE);

            fabAdd.startAnimation(fabOpenRotateAnim);
            fabCreate.startAnimation(fabExpandAnim);
            fabJoin.startAnimation(fabExpandAnim);
            tvCreate.startAnimation(fabExpandAnim);
            tvJoin.startAnimation(fabExpandAnim);
        }else{
            fabCreate.setVisibility(View.INVISIBLE);
            fabJoin.setVisibility(View.INVISIBLE);
            tvCreate.setVisibility(View.INVISIBLE);
            tvJoin.setVisibility(View.INVISIBLE);

            fabAdd.startAnimation(fabCloseRotateAnim);
            fabCreate.startAnimation(fabCollapseAnim);
            fabJoin.startAnimation(fabCollapseAnim);
            tvCreate.startAnimation(fabCollapseAnim);
            tvJoin.startAnimation(fabCollapseAnim);
        }
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
        startActivity(intent);
    }

    private void goToCreateCompanyActivity() {
        Intent intent = new Intent(this, CreateCompanyActivity.class);
        intent.putExtra("user", this.user);
        startActivity(intent);
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

    private Company fetchUserCompany(User user) {
        Company company = new Company("SALARIS", "Adress line", "City", "1000", "SL1234567", "About this company");

        return company;
    }

    private void leaveCompany() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Remove company");
        builder.setMessage("Are you sure?");
        builder.setPositiveButton("YES", (dialog, which) -> {
            this.company = null;

            this.cardAlert.setVisibility(View.VISIBLE);
            this.cardCompany.setVisibility(View.GONE);

            dialog.dismiss();
        });
        builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Do nothing

                dialog.dismiss();
            }
        });
        AlertDialog alert = builder.create();
        alert.show();
    }
}