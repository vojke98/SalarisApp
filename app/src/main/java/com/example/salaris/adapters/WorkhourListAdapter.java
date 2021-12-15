package com.example.salaris.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.salaris.R;
import com.example.salaris.models.Workhour;

import java.util.ArrayList;

public class WorkhourListAdapter extends RecyclerView.Adapter<WorkhourListAdapter.WorkhourHolder> {

    private ArrayList<Workhour> workhours;
    private Context context;

    public WorkhourListAdapter(ArrayList<Workhour> workhours, Context context) {
        this.workhours = workhours;
        this.context = context;
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

        holder.setWorkhourDayOfWeek(workhour.getDayOfWeekString());
        holder.setWorkhourDate(workhour.getDateString());
        holder.setWorkhourTimeFrom(workhour.getTimeFromString());
        holder.setWorkhourTimeUntil(workhour.getTimeUntilString());
        holder.setWorkhourEarned(workhour.getTotal() + " €");
        holder.setWorkhourTotalHours(workhour.getHours() + "h (" + workhour.getHourlyRateAtTheTime() + " €/h)");
    }

    public class WorkhourHolder extends RecyclerView.ViewHolder {

        private TextView tvDayOfWeek, tvDate, tvTimeFrom, tvTimeUntil, tvEarned, tvTotalHours;

        public WorkhourHolder(View itemView) {
            super(itemView);

            tvDayOfWeek = (TextView) itemView.findViewById(R.id.tvDayOfWeek);
            tvDate = (TextView) itemView.findViewById(R.id.tvDate);
            tvTimeFrom = (TextView) itemView.findViewById(R.id.tvTimeFrom);
            tvTimeUntil = (TextView) itemView.findViewById(R.id.tvTimeUntil);
            tvEarned = (TextView) itemView.findViewById(R.id.tvEarned);
            tvTotalHours = (TextView) itemView.findViewById(R.id.tvTotalHours);

        }

        public void setWorkhourDayOfWeek(String day) {
            tvDayOfWeek.setText(day);
        }

        public void setWorkhourDate(String date) {
            tvDate.setText(date);
        }

        public void setWorkhourTimeFrom(String time) {
            tvTimeFrom.setText(time);
        }

        public void setWorkhourTimeUntil(String time) {
            tvTimeUntil.setText(time);
        }
        public void setWorkhourEarned(String earned) {
            tvEarned.setText(earned);
        }

        public void setWorkhourTotalHours(String hours) {
            tvTotalHours.setText(hours);
        }

    }
}