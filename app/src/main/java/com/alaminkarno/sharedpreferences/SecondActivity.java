package com.alaminkarno.sharedpreferences;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {

    private TextView nameTV,emailTV;

    private String name,email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);


        initialization();

        getSharedPreferencesData();
    }

    private void getSharedPreferencesData() {

        SharedPreferences sharedPreferences = getSharedPreferences("UserInfo",MODE_PRIVATE);

        name = sharedPreferences.getString("name",null);
        email = sharedPreferences.getString("email",null);


        nameTV.setText("Hello, "+name);
        emailTV.setText("Email: "+email);

    }

    private void initialization() {
        nameTV = findViewById(R.id.nameTV);
        emailTV = findViewById(R.id.emailTV);
    }
}