package com.br14x.carfixz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewFlipper;

import java.util.Random;

public class A1garageIntro extends AppCompatActivity  {

    ViewFlipper viewFlipper;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_a1garage_intro);

        btn=findViewById(R.id.button);
        viewFlipper=findViewById(R.id.view_flipper);


        int images[]={R.drawable.a1,R.drawable.b1,R.drawable.g1};

        for (int image:images)
        {
            flipperImages(image);
        }

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(A1garageIntro.this,A1garageApptBooking.class));
            }
        });

    }

    public void flipperImages(int image){
        ImageView imageView=new ImageView(this);
        imageView.setBackgroundResource(image);

        viewFlipper.addView(imageView);
        viewFlipper.setFlipInterval(3000);
        viewFlipper.setAutoStart(true);

        viewFlipper.setInAnimation(this,android.R.anim.slide_in_left);
        viewFlipper.setOutAnimation(this,android.R.anim.slide_out_right);


        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(viewFlipper.isFlipping()){
                    viewFlipper.stopFlipping();
                }
                else
                    viewFlipper.startFlipping();
            }
        });
    }


}
