package com.example.digdig.finalexam.model;

/**
 * Created by digdig on 17-06-29.
 */


public class WeatherWeek {
    private String code;
    private String date;
    private String day;
    private String high;
    private String low;

    public String getLow() {
        return low;
    }

    public void setLow(String low) {
        this.low = low;
    }

    private String info;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getHigh() {
        return high;
    }

    public void setHigh(String high) {
        this.high = high;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public WeatherWeek(String code, String date, String day, String high, String low, String info) {
        this.code = code;
        this.date = date;
        this.day = day;
        this.high = high;
        this.low = low;
        this.info = info;
    }

    @Override
    public String toString() {
        return "WeatherWeek{" +
                "code='" + code + '\'' +
                ", date='" + date + '\'' +
                ", day='" + day + '\'' +
                ", high='" + high + '\'' +
                ", low='" + low + '\'' +
                ", info='" + info + '\'' +
                '}';
    }
}
