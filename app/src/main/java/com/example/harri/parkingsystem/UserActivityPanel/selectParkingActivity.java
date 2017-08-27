package com.example.harri.parkingsystem.UserActivityPanel;

import android.annotation.TargetApi;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.harri.parkingsystem.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;
import java.util.Date;

public class selectParkingActivity extends AppCompatActivity {

    boolean check=false;
    static final  int dailog_id=0;
    BookingInfo myBookingInfo;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference mDataRefrence;
    FirebaseAuth firebaseAuth;
    String year_x, month_x, day_x;
    String initial_hour,initial_min,end_hour ,end_min;
    String AreaNAme;
    Button slot1_button;
    Button slot2_button;
    Button slot3_button;
    Button slot4_button;
    LinearLayout parking_slot;

    Button set_button;
    Button dateButton;
    Spinner initial_hour_spinner;
    ArrayAdapter<CharSequence> initial_hour_adapter;
    Spinner initial_min_spinner;
    ArrayAdapter<CharSequence> initial_min_adapter;
    Spinner end_hour_spinner;
  //  Spinner end_min_spinner;




    @TargetApi(Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_parking);

        mDataRefrence=FirebaseDatabase.getInstance().getReference();
        slot1_button=(Button)findViewById(R.id.Slot1);
        slot2_button=(Button)findViewById(R.id.Slot2);
        slot3_button=(Button)findViewById(R.id.Slot3);
        slot4_button=(Button)findViewById(R.id.Slot4);


        Intent   i = getIntent();

AreaNAme=i.getStringExtra("AreaName");

        parking_slot=(LinearLayout)findViewById(R.id.slot_layout);
        parking_slot.setVisibility(View.INVISIBLE);
        set_end_time();

        set_initial_time();

        setCurrentDate();

        set_button=(Button)findViewById(R.id.setButton);

        set_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                slot1_button.setEnabled(true);
                slot2_button.setEnabled(true);
                slot3_button.setEnabled(true);
                slot4_button.setEnabled(true);
                slot1_button.setBackgroundColor(Color.GREEN);
                slot2_button.setBackgroundColor(Color.GREEN);
                slot3_button.setBackgroundColor(Color.GREEN);
                slot4_button.setBackgroundColor(Color.GREEN);


mDataRefrence.addValueEventListener(new ValueEventListener() {
    @Override
    public void onDataChange(DataSnapshot dataSnapshot) {
        Iterable<DataSnapshot> booking = dataSnapshot.child("Booking").getChildren();
        for (DataSnapshot c: booking) {

           BookingInfo CurrentInfo= c.getValue(BookingInfo.class);
            int start_hour_time=Integer.parseInt(initial_hour);
            int end_hour_time=Integer.parseInt(initial_hour)+ Integer.parseInt(end_hour);
            int DB_start_hout_time=Integer.parseInt(CurrentInfo.getInitial_hour());
            int DB_end_hout_time=Integer.parseInt(CurrentInfo.getInitial_hour()) + Integer.parseInt(CurrentInfo.getEnd_hour());
int start_min=Integer.parseInt(initial_min);
            int db_min= Integer.parseInt(CurrentInfo.getInitial_min());
//            if(start_hour_time+end_hour_time>=24){
//               check=true;
//                return;
//
//
//            }

if (AreaNAme.equals(CurrentInfo.getArea())){
    System.out.println("hello");
    System.out.println(year_x);
    System.out.println(month_x);
    System.out.println(day_x);
    System.out.println(CurrentInfo.getYear());
    System.out.println( CurrentInfo.getMonth());
    System.out.println(CurrentInfo.getDay());

    System.out.println();


    if (year_x.equals(CurrentInfo.getYear())&&month_x.equals( CurrentInfo.getMonth())&&day_x.equals(CurrentInfo.getDay()) ){


        System.out.println("hello");
        System.out.println(start_hour_time);
        System.out.println(end_hour_time);
        System.out.println(DB_start_hout_time);

        System.out.println(DB_end_hout_time);
if(start_hour_time<=DB_start_hout_time&&end_hour_time>=DB_start_hout_time){

    System.out.println("condition 1 trues");
    if (start_hour_time==DB_start_hout_time){



    }
    if(end_hour_time==DB_end_hout_time){
        if (start_min>=db_min){System.out.println("condition 1 trues");
            System.out.println(CurrentInfo.getSlotNum().toString());
            ColorTheButton(CurrentInfo.getSlotNum().toString());
        }
    }else if (start_min<db_min){}else{

    System.out.println("condition 1 trues");
    System.out.println(CurrentInfo.getSlotNum().toString());
    ColorTheButton(CurrentInfo.getSlotNum().toString());}
}else if(start_hour_time<=DB_start_hout_time&&end_hour_time<=DB_end_hout_time&&end_hour_time>DB_start_hout_time){
    System.out.println("condition 2 trues");
    if (start_min<db_min){}else {
        System.out.println("condition 2 trues");
        System.out.println(CurrentInfo.getSlotNum().toString());
        ColorTheButton(CurrentInfo.getSlotNum().toString());
    }

}else if(DB_start_hout_time<=start_hour_time&&start_hour_time<DB_end_hout_time){
    System.out.println("condition 3 trues");
    if (start_min<db_min){}else{

    System.out.println("condition 3 trues");

    System.out.println(CurrentInfo.getSlotNum().toString());
    ColorTheButton(CurrentInfo.getSlotNum().toString());}
} else if(DB_start_hout_time<start_hour_time&&end_hour_time<DB_end_hout_time){
    System.out.println("condition 4 trues");
    if (start_min<db_min){}else{
    System.out.println("condition 4 trues");

    System.out.println(CurrentInfo.getSlotNum().toString());
    ColorTheButton(CurrentInfo.getSlotNum().toString());}

}


    }
}

        }

    }

    @Override
    public void onCancelled(DatabaseError databaseError) {

    }
});




