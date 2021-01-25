package com.br14x.carfixz;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class DisplayOrders extends AppCompatActivity {
    ListView listView;
    String[] orderID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_orders);

        orderID=new String[MainActivity.ord.size()];

        for (int i = 0; i < MainActivity.ord.size(); i++) {
            orderID[i] = MainActivity.ord.get(i).orderID;
        }

        listView = findViewById(R.id.displayOrders);

        ArrayAdapter<String> adapter=new ArrayAdapter<>(this,
                R.layout.viewordersrow,R.id.orderIDtextView,orderID);

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String x=parent.getItemAtPosition(position).toString();
                Intent intent=new Intent(DisplayOrders.this,OrderDetails.class);
                intent.putExtra("order_id",x);
                startActivity(intent);
                Log.i("X",x);
            }
        });

    }
    @Override
    public void onBackPressed() {
        startActivity(new Intent(DisplayOrders.this,MainActivity.class));
    }

}