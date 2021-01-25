package com.br14x.carfixz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ViewFlipper;

public class SaiServiceIntro extends AppCompatActivity {
    ViewFlipper viewFlipper;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saiservice_intro);

        btn=findViewById(R.id.SaiBookingButton);
        viewFlipper=findViewById(R.id.view_flipper_sai);

        int images[]={R.drawable.a3,R.drawable.b3,R.drawable.g3};

        for (int image:images)
        {
            flipperImages(image);
        }

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SaiServiceIntro.this,SaiSerViceCenterApptBooking.class));
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
