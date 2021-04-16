package com.project.universitystudentassistant.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.project.universitystudentassistant.R;
import com.project.universitystudentassistant.models.Subject;

import java.util.ArrayList;
import java.util.List;

public class SubjectAdapter extends RecyclerView.Adapter<SubjectAdapter.SubjectHolder> {

    private List<Subject> subjects = new ArrayList<>();
    private SubjectHolder.OnSubjectClickedListener listener;

    public SubjectAdapter(List<Subject> subjects, SubjectHolder.OnSubjectClickedListener listener) {
        this.subjects = subjects;
        this.listener = listener;
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
        holder.hours.setText(subject.getHours().get(0).toString());
        holder.setSubject(subject);
    }

    @Override
    public int getItemCount() {
        return subjects.size();
    }

    public static class SubjectHolder extends RecyclerView.ViewHolder {

        TextView name, hours;
        Subject subject;
        public SubjectHolder(@NonNull View itemView, OnSubjectClickedListener listener) {
            super(itemView);
            name = itemView.findViewById(R.id.txt_subject_name);
            hours = itemView.findViewById(R.id.txt_subject_hours);
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
