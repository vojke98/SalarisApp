package com.example.salaris;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;
import com.example.salaris.adapters.ItemListener;
import com.example.salaris.adapters.OnClickInterface;
import com.example.salaris.adapters.StaffListAdapter;
import com.example.salaris.models.Company;
import com.example.salaris.models.Role;
import com.example.salaris.models.User;
import java.util.ArrayList;

public class StaffActivity extends AppCompatActivity implements StaffListAdapter.OnUserListener {

    private User user;
    private Company company;

    private StaffListAdapter staffAdapter;
    private ArrayList<User> staff = new ArrayList<>();

    private RecyclerView rvStaffList;
    private TextView tvStaffCount, tvCompanyCEO, tvCompanyName, tvTaxNo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staff);

        initializeComponents();
    }

    private void initializeComponents() {
        this.user = (User) getIntent().getSerializableExtra("user");
        this.company = (Company) getIntent().getSerializableExtra("company");

        this.rvStaffList = (RecyclerView) findViewById(R.id.rvStaffList);
        this.tvStaffCount = (TextView) findViewById(R.id.tvStaffCount);
        this.tvCompanyCEO = (TextView) findViewById(R.id.tvCompanyCEO);
        this.tvCompanyName = (TextView) findViewById(R.id.tvCompanyName);
        this.tvTaxNo = (TextView) findViewById(R.id.tvTaxNo);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        this.rvStaffList.setLayoutManager(layoutManager);
        this.staffAdapter = new StaffListAdapter(this.staff, this);
        this.rvStaffList.setAdapter(this.staffAdapter);

        fetchStaff();

        this.tvStaffCount.setText(this.staff.size() + "");
        this.tvCompanyCEO.setText(this.company.getCEO().getFirstName());
        this.tvCompanyName.setText(this.company.getName());
        this.tvTaxNo.setText(this.company.getTaxNo());
    }

    @Override
    public void onUserClick(int position) {
        Intent intent = new Intent(this, WorkhoursActivity.class);
        intent.putExtra("user", staff.get(position));
        startActivity(intent);
    }

    private void fetchStaff() {
        Role programmer = new Role("Programmer", 10.0);
        User user = new User("Janez", "Novak", "Gerbičeva ulica 51a", "Ljubljana", "1000", "123456789", "janez.novak@gmail.com", "test123");
        user.setRole(programmer);

        this.staff.add(user);
        this.staffAdapter.notifyDataSetChanged();
    }

    private void goToUserWorkhoursActivity() {
        Intent intent = new Intent(this, UserWorkhoursActivity.class);
        intent.putExtra("argName", "value");
        startActivity(intent);
    }

}