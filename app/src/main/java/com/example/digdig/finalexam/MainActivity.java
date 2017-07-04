package com.example.digdig.finalexam;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.digdig.finalexam.model.CustomWeekAdapter;
import com.example.digdig.finalexam.model.HttpGetYahooWeather;
import com.example.digdig.finalexam.model.JSONManagement;
import com.example.digdig.finalexam.model.JSONManagementWeek;
import com.example.digdig.finalexam.model.Weather;
import com.example.digdig.finalexam.model.WeatherWeek;
import com.example.digdig.finalexam.model.WebSiteService;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements WebSiteService,AdapterView.OnItemClickListener,View.OnClickListener {
TextView textViewDate,textViewSunrise,textViewSunset,textViewTemperature,textViewHumidity,textviewWeatherText,textViewLastSearch;
EditText editTextCountry,editTextCity;
Button buttonFind;
ImageView imageViewWeather;
    ListView listView;
    ArrayAdapter<WeatherWeek> arrayAdapter;
    CustomWeekAdapter customWeekAdapter;
    String drawable = "cond_";


    boolean search=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initialize();
    }

    private void initialize() {
        textViewDate = (TextView) findViewById(R.id.textViewDateTimeMenu);
        textViewSunrise = (TextView) findViewById(R.id.textViewSunrise);
        textViewSunset = (TextView) findViewById(R.id.textViewSunset);
        textViewTemperature = (TextView) findViewById(R.id.textViewTemperature);
        textViewHumidity = (TextView) findViewById(R.id.textViewHumidity);
        textviewWeatherText = (TextView) findViewById(R.id.textViewWeatherText);
        textViewLastSearch = (TextView) findViewById(R.id.textViewDateTimeMenuLast);
        imageViewWeather = (ImageView) findViewById(R.id.imageView);
        editTextCity = (EditText) findViewById(R.id.editTextCity);
        editTextCountry = (EditText) findViewById(R.id.editTextCountry);
        listView = (ListView) findViewById(R.id.listView);
        buttonFind = (Button) findViewById(R.id.button);
        buttonFind.setOnClickListener(this);
        editTextCountry.setText("Canada");
        editTextCity.setText("Montreal");
        // boolean false I will print the Montreal info
      if(!search) {
          if (!editTextCountry.getText().toString().equals("") || !editTextCity.getText().toString().equals("")) {
             String country = "Canada";
              String city = "Montreal";
              String part1 = "https://query.yahooapis.com/v1/public/yql?q=";
              String part2 = "select%20*%20from%20weather.forecast%20where%20woeid%20in%20(select%20woeid%20from%20geo.places(1)%20where%20text%3D%22" + city + "%2C%20" + country + "%22)%20and%20u%3D%22c%22";
              String part3 = "&format=json&env=store%3A%2F%2Fdatatables.org%2Falltableswithkeys";
              String website = part1 + part2 + part3;
              HttpGetYahooWeather httpGetYahooWeather = new HttpGetYahooWeather(this, website, textViewDate);
              httpGetYahooWeather.execute();
          }
       }





    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void sucess(String dataInXML) {
        ArrayList<Weather> weather = JSONManagement.processJSONData(dataInXML);


        if(weather.size()==0)
        {
           Toast.makeText(this,"The City or Country was not Found",Toast.LENGTH_SHORT).show();
        }
        else
        {
            ArrayList<WeatherWeek> listWeek = JSONManagementWeek.processJSONData(dataInXML);
        for(int i=0; i<weather.size();i++)
        {

            textViewDate.setText(weather.get(i).getDateTime());
            textViewSunrise.setText("Sunrise "+weather.get(i).getSunrise());

            textViewSunset.setText("Sunset "+weather.get(i).getSunset());
            textViewTemperature.setText("Temperature "+weather.get(i).getTemperatureUnit()+" "+weather.get(i).getTemperatureValue());
            textViewHumidity.setText("Humidity "+weather.get(i).getHumidty());
            textviewWeatherText.setText(weather.get(i).getWeatherText());
            textViewLastSearch.setText("Last Searched " +weather.get(i).getCity());
            int width = 220;
            int height = 220;
            LinearLayout.LayoutParams parms = new LinearLayout.LayoutParams(width,height);
            imageViewWeather.setLayoutParams(parms);
            int resId = getImg(weather.get(i).getCode());
            imageViewWeather.setImageResource(resId);

        }

        customWeekAdapter = new CustomWeekAdapter(this,listWeek);
        listView.setAdapter(customWeekAdapter);
        listView.setOnItemClickListener(this);
    }}

    public int getImg(int code)
    {
        int resId = getResources().getIdentifier(drawable+code, "drawable", getPackageName());
        return resId;
    }
    @Override
    public void failure(Exception exception) {
     textViewDate.setText(exception.getMessage());


    }

    @Override
    public void onClick(View v) {
        String city2 =  editTextCity.getText().toString();
        String country2  = editTextCountry.getText().toString();
        if (!city2.equals("") && !country2.equals(""))
        {
        String country = country2;
        String city = city2;
        String part1 = "https://query.yahooapis.com/v1/public/yql?q=";
        String part2 = "select%20*%20from%20weather.forecast%20where%20woeid%20in%20(select%20woeid%20from%20geo.places(1)%20where%20text%3D%22" + city + "%2C%20" + country + "%22)%20and%20u%3D%22c%22";
        String part3 = "&format=json&env=store%3A%2F%2Fdatatables.org%2Falltableswithkeys";
        String website = part1 + part2 + part3;
        HttpGetYahooWeather httpGetYahooWeather = new HttpGetYahooWeather(this, website, textViewDate);
        httpGetYahooWeather.execute();
        search=true;
        }
        else
        {
            Toast.makeText(this,"Please Type the Country and City",Toast.LENGTH_SHORT).show();
        }


    }
}
