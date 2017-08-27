package com.example.harri.parkingsystem.AdminActivityPanel;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.harri.parkingsystem.AccountCreationFlow.SignUp;
import com.example.harri.parkingsystem.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Users_List extends AppCompatActivity {

    ArrayList<SignUp> myUsersList;
    FirebaseDatabase database;
    FirebaseAuth mAuth;
    DatabaseReference mDataRefrence;
    AdminUserAdapater adapter;
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

        myUsersList =new ArrayList<>();
        mDataRefrence.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                myUsersList.clear();
                Iterable<DataSnapshot> booking = dataSnapshot.child("User").getChildren();
                for(DataSnapshot c: booking) {

                    SignUp CurrentInfo = c.getValue(SignUp.class);
                         if (CurrentInfo.getMemberType().equals("Admin")) {

                  }else {  myUsersList.add(CurrentInfo);}

                    adapter.notifyDataSetChanged();
                }
            }
            @Override

            public void onCancelled(DatabaseError databaseError) {

            }
        });
        adapter= new AdminUserAdapater(myUsersList,getApplicationContext());
        myListView.setAdapter(adapter);
        myListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                // int i= myListView.getCheckedItemPosition();
                System.out.println(position);
                String key=myUsersList.get(position).getUUID();
                adapter.remove(myUsersList.get(position));
                adapter.notifyDataSetChanged();
                DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("User");
                ref.child(key).removeValue();

                return false;
            }
        });
    }
}
