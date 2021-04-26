package com.project.universitystudentassistant.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.project.universitystudentassistant.R;
import com.project.universitystudentassistant.models.SubjectSchedule;
import com.project.universitystudentassistant.utils.SortManager;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Handler;

public class SubjectAdapter extends RecyclerView.Adapter<SubjectAdapter.SubjectHolder> {

    private List<SubjectSchedule> subjects = new ArrayList<>();
    private SubjectHolder.OnSubjectClickedListener listener;
    private SortManager sortManager = new SortManager();

    public SubjectAdapter(List<SubjectSchedule> subjects, SubjectHolder.OnSubjectClickedListener listener) {
        this.subjects = subjects;
        this.listener = listener;
    }

    public void updateData(List<SubjectSchedule> data) {
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
        SubjectSchedule subject = subjects.get(position);
        holder.name.setText(subject.getName());
        holder.teacher.setText(subject.getTeacher());
        holder.location.setText(subject.getLocation());
        holder.hours.setText(subject.getStartHour() + " - " + subject.getEndHour());
        holder.setSubject(subject);
        holder.itemView.setBackgroundColor(subject.getColor());
    }

    @Override
    public int getItemCount() {
        return subjects.size();
    }

    public static class SubjectHolder extends RecyclerView.ViewHolder {

        private TextView name, hours, teacher, location;
        private SubjectSchedule subject;
        private DayOfWeek dayOfWeek;
        private ImageButton imgEdit, imgDelete;

        public SubjectHolder(@NonNull View itemView, OnSubjectClickedListener listener) {
            super(itemView);
            name = itemView.findViewById(R.id.txt_subject_name);
            hours = itemView.findViewById(R.id.txt_subject_hours);
            teacher = itemView.findViewById(R.id.txt_subject_teacher);
            location = itemView.findViewById(R.id.txt_subject_location);
            imgEdit = itemView.findViewById(R.id.img_edit);
            imgDelete = itemView.findViewById(R.id.img_delete);
            itemView.setOnClickListener(view -> {
                listener.onSubjectClicked(subject);
                toggleVisibility();

            });
            imgDelete.setOnClickListener(view ->
                    listener.onDeleteClicked(subject));
            imgEdit.setOnClickListener(view ->
                    listener.onEditClicked(subject));
        }


        public void setSubject(SubjectSchedule subject) {
            this.subject = subject;
        }

        private void toggleVisibility() {
            if (imgDelete.getVisibility() == View.GONE && imgEdit.getVisibility() == View.GONE) {
                setViewsVisibility(View.VISIBLE);
                imgDelete.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        setViewsVisibility(View.GONE);
                    }
                }, 5000);
            } else {
                setViewsVisibility(View.GONE);
            }
        }

        private void setViewsVisibility(int visible) {
            imgDelete.setVisibility(visible);
            imgEdit.setVisibility(visible);
        }

        public interface OnSubjectClickedListener {
            void onSubjectClicked(SubjectSchedule subject);

            void onEditClicked(SubjectSchedule subject);

            void onDeleteClicked(SubjectSchedule subject);
        }
    }
}
