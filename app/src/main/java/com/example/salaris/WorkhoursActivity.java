package com.example.salaris;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
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
    private TextView tvCompanyName, tvTaxNo, tvWelcome, tvUser, tvTotalEarned;
    private Button btnStartStop;

    private boolean working = false;
    private boolean readOnly = false;
    private Date from;
    private double total = 0.0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workhours);

        initializeComponents();
        setEventListeners();
    }

    private void initializeComponents() {

        this.user = (User) getIntent().getSerializableExtra("user");
        this.company = (Company) getIntent().getSerializableExtra("company");
        this.readOnly = getIntent().getBooleanExtra("readOnly", false);

        this.rvWorkhourList = (RecyclerView) findViewById(R.id.rvWorkhourList);
        this.tvCompanyName = (TextView) findViewById(R.id.tvCompanyName);
        this.tvTaxNo = (TextView) findViewById(R.id.tvTaxNo);
        this.tvWelcome = (TextView) findViewById(R.id.tvWelcome);
        this.tvUser = (TextView) findViewById(R.id.tvUser);
        this.tvTotalEarned = (TextView) findViewById(R.id.tvTotalEarned);
        this.btnStartStop = (Button) findViewById(R.id.btnStartStop);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        this.rvWorkhourList.setLayoutManager(layoutManager);
        this.workhourListAdapter = new WorkhourListAdapter(this.workhours);
        this.rvWorkhourList.setAdapter(this.workhourListAdapter);

        fetchWorkhours();
        this.workhourListAdapter.notifyDataSetChanged();

        for(Workhour w : this.workhours)  this.total += w.getTotalEarned();

        this.tvTotalEarned.setText(this.total + getString(R.string.eur));
        this.tvCompanyName.setText(this.company.getName());
        this.tvTaxNo.setText(this.user.getTaxNo());

        if(this.readOnly) {
            this.btnStartStop.setVisibility(View.GONE);
            this.tvWelcome.setText("Worker ");
            this.tvUser.setText(this.user.getFullName());
        } else {
            this.tvUser.setText(this.user.getFirstName());
        }
    }

    private void setEventListeners() {
        this.btnStartStop.setOnClickListener(view -> toggleShift());
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
            this.total += workhour.getTotalEarned();
            this.tvTotalEarned.setText(this.total + getString(R.string.eur));

            this.workhourListAdapter.notifyDataSetChanged();
        } else {
            this.btnStartStop.setBackgroundColor(getResources().getColor(R.color.red));
            this.btnStartStop.setText(R.string.stopShift);

            this.from = new Date();
        }

        this.working = !this.working;
    }
}