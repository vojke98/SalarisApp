package com.example.salaris.adapters;

import android.content.Context;
import android.content.Intent;
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

public interface ItemListener {
    void clicked(User clickedUser);
}

public class StaffListAdapter extends RecyclerView.Adapter<StaffListAdapter.StaffHolder> {

    private ArrayList<User> staff;
    private List<ItemListener> listeners = new ArrayList<>();

    public void addItemListener(ItemListener il) {
        listeners.add(il);
    }

    public StaffListAdapter(ArrayList<User> staff) {
        this.staff = staff;
    }

    @Override
    public StaffHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());

        View view = layoutInflater.inflate(R.layout.staff_list_item, parent, false);
        return new StaffHolder(view);
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

    public class StaffHolder extends RecyclerView.ViewHolder{

        private TextView tvLastName, tvFirstName, tvRole, tvAddress, tvPostNo, tvCity, tvHourlyRate;

        public StaffHolder(View itemView) {
            super(itemView);

            tvLastName = (TextView) itemView.findViewById(R.id.tvLastName);
            tvFirstName = (TextView) itemView.findViewById(R.id.tvFirstName);
            tvRole = (TextView) itemView.findViewById(R.id.tvRole);
            tvAddress = (TextView) itemView.findViewById(R.id.tvAddress);
            tvPostNo = (TextView) itemView.findViewById(R.id.tvPostNo);
            tvCity = (TextView) itemView.findViewById(R.id.tvCity);
            tvHourlyRate = (TextView) itemView.findViewById(R.id.tvHourlyRate);

            itemView.setOnClickListener(view -> {
                int pos = getAdapterPosition();

                if(pos != RecyclerView.NO_POSITION){
                    User clickeUser = staff.get(pos);
                    for (ItemListener hl : listeners)
                        hl.clicked(clickeUser);
                    Toast.makeText(view.getContext(), "You clicked " + clickeUser.getFirstName(), Toast.LENGTH_SHORT).show();
                }
            });
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
}