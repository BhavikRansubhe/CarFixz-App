package com.br14x.carfixz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

public class OmSerViceCenterApptBooking extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    protected static String ownername, ownercontact, regno, vehicle_brand, vehicle_model,bid;
    private DatabaseReference mDatabase;
    private Spinner brand, cars;
    private List<String> audi_cars, bmw_cars, chevrolet_cars, honda_cars, toyota_cars, msuzuki_cars;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_om_ser_vice_center_appt_booking);
        Button book_btn = findViewById(R.id.bookButton);
        final EditText customer_name = findViewById(R.id.cNameInput);
        final EditText veh_reg_no = findViewById(R.id.cRegNoInput);
        final EditText customer_contact=findViewById(R.id.cContactInput);
        brand = (Spinner) findViewById(R.id.brandSpinner);
        cars = (Spinner) findViewById(R.id.modelSpinner);
        brand.setOnItemSelectedListener((AdapterView.OnItemSelectedListener) this);
        cars.setOnItemSelectedListener((AdapterView.OnItemSelectedListener) this);

        mDatabase = FirebaseDatabase.getInstance().getReference();

        List<String> brands = new ArrayList<String>();
        brands.add("Select vehicle brand");
        brands.add("Audi");
        brands.add("BMW");
        brands.add("Chevrolet");
        brands.add("Honda");
        brands.add("Toyota");
        brands.add("Maruti Suzuki");

        audi_cars = new ArrayList<String>();
        audi_cars.add("Select model");
        audi_cars.add("A3");
        audi_cars.add("A4");
        audi_cars.add("A6");
        audi_cars.add("A8");
        audi_cars.add("RS6 Sportback");

        bmw_cars = new ArrayList<String>();
        bmw_cars.add("Select model");
        bmw_cars.add("3 Series");
        bmw_cars.add("5 Series");
        bmw_cars.add("7 series");
        bmw_cars.add("X3");

        chevrolet_cars = new ArrayList<String>();
        chevrolet_cars.add("Select Model");
        chevrolet_cars.add("Camaro");
        chevrolet_cars.add("Cruze");
        chevrolet_cars.add("Spark");
        chevrolet_cars.add("Trailblazer");

        honda_cars = new ArrayList<String>();
        honda_cars.add("Select Model");
        honda_cars.add("City");
        honda_cars.add("Amaze");
        honda_cars.add("Jazz");
        honda_cars.add("CR-V");

        toyota_cars = new ArrayList<String>();
        toyota_cars.add("Select Model");
        toyota_cars.add("Innova");
        toyota_cars.add("Corolla");
        toyota_cars.add("Yaris");
        toyota_cars.add("Camry");
        toyota_cars.add("Fortuner");

        msuzuki_cars = new ArrayList<String>();
        msuzuki_cars.add("Select Model");
        msuzuki_cars.add("SX4");
        msuzuki_cars.add("Ciaz");
        msuzuki_cars.add("Baleno");
        msuzuki_cars.add("Swift");
        msuzuki_cars.add("Ertiga");

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, brands);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        brand.setAdapter(dataAdapter);

        book_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BookingIDgenerator obj=new BookingIDgenerator();
                String x=obj.getRandomString(5);
                bid="OmB"+x;
                if (customer_name.getText().toString().isEmpty()) {
                    Toast.makeText(OmSerViceCenterApptBooking.this, "Enter a Customer name!!!", Toast.LENGTH_SHORT).show();
                    customer_name.requestFocus();
                }
                else if (veh_reg_no.getText().toString().isEmpty()) {
                    Toast.makeText(OmSerViceCenterApptBooking.this, "Enter a registration number!!!", Toast.LENGTH_SHORT).show();
                    veh_reg_no.requestFocus();
                }
                else if (customer_contact.getText().toString().isEmpty()) {
                    Toast.makeText(OmSerViceCenterApptBooking.this, "Enter a Contact Number!!!", Toast.LENGTH_SHORT).show();
                    customer_contact.requestFocus();
                }
                else if(customer_contact.getText().toString().length()!=10){
                    Toast.makeText(OmSerViceCenterApptBooking.this, "Enter a valid 10-digit Contact Number!!!", Toast.LENGTH_SHORT).show();
                    customer_contact.requestFocus();
                }
                else {
                    ownername = customer_name.getText().toString();
                    regno = veh_reg_no.getText().toString();
                    ownercontact = customer_contact.getText().toString();

                    startActivity(new Intent(OmSerViceCenterApptBooking.this,OmServiceCenterApptBooking_1.class));
                    overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
                }
            }


        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        switch(parent.getId()) {
            case (R.id.brandSpinner):
                vehicle_brand = parent.getItemAtPosition(position).toString();
                //Toast.makeText(this, vehicle_brand, Toast.LENGTH_SHORT).show();
                if (vehicle_brand.contentEquals("Audi")) {
                    ArrayAdapter<String> audi = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, audi_cars);
                    audi.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    audi.notifyDataSetChanged();
                    cars.setAdapter(audi);
                }
                if (vehicle_brand.contentEquals("BMW")) {

                    final ArrayAdapter<String> bmw = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, bmw_cars);
                    bmw.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    bmw.notifyDataSetChanged();
                    cars.setAdapter(bmw);
                }
                if (vehicle_brand.contentEquals("Chevrolet")) {

                    final ArrayAdapter<String> chevrolet = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, chevrolet_cars);
                    chevrolet.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    chevrolet.notifyDataSetChanged();
                    cars.setAdapter(chevrolet);
                }
                if (vehicle_brand.contentEquals("Honda")) {

                    final ArrayAdapter<String> honda = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, honda_cars);
                    honda.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    honda.notifyDataSetChanged();
                    cars.setAdapter(honda);
                }
                if (vehicle_brand.contentEquals("Toyota")) {

                    final ArrayAdapter<String> toyota = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, toyota_cars);
                    toyota.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    toyota.notifyDataSetChanged();
                    cars.setAdapter(toyota);
                }
                if (vehicle_brand.contentEquals("Maruti Suzuki")) {

                    final ArrayAdapter<String> msuzuki= new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, msuzuki_cars);
                    msuzuki.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    msuzuki.notifyDataSetChanged();
                    cars.setAdapter(msuzuki);
                }


                break;

            case R.id.modelSpinner:
                vehicle_model = parent.getItemAtPosition(position).toString();
                break;

        }

        // Showing selected spinner item
        //Toast.makeText(parent.getContext(), "Selected: " + vehicle_brand, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}

