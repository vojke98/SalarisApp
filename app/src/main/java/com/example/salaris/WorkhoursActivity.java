package com.example.salaris;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import com.example.salaris.adapters.OnClickInterface;
import com.example.salaris.adapters.WorkhourListAdapter;
import com.example.salaris.models.Company;
import com.example.salaris.models.Role;
import com.example.salaris.models.User;
import com.example.salaris.models.User_Company;
import com.example.salaris.models.Workhour;
import java.util.ArrayList;
import java.util.Date;

public class WorkhoursActivity extends AppCompatActivity {
    private User user;
    private Company company;
    private WorkhourListAdapter workhourListAdapter;
    private ArrayList<Workhour> workhours = new ArrayList<>();;

    private RecyclerView rvWorkhourList;
    private TextView tvTotalEarned;
    private Button btnStartStop;

    private OnClickInterface onClickInterface;
    private boolean working = false;
    private Date from;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workhours);

        initializeComponents();
    }

    private void initializeComponents() {
        this.onClickInterface = new OnClickInterface() {
            @Override
            public void setClick(int position) {}
        };

        this.user = (User) getIntent().getSerializableExtra("user");
        this.company = (Company) getIntent().getSerializableExtra("company");

        this.rvWorkhourList = (RecyclerView) findViewById(R.id.rvWorkhourList);
        this.tvTotalEarned = (TextView) findViewById(R.id.tvTotalEarned);
        this.btnStartStop = (Button) findViewById(R.id.btnStartStop);

        this.btnStartStop.setOnClickListener(view -> toggleShift());

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        this.rvWorkhourList.setLayoutManager(layoutManager);
        this.workhourListAdapter = new WorkhourListAdapter(this.workhours);
        this.rvWorkhourList.setAdapter(this.workhourListAdapter);

        fetchWorkhours();
        this.workhourListAdapter.notifyDataSetChanged();

        double total = 0.0;
        for(Workhour w : this.workhours)  total += w.getTotalEarned();

        this.tvTotalEarned.setText(total + getString(R.string.eur));
    }

    private void fetchWorkhours() {
        Role role = new Role("CEO", 15.0);
        User_Company user_company = new User_Company(this.user, this.company, role, 20.0);

        Date from = new Date();
        Date until = new Date();
        from.setHours(from.getHours() - 8);

        this.workhours.add(new Workhour(user_company, from, until));
    }

    private void toggleShift() {
        if(this.working) {
            this.btnStartStop.setBackgroundColor(getResources().getColor(R.color.accent));
            this.btnStartStop.setText(R.string.startShift);
            Role role = new Role("CEO", 15.0);
            User_Company user_company = new User_Company(this.user, this.company, role, 20.0);
            Workhour workhour = new Workhour(user_company, this.from, new Date());

            this.workhours.add(workhour);

            this.workhourListAdapter.notifyDataSetChanged();
        } else {
            this.btnStartStop.setBackgroundColor(getResources().getColor(R.color.red));
            this.btnStartStop.setText(R.string.stopShift);

            this.from = new Date();
        }

        this.working = !this.working;
    }
}