package com.project.universitystudentassistant.data.local;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.project.universitystudentassistant.models.SubjectTime;

import java.lang.reflect.Type;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;


public class SubjectTimeConverter {

    private Gson gson = new Gson();

    @TypeConverter
    public String fromMapOfSubjTime(Map<DayOfWeek, SubjectTime> week) {
        return gson.toJson(week);
    }

    @TypeConverter
    public Map<DayOfWeek, SubjectTime> weekMapFromJson(String json) {
        Type type = new TypeToken<Map<DayOfWeek, SubjectTime>>() {
        }.getType();
        return new Gson().fromJson(json, type);
    }

    @TypeConverter
    public String fromMapOfSubjTime(SubjectTime s) {
        return gson.toJson(s);
    }

    @TypeConverter
    public SubjectTime sujectTimefromJson(String json) {
        Type type = new TypeToken<SubjectTime>() {
        }.getType();
        return gson.fromJson(json, type);
    }

    @TypeConverter
    public String fromLocalTime(LocalTime localTime) {
        int hour = localTime.getHour();
        int minute = localTime.getMinute();
        String time = hour + ":" + minute;
        return time;
    }

    @TypeConverter
    public LocalTime toLocalTime(String localTimeString) {
        LocalTime localTime;
        try {
            String[] time = localTimeString.split(":");
            localTime = LocalTime.of(Integer.parseInt(time[0]), Integer.parseInt(time[0]));
        }catch (Exception e){
            localTime = LocalTime.of(1, 1);
        }

        return localTime;
    }

    @TypeConverter
    public String fromLocalDate(LocalDate localDate) {
        return gson.toJson(localDate);
    }

    @TypeConverter
    public LocalDate toLocalDate(String localDateJson) {
        Type type = new TypeToken<LocalDate>() {
        }.getType();
        return gson.fromJson(localDateJson, type);
    }

    @TypeConverter
    public String fromDayOfWeek(DayOfWeek dayOfWeek) {
        return gson.toJson(dayOfWeek);
    }

    @TypeConverter
    public DayOfWeek toDayOfWeek(String dayOfWeekJson) {
        Type type = new TypeToken<DayOfWeek>() {
        }.getType();
        return gson.fromJson(dayOfWeekJson, type);
    }


}
