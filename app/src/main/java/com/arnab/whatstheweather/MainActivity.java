package com.arnab.whatstheweather;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.input.InputManager;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethod;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.net.URL;
import java.net.URLEncoder;

public class MainActivity extends AppCompatActivity {

    EditText cityEditText;
    Button getInfoButton;
    TextView infoTextView;

    public void getWeatherInfo(View view){
        String cityname = cityEditText.getText().toString();
        InputMethodManager manager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        manager.hideSoftInputFromWindow(view.getWindowToken(),0);
        if(cityname.equals("")){
            Toast.makeText(this,"Enter a city",Toast.LENGTH_SHORT).show();
        } else{
            WeatherJSON weatherJSON = new WeatherJSON();
            try{
                cityname = URLEncoder.encode(cityname,"UTF-8");
                String url = "https://openweathermap.org/data/2.5/weather?q=" +cityname+ "&appid=b6907d289e10d714a6e88b30761fae22";
                String message =  weatherJSON.execute(url).get();
                infoTextView.setText(message);
            } catch(Exception e){
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cityEditText = findViewById(R.id.editText);
        getInfoButton = findViewById(R.id.button);
        infoTextView = findViewById(R.id.infoTextView);


    }
}
