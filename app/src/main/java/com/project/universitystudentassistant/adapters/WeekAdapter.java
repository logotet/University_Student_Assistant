package com.project.universitystudentassistant.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.islandparadise14.mintable.MinTimeTableView;
import com.project.universitystudentassistant.R;

import java.util.ArrayList;
import java.util.List;

public class WeekAdapter extends RecyclerView.Adapter<WeekAdapter.WeekHolder> {

    List<String> weeks = new ArrayList<>();

    public WeekAdapter(List<String> weeks) {
        this.weeks = weeks;
    }

    @NonNull
    @Override
    public WeekHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.week_view, parent, false);
        return new WeekHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WeekHolder holder, int position) {
        holder.initTable();
    }

    @Override
    public int getItemCount() {
        return weeks.size();
    }

    public class WeekHolder extends RecyclerView.ViewHolder {

        private MinTimeTableView minTimeTableView;
        private String[] days = new String[]{"Mon", "Tue", "Wen", "Thu", "Fri", "Sat", "Sun"};

        public WeekHolder(@NonNull View itemView) {
            super(itemView);
            minTimeTableView = itemView.findViewById(R.id.table);
        }

        public void initTable(){
            minTimeTableView.initTable(days);
        }
    }
}
