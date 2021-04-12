package com.project.universitystudentassistant.data.entities;

import androidx.room.Entity;
import androidx.room.Index;

@Entity(tableName = "prepopulated_universities_table", indices = @Index(value = {"name"}, unique = true))
public class UniversityEntityPrep extends UniversityEntity {

    public UniversityEntityPrep(String name) {
        super(name);
    }
}