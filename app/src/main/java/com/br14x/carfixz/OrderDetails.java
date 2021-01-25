package com.br14x.carfixz;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class OrderDetails extends AppCompatActivity {
    public int finalX;
    ListView listView;
    List<Order> order=new ArrayList<>();
    String[] items;
    int[] quantity;
    TextView totPrice;
    Button cancel;
    final LoadingDialog loadingDialog = new LoadingDialog(OrderDetails.this);

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_details);
        Intent intent = getIntent();
        String x = intent.getStringExtra("order_id");
        Log.i("X:", x);
        Log.i("Main orders:", String.valueOf(MainActivity.ord));
        LiveData<Double> totalPrice;
        totPrice = findViewById(R.id.totalPrice);
        cancel = findViewById(R.id.cancelOrder);
        int y = 0;
        for (int i = 0; i < MainActivity.ord.size(); i++) {
            if (MainActivity.ord.get(i).orderID.equals(x)) {
                //totPrice.setText(String.valueOf(MainActivity.ord.get(i).totalPrice));
                totalPrice = MainActivity.ord.get(i).totalPrice;
                Log.i("total price:", totalPrice.getValue().toString());
                order = MainActivity.ord.get(i).orderItems;
                totPrice.setText(totalPrice.getValue().toString());
                finalX = i;
                break;
            }
        }
        Log.i("Majhi final list:", order.toString());

        items = new String[order.size()];
        quantity = new int[order.size()];
        for (int i = 0; i < order.size(); i++) {
            items[i] = order.get(i).name;
            Log.i("Pahila item", items[i]);
            quantity[i] = order.get(i).Quantity;
        }
        listView = findViewById(R.id.orderDetails);
        /*ArrayAdapter<String> adapter=new ArrayAdapter<>(this,
                R.layout.orderdetailsrow,R.id.orderNametextView,items,R.id.orderQuantitytextView,quantity);*/
        Myadapter adapter = new Myadapter(this, items, quantity);
        listView.setAdapter(adapter);
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
                MainActivity.ord.remove(finalX);
                loadingDialog.startAlertDialog();
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        loadingDialog.dismissDialog();
                        Intent intent = new Intent(OrderDetails.this, DisplayOrders.class);
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


        class Myadapter extends ArrayAdapter<String> {
            Context context;
            String[] items;
            int[] quamtity;

            Myadapter(Context c, String[] title, int[] quantities) {
                super(c, R.layout.orderdetailsrow, R.id.orderNametextView, title);
                this.context = c;
                this.items = title;
                this.quamtity = quantities;

            }

            @NonNull
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                LayoutInflater layoutInflater = (LayoutInflater) getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View row = layoutInflater.inflate(R.layout.orderdetailsrow, parent, false);

                TextView myTitle = row.findViewById(R.id.orderNametextView);
                TextView myDescription = row.findViewById(R.id.orderQuantitytextView);

                //Setting resources now

                myTitle.setText(items[position]);
                myDescription.setText(String.valueOf(quamtity[position]));

                return row;
            }
        }


    }



