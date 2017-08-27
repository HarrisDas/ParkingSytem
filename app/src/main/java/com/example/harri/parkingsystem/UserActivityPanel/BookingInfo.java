package com.example.harri.parkingsystem.UserActivityPanel;

/**
 * Created by harri on 7/27/2017.
 */

public class BookingInfo {

    public BookingInfo() {
    }

private  String Area;
    private String key;
    private String initial_hour,initial_min,end_hour,end_min;
    private String year, month, day;
    private String UID,SlotNum;



    public BookingInfo(String initial_hour, String initial_min, String end_hour, String end_min, String year, String month, String day, String UID, String slotNum,String area,String k) {
        this.initial_hour = initial_hour;
        this.initial_min = initial_min;
        this.end_hour = end_hour;
        this.end_min = end_min;
        this.year = year;
        this.month = month;
        this.day = day;
        this.UID = UID;
        this.SlotNum = slotNum;
        this.Area=area;
    this.key=k;
    }

    public String getArea() {
        return Area;
    }

    public void setArea(String area) {
        Area = area;
    }

    public String getInitial_hour() {
        return initial_hour;
    }

    public void setInitial_hour(String initial_hour) {
        this.initial_hour = initial_hour;
    }

    public String getInitial_min() {
        return initial_min;
    }

    public void setInitial_min(String initial_min) {
        this.initial_min = initial_min;
    }

    public String getEnd_hour() {
        return end_hour;
    }

    public void setEnd_hour(String end_hour) {
        this.end_hour = end_hour;
    }

    public String getEnd_min() {
        return end_min;
    }

    public void setEnd_min(String end_min) {
        this.end_min = end_min;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getUID() {
        return UID;
    }

    public void setUID(String UID) {
        this.UID = UID;
    }

    public String getSlotNum() {
        return SlotNum;
    }

    public void setSlotNum(String slotNum) {
        SlotNum = slotNum;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
