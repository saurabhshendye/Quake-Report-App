package com.example.android.quakereport;

/**
 * Created by Saurabh on 10/16/2017.
 */

public class EarthQuake {

    private String Magnitude;
    private String City;
    private String Time;

//    private int Time;
//    private float Magnitude;

    public EarthQuake(String mag, String city, String date) {
        this.Magnitude = mag;
        this.City = city;
        this.Time = date;
    }

//    public EarthQuake(float mag, String city, int date) {
//        this.Magnitude = mag;
//        this.City = city;
//        this.Time = date;
//    }

    public String getMagnitude() {
        return Magnitude;
    }

    public String getCity() {
        return City;
    }

    public String getTime() {
        return Time;
    }
}
