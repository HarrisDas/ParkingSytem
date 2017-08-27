package com.example.harri.parkingsystem.UserActivityPanel;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.harri.parkingsystem.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

/**
 * Created by harri on 8/4/2017.
 */

public class FeedbackAdapter  extends ArrayAdapter<Feedback> {

    DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("Feedback");
    private ArrayList<Feedback> dataSet;
    Context mContext;
    private static class ViewHolder {
        TextView msg;
        TextView reply;

        Button deleteButton;
    }
    public FeedbackAdapter(ArrayList<Feedback> data, Context context) {
        super(context, R.layout.row_item3, data);
        this.dataSet = data;
        this.mContext=context;

    }

    @NonNull
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final Feedback dataModel=getItem(position);

        FeedbackAdapter.ViewHolder viewHolder;

        final View result;
        if(convertView==null){

            viewHolder= new FeedbackAdapter.ViewHolder();
            LayoutInflater inflater=LayoutInflater.from(getContext());
            convertView=inflater.inflate(R.layout.row_item3,parent,false);
            viewHolder.msg=(TextView) convertView.findViewById(R.id.myFeedback);
            viewHolder.reply=(TextView) convertView.findViewById(R.id.adminReply);

            //  viewHolder.deleteButton=(Button)convertView.findViewById(R.id.deleteButton);
            result= convertView;
            convertView.setTag(viewHolder);

        }else{

            viewHolder = (FeedbackAdapter.ViewHolder) convertView.getTag();
            result=convertView;
        }
//        viewHolder.deleteButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//              //  ref.child(dataModel.getKey()).removeValue();
//            }
//
//        });
        viewHolder.msg.setText(dataModel.getMsg());

        viewHolder.reply.setText(dataModel.getReply());




        return convertView;
    }
}
