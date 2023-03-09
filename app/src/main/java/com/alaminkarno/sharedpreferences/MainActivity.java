package com.alaminkarno.sharedpreferences;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText nameET,emailET;
    private Button saveBTN,secondBTN,nameRemoveBTN,clearBTN;
    private Switch darkSwitch;
    private View mView;

    private String name,email;

    SharedPreferences sharedPreferences,activityPreferences;
    SharedPreferences.Editor editor,activityEditor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        initialization();

        getBackgroundColor();

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

        darkSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isDark) {
                activityEditor.putBoolean("isDark",isDark);

                mView.setBackgroundColor(isDark ? Color.BLUE : Color.WHITE);

                activityEditor.apply();
            }
        });
    }

    private void getBackgroundColor() {
        boolean isDark = activityPreferences.getBoolean("isDark",false);
        mView.setBackgroundColor(isDark ? Color.BLUE : Color.WHITE);
        darkSwitch.setChecked(isDark);
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
        darkSwitch = findViewById(R.id.dartModeSwitch);
        mView = findViewById(R.id.viewLL);


        sharedPreferences = getSharedPreferences("UserInfo",MODE_PRIVATE);
        activityPreferences = getPreferences(MODE_PRIVATE);

        editor = sharedPreferences.edit();
        activityEditor = activityPreferences.edit();
    }
}