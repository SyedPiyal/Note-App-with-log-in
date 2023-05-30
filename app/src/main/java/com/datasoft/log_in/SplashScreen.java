package com.datasoft.log_in;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

import java.util.Objects;

public class SplashScreen extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                SharedPreferences prefs = getSharedPreferences("MyPreference", MODE_PRIVATE);
                String name = prefs.getString("name", "Blank Name"); //"Blank Name" the default value.
                String idName = prefs.getString("id", "Blank Id");
                /*SharedPreferences sharedPreferences = getSharedPreferences("login",MODE_PRIVATE);
                String check = sharedPreferences.getString("userId","");
                Boolean check2 = sharedPreferences.getBoolean("userPass",false);*/

                Intent intent;
                if(Objects.equals(name, "Blank Name")){
                    intent = new Intent(SplashScreen.this,Login.class);

                }else{
                    intent = new Intent(SplashScreen.this,Dashboard.class);
                }


                startActivity(intent);
            }
        },2000);


    }
}