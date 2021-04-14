package com.project.universitystudentassistant.data.models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import com.project.universitystudentassistant.utils.AppConstants;

@Entity(tableName = "my_universities_table", indices = @Index(value = {"name"}, unique = true))
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
    @ColumnInfo(name = "graduation_rate")
    private int graduationRate;
    @ColumnInfo(name = "acceptance_rate")
    private int acceptanceRate;
    @ColumnInfo(name = "description")
    private String description;
    @Ignore
    private boolean isSelected;


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

    public int getGraduationRate() {
        return graduationRate;
    }

    public void setGraduationRate(int graduationRate) {
        this.graduationRate = graduationRate;
    }

    public int getAcceptanceRate() {
        return acceptanceRate;
    }

    public void setAcceptanceRate(int acceptanceRate) {
        this.acceptanceRate = acceptanceRate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isSelected() {
        return getImage().equals(AppConstants.SAVED);
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }
}
