package com.example.salaris.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.salaris.R;
import com.example.salaris.models.JoinRequest;
import java.util.ArrayList;
import java.util.List;

public class JoinRequestListAdapter extends RecyclerView.Adapter<JoinRequestListAdapter.JoinRequestHolder> {

    private ArrayList<JoinRequest> joinRequests;
    private List<ItemListener> listeners = new ArrayList<>();

    public void addItemListener(ItemListener il) {
        listeners.add(il);
    }

    public JoinRequestListAdapter(ArrayList<JoinRequest> joinRequests) {
        this.joinRequests = joinRequests;
    }

    @Override
    public JoinRequestHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());

        View view = layoutInflater.inflate(R.layout.join_request_list_item, parent, false);
        return new JoinRequestHolder(view);
    }

    @Override
    public int getItemCount() {
        return joinRequests == null? 0: joinRequests.size();
    }

    @Override
    public void onBindViewHolder(@NonNull JoinRequestHolder holder, final int position) {
        final JoinRequest worker = joinRequests.get(position);

        /*holder.setLastName(worker.getLastName());
        holder.setFirstName(worker.getFirstName());
        holder.setRole(worker.getRole().getTitle());
        holder.setAddress(worker.getAddress());
        holder.setPostNo(worker.getPostNo());
        holder.setCity(worker.getCity());
        holder.setHourlyRate(worker.getHourlyRate() + " €/h)");*/
    }

    public class JoinRequestHolder extends RecyclerView.ViewHolder{

        private TextView tvLastName, tvFirstName, tvRole, tvAddress, tvPostNo, tvCity, tvHourlyRate;

        public JoinRequestHolder(View itemView) {
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
                    JoinRequest clickedJoinRequest = joinRequests.get(pos);
                    Toast.makeText(view.getContext(), "You clicked " + clickedJoinRequest, Toast.LENGTH_SHORT).show();
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