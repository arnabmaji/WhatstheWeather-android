package com.arnab.whatstheweather;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class WeatherJSON extends AsyncTask<String, Void, String> {
    @Override
    protected String doInBackground(String... urls) {
        URL url = null;
        HttpURLConnection connection = null;
        String weather = "";
        String message = "";

        try{
            url = new URL(urls[0]);
            connection = (HttpURLConnection) url.openConnection();
            connection.connect();
            InputStreamReader reader = new InputStreamReader(connection.getInputStream());

            int data = reader.read();
            while(data != -1){
                char current = (char) data;
                weather += current;
                data = reader.read();
            }

            JSONObject jsonObject = new JSONObject(weather);
            String weatherInfo = jsonObject.getString("weather");
            JSONArray arr = new JSONArray(weatherInfo);
            String mainInfo = arr.getJSONObject(0).getString("main");
            String descInfo = arr.getJSONObject(0).getString("description");
            message = mainInfo + "\n" + descInfo;

        }catch(Exception e){
            e.printStackTrace();
        } finally{
            if(connection != null){
                connection.disconnect();
            }
        }
        return message;

    }
}
