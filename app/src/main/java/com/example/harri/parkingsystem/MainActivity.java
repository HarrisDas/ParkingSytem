package com.example.harri.parkingsystem;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.harri.parkingsystem.AccountCreationFlow.AccountCreation;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent i= new Intent (this, AccountCreation.class);
        startActivity(i);
        finish();


    }
}
