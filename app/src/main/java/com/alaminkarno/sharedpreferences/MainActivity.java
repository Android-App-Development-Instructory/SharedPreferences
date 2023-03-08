package com.alaminkarno.sharedpreferences;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText nameET,emailET;
    private Button saveBTN,secondBTN,nameRemoveBTN,clearBTN;

    private String name,email;

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        initialization();

        saveBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                name = nameET.getText().toString();
                email = emailET.getText().toString();

                if(name.isEmpty()){
                    Toast.makeText(MainActivity.this, "Please write name", Toast.LENGTH_SHORT).show();
                }
                else if(email.isEmpty()){
                    Toast.makeText(MainActivity.this, "Please write email", Toast.LENGTH_SHORT).show();
                }else{
                    savedOnSharePreferences();
                }

            }
        });

        secondBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,SecondActivity.class);
                startActivity(intent);
            }
        });

        nameRemoveBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editor.remove("name");
                editor.apply();
            }
        });

        clearBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editor.clear();
                editor.apply();
            }
        });
    }

    private void savedOnSharePreferences() {

        editor.putString("name",name);
        editor.putString("email",email);
        editor.apply();

    }

    private void initialization() {
        nameET = findViewById(R.id.nameET);
        emailET = findViewById(R.id.emailET);
        saveBTN = findViewById(R.id.saveBTN);
        secondBTN = findViewById(R.id.secondActivityBTN);
        nameRemoveBTN = findViewById(R.id.removeNameBTN);
        clearBTN = findViewById(R.id.clearBTN);


        sharedPreferences = getSharedPreferences("UserInfo",MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }
}