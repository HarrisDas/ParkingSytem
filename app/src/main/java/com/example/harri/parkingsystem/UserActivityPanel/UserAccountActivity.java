package com.example.harri.parkingsystem.UserActivityPanel;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.example.harri.parkingsystem.AccountCreationFlow.AccountCreation;
import com.example.harri.parkingsystem.R;
import com.google.firebase.auth.FirebaseAuth;

public class UserAccountActivity extends AppCompatActivity {
FirebaseAuth mAuth;
    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_account);
mAuth=FirebaseAuth.getInstance();
        mAuth.getCurrentUser();

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        Intent i;
        switch (item.getItemId()) {
            case R.id.feedback:
                sendFeefback();
                return true;
            case R.id.book_parking:
                bookParking();
                return true;
            case R.id.view_my_booking:

              i = new Intent(this, BookingList.class);
                startActivity(i);



                return true;
            case R.id.sign_out:
                mAuth.signOut();
               // sharedPreferences.edit().remove(getResources().getString(R.string.prefType)).apply();
               i = new Intent(this, AccountCreation.class);


                startActivity(i);
                finish();

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void sendFeefback() {
        Intent i = new Intent(this, sendingFeedbackActivity.class);
        startActivity(i);
    }

    public void startbookParking(View v){

    bookParking();
}
    public void bookParking() {
  
Intent i = new Intent(this, BookingCarParking.class);
startActivity(i);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }
}

