package com.project.universitystudentassistant.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.project.universitystudentassistant.R;
import com.project.universitystudentassistant.models.Subject;
import com.project.universitystudentassistant.utils.SortManager;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class SubjectAdapter extends RecyclerView.Adapter<SubjectAdapter.SubjectHolder> {

    private List<Subject> subjects = new ArrayList<>();
    private SubjectHolder.OnSubjectClickedListener listener;
    private SortManager sortManager = new SortManager();
    private DayOfWeek dayOfWeek;

    public SubjectAdapter(List<Subject> subjects, SubjectHolder.OnSubjectClickedListener listener, DayOfWeek dayOfWeek) {
        this.subjects = subjects;
        this.listener = listener;
        this.dayOfWeek = dayOfWeek;
    }

    public void setDayOfWeek(DayOfWeek dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public void updateData(List<Subject> data){
        subjects.clear();
        subjects.addAll(data);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public SubjectHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.subject_day_view, parent, false);
        return new SubjectHolder(view, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull SubjectHolder holder, int position) {
        Subject subject = subjects.get(position);
        holder.name.setText(subject.getName());
        holder.teacher.setText(subject.getTeacher());
        holder.location.setText(subject.getLocation());
        LocalTime startHour = subject.getWeekMap().get(dayOfWeek).getStartHour();
        holder.hours.setText(startHour.toString());
        holder.setSubject(subject);
        holder.itemView.setBackgroundColor(subject.getColor());
    }

    @Override
    public int getItemCount() {
        return subjects.size();
    }

    public static class SubjectHolder extends RecyclerView.ViewHolder {

        TextView name, hours, teacher, location;
        Subject subject;
        private DayOfWeek dayOfWeek;
        public SubjectHolder(@NonNull View itemView, OnSubjectClickedListener listener) {
            super(itemView);
            name = itemView.findViewById(R.id.txt_subject_name);
            hours = itemView.findViewById(R.id.txt_subject_hours);
            teacher = itemView.findViewById(R.id.txt_subject_teacher);
            location = itemView.findViewById(R.id.txt_subject_location);
            itemView.setOnClickListener(view -> {
                listener.onSubjectClicked(subject);
            });
        }

        public void setSubject(Subject subject) {
            this.subject = subject;
        }

        public interface OnSubjectClickedListener{
            void onSubjectClicked(Subject subject);
        }
    }
}
