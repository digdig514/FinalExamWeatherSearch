package com.example.digdig.finalexam.model;

/**
 * Created by digdig on 17-06-29.
 */

public class Weather {
    private String sunrise;
    private String sunset;
    private int humidty;
    private String temperatureUnit;
    private int temperatureValue;
    private String dateTime;
    private String weatherText;
    private int code;
    private String city;

    public Weather(String sunrise, String sunset, int humidty, String temperatureUnit, int temperatureValue, String dateTime, String weatherText, int code, String city) {
        this.sunrise = sunrise;
        this.sunset = sunset;
        this.humidty = humidty;
        this.temperatureUnit = temperatureUnit;
        this.temperatureValue = temperatureValue;
        this.dateTime = dateTime;
        this.weatherText = weatherText;
        this.code = code;
        this.city = city;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }


    public String getWeatherText() {
        return weatherText;
    }

    public void setWeatherText(String weatherText) {
        this.weatherText = weatherText;
    }

    @Override
    public String toString() {
        return "Weather{" +
                "sunrise='" + sunrise + '\'' +
                ", sunset='" + sunset + '\'' +
                ", humidty=" + humidty +
                ", temperatureUnit='" + temperatureUnit + '\'' +
                ", temperatureValue=" + temperatureValue +
                ", dateTime='" + dateTime + '\'' +
                ", weatherText='" + weatherText + '\'' +
                ", code=" + code +
                '}';
    }

    public String getSunrise() {
        return sunrise;
    }

    public void setSunrise(String sunrise) {
        this.sunrise = sunrise;
    }

    public String getSunset() {
        return sunset;
    }

    public void setSunset(String sunset) {
        this.sunset = sunset;
    }

    public int getHumidty() {
        return humidty;
    }

    public void setHumidty(int humidty) {
        this.humidty = humidty;
    }

    public String getTemperatureUnit() {
        return temperatureUnit;
    }

    public void setTemperatureUnit(String temperatureUnit) {
        this.temperatureUnit = temperatureUnit;
    }

    public int getTemperatureValue() {
        return temperatureValue;
    }

    public void setTemperatureValue(int temperatureValue) {
        this.temperatureValue = temperatureValue;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }
}
