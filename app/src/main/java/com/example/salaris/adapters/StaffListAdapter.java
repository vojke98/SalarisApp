package com.example.salaris.adapters;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.salaris.R;
import com.example.salaris.UserWorkhoursActivity;
import com.example.salaris.models.User;
import java.util.ArrayList;
import java.util.List;

public class StaffListAdapter extends RecyclerView.Adapter<StaffListAdapter.StaffHolder> {

    private static final String TAG = "StaffRecyclerAdapter";

    private ArrayList<User> staff;;
    private OnUserListener mOnUserListener;

    public StaffListAdapter(ArrayList<User> staff, OnUserListener onUserListener) {
        this.staff = staff;
        this.mOnUserListener = onUserListener;
    }

    @NonNull
    @Override
    public StaffHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.staff_list_item, parent, false);
        return new StaffHolder(view, mOnUserListener);
    }

    @Override
    public int getItemCount() {
        return staff == null? 0: staff.size();
    }

    @Override
    public void onBindViewHolder(@NonNull StaffHolder holder, final int position) {
        final User worker = staff.get(position);

        holder.setLastName(worker.getLastName());
        holder.setFirstName(worker.getFirstName());
        holder.setRole(worker.getRole().getTitle());
        holder.setAddress(worker.getAddress());
        holder.setPostNo(worker.getPostNo());
        holder.setCity(worker.getCity());
        holder.setHourlyRate(worker.getHourlyRate() + " €/h)");
    }

    public class StaffHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView tvLastName, tvFirstName, tvRole, tvAddress, tvPostNo, tvCity, tvHourlyRate;
        OnUserListener mOnUserListener;

        public StaffHolder(View itemView, OnUserListener onUserListener) {
            super(itemView);

            tvLastName = (TextView) itemView.findViewById(R.id.tvLastName);
            tvFirstName = (TextView) itemView.findViewById(R.id.tvFirstName);
            tvRole = (TextView) itemView.findViewById(R.id.tvRole);
            tvAddress = (TextView) itemView.findViewById(R.id.tvAddress);
            tvPostNo = (TextView) itemView.findViewById(R.id.tvPostNo);
            tvCity = (TextView) itemView.findViewById(R.id.tvCity);
            tvHourlyRate = (TextView) itemView.findViewById(R.id.tvHourlyRate);
            mOnUserListener = onUserListener;

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Log.d(TAG, "onClick: " + getAdapterPosition());
            mOnUserListener.onUserClick(getAdapterPosition());
        }

        public void setLastName(String lastName) {
            this.tvLastName.setText(lastName);
        }

        public void setFirstName(String firstName) {
            this.tvFirstName.setText(firstName);
        }

        public void setRole(String role) {
            this.tvRole.setText(role);
        }

        public void setAddress(String address) {
            this.tvAddress.setText(address);
        }

        public void setPostNo(String postNo) {
            this.tvPostNo.setText(postNo);
        }

        public void setCity(String city) {
            this.tvCity.setText(city);
        }

        public void setHourlyRate(String hourlyRate) {
            this.tvHourlyRate.setText(hourlyRate);
        }
    }

    public interface OnUserListener{
        void onUserClick(int position);
    }
}