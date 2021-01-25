package com.br14x.carfixz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.br14x.carfixz.views.Shop;

import java.util.ArrayList;
import java.util.List;

public class searchActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    Spinner brandSpinner,modelSpinner;
    private List<String> carBrands,carModelsBMW,carModelsToyota,carModelsKia,carModelsMG,carModelsMS,carModelsHonda;
    public static String vehiclebrand="",vehiclemodel="";
    Button searchButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        brandSpinner=findViewById(R.id.sp1brand);
        modelSpinner=findViewById(R.id.sp2Model);
        brandSpinner.setOnItemSelectedListener(this);
        modelSpinner.setOnItemSelectedListener(this);
        searchButton=findViewById(R.id.searchButton);

        carBrands=new ArrayList<>();
        carBrands.add("Select Brand");
        carBrands.add("BMW");
        carBrands.add("Honda");
        carBrands.add("Kia");
        carBrands.add("Morris Garages(MG)");
        carBrands.add("Maruti Suzuki");
        carBrands.add("Toyota");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, carBrands);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        brandSpinner.setAdapter(dataAdapter);

        carModelsBMW=new ArrayList<>();
        carModelsBMW.add("3 Series");
        carModelsBMW.add("5 Series");
        //carModelsBMW.add("7 Series");

        carModelsToyota=new ArrayList<>();
        carModelsToyota.add("Innova");
        carModelsToyota.add("Innova Crysta");
        //carModelsToyota.add("Land Cruiser");
        //carModelsToyota.add("Camry");

        carModelsKia=new ArrayList<>();
        carModelsKia.add("Seltos");
        carModelsKia.add("Carnival");
        carModelsKia.add("Sonnet");

        carModelsMG=new ArrayList<>();
        carModelsMG.add("Hector");

        carModelsMS=new ArrayList<>();
        carModelsMS.add("SX4");
        //carModelsMS.add("Ciaz");
        //carModelsMS.add("Ertiga");
        //carModelsMS.add("Swift");

        carModelsHonda=new ArrayList<>();
        carModelsHonda.add("City");
        carModelsHonda.add("Amaze");
        carModelsHonda.add("Accord");
        carModelsHonda.add("CR-V");

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(searchActivity.this, Shop.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        switch(parent.getId()){
            case R.id.sp1brand:
                vehiclebrand = parent.getItemAtPosition(position).toString();
                switch(vehiclebrand){
                    case "BMW" :
                        ArrayAdapter<String> bmw = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, carModelsBMW);
                        bmw.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        bmw.notifyDataSetChanged();
                        modelSpinner.setAdapter(bmw);
                        break;

                    case "Honda":
                        ArrayAdapter<String> honda = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, carModelsHonda);
                        honda.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        honda.notifyDataSetChanged();
                        modelSpinner.setAdapter(honda);
                        break;

                    case "Toyota":
                        ArrayAdapter<String> toyota = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, carModelsToyota);
                        toyota.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        toyota.notifyDataSetChanged();
                        modelSpinner.setAdapter(toyota);
                        break;

                    case "Kia":
                        ArrayAdapter<String> kia = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, carModelsKia);
                        kia.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        kia.notifyDataSetChanged();
                        modelSpinner.setAdapter(kia);
                        break;

                    case "Morris Garages(MG)":
                        ArrayAdapter<String> mg = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, carModelsMG);
                        mg.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        mg.notifyDataSetChanged();
                        modelSpinner.setAdapter(mg);
                        break;

                    case "Maruti Suzuki":
                        ArrayAdapter<String> ms = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, carModelsMS);
                        ms.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        ms.notifyDataSetChanged();
                        modelSpinner.setAdapter(ms);
                        break;

                }
                break;

            case R.id.sp2Model:
                vehiclemodel=parent.getItemAtPosition(position).toString();

        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
