package com.project.universitystudentassistant.models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "all_university_database_table")
public class UniversityEntityPrep  {

    @PrimaryKey(autoGenerate = true)
    private int uid;
    @ColumnInfo(name = "name")
    private String name;
    @ColumnInfo(name = "address")
    private String address;
    @ColumnInfo(name = "city")
    private String city;
    @ColumnInfo(name = "state")
    private String state;
    @ColumnInfo(name = "cost")
    private int costOfAttendance;
    @ColumnInfo(name = "web_page")
    private String webPage;
    @ColumnInfo(name = "image")
    private String image;


    public UniversityEntityPrep(String name) {
        this.name = name;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getCostOfAttendance() {
        return costOfAttendance;
    }

    public void setCostOfAttendance(int costOfAttendance) {
        this.costOfAttendance = costOfAttendance;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getWebPage() {
        return webPage;
    }

    public void setWebPage(String webPage) {
        this.webPage = webPage;
    }
}
