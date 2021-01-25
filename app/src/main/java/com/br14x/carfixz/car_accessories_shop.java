package com.br14x.carfixz;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.br14x.carfixz.views.CartFragment;
import com.br14x.carfixz.views.Shop;
import com.br14x.carfixz.views.ShopFragment;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;

import static com.br14x.carfixz.MainActivity.currentUser;

public class car_accessories_shop extends AppCompatActivity {

    public String category;
    public static String product;
    private Button shop_by_car, go_btn;
    public static int id;

    private DrawerLayout d1;
    private ActionBarDrawerToggle t1;
    NavigationView navigationView;
    View headerView;
    TextView name,email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_accessories_shop);


        shop_by_car = (Button) findViewById(R.id.shop_by_car);
        go_btn = (Button) findViewById(R.id.GObutton);
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



        //Shop by car sends user to another activity
        shop_by_car.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                id = 1;
                Intent intent = new Intent(car_accessories_shop.this, searchActivity.class);
                startActivity(intent);
            }
        });

        go_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchActivity.vehiclebrand = "";
                Intent intent = new Intent(car_accessories_shop.this, Shop.class);
                startActivity(intent);
            }
        });

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id=item.getItemId();
                if(id==R.id.account){
                    startActivity(new Intent(car_accessories_shop.this,MainActivity.class));
                }
                if(id==R.id.settings){
                    startActivity(new Intent(car_accessories_shop.this, DisplayBookings.class));
                }
                if(id==R.id.mycart){
                    startActivity(new Intent(car_accessories_shop.this, DisplayOrders.class));
                }
                if(id==R.id.logout){
                    FirebaseAuth.getInstance().signOut();

                    //After logging out send user to Login Activity to login again
                    startActivity(new Intent(car_accessories_shop.this,LoginActivity.class));
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
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(car_accessories_shop.this, MainActivity.class));
        finish();

    }
}




