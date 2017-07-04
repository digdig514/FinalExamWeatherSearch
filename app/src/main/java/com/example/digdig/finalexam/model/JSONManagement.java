package com.example.digdig.finalexam.model;

import android.util.Log;

import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by digdig on 17-06-29.
 */

public class JSONManagement {

    public static ArrayList<Weather> processJSONData(String data){
        ArrayList<Weather> checkWeather =  new ArrayList<>();

        try {
            JSONObject json = new JSONObject(data);
            JSONObject main = json.getJSONObject("query").getJSONObject("results").getJSONObject("channel");
            JSONObject atmosphere = main.getJSONObject("atmosphere");
            JSONObject astronomy = main.getJSONObject("astronomy");
            JSONObject temperature = main.getJSONObject("units");

            JSONObject location = main.getJSONObject("location");
            JSONObject items = main.getJSONObject("item");
            JSONObject details = items.getJSONObject("condition");


            String img = details.getString("code");
            int imgInt = Integer.valueOf(img);
            String weatherText = details.getString("text");


            String city = location.getString("city");
            String sunrise = astronomy.getString("sunrise");
            String sunset = astronomy.getString("sunset");
            String humidty =  atmosphere.getString("humidity");
            int humidtyInt = Integer.valueOf(humidty);
            String temperatureValue = details.getString("temp");
            int temperatureValueInt = Integer.valueOf(temperatureValue);
            String temperatureUnit = temperature.getString("temperature");
            String dateTime = details.getString("date");

            Weather weather = new Weather(sunrise,sunset,humidtyInt,temperatureUnit,temperatureValueInt,dateTime,weatherText,imgInt,city);
            checkWeather.add(weather);
            Log.d("JSON",weather.toString());


        } catch (Exception e) {
            Log.d("JSON",e.getMessage());
        }
        return checkWeather;
    }
}
