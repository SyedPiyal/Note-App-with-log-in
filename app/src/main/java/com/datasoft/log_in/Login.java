package com.datasoft.log_in;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.datasoft.log_in.DataBase.DAO;
import com.datasoft.log_in.DataBase.RoomDB;
import com.datasoft.log_in.Models.Users;

public class Login extends AppCompatActivity {

    EditText LoguserId, Logpassword;
    TextView Logsignup;
    Button Loglogin;
    Intent intent;

    SharedPreferences sharedPreferences;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LoguserId = findViewById(R.id.etLogId);
        Logpassword = findViewById(R.id.etLoginPassword);
        Loglogin = findViewById(R.id.btnLogin);
        Logsignup = findViewById(R.id.tvRegister);




        Loglogin.setOnClickListener(new View.OnClickListener() {
            @Override //Log
            public void onClick(View v) {

                SharedPreferences sharedPreferences = getSharedPreferences("login",MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putBoolean("flag",true);
                editor.apply();

                Intent intent =  new Intent(Login.this,Dashboard.class);
                startActivity(intent);

                String userIdText = LoguserId.getText().toString();
                String passwordText = Logpassword.getText().toString();
                if (userIdText.isEmpty() || passwordText.isEmpty()){
                    Toast.makeText(getApplicationContext(), "Fill all Fields", Toast.LENGTH_SHORT).show();
                }
                else {
                    //perform query
                    RoomDB userDatabse = RoomDB.getInstance(getApplicationContext());
                    DAO dao = userDatabse.dao();
                    new Thread(new Runnable() {
                        @Override
                        public void run() {

                            Users users = dao.login(userIdText,passwordText);
                            if (users == null){
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(getApplicationContext(), "Invalid", Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }
                            else {
                                startActivity(new Intent(Login.this, Dashboard.class));
                            }
                        }
                    }).start();
                }
            }
        });

        Logsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Login.this, Signup.class));
            }
        });
    }
}