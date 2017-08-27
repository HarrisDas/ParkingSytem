package com.example.harri.parkingsystem.AdminActivityPanel;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.harri.parkingsystem.AccountCreationFlow.SignUp;
import com.example.harri.parkingsystem.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

/**
 * Created by harri on 8/4/2017.
 */

public class AdminUserAdapater extends ArrayAdapter<SignUp> {
    DatabaseReference ref =   FirebaseDatabase.getInstance().getReference().child("User");
    private ArrayList<SignUp> dataSet;
    Context mContext;

    private static class ViewHolder {
        TextView Name;
        TextView Email;
        Button deleteButton;
    }
    public AdminUserAdapater(ArrayList<SignUp> data, Context context) {
        super(context, R.layout.row_item2, data);
        this.dataSet = data;
        this.mContext=context;

    }
    @NonNull
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final SignUp dataModel=getItem(position);

        AdminUserAdapater.ViewHolder viewHolder;

        final View result;
        if(convertView==null){

            viewHolder= new AdminUserAdapater.ViewHolder();
            LayoutInflater inflater=LayoutInflater.from(getContext());
            convertView=inflater.inflate(R.layout.row_item2,parent,false);
            viewHolder.Name=(TextView) convertView.findViewById(R.id.myName);
            viewHolder.Email=(TextView) convertView.findViewById(R.id.myEmail);

            //  viewHolder.deleteButton=(Button)convertView.findViewById(R.id.deleteButton);
            result= convertView;
            convertView.setTag(viewHolder);

        }else{

            viewHolder = (AdminUserAdapater.ViewHolder) convertView.getTag();
            result=convertView;
        }
//        viewHolder.deleteButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//              //  ref.child(dataModel.getKey()).removeValue();
//            }
//
//        });
        viewHolder.Name.setText(dataModel.getName());

        viewHolder.Email.setText(dataModel.getEmail());




        return convertView;
    }

}