                parking_slot.setVisibility(View.VISIBLE);


            }
        }  );



dateButton =(Button)findViewById(R.id.date_picker);
dateButton.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        showDialog(dailog_id);

    }
});
    }

    public void ColorTheButton(String s) {

        Button currentButton=null;
        switch (s){
            case "Slot1":
                currentButton=slot1_button;
                break;

            case "Slot2":
                currentButton=slot2_button;
                break;
            case "Slot3":
                currentButton=slot3_button;
                break;
            case "Slot4":
                currentButton=slot4_button;
                break;

        }
        if(currentButton!=null) {
            currentButton.setEnabled(false);
            currentButton.setBackgroundColor(Color.RED);
        }

    }

    private void set_end_time() {

        end_hour_spinner = (Spinner)findViewById(R.id.spinner_end_Hour);
        initial_hour_adapter=ArrayAdapter.createFromResource(this,R.array.End_hours,android.R.layout.simple_spinner_item);


 //  end_min_spinner = (Spinner)findViewById(R.id.spinner_end_min);
        initial_hour_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
       end_hour_spinner.setAdapter(initial_hour_adapter);
        end_hour_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                end_hour= parent.getItemAtPosition(position).toString();
                Toast.makeText(selectParkingActivity.this, end_hour+"Selected", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        initial_min_adapter=ArrayAdapter.createFromResource(this,R.array.Minutes,android.R.layout.simple_spinner_item);
        initial_min_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        end_min_spinner.setAdapter(initial_min_adapter);
//        end_min_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                end_min=  parent.getItemAtPosition(position).toString();
//                Toast.makeText(selectParkingActivity.this, end_min+"Selected", Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//
//            }
//        });
    }

    private void set_initial_time() {
        initial_hour_spinner= (Spinner)findViewById(R.id.spinner_initial_Hour);
        initial_hour_adapter=ArrayAdapter.createFromResource(this,R.array.Hours,android.R.layout.simple_spinner_item);
        initial_hour_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        initial_hour_spinner.setAdapter(initial_hour_adapter);
        initial_hour_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                initial_hour=  parent.getItemAtPosition(position).toString();
                Toast.makeText(selectParkingActivity.this, initial_hour+"Selected", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        initial_min_spinner= (Spinner)findViewById(R.id.spinner_initial_min);
        initial_min_adapter=ArrayAdapter.createFromResource(this,R.array.Minutes,android.R.layout.simple_spinner_item);
        initial_min_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        initial_min_spinner.setAdapter(initial_min_adapter);
        initial_min_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                initial_min= parent.getItemAtPosition(position).toString();
                Toast.makeText(selectParkingActivity.this, initial_min+"Selected", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }


    private void setCurrentDate() {

        final Calendar calendar= Calendar.getInstance();
        year_x= String.valueOf(calendar.get(Calendar.YEAR));
        month_x= String.valueOf(calendar.get(Calendar.MONTH));
        day_x= String.valueOf(calendar.get(Calendar.DAY_OF_MONTH));

    }


    @Override
    protected Dialog onCreateDialog(int id)
    {
if (id==dailog_id)
{
    Date today =new Date();
    long h=today.getTime();

    DatePickerDialog datePicker=new DatePickerDialog(this,dpickerListner,Integer.parseInt( year_x),Integer.parseInt(  month_x),Integer.parseInt(  day_x));
    datePicker.getDatePicker().setMinDate(h);
    return datePicker;
}
        return null;
    }

private DatePickerDialog.OnDateSetListener dpickerListner= new DatePickerDialog.OnDateSetListener() {
    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
year_x= String.valueOf(year);
        month_x  = String.valueOf((month+1));
    day_x= String.valueOf(dayOfMonth);
    }


};
    public void clickedOnBooking(View view){
Button button =(Button)findViewById(view.getId());
String myId=button.getText().toString();
BookingInfo myBookingInfo;

button.setBackgroundColor(Color.green(1));
        button.setEnabled(false);
        firebaseDatabase=FirebaseDatabase.getInstance();
        String Key=firebaseDatabase.getReference().child("Booking").push().getKey();
        myBookingInfo=  new BookingInfo(initial_hour,initial_min,end_hour,end_min,year_x ,month_x,day_x,(FirebaseAuth.getInstance().getCurrentUser().getUid()),myId,AreaNAme,Key);

        firebaseDatabase.getReference().child("Booking").child(Key).setValue(myBookingInfo);

    }
}



