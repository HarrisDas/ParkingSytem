package com.example.harri.parkingsystem.UserActivityPanel;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.harri.parkingsystem.R;

public class BookingCarParking extends AppCompatActivity {

    Button Area1 ;
    Button Area2 ;
    Button Area3 ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_car_parking);
attachWidgets();

        Area1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String area= Area1.getText().toString();


                Intent i =new Intent(BookingCarParking.this,selectParkingActivity.class);
               i.putExtra("AreaName",area);
                startActivity(i);
                finish();
            }
        });
        Area2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String area= Area2.getText().toString();


                Intent i =new Intent(BookingCarParking.this,selectParkingActivity.class);
                i.putExtra("AreaName",area);
                startActivity(i);
                finish();
            }
        });
        Area3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String area= Area3.getText().toString();


                Intent i =new Intent(BookingCarParking.this,selectParkingActivity.class);
                i.putExtra("AreaName",area);
                startActivity(i);
                finish();
            }
        });
    }

    private void attachWidgets() {

        Area1=(Button)findViewById(R.id.Area1_Button);

        Area2=(Button)findViewById(R.id.Area2_Button);
        Area3=(Button)findViewById(R.id.Area3_Button);
    }

}
