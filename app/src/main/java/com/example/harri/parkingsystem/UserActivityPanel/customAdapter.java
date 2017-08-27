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
 * Created by harri on 8/2/2017.
 */

public class customAdapter extends ArrayAdapter<BookingInfo> {
    DatabaseReference ref =
            FirebaseDatabase.getInstance().getReference().child("Bookings");
    private ArrayList<BookingInfo> dataSet;
    Context mContext;



    private static class ViewHolder {
        TextView Start_time;
        TextView End_time;
        TextView current_Date;
        TextView slot_num;
        TextView Area_Name;
        Button deleteButton;
    }

    public customAdapter(ArrayList<BookingInfo> data, Context context) {
        super(context, R.layout.row_item, data);
        this.dataSet = data;
        this.mContext=context;

    }

    @NonNull
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
      final BookingInfo dataModel=getItem(position);

        ViewHolder viewHolder;

        final View result;
        if(convertView==null){

            viewHolder= new ViewHolder();
            LayoutInflater inflater=LayoutInflater.from(getContext());
            convertView=inflater.inflate(R.layout.row_item,parent,false);
            viewHolder.Start_time=(TextView) convertView.findViewById(R.id.start_time);
            viewHolder.End_time=(TextView) convertView.findViewById(R.id.end_time);
            viewHolder.current_Date=(TextView) convertView.findViewById(R.id.current_date);
            viewHolder.slot_num=(TextView) convertView.findViewById(R.id.slot_num);
            viewHolder.Area_Name=(TextView) convertView.findViewById(R.id.area_name);
          //  viewHolder.deleteButton=(Button)convertView.findViewById(R.id.deleteButton);
            result= convertView;
            convertView.setTag(viewHolder);

        }else{

            viewHolder = (ViewHolder) convertView.getTag();
            result=convertView;
        }
//        viewHolder.deleteButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//              //  ref.child(dataModel.getKey()).removeValue();
//            }
//
//        });
        viewHolder.Area_Name.setText(dataModel.getArea());

        viewHolder.slot_num.setText(dataModel.getSlotNum());
        viewHolder.current_Date.setText(dataModel.getYear()+"/"+dataModel.getMonth()+"/"+dataModel.getDay());

        viewHolder.Start_time.setText(dataModel.getInitial_hour()+":"+dataModel.getInitial_min());

        viewHolder.End_time.setText(dataModel.getEnd_hour());




        return convertView;
    }

}
