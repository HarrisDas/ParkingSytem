package com.example.harri.parkingsystem.UserActivityPanel;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.example.harri.parkingsystem.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class sendingFeedbackActivity extends AppCompatActivity {
EditText feedback_Editer;
    Button send_Feedback;
    ArrayList<Feedback> myFeedbacks;
    FirebaseDatabase database;
    FirebaseAuth mAuth;
    DatabaseReference mDataRefrence;
  FeedbackAdapter adapter;
    private ListView myListView ;

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sending_feedback);

        mAuth=FirebaseAuth.getInstance();
        database=FirebaseDatabase.getInstance();
        mDataRefrence=database.getReference();

        send_Feedback=(Button)findViewById(R.id.send_feedback) ;
        feedback_Editer=(EditText)findViewById(R.id.feedback_editer);
        myListView= (ListView)findViewById(R.id.feedback_List);

        myFeedbacks =new ArrayList<>();

        mDataRefrence.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                myFeedbacks.clear();
                Iterable<DataSnapshot> feedback = dataSnapshot.child("Feedback").getChildren();
                for(DataSnapshot c: feedback) {

                    Feedback CurrentInfo = c.getValue(Feedback.class);
                    if (CurrentInfo.getUID().equals(mAuth.getCurrentUser().getUid())) {

                        myFeedbacks.add(CurrentInfo);
                    }
                    adapter.notifyDataSetChanged();
                }
            }
            @Override

            public void onCancelled(DatabaseError databaseError) {

            }
        });

        adapter= new FeedbackAdapter(myFeedbacks,getApplicationContext());
        myListView.setAdapter(adapter);
        myListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                // int i= myListView.getCheckedItemPosition();
                System.out.println(position);
                String key=myFeedbacks.get(position).getUID();
                adapter.remove(myFeedbacks.get(position));
                adapter.notifyDataSetChanged();
                DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("Feedback");
                ref.child(key).removeValue();

                return false;
            }
        });
        send_Feedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("Feedback");

                String feedback_text=feedback_Editer.getText().toString();
                String reply="";
                Feedback myFeedback = new Feedback(feedback_text,reply,mAuth.getCurrentUser().getUid().toString());
                FirebaseDatabase.getInstance().getReference().child("Feedback").push().setValue(myFeedback);
            }
        });
    }



}
