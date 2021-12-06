package com.example.salaris.adapters;
import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.salaris.R;
import com.example.salaris.models.Company;
import com.example.salaris.models.User;

import java.util.List;

public class CompanyListAdapter extends ArrayAdapter<Company> {
    Context context;
    int resource;
    List<Company> companyList;
    User user;
    ConstraintLayout clEmpty;
    LinearLayout llCompanyExists;
    TextView tvCompanyName, tvCompanyCEO, tvRole, tvHourlyRate, tvDateJoined, tvAbout;

    CompanyListAdapter(Context context, int resource, List<Company> companyList, User user) {
        super(context,resource, companyList);
        this.context = context;
        this.resource = resource;
        this.companyList = companyList;
        this.user = user;
    }

    @SuppressLint("SetTextI18n")
    @NonNull
    @Override
    public View getView(int id, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        @SuppressLint("ViewHolder") View view = inflater.inflate(resource,null,false);
        tvCompanyName = view.findViewById(R.id.tvCompanyName);
        tvCompanyCEO = view.findViewById(R.id.tvCompanyCEO);
        tvRole = view.findViewById(R.id.tvRole);
        tvHourlyRate = view.findViewById(R.id.tvHourlyRate);
        tvDateJoined = view.findViewById(R.id.tvDateJoined);
        tvAbout = view.findViewById(R.id.tvDescription);

        Company company = companyList.get(id);

        tvCompanyName.setText(company.getName());
        tvCompanyCEO.setText("by " + company.getCEO().getLastName() + ", " + company.getCEO().getFirstName());
        tvRole.setText(this.user.getRole().getTitle());
        tvHourlyRate.setText(this.user.getHourlyRate() + "");
        tvDateJoined.setText(this.user.getDateJoined().toString());
        tvAbout.setText(company.getAbout());

        return view;
    }
}