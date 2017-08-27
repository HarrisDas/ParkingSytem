package com.example.harri.parkingsystem.AdminActivityPanel;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.harri.parkingsystem.R;
import com.example.harri.parkingsystem.UserActivityPanel.BookingInfo;
import com.example.harri.parkingsystem.UserActivityPanel.customAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Admin_Booking_list extends AppCompatActivity {

    ArrayList<BookingInfo> myBookings;
    FirebaseDatabase database;
    FirebaseAuth mAuth;
    DatabaseReference mDataRefrence;
    customAdapter adapter;
    private ListView myListView ;

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_list);



        mAuth=FirebaseAuth.getInstance();
        database=FirebaseDatabase.getInstance();
        mDataRefrence=database.getReference();

        myListView= (ListView)findViewById(R.id.my_list);

        myBookings=new ArrayList<>();


        mDataRefrence.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                myBookings.clear();
                Iterable<DataSnapshot> booking = dataSnapshot.child("Booking").getChildren();
                for(DataSnapshot c: booking) {

                    BookingInfo CurrentInfo = c.getValue(BookingInfo.class);
              //      if (CurrentInfo.getUID().equals(mAuth.getCurrentUser().getUid())) {

                        myBookings.add(CurrentInfo);

                    adapter.notifyDataSetChanged();
                }
            }
            @Override

            public void onCancelled(DatabaseError databaseError) {

            }
        });

        adapter= new customAdapter(myBookings,getApplicationContext());
        myListView.setAdapter(adapter);
        myListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                // int i= myListView.getCheckedItemPosition();
                System.out.println(position);
                String key=myBookings.get(position).getKey();
                adapter.remove(myBookings.get(position));
                adapter.notifyDataSetChanged();
                DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("Booking");
                ref.child(key).removeValue();

                return false;
            }
        });

    }
}
