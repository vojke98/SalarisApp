package com.example.salaris;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.widget.TextView;
import com.example.salaris.adapters.OnClickInterface;
import com.example.salaris.adapters.StaffListAdapter;
import com.example.salaris.adapters.WorkhourListAdapter;
import com.example.salaris.models.Company;
import com.example.salaris.models.Role;
import com.example.salaris.models.User;
import com.example.salaris.models.User_Company;
import com.example.salaris.models.Workhour;
import java.util.ArrayList;
import java.util.Date;

public class WorkhoursActivity extends AppCompatActivity {
    private WorkhourListAdapter workhourListAdapter;
    private ArrayList<Workhour> workhours = new ArrayList<>();
    private RecyclerView rvWorkhourList;
    private TextView tvTotalHoursLbl;
    private OnClickInterface onClickInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workhours);

        onClickInterface = new OnClickInterface() {
            @Override
            public void setClick(int position) {

            }
        };

        this.rvWorkhourList = (RecyclerView) findViewById(R.id.rvWorkhourList);
        this.tvTotalHoursLbl = (TextView) findViewById(R.id.tvTotalHoursLbl);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        rvWorkhourList.setLayoutManager(layoutManager);
        workhourListAdapter = new WorkhourListAdapter(workhours);
        rvWorkhourList.setAdapter(workhourListAdapter);

        Role role = new Role("Programmer", 10.0);
        User user = new User("Janez", "Novak", "Gerbičeva ulica 51a", "Ljubljana", "1000", "123456789", "janez.novak@gmail.com", "test123");
        Company company = new Company("SALARIS", "Adress line", "City", "1000", "SL1234567", "About this company");
        User_Company user_company = new User_Company(user, company, role, 20.0);

        Date from = new Date();
        Date until = new Date();
        from.setHours(from.getHours() - 8);

        Workhour workhour = new Workhour(user_company, from, until);

        workhours.add(workhour);

        tvTotalHoursLbl.setText(workhours.size() + "");

        workhourListAdapter.notifyDataSetChanged();
    }
}