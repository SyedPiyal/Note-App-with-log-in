package com.datasoft.log_in;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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

    EditText userId, password;
    TextView signup;
    Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userId = findViewById(R.id.etLogId);
        password = findViewById(R.id.etLoginPassword);
        login = findViewById(R.id.btnLogin);
        signup = findViewById(R.id.tvRegister);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String userIdText = userId.getText().toString();
                String passwordText = password.getText().toString();
                if (userIdText.isEmpty() || passwordText.isEmpty()){
                    Toast.makeText(getApplicationContext(), "Fill all Fields", Toast.LENGTH_SHORT).show();
                }else {
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

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Login.this, Signup.class));
            }
        });
    }
}