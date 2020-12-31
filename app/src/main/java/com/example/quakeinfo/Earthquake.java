package com.example.quakeinfo;

import java.text.DecimalFormat;

public class Earthquake {
    private String mQuakeTitle;
    private String mQuakeLocation;
    private String mQuakeUrl;
    private String mQuakeDetail;
    private double mQuakeMagnitude;
    private long mQuakeTime;


    Earthquake(String title, String location, String url, String detail, double mag, long time) {
        mQuakeTitle = title;
        mQuakeLocation = location;
        mQuakeUrl = url;
        mQuakeDetail = detail;
        mQuakeMagnitude = mag;
        mQuakeTime = time;
    }

    public String getmQuakeTitle() {
        return mQuakeTitle;
    }

    public String getmQuakeLocation() {
        return mQuakeLocation;
    }

    public String getmQuakeUrl() {
        return mQuakeUrl;
    }

    public String getmQuakeDetail() {
        return mQuakeDetail;
    }

    public String getmQuakeMagnitude() {
        DecimalFormat magnitudeFormat = new DecimalFormat("0.0");
        return magnitudeFormat.format(mQuakeMagnitude);
    }

    public long getmQuakeTime() {
        return mQuakeTime;
    }
}
