package com.example.android.quakereport;

import java.util.Date;

/**
 * Created by Saurabh on 10/16/2017.
 */

public class EarthQuake {

    private String Magnitude;
    private String City;
    private Date Time;
    private String url;

//    private int Time;
//    private float Magnitude;

    public EarthQuake(String mag, String city, Date date, String url) {
        this.Magnitude = mag;
        this.City = city;
        this.Time = date;
        this.url = url;
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

    public Date getTime() {
        return Time;
    }

    public String getUrl() {
        return url;
    }
}
