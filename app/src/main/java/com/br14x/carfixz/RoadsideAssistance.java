package com.br14x.carfixz;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;

import static com.br14x.carfixz.MainActivity.currentUser;

public class RoadsideAssistance extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    Button call_btn;
    TextView phoneNo_textv;
    Spinner city_Spinner;
    private String citySelected,phoneNo;
    private List<String> cities;
    private DrawerLayout d1;
    private ActionBarDrawerToggle t1;
    NavigationView navigationView;
    View headerView;
    TextView name,email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_roadside_assistance);

        call_btn=(Button)findViewById(R.id.CallButton);
        phoneNo_textv=(TextView)findViewById(R.id.textView6);
        city_Spinner=(Spinner)findViewById(R.id.citySpinner);

        d1=(DrawerLayout)findViewById(R.id.drawerLayoutcs);
        t1=new ActionBarDrawerToggle(this,d1,R.string.Open,R.string.Close);
        String mail=currentUser.getEmail();
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

        d1.addDrawerListener(t1);
        t1.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nvcs);
        View header = navigationView.getHeaderView(0);
        TextView t2=(TextView) header.findViewById(R.id.nameofuser);
        TextView text = (TextView) header.findViewById(R.id.emailofuser);
        t2.setText(uname);
        text.setText(currentUser.getEmail());

        city_Spinner.setOnItemSelectedListener(this);

        cities=new ArrayList<>();
        cities.add("Select City");
        cities.add("Ahemedabad");
        cities.add("Bangalore");
        cities.add("Chennai");
        cities.add("Delhi");
        cities.add("Kolkata");
        cities.add("Mumbai");
        cities.add("Pune");

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, cities);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        city_Spinner.setAdapter(dataAdapter);

        call_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:"+phoneNo));
                startActivity(intent);
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id=item.getItemId();
                if(id==R.id.account){
                    startActivity(new Intent(RoadsideAssistance.this,MainActivity.class));
                }
                if(id==R.id.settings){
                    startActivity(new Intent(RoadsideAssistance.this, DisplayBookings.class));
                }
                if(id==R.id.mycart){

                    startActivity(new Intent(RoadsideAssistance.this, DisplayOrders.class));
                }
                if(id==R.id.logout){
                    FirebaseAuth.getInstance().signOut();
                    //After logging out send user to Login Activity to login again
                    startActivity(new Intent(RoadsideAssistance.this,LoginActivity.class));
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
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        citySelected = parent.getItemAtPosition(position).toString();
        switch(citySelected){
            case "Ahemedabad":
                phoneNo="+919823181723";
                phoneNo_textv.setText(phoneNo);
                phoneNo_textv.setVisibility(View.VISIBLE);
                call_btn.setVisibility(View.VISIBLE);
                break;
            case "Bangalore":
                phoneNo="+919823181724";
                phoneNo_textv.setText(phoneNo);
                phoneNo_textv.setVisibility(View.VISIBLE);
                call_btn.setVisibility(View.VISIBLE);
                break;
                case "Chennai":
                phoneNo="+919823181725";
                phoneNo_textv.setText(phoneNo);
                    phoneNo_textv.setVisibility(View.VISIBLE);
                    call_btn.setVisibility(View.VISIBLE);
                break;
                case "Delhi":
                phoneNo="+919823181726";
                phoneNo_textv.setText(phoneNo);
                    phoneNo_textv.setVisibility(View.VISIBLE);
                    call_btn.setVisibility(View.VISIBLE);
                break;
            case "Kolkata":
                phoneNo="+919823181727";
                phoneNo_textv.setText(phoneNo);
                phoneNo_textv.setVisibility(View.VISIBLE);
                call_btn.setVisibility(View.VISIBLE);
                break;
                case "Mumbai":
                phoneNo="+919823181728";
                phoneNo_textv.setText(phoneNo);
                    phoneNo_textv.setVisibility(View.VISIBLE);
                    call_btn.setVisibility(View.VISIBLE);
                break;
                case "Pune":
                phoneNo="+919823181729";
                phoneNo_textv.setText(phoneNo);
                    phoneNo_textv.setVisibility(View.VISIBLE);
                    call_btn.setVisibility(View.VISIBLE);
                break;
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
