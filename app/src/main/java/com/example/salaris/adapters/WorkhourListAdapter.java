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
import com.example.salaris.models.Workhour;

import java.util.ArrayList;
import java.util.Date;

public class WorkhourListAdapter extends RecyclerView.Adapter<WorkhourListAdapter.WorkhourHolder> {

    private ArrayList<Workhour> workhours;

    public WorkhourListAdapter(ArrayList<Workhour> workhours) {
        this.workhours = workhours;
    }

    @Override
    public WorkhourHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());

        View view = layoutInflater.inflate(R.layout.workhour_list_item, parent, false);
        return new WorkhourHolder(view);
    }

    @Override
    public int getItemCount() {
        return workhours == null? 0: workhours.size();
    }

    @Override
    public void onBindViewHolder(@NonNull WorkhourHolder holder, final int position) {
        final Workhour workhour = workhours.get(position);

        holder.setDayOfWeek(workhour.getDayOfWeekString());
        holder.setDate(workhour.getDateString());
        holder.setTimeFrom(workhour.getTimeFromString());
        holder.setTimeUntil(workhour.getTimeUntilString());
        holder.setEarned(workhour.getTotalEarned());
        holder.setTotalHours(workhour.getTotalHours());
    }

    public class WorkhourHolder extends RecyclerView.ViewHolder{

        private TextView tvDayOfWeek, tvDate, tvTimeFrom, tvTimeUntil, tvEarned, tvTotalHours;

        public WorkhourHolder(View itemView) {
            super(itemView);

            tvDayOfWeek = (TextView) itemView.findViewById(R.id.tvDayOfWeek);
            tvDate = (TextView) itemView.findViewById(R.id.tvDate);
            tvTimeFrom = (TextView) itemView.findViewById(R.id.tvTimeFrom);
            tvTimeUntil = (TextView) itemView.findViewById(R.id.tvTimeUntil);
            tvEarned = (TextView) itemView.findViewById(R.id.tvEarned);
            tvTotalHours = (TextView) itemView.findViewById(R.id.tvTotalHours);

            itemView.setOnClickListener(view -> {
                int pos = getAdapterPosition();

                if(pos != RecyclerView.NO_POSITION){
                    Workhour clickedWorkhour = workhours.get(pos);
                    Toast.makeText(view.getContext(), "You clicked " + clickedWorkhour, Toast.LENGTH_SHORT).show();
                }
            });
        }

        public void setDayOfWeek(String day) { this.tvDayOfWeek.setText(day); }
        public void setDate(String date) {
            this.tvDate.setText(date);
        }
        public void setTimeFrom(String time) {
            this.tvTimeFrom.setText(time);
        }
        public void setTimeUntil(String time) {
            this.tvTimeUntil.setText(time);
        }
        public void setEarned(Double earned) { this.tvEarned.setText(String.format("%.2f", earned)); }
        public void setTotalHours(Double hours) { this.tvTotalHours.setText(String.format("%.2f", hours)); }
    }
}