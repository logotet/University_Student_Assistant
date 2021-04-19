package com.project.universitystudentassistant.models;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class SubjectTime {
    private LocalTime startHour;
    private LocalTime endHour;
    private LocalDate date;
    private DayOfWeek dayOfWeek;
    private boolean active;

    public SubjectTime(LocalTime startHour, LocalTime endHour, LocalDate date) {
        this.startHour = startHour;
        this.endHour = endHour;
        this.date = date;
    }

    public SubjectTime() {
    }

    public DayOfWeek getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(DayOfWeek dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getStartHour() {
        return startHour;
    }

    public void setStartHour(LocalTime startHour) {
        this.startHour = startHour;
    }

    public LocalTime getEndHour() {
        return endHour;
    }

    public void setEndHour(LocalTime endHour) {
        this.endHour = endHour;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public static List<SubjectTime> getDummySubjectData() {
        List<SubjectTime> data = new ArrayList<>();
        for (int i = 1; i <= 7; i++) {
            SubjectTime subjectTime = new SubjectTime();
            subjectTime.setDayOfWeek(DayOfWeek.of(i));
            subjectTime.setStartHour(LocalTime.of(9, 0));
            subjectTime.setEndHour(LocalTime.of(10, 0));
            subjectTime.setActive(false);
            data.add(subjectTime);
        }
        return data;
    }
}
