package com.project.universitystudentassistant.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;

import com.project.universitystudentassistant.R;
import com.project.universitystudentassistant.models.Subject;
import com.project.universitystudentassistant.models.SubjectTime;

import java.time.DayOfWeek;
import java.time.format.TextStyle;
import java.util.List;
import java.util.Locale;

public class WeekPickerAdapter extends RecyclerView.Adapter<WeekPickerAdapter.WeekPickerViewHolder> {

    private OnWeekPickerClickListener listener;
    List<SubjectTime> weekList;

    public WeekPickerAdapter(OnWeekPickerClickListener listener, List<SubjectTime> weekList) {
        this.listener = listener;
        this.weekList = weekList;
    }

    public void updateData(List<SubjectTime> data) {
        weekList.clear();
        weekList.addAll(data);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public WeekPickerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.week_day_picker, parent, false);
        return new WeekPickerViewHolder(view, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull WeekPickerViewHolder holder, int position) {
        SubjectTime subjectTime = weekList.get(position);
        holder.weekDay.setText(subjectTime.getDayOfWeek().getDisplayName(TextStyle.SHORT, Locale.US));
        if(subjectTime.isActive()){
            holder.weekDay.setChecked(true);
        }
        holder.weekDay.setChecked(subjectTime.isActive());
        holder.start.setText(subjectTime.getStartHour().toString());
        holder.end.setText(subjectTime.getEndHour().toString());
        holder.setSubjectTime(subjectTime);
    }

    @Override
    public int getItemCount() {
        return weekList.size();
    }

    public class WeekPickerViewHolder extends ViewHolder {
        private TextView start, end;
        private CheckBox weekDay;
        private SubjectTime subjectTime = new SubjectTime();

        public WeekPickerViewHolder(@NonNull View itemView, OnWeekPickerClickListener listener) {
            super(itemView);
            weekDay = itemView.findViewById(R.id.checkbox_week_day);
            start = itemView.findViewById(R.id.txt_from_picker);
            end = itemView.findViewById(R.id.txt_to_picker);
            setViews(false);
            weekDay.setOnCheckedChangeListener((compoundButton, b) -> {
                setViews(b);
//                listener.onWeekDayChecked(subjectTime, b);
            });
            start.setOnClickListener(v -> listener.onHourClicked(subjectTime, true));
            end.setOnClickListener(v -> listener.onHourClicked(subjectTime, false));
        }


        public void setSubjectTime(SubjectTime subjectTime) {
            this.subjectTime = subjectTime;
        }

        public void setViews(boolean checked) {
            if (checked) {
                weekDay.setChecked(true);
                start.setEnabled(true);
                end.setEnabled(true);
            } else {
                weekDay.setChecked(false);
                start.setEnabled(false);
                end.setEnabled(false);
            }
        }
    }


    public interface OnWeekPickerClickListener {
//        void onWeekDayChecked(SubjectTime subjectTime, boolean checked);

        void onHourClicked(SubjectTime subjectTime, boolean startHour);

    }
}
