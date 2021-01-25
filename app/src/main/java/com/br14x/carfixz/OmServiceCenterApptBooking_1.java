package com.br14x.carfixz;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;


public class OmServiceCenterApptBooking_1 extends OmSerViceCenterApptBooking implements DatePickerDialog.OnDateSetListener,AdapterView.OnItemSelectedListener{
    String date,timeSelected;
    Button selectDate;
    Spinner selectTime;
    private DatabaseReference mDatabase;
    Button bookButton;
    TextView suggestions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_om_service_center_appt_booking_1);

        selectDate = findViewById(R.id.selectDateButtonOm);
        selectTime = findViewById(R.id.timeSpinnerOm);
        bookButton = (Button) findViewById(R.id.bookApptButtonOm);
        selectTime.setOnItemSelectedListener((AdapterView.OnItemSelectedListener) this);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        suggestions=findViewById(R.id.complaintsTextBox);
        final LoadingDialog loadingDialog=new LoadingDialog(OmServiceCenterApptBooking_1.this);

        List<String> timeSlots = new ArrayList<String>();
        timeSlots.add("Select Time Slot");
        timeSlots.add("11:00 am - 12:00 pm");
        timeSlots.add("12:00 pm - 1:00 pm");
        timeSlots.add("1:00 pm - 2:00 pm");
        timeSlots.add("2:00 pm - 3:00 pm");
        timeSlots.add("3:00 pm - 4:00 pm");
        timeSlots.add("4:00 pm - 5:00 pm");


        selectDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog();
            }

        });

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, timeSlots);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        selectTime.setAdapter(dataAdapter);




        bookButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                writeNewBooking();
                loadingDialog.startAlertDialog();
                Handler handler=new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        loadingDialog.dismissDialog();
                        Intent intent=new Intent(OmServiceCenterApptBooking_1.this, Appt_Booking_final.class);
                        intent.putExtra("garageName","Om Service Center");
                        intent.putExtra("BookingID",OmSerViceCenterApptBooking.bid);
                        intent.putExtra("Date",date);
                        intent.putExtra("Time",timeSelected);
                        startActivity(intent);
                    }
                },4000);
            }
        });
    }

    public void writeNewBooking(){
        String owner_name,owner_contact,car_reg_no,car_brand,car_model;
        owner_name=OmSerViceCenterApptBooking.ownername;
        owner_contact=OmSerViceCenterApptBooking.ownercontact;
        car_reg_no=OmSerViceCenterApptBooking.regno;
        car_brand=OmSerViceCenterApptBooking.vehicle_brand;
        car_model=OmSerViceCenterApptBooking.vehicle_model;
        CharSequence sugg=suggestions.getText().toString().trim();
        ApptInfo appt=new ApptInfo(OmSerViceCenterApptBooking.bid,owner_name,owner_contact,car_reg_no,car_brand,car_model,timeSelected,date,"Om Service Center",sugg);
        MainActivity.apptinfo.add(appt);
        mDatabase.child("Bookings").child("Om Service Center").child(OmSerViceCenterApptBooking.bid).setValue(appt);
    }


    public void showDatePickerDialog(){
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this,
                this,
                Calendar.getInstance().get(Calendar.YEAR),
                Calendar.getInstance().get(Calendar.MONTH),
                Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
        datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
        datePickerDialog.show();
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        date =  + dayOfMonth + "/" + (month+1) + "/" + year;
        Toast.makeText(this, date, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        timeSelected=parent.getItemAtPosition(position).toString();
        Toast.makeText(this,timeSelected, Toast.LENGTH_SHORT).show();
    }
    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }


}
