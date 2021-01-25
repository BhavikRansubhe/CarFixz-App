package com.br14x.carfixz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class DisplayBookings extends AppCompatActivity {
    ListView listView;
    String[] bookingID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_bookings);

        bookingID=new String[MainActivity.apptinfo.size()];
        for (int i = 0; i < MainActivity.apptinfo.size(); i++) {
            bookingID[i] = MainActivity.apptinfo.get(i).bookingID;
        }

        listView = findViewById(R.id.displayBookings);

        ArrayAdapter<String> adapter=new ArrayAdapter<>(this,
                R.layout.viewbookingsrow,R.id.bookingIDtextView,bookingID);

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String x=parent.getItemAtPosition(position).toString();
                Intent intent=new Intent(DisplayBookings.this,BookingDetails.class);
                intent.putExtra("booking_id",x);
                startActivity(intent);
                Log.i("X",x);
            }
        });
    }
    @Override
    public void onBackPressed() {
        startActivity(new Intent(DisplayBookings.this,MainActivity.class));
    }
}
