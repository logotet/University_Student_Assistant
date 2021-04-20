package com.project.universitystudentassistant.models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import org.jetbrains.annotations.NotNull;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Entity(tableName = "subjects_table", indices = @Index(value = {"name"}, unique = true))
public class Subject {
    @NotNull
    @PrimaryKey(autoGenerate = true)
    public int uid;
    @ColumnInfo(name = "name")
    private String name;
    @ColumnInfo(name = "teacher")
    private String teacher;
    @ColumnInfo(name = "location")
    private String location;
    @ColumnInfo(name = "repeating_time")
    private Map<DayOfWeek, SubjectTime> weekMap;
    @ColumnInfo(name = "color")
    private int color;
    @ColumnInfo(name = "is_repeating_event")
    private boolean isRepeating;
    @ColumnInfo(name = "single_time")
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
