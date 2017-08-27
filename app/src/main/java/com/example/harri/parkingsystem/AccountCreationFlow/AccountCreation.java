package com.example.harri.parkingsystem.AccountCreationFlow;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.harri.parkingsystem.AdminActivityPanel.AdminActivity;
import com.example.harri.parkingsystem.R;
import com.example.harri.parkingsystem.UserActivityPanel.UserAccountActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class AccountCreation extends AppCompatActivity {
    private static Context context;
    private FirebaseAuth mAuth;
    private SharedPreferences sharedPreferences;
    private FirebaseDatabase DatabaseRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_creation);

        context = getApplicationContext();
       sharedPreferences = this.getSharedPreferences(getResources().getString(R.string.prefKey),0);

    mAuth= FirebaseAuth.getInstance();
        String membershipType = sharedPreferences.getString(getResources().getString(R.string.prefType), "");
        if (  mAuth.getCurrentUser()!=null&&!membershipType.equals("")){

//DatabaseRef.getReference().child("User").child(mAuth.getCurrentUser().getUid()).equals()

if (membershipType.equals("User")){

    Intent i = new Intent(this, UserAccountActivity.class);
    startActivity(i);
    finish();
}else if(membershipType.equals("Admin")){

    Intent i = new Intent(this, AdminActivity.class);
    startActivity(i);
    finish();

}

        }
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.fragment_container, new SignInFragment())
                .commit();
    }
    public static Context getContext() {
        return context;
    }
}
