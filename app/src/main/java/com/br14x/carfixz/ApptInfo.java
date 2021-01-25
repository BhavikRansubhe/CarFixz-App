package com.br14x.carfixz;

public class ApptInfo {
    public String bookingID;
    public String customer_name;
    public String customer_contact;
    public String vehicle_reg_no;
    public String vehicle_brand;
    public String vehicle_model;
    public String booking_appt_date;
    public String booking_appt_timeslot;
    public String garage_name;
    public CharSequence suggestions;

    public ApptInfo(){
    }

    public ApptInfo(String bid,String name, String contact, String reg_no, String brand, String model,String time,String date,String garage_name,CharSequence suggestions){
        this.bookingID=bid;
        this.customer_name=name;
        this.customer_contact=contact;
        this.vehicle_reg_no=reg_no;
        this.vehicle_brand=brand;
        this.vehicle_model=model;
        this.booking_appt_timeslot=time;
        this.booking_appt_date=date;
        this.garage_name=garage_name;
        this.suggestions=suggestions;
    }



}
