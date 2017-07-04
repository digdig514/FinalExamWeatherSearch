package com.example.digdig.finalexam.model;

import android.os.AsyncTask;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by digdig on 17-06-29.
 */

public class HttpGetYahooWeather extends AsyncTask<Void,Integer,String>{
    private WebSiteService websiteService;
    private String location;
    private TextView textViewProgress;

    public HttpGetYahooWeather(WebSiteService websiteService, String website, TextView textViewProgress) {
        this.websiteService = websiteService;
        this.location = website;
        this.textViewProgress = textViewProgress;
    }

    @Override
    protected String doInBackground(Void... params) {
        String resultXML;
        HttpURLConnection httpURLConnection=null;
       try {
           httpURLConnection = (HttpURLConnection) new URL(location).openConnection();
           InputStream is = httpURLConnection.getInputStream();
           BufferedInputStream bis = new BufferedInputStream(is);
           resultXML = readStream(is);
           return resultXML;
       } catch (Exception e)
       {
           websiteService.failure(e);
           return null;
       }

    }

    private String readStream(InputStream is) {
        BufferedReader br=null;
        StringBuilder sb = new StringBuilder();
        InputStreamReader isr = new InputStreamReader(is);

        br =  new BufferedReader(isr);
        String oneLine=null;
        int cpt=0;

        try {
            while ((oneLine=br.readLine())!=null)
            {
                sb.append(oneLine);
                publishProgress(cpt++);

            }
        } catch (IOException e) {
            websiteService.failure(e);
        }
        return sb.toString();
    }

    @Override
    protected void onPostExecute(String resultInXML) {
        super.onPostExecute(resultInXML);
        websiteService.sucess(resultInXML);
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
        textViewProgress.setText(String.valueOf(values[0]));

    }
}
