package com.project.universitystudentassistant.models;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Subject {
    private String name;
    private String teacher;
    private String location;
    private Map<DayOfWeek, SubjectTime> weekMap;
    private int color;
    private boolean isRepeating;
    private SubjectTime singleEventInfo;


    public Subject() {
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

    public Map<DayOfWeek, SubjectTime> getWeekMap() {
        return weekMap;
    }

    public void setWeekMap(Map<DayOfWeek, SubjectTime> weekMap) {
        this.weekMap = weekMap;
    }

    public boolean isRepeating() {
        return isRepeating;
    }

    public void setRepeating(boolean repeating) {
        isRepeating = repeating;
    }

    public SubjectTime getSingleEventInfo() {
        return singleEventInfo;
    }

    public void setSingleEventInfo(SubjectTime singleEventInfo) {
        this.singleEventInfo = singleEventInfo;
    }

    public static Map<DayOfWeek, SubjectTime> createWeekMap(List<SubjectTime> days){
        Map<DayOfWeek, SubjectTime> dayMap = new HashMap<>();
        SubjectTime subjectTime;
        for (int i = 0; i < days.size(); i++) {
            subjectTime = days.get(i);
            if(subjectTime.isActive()){
                dayMap.put(subjectTime.getDayOfWeek(), subjectTime);
            }

        }
        return dayMap;
    }


//    public void setScheduleInfo(SubjectTime time){
//        if(isRepeating){
//            createWeekMap();
//            setSingleEventInfo(time);
//        }else {
//            setWeekMap(null);
//            setSingleEventInfo(time);
//        }
//    }

}
