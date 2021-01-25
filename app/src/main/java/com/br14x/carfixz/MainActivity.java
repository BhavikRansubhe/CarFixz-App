package com.br14x.carfixz;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    public static List<UserOrder> ord = new ArrayList<>();
    public static List<ApptInfo> apptinfo=new ArrayList<>();
    private long pressedTime;

    private Button book_service_btn, view_orders,view_bookings, car_accessories_btn, roadside_assistance_btn;
    public static FirebaseUser currentUser;//used to store current user of account


    FirebaseAuth mAuth;//Used for firebase authentication
    Button logout;

    private DrawerLayout d1;
    private ActionBarDrawerToggle t1;
    NavigationView navigationView;
    View headerView;
    TextView name,email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();


        //Log.i("Current User",currentUser.getEmail());
        //logout = (Button)findViewById(R.id.logout);


        book_service_btn = findViewById(R.id.button);
        //view_orders = findViewById(R.id.button2);
        car_accessories_btn = findViewById(R.id.button3);
        roadside_assistance_btn = findViewById(R.id.button4);
        //view_bookings=findViewById(R.id.button5);
        d1=(DrawerLayout)findViewById(R.id.drawerLayout);

        t1=new ActionBarDrawerToggle(this,d1,R.string.Open,R.string.Close);
        NavigationView navigationView = (NavigationView) findViewById(R.id.nvmain);


        d1.addDrawerListener(t1);
        t1.syncState();


       // navigationView.setNavigationItemSelectedListener((NavigationView.OnNavigationItemSelectedListener) this);

        book_service_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, garage_view.class);
                startActivity(intent);
            }
        });

        car_accessories_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, car_accessories_shop.class);
                startActivity(intent);
            }
        });

        roadside_assistance_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, RoadsideAssistance.class);
                startActivity(intent);
            }
        });

        /*view_orders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, DisplayOrders.class));
            }
        });

        view_bookings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, DisplayBookings.class));
            }
        });

        */
         if(currentUser!=null){
            String mail=currentUser.getEmail();
            assert mail != null;
            char[] m=mail.toCharArray();
            char[] n=new char[30];
            int c=0;
            while(m[c]!='@'){
                c++;
            }
            for(int i=0;i<c;i++){
                n[i]=m[i];
            }
            String uname=new String(n);
            View header = navigationView.getHeaderView(0);
            TextView t2=(TextView) header.findViewById(R.id.nameofuser);
            TextView text = (TextView) header.findViewById(R.id.emailofuser);
            t2.setText(uname);
            text.setText(currentUser.getEmail());
        }

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id=item.getItemId();
                if(id==R.id.account){
                    startActivity(new Intent(MainActivity.this,MainActivity.class));
                }
                if(id==R.id.settings){
                    startActivity(new Intent(MainActivity.this, DisplayBookings.class));
                }
                if(id==R.id.mycart){
                    startActivity(new Intent(MainActivity.this, DisplayOrders.class));
                }
                if(id==R.id.logout){
                    FirebaseAuth.getInstance().signOut();
                    //After logging out send user to Login Activity to login again
                    sendToLoginActivity();
                }
                return true;
            }
        });


    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        return t1.onOptionsItemSelected(item) || super.onOptionsItemSelected(item);
    }

    @Override
    protected void onStart() {
        super.onStart();
        //Check if user has already signed in if not send user to Login Activity
        if(currentUser==null)
        {
            sendToLoginActivity();
        }

    }

    @Override
    public void onBackPressed() {
        Intent a = new Intent(Intent.ACTION_MAIN);
        a.addCategory(Intent.CATEGORY_HOME);
        a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(a);
        }


    private void sendToLoginActivity() {
        //To send user to Login Activity
        Intent loginIntent = new Intent(MainActivity.this,LoginActivity.class);
        startActivity(loginIntent);
    }

}