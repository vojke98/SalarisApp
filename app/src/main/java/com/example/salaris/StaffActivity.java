package com.example.salaris;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.salaris.adapters.OnClickInterface;
import com.example.salaris.adapters.StaffListAdapter;
import com.example.salaris.models.Role;
import com.example.salaris.models.User;
import java.util.ArrayList;

public class StaffActivity extends AppCompatActivity {
    private StaffListAdapter staffAdapter;
    private ArrayList<User> staff = new ArrayList<>();
    private RecyclerView rvStaffList;
    private TextView tvStaffCount;
    private OnClickInterface onClickInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staff);

        onClickInterface = new OnClickInterface() {
            @Override
            public void setClick(int position) {

            }
        };

        this.rvStaffList = (RecyclerView) findViewById(R.id.rvStaffList);
        this.tvStaffCount = (TextView) findViewById(R.id.tvStaffCount);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        rvStaffList.setLayoutManager(layoutManager);
        staffAdapter = new StaffListAdapter(staff);
        rvStaffList.setAdapter(staffAdapter);

        Role programmer = new Role("Programmer", 10.0);
        User user = new User("Janez", "Novak", "Gerbičeva ulica 51a", "Ljubljana", "1000", "123456789", "janez.novak@gmail.com", "test123");
        user.setRole(programmer);

        staff.add(user);

        tvStaffCount.setText(staff.size() + "");

        staffAdapter.notifyDataSetChanged();
    }

    void goToUserWorkhoursActivity() {
        Intent intent = new Intent(this, UserWorkhoursActivity.class);
        intent.putExtra("argName", "value");
        startActivity(intent);
    }

}