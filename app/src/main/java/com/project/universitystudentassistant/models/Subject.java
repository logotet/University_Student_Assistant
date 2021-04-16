package com.project.universitystudentassistant.models;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;

public class Subject {
    private String name;
    private String teacher;
    private static List<DayOfWeek> daysOfWeek;
    private List<Integer> hours;
    private String location;
    private int color;

    public Subject(String name, String teacher, List<DayOfWeek> daysOfWeek, List<Integer> hours, String location) {
        this.name = name;
        this.teacher = teacher;
        this.daysOfWeek = daysOfWeek;
        this.hours = hours;
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public List<DayOfWeek> getDaysOfWeek() {
        return daysOfWeek;
    }

    public void setDaysOfWeek(List<DayOfWeek> daysOfWeek) {
        this.daysOfWeek = daysOfWeek;
    }

    public List<Integer> getHours() {
        return hours;
    }

    public void setHours(List<Integer> hours) {
        this.hours = hours;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public static List<Subject> getDummySubjectData(){
        List<Subject> data = new ArrayList<>();
        List<DayOfWeek> dayOfWeeks = new ArrayList<>();
        dayOfWeeks.add(DayOfWeek.MONDAY);
        dayOfWeeks.add(DayOfWeek.TUESDAY);
        dayOfWeeks.add(DayOfWeek.WEDNESDAY);
        dayOfWeeks.add(DayOfWeek.THURSDAY);
        List<Integer> hours = new ArrayList<>();
        hours.add(1);
        for (int i = 0; i < 6; i++) {
            Subject subject = new Subject("Name"+i,
                    "teacher" +i,
                    daysOfWeek,
                    hours,
                    "412");
            data.add(subject);
        }
        return data;
    }
}
