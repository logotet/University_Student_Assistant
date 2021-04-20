package com.project.universitystudentassistant.data.local;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.project.universitystudentassistant.models.SubjectTime;

import java.lang.reflect.Type;
import java.time.DayOfWeek;
import java.util.List;
import java.util.Map;


public class SubjectTimeConverter {

    private Gson gson = new Gson();

    @TypeConverter
    public String fromMapOfSubjTime(Map<DayOfWeek, SubjectTime> week){
        return gson.toJson(week);
    }

    @TypeConverter
    public Map<DayOfWeek, SubjectTime> weekMapFromJson(String json){
        Type type = new TypeToken<Map<DayOfWeek, SubjectTime>>() {}.getType();
        return new Gson().fromJson(json, type);
    }

    @TypeConverter
    public String fromMapOfSubjTime(SubjectTime s){
        return gson.toJson(s);
    }

    @TypeConverter
    public SubjectTime sujectTimefromJson(String json){
        Type type = new TypeToken<SubjectTime>() {}.getType();
        return new Gson().fromJson(json, type);
    }


}
