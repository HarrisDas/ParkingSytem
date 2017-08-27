package com.example.harri.parkingsystem.AdminActivityPanel;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.harri.parkingsystem.AccountCreationFlow.AccountCreation;
import com.example.harri.parkingsystem.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AdminActivity extends AppCompatActivity {
FirebaseAuth mAuth;
    SharedPreferences sharedPreferences;
    FirebaseDatabase Database;
    DatabaseReference mRefrence;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

    mAuth= FirebaseAuth.getInstance();
        mAuth.getCurrentUser();
        mAuth.getCurrentUser().getUid();
        Database=FirebaseDatabase.getInstance();
        mRefrence=Database.getReference();



    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu2, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        Intent i;
        switch (item.getItemId()) {

            case R.id.view_users:

                viewUsers();
                return true;
            case  R.id.show_parkings:
                show_Parkings();

                return true;

            case R.id.sign_out:
                mAuth.signOut();
              //  sharedPreferences.edit().remove(getResources().getString(R.string.prefType)).apply();
                i = new Intent(this, AccountCreation.class);


                startActivity(i);
                finish();

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void viewUsers() {
   Intent i = new Intent(this,Users_List.class);
        startActivity(i);
    }

    private void show_Parkings() {

Intent I= new Intent (this,Admin_Booking_list.class);
        startActivity(I);


    }


}
