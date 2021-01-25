package com.br14x.carfixz;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class garage_view extends AppCompatActivity {

    ListView listView;
    String[] mTitle ={"A1 Garage","Om Service Center","Sai Service"};
    String[] mDescription ={"Garage1 description","Garage2 description","Garage3 description"};
    int[] images = {R.drawable.a1logo,R.drawable.omlogo,R.drawable.sailogo};
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_garage_view);

        listView = findViewById(R.id.garageView);
        button=findViewById(R.id.button6);

        MyAdapter adapter=new MyAdapter(this,mTitle,mDescription,images);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(position==0){
                    Intent intent = new Intent(garage_view.this, A1garageIntro.class);
                    startActivity(intent);
                }
                if(position==1){
                    Intent intent = new Intent(garage_view.this,OMServiceCenterIntro.class);
                    startActivity(intent);
                }
                if(position==2){
                    Intent intent=new Intent(garage_view.this,SaiServiceIntro.class);
                    startActivity(intent);
                }

            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(garage_view.this,MapsActivity.class));
            }
        });
    }


    //Creating an adapter class
        class MyAdapter extends ArrayAdapter<String>{
            Context context;
            String[] rTitle;
            String[] rDescription;
            int[] rImgs;

            MyAdapter(Context c, String[] title, String[] description, int[] imgs){
                super(c,R.layout.row,R.id.textView1,title);
                this.context=c;
                this.rTitle=title;
                this.rDescription=description;
                this.rImgs=imgs;

            }

            @NonNull
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                LayoutInflater layoutInflater=(LayoutInflater)getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View row=layoutInflater.inflate(R.layout.row , parent ,false);

                ImageView image=row.findViewById(R.id.garageImage);
                TextView  myTitle=row.findViewById(R.id.textView1);
                TextView myDescription=row.findViewById(R.id.textView2);

                //Setting resources now

                image.setImageResource(rImgs[position]);
                myTitle.setText(rTitle[position]);
                myDescription.setText(rDescription[position]);



                return row;
            }
        }
    }

