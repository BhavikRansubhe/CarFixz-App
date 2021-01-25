package com.br14x.carfixz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;



public class Appt_Booking_final extends AppCompatActivity {

    TextView garageInf, bIdInf, DTInf;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.appt_booking_final);
        Intent intent = getIntent();
        String gname = intent.getStringExtra("garageName");
        String bid = intent.getStringExtra("BookingID");
        String date = intent.getStringExtra("Date");
        String time = intent.getStringExtra("Time");

        garageInf = (TextView) findViewById(R.id.garageInformer);
        bIdInf = (TextView) findViewById(R.id.bookingIdInformer);
        DTInf = (TextView) findViewById(R.id.dtInformer);


        garageInf.setText("You have successfully booked appointment at " + gname);
        bIdInf.setText("Your booking ID :" + bid + "\nUse this if prompted");
        DTInf.setText("Details\nDate : " + date + "\nTime : " + time);
        Runnable r = new Runnable() {

            @Override
            public void run() {
                // if you are redirecting from a fragment then use getActivity() as the context.
                startActivity(new Intent(Appt_Booking_final.this, MainActivity.class));

            }
        };


        Handler h = new Handler();
        // The Runnable will be executed after the given delay time
        h.postDelayed(r, 10000); // will be delayed for 10 seconds    }

    }
}
