package com.example.rickb.week2d2;

import android.os.Parcel;
import android.os.Parcelable;

public class Student implements Parcelable {
    private String SSN;
    private String Name;
    private String Major;
    private String Minor;
    private String GPA;
    private String DOB;
    private String HomeCity;
    private String HomeState;


    public Student() {
    }

    public Student(String SSN, String name, String major, String minor, String GPA, String DOB, String homeCity, String homeState) {
        Name = name;
        Major = major;
        Minor = minor;
        this.GPA = GPA;
        this.DOB = DOB;
        HomeCity = homeCity;
        HomeState = homeState;
        this.SSN = SSN;
    }

    protected Student(Parcel in) {
        SSN = in.readString();
        Name = in.readString();
        Major = in.readString();
        Minor = in.readString();
        GPA = in.readString();
        DOB = in.readString();
        HomeCity = in.readString();
        HomeState = in.readString();
    }

    public static final Creator<Student> CREATOR = new Creator<Student>() {
        @Override
        public Student createFromParcel(Parcel in) {
            return new Student(in);
        }

        @Override
        public Student[] newArray(int size) {
            return new Student[size];
        }
    };

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getMajor() {
        return Major;
    }

    public void setMajor(String major) {
        Major = major;
    }

    public String getMinor() {
        return Minor;
    }

    public void setMinor(String minor) {
        Minor = minor;
    }

    public String getGPA() {
        return GPA;
    }

    public void setGPA(String GPA) {
        this.GPA = GPA;
    }

    public String getDOB() {
        return DOB;
    }

    public void setDOB(String DOB) {
        this.DOB = DOB;
    }

    public String getHomeCity() {
        return HomeCity;
    }

    public void setHomeCity(String homeCity) {
        HomeCity = homeCity;
    }

    public String getHomeState() {
        return HomeState;
    }

    public void setHomeState(String homeState) {
        HomeState = homeState;
    }

    public String getSSN() {
        return SSN;
    }

    public void setSSN(String SSN) {
        this.SSN = SSN;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(SSN);
        dest.writeString(Name);
        dest.writeString(Major);
        dest.writeString(Minor);
        dest.writeString(GPA);
        dest.writeString(DOB);
        dest.writeString(HomeCity);
        dest.writeString(HomeState);
    }
}
