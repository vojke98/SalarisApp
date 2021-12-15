package com.example.salaris;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.salaris.adapters.WorkhourListAdapter;
import com.example.salaris.models.Workhour;

import java.util.ArrayList;
import java.util.Date;

public class UserWorkhoursActivity extends AppCompatActivity {
    private WorkhourListAdapter workhourAdapter;
    private ArrayList<Workhour> workhours = new ArrayList<>();
    private RecyclerView rvWorkhourList;
    private TextView tvTotalHours, tvTotalEarned, tvTotalPaid;
    private Button btnShift;
    private boolean working = false;
    Context context;
    Date from, until;
    Double totalHours = 0.0, totalEarned = 0.0, totalPaid = 0.0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_workhours);

        this.tvTotalHours = (TextView) findViewById(R.id.tvTotalHours);
        this.tvTotalEarned = (TextView) findViewById(R.id.tvTotalEarned);
        this.tvTotalPaid = (TextView) findViewById(R.id.tvTotalPaid);
        this.btnShift = (Button) findViewById(R.id.btnShift);
        context = this;

        this.rvWorkhourList = (RecyclerView) findViewById(R.id.rvWorkhourList);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        rvWorkhourList.setLayoutManager(layoutManager);
        workhourAdapter = new WorkhourListAdapter(workhours, this);
        rvWorkhourList.setAdapter(workhourAdapter);

        from = new Date();
        until = new Date();
        until.setHours(until.getHours()+8);
        workhours.add(new Workhour(null, null, from, until, 5.5));
        workhours.add(new Workhour(null, null, from, until, 10));

        workhourAdapter.notifyDataSetChanged();

        totalHours = getTotalHours();
        tvTotalHours.setText(totalHours + "h");
        totalEarned = getTotalEarned();
        tvTotalEarned.setText(totalEarned + " €");
        totalPaid = getTotalPaid();
        tvTotalPaid.setText(totalPaid + " €");

        btnShift.setOnClickListener(view -> shiftHandler());
    }

    private void shiftHandler() {
        working = !working;

        if(working) {
            btnShift.setText("STOP SHIFT");
            btnShift.setBackgroundColor(ContextCompat.getColor(context, R.color.danger));
            from = new Date();
        } else {
            btnShift.setText("START SHIFT");
            btnShift.setBackgroundColor(ContextCompat.getColor(context, R.color.primary));
            until = new Date();
            workhours.add(new Workhour(null, null, from, until, 5.5));
            workhourAdapter.notifyDataSetChanged();
        }
    }

    private double getTotalHours() {
        double total = 0;
        for(Workhour wh : workhours) {
            total += wh.getHours();
        }
        return total;
    }

    private double getTotalEarned() {
        double total = 0;
        for(Workhour wh : workhours) {
            total += wh.getTotal();
            System.out.println(wh.getTotal());
        }
        return total;
    }

    private double getTotalPaid() {
        double total = 0;
        for(Workhour wh : workhours) {
            total += wh.getTotal();
        }
        return total;
    }

}