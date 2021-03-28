package com.logotet.universitystudentassistant.data.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "my_universities_table")
public class UniversityEntity {
    //    TODO: consider changing the field names when getting the data from the REST API
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


    public UniversityEntity(String name) {
        this.name = name;
    }

//    public UniversityEntity(int uid, String name, String city, String state, int costOfAttendance, String image) {
//        this.uid = uid;
//        this.name = name;
//        this.city = city;
//        this.state = state;
//        this.costOfAttendance = costOfAttendance;
//        this.image = image;
//    }

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
