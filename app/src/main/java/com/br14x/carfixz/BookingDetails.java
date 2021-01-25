package com.br14x.carfixz;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class BookingDetails extends AppCompatActivity {
    public static int finalY;
    TextView gname,bid,date,time;
    Button cancel;
    final LoadingDialog loadingDialog = new LoadingDialog(BookingDetails.this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_details);
        gname=findViewById(R.id.gnameTV);
        bid=findViewById(R.id.bidTV);
        date=findViewById(R.id.dateTV);
        time=findViewById(R.id.timeTV);
        cancel=findViewById(R.id.cancelOrder);
        int y=0;
        Intent intent = getIntent();
        String x=intent.getStringExtra("booking_id");

        for(int i=0;i<MainActivity.apptinfo.size();i++) {
            if (MainActivity.apptinfo.get(i).bookingID.equals(x)) {
                gname.setText(MainActivity.apptinfo.get(i).garage_name);
                bid.setText(MainActivity.apptinfo.get(i).bookingID);
                date.setText(MainActivity.apptinfo.get(i).booking_appt_date);
                time.setText(MainActivity.apptinfo.get(i).booking_appt_timeslot);
                y=i;
                break;
            }
        }
        finalY = y;
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            openDialog();
            }
        });
    }

    private void openDialog() {

        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setMessage("Are you Sure?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                MainActivity.apptinfo.remove(finalY);
                loadingDialog.startAlertDialog();
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        loadingDialog.dismissDialog();
                        Intent intent = new Intent(BookingDetails.this, DisplayBookings.class);
                        startActivity(intent);
                        overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
                    }
                }, 2500);
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.create();
        builder.show();
    }


}

