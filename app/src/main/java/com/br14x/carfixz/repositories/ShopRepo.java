package com.br14x.carfixz.repositories;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.br14x.carfixz.searchActivity;
import com.br14x.carfixz.models.Product;
import com.br14x.carfixz.views.Shop;
import com.br14x.carfixz.car_accessories_shop;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ShopRepo {
    private DatabaseReference mDatabase;

    private MutableLiveData<List<Product>> mutableProductList;

    public LiveData<List<Product>> getProducts(){
        if(mutableProductList == null) {
            mutableProductList = new MutableLiveData<>();
        }
            if(searchActivity.vehiclebrand.isEmpty()) {
                loadproducts();
            }
            else{
                switch (searchActivity.vehiclebrand) {
                    case "BMW":
                        loadBMWproducts(searchActivity.vehiclemodel);
                        break;
                    case "Toyota":
                        loadToyota(searchActivity.vehiclemodel);
                        break;

                    case "Morris Garages(MG)":
                        loadMGproducts(searchActivity.vehiclemodel);
                        break;
                    case "Honda":
                        loadHondaproducts(searchActivity.vehiclemodel);
                        break;
                    case "Kia":
                        loadKiaproducts(searchActivity.vehiclemodel);
                        break;
                    case "Maruti Suzuki":
                        loadMSproducts(searchActivity.vehiclemodel);
                        break;
                }
            }
        return mutableProductList;
    }

    private void loadproducts(){
       List<Product> productList=new ArrayList<>();
        productList.add(new Product(UUID.randomUUID().toString(),"BMW Series 3 GT Seat Covers",8500,true,"https://firebasestorage.googleapis.com/v0/b/servocaretry1.appspot.com/o/seat_cover2.jpg?alt=media&token=a5e05902-e000-47a2-86c4-b96a454ee0ef"));
        productList.add(new Product(UUID.randomUUID().toString(),"BMW Series 3 Sunshades",2000,true,"https://firebasestorage.googleapis.com/v0/b/servocaretry1.appspot.com/o/bmw3seriessunshades.jpg?alt=media&token=686a0bcb-92f6-47fc-9fb2-ac4649ced48d"));
        productList.add(new Product(UUID.randomUUID().toString(),"BMW 3 series Foot Mats",2000, true,"https://firebasestorage.googleapis.com/v0/b/servocaretry1.appspot.com/o/bmw3seriesfootmats.jpg?alt=media&token=382b384f-edbb-405c-869d-5ebbf2d9559d"));
        productList.add(new Product(UUID.randomUUID().toString(),"BMW Series 3 GT Body Covers",3000,true,"https://firebasestorage.googleapis.com/v0/b/servocaretry1.appspot.com/o/bmw3seriesBodyCover.jpg?alt=media&token=8b269f09-4d3e-4c19-a1bb-58347a82b865"));
        productList.add(new Product(UUID.randomUUID().toString(),"BMW 5 Series Sunshades",7000,true,"https://firebasestorage.googleapis.com/v0/b/servocaretry1.appspot.com/o/B5sSS.jpg?alt=media&token=04072377-c558-46b6-a526-23ecdfd218a8"));
        productList.add(new Product(UUID.randomUUID().toString(),"BMW 5 Series Foot Mats",4000,true,"https://firebasestorage.googleapis.com/v0/b/servocaretry1.appspot.com/o/BMWs5FM.jpg?alt=media&token=9d1cd7ab-3735-4cd3-9a2f-e3096f50e1bc"));
        productList.add(new Product(UUID.randomUUID().toString(), "Suzuki SX4 Seat Covers", 2000, true, "https://firebasestorage.googleapis.com/v0/b/servocaretry1.appspot.com/o/seat_cover.jpg?alt=media&token=937e5c41-c248-4880-9cf9-ca02e7d3ff8f"));
        productList.add(new Product(UUID.randomUUID().toString(), "Suzuki SX4 Sunshades", 2500, true, "https://firebasestorage.googleapis.com/v0/b/servocaretry1.appspot.com/o/SX4SS.jpg?alt=media&token=9e119e8e-b238-4920-a18d-fd3d1d665d67"));
        productList.add(new Product(UUID.randomUUID().toString(),"Suzuki SX4 Foot Mats",1200,true,"https://firebasestorage.googleapis.com/v0/b/servocaretry1.appspot.com/o/SX4FM.jpg?alt=media&token=b58cb632-1ef3-4020-aafb-207e384f5236"));
        productList.add(new Product(UUID.randomUUID().toString(),"Toyota Innova Seat Covers",2500,true,"https://firebasestorage.googleapis.com/v0/b/servocaretry1.appspot.com/o/seat_cover.jpg?alt=media&token=937e5c41-c248-4880-9cf9-ca02e7d3ff8f"));
        productList.add(new Product(UUID.randomUUID().toString(),"Toyota Innova Sunshades",1000,true,"https://firebasestorage.googleapis.com/v0/b/servocaretry1.appspot.com/o/TISS.jpg?alt=media&token=39cf284a-7891-4992-a500-573ffa4e7eb3"));
        productList.add(new Product(UUID.randomUUID().toString(),"Toyota Innova Premium Sunshades",2500,true,"https://firebasestorage.googleapis.com/v0/b/servocaretry1.appspot.com/o/TISS.jpg?alt=media&token=39cf284a-7891-4992-a500-573ffa4e7eb3"));
        productList.add(new Product(UUID.randomUUID().toString(),"Toyota Innova Foot Mats",1499,true,"https://firebasestorage.googleapis.com/v0/b/servocaretry1.appspot.com/o/InnovaFM.jpg?alt=media&token=4d6c973b-5bb0-4da2-bdf6-68adf43ccbb8"));
        productList.add(new Product(UUID.randomUUID().toString(),"Toyota Innova Crysta Seat Covers",3000,true,"https://firebasestorage.googleapis.com/v0/b/servocaretry1.appspot.com/o/seat_cover.jpg?alt=media&token=937e5c41-c248-4880-9cf9-ca02e7d3ff8f"));
        productList.add(new Product(UUID.randomUUID().toString(),"MG Hector Seat Covers",4200,true,"https://firebasestorage.googleapis.com/v0/b/servocaretry1.appspot.com/o/seat_cover.jpg?alt=media&token=937e5c41-c248-4880-9cf9-ca02e7d3ff8f"));
        productList.add(new Product(UUID.randomUUID().toString(),"MG Hector Sunshades",4000,true,"https://firebasestorage.googleapis.com/v0/b/servocaretry1.appspot.com/o/MGHectorSS.jpg?alt=media&token=de519a09-91ce-4ae0-8bf5-fea9767c7e91"));
        productList.add(new Product(UUID.randomUUID().toString(),"MG Hector Seat Cushions",1200,true,"https://firebasestorage.googleapis.com/v0/b/servocaretry1.appspot.com/o/MGHectorseatcushions.jpg?alt=media&token=d024cade-d2e4-4785-b164-90d414c4d605"));
        productList.add(new Product(UUID.randomUUID().toString(),"MG Hector Foot Mats",2000,true,"https://firebasestorage.googleapis.com/v0/b/servocaretry1.appspot.com/o/MGHectorFM.jpg?alt=media&token=69a557ad-9acb-4a73-957e-6059e8259268"));
        productList.add(new Product(UUID.randomUUID().toString(),"MG Hector Interior Ambient Lights (Blue)",2500,true,"https://firebasestorage.googleapis.com/v0/b/servocaretry1.appspot.com/o/MGHectorinteriorlights.jpg?alt=media&token=a140684b-8b9f-449e-8473-8a32e5ad476c"));
        productList.add(new Product(UUID.randomUUID().toString(),"MG Hector Body Covers",2500,true,"https://firebasestorage.googleapis.com/v0/b/servocaretry1.appspot.com/o/MGHectorBodyCovers.jpg?alt=media&token=93c8e1d2-44bb-413d-9a51-82b613e7adaa"));
        productList.add(new Product(UUID.randomUUID().toString(),"Honda Accord Seat Covers",3000,true,"https://firebasestorage.googleapis.com/v0/b/servocaretry1.appspot.com/o/seatcovers.jpg?alt=media&token=8e26b9cd-b1b3-411b-b887-f88f36d009d6"));
        productList.add(new Product(UUID.randomUUID().toString(),"Honda Accord Sunshades",1500,true,"https://firebasestorage.googleapis.com/v0/b/servocaretry1.appspot.com/o/HondaAccordSunshades.jpg?alt=media&token=989b8ab6-ca8d-4219-bf6d-8b995ee8c1b7"));
        productList.add(new Product(UUID.randomUUID().toString(),"Honda Accord Seat Cushions",1500,true,"https://firebasestorage.googleapis.com/v0/b/servocaretry1.appspot.com/o/HondaAccordSeatCushions.jpg?alt=media&token=154fc4d5-b469-4c5f-be6c-8b75bbbd48d5"));
        productList.add(new Product(UUID.randomUUID().toString(),"Honda Accord FootMats",2500,true,"https://firebasestorage.googleapis.com/v0/b/servocaretry1.appspot.com/o/HondaCityFootMats.jpg?alt=media&token=d87939aa-ee1b-4f4b-bc72-0b3ffd209d52"));
        productList.add(new Product(UUID.randomUUID().toString(),"Honda Accord Door Logo Lights",3000,true,"https://firebasestorage.googleapis.com/v0/b/servocaretry1.appspot.com/o/HondaAccordDoorLogoLights.jpg?alt=media&token=83831ccc-47ac-4fde-9b7c-e30a79bb8b1c"));
        productList.add(new Product(UUID.randomUUID().toString(),"Honda Accord Body Covers",1000,true,"https://firebasestorage.googleapis.com/v0/b/servocaretry1.appspot.com/o/HondaAccordBodyCover.jpg?alt=media&token=fc07d368-f1f1-459d-967c-4ddb9ead03bc"));
        productList.add(new Product(UUID.randomUUID().toString(),"Honda Accord Parking Sensors & Camera Set",4000,true,"https://firebasestorage.googleapis.com/v0/b/servocaretry1.appspot.com/o/HondaAccordParkingsensors%26cameras.jpg?alt=media&token=c37d9476-4d3c-47da-a370-479ace80448c"));
        productList.add(new Product(UUID.randomUUID().toString(),"Honda City Seat Covers",2500,true,"https://firebasestorage.googleapis.com/v0/b/servocaretry1.appspot.com/o/seatcovers.jpg?alt=media&token=8e26b9cd-b1b3-411b-b887-f88f36d009d6"));
        productList.add(new Product(UUID.randomUUID().toString(),"Honda City Sunshades",1000,true,"https://firebasestorage.googleapis.com/v0/b/servocaretry1.appspot.com/o/HondaCitySunshades.jpg?alt=media&token=5fe9474f-3394-42ea-93a4-ed58629bca3a"));
        productList.add(new Product(UUID.randomUUID().toString(),"Honda City Seat Cushions",800,true,"https://firebasestorage.googleapis.com/v0/b/servocaretry1.appspot.com/o/HondaCitySeatCushions.jpg?alt=media&token=31e299f3-b0ef-4ab2-b33d-baf489e2d061"));
        productList.add(new Product(UUID.randomUUID().toString(),"Honda City FootMats",1500,true,"https://firebasestorage.googleapis.com/v0/b/servocaretry1.appspot.com/o/HondaCityFootMats.jpg?alt=media&token=d87939aa-ee1b-4f4b-bc72-0b3ffd209d52"));
        productList.add(new Product(UUID.randomUUID().toString(),"Honda Door logo lights",2500,true,"https://firebasestorage.googleapis.com/v0/b/servocaretry1.appspot.com/o/Hondadoorlogolights.jpg?alt=media&token=a0ee1a03-4be2-483f-94ac-0739699f1a0d"));
        productList.add(new Product(UUID.randomUUID().toString(),"Honda City Body Cover",2000,true,"https://firebasestorage.googleapis.com/v0/b/servocaretry1.appspot.com/o/HondaCityBodyCover.jpg?alt=media&token=103a9d35-8cd1-4a7b-a79c-1701fabf78cb"));
        productList.add(new Product(UUID.randomUUID().toString(),"Honda City Parking Sensors & Camera Set",3000,true,"https://firebasestorage.googleapis.com/v0/b/servocaretry1.appspot.com/o/HondaCityParkingSensors%26Lights.jpg?alt=media&token=24334d56-ccc1-4abb-92b5-f616160b8a06"));
        productList.add(new Product(UUID.randomUUID().toString(),"Honda Amaze Seat Covers",2199,true,"https://firebasestorage.googleapis.com/v0/b/servocaretry1.appspot.com/o/seatcovers.jpg?alt=media&token=8e26b9cd-b1b3-411b-b887-f88f36d009d6"));
        productList.add(new Product(UUID.randomUUID().toString(),"Honda Amaze Sunshades",799,true,"https://firebasestorage.googleapis.com/v0/b/servocaretry1.appspot.com/o/HondaAmazeSunshades.jpg?alt=media&token=53082c0e-56cf-45c7-a118-a8b3f33f09e1"));
        productList.add(new Product(UUID.randomUUID().toString(),"Honda Amaze Seat Cushions",500,true,"https://firebasestorage.googleapis.com/v0/b/servocaretry1.appspot.com/o/HondaAmazeSeatCushions.jpg?alt=media&token=9bbfc32f-b958-44fa-8ac0-21b61e8e3e5d"));
        productList.add(new Product(UUID.randomUUID().toString(),"Honda Amaze FootMats",1200,true,"https://firebasestorage.googleapis.com/v0/b/servocaretry1.appspot.com/o/HondaCityFootMats.jpg?alt=media&token=d87939aa-ee1b-4f4b-bc72-0b3ffd209d52"));
        productList.add(new Product(UUID.randomUUID().toString(),"Honda Door logo lights",2500,true,"https://firebasestorage.googleapis.com/v0/b/servocaretry1.appspot.com/o/Hondadoorlogolights.jpg?alt=media&token=a0ee1a03-4be2-483f-94ac-0739699f1a0d"));
        productList.add(new Product(UUID.randomUUID().toString(),"Honda Amaze Body Cover",800,true,"https://firebasestorage.googleapis.com/v0/b/servocaretry1.appspot.com/o/HondaAmazeBodyCover.jpg?alt=media&token=7753ee83-3057-4f99-bc49-93840255e486"));
        productList.add(new Product(UUID.randomUUID().toString(),"Honda Amaze Parking Sensors & Camera Set",3000,true,"https://firebasestorage.googleapis.com/v0/b/servocaretry1.appspot.com/o/HondaAmazeParkingsensors%26cameras.jpg?alt=media&token=8963d605-4b53-49f2-9814-098006fdb871"));
        productList.add(new Product(UUID.randomUUID().toString(),"Honda CR-V Sunshades",1699,true,"https://firebasestorage.googleapis.com/v0/b/servocaretry1.appspot.com/o/CRVsunshades.jpg?alt=media&token=26f8be0b-8c29-472d-ba90-880b000401dc"));
        productList.add(new Product(UUID.randomUUID().toString(),"Honda CR-V Seat Cushions",1550,true,"https://firebasestorage.googleapis.com/v0/b/servocaretry1.appspot.com/o/CRVcushions.jpg?alt=media&token=bf353f7c-b186-4cdc-a9b1-827c3f47f0f5"));
        productList.add(new Product(UUID.randomUUID().toString(),"Honda CR-V FootMats",2700,true,"https://firebasestorage.googleapis.com/v0/b/servocaretry1.appspot.com/o/HondaCityFootMats.jpg?alt=media&token=d87939aa-ee1b-4f4b-bc72-0b3ffd209d52"));
        productList.add(new Product(UUID.randomUUID().toString(),"Honda Door logo lights",2500,true,"https://firebasestorage.googleapis.com/v0/b/servocaretry1.appspot.com/o/Hondadoorlogolights.jpg?alt=media&token=a0ee1a03-4be2-483f-94ac-0739699f1a0d"));
        productList.add(new Product(UUID.randomUUID().toString(),"Honda CR-V Body Covers",1500,true,"https://firebasestorage.googleapis.com/v0/b/servocaretry1.appspot.com/o/CRVbodycover.jpg?alt=media&token=af2e92b9-de78-47da-ab6f-6dde24327b70"));
        productList.add(new Product(UUID.randomUUID().toString(),"Honda CR-V Parking Sensors & Camera Set",5000,true,"https://firebasestorage.googleapis.com/v0/b/servocaretry1.appspot.com/o/CRVparkingsensors%26cameras.jpg?alt=media&token=f90177b8-dcf7-4cf2-97d8-611f5b510624"));
        productList.add(new Product(UUID.randomUUID().toString(),"Kia Seltos Seat Covers",3040,false,"https://firebasestorage.googleapis.com/v0/b/servocaretry1.appspot.com/o/seat_cover1.jpeg?alt=media&token=26a74fe0-28af-40ef-98f2-c85155a9dfad"));
        productList.add(new Product(UUID.randomUUID().toString(),"Kia Seltos Sunshades",3000,true,"https://firebasestorage.googleapis.com/v0/b/servocaretry1.appspot.com/o/KSSS.jpg?alt=media&token=833cdc01-e2de-4771-948e-237356985c51"));
        productList.add(new Product(UUID.randomUUID().toString(),"Kia Seltos Foot Mats",1500,true,"https://firebasestorage.googleapis.com/v0/b/car-fixz.appspot.com/o/KiaSeltosfootmats.jpg?alt=media&token=cf0d7321-dbaa-48b1-834d-7d9dc1e02ba7"));
        productList.add(new Product(UUID.randomUUID().toString(),"Kia Seltos Seat Cushions",800,true,"https://firebasestorage.googleapis.com/v0/b/car-fixz.appspot.com/o/KiaSeltoscushions.jpg?alt=media&token=9efcff28-9477-4e30-a2ca-fd17a86c3cc1"));
        productList.add(new Product(UUID.randomUUID().toString(),"Kia Seltos Body Covers",2000,true,"https://firebasestorage.googleapis.com/v0/b/car-fixz.appspot.com/o/KiaSeltosBodyCovers.jpg?alt=media&token=155b1192-6362-414c-b85c-6c08e9ac3ac5"));
        productList.add(new Product(UUID.randomUUID().toString(),"Kia Carnival Seat Covers",3000,true,"https://firebasestorage.googleapis.com/v0/b/servocaretry1.appspot.com/o/seat_cover1.jpeg?alt=media&token=26a74fe0-28af-40ef-98f2-c85155a9dfad"));
        productList.add(new Product(UUID.randomUUID().toString(),"Kia Carnival Sunshades",2000,true,"https://firebasestorage.googleapis.com/v0/b/servocaretry1.appspot.com/o/KiaCarnivalSunshades.jpg?alt=media&token=c1b2f5e9-eb49-4d8d-a306-1ab8d1269454"));
        productList.add(new Product(UUID.randomUUID().toString(),"Kia Carnival Foot Mats",1800,true,"https://firebasestorage.googleapis.com/v0/b/servocaretry1.appspot.com/o/KiaCarnivalFootmats.jpg?alt=media&token=ad6a9020-2a07-48f5-a72a-f65c02c5f41f"));
        productList.add(new Product(UUID.randomUUID().toString(),"Kia Carnival Seat Cushions",1000,true,"https://firebasestorage.googleapis.com/v0/b/servocaretry1.appspot.com/o/KiaCarnivalSeatCushions.jpg?alt=media&token=17cd0fae-32cf-47b7-bcf7-bd0676208982"));
        productList.add(new Product(UUID.randomUUID().toString(),"Kia Carnival Body Covers",1500,true,"https://firebasestorage.googleapis.com/v0/b/servocaretry1.appspot.com/o/KiaCarnivalBodyCovers.jpg?alt=media&token=dc3a4e08-7f4f-48b5-9af1-685d413b46de"));
        productList.add(new Product(UUID.randomUUID().toString(),"Kia Sonnet Seat Covers",2000,true,"https://firebasestorage.googleapis.com/v0/b/servocaretry1.appspot.com/o/seat_cover1.jpeg?alt=media&token=26a74fe0-28af-40ef-98f2-c85155a9dfad"));
        productList.add(new Product(UUID.randomUUID().toString(),"Kia Sonnet Sunshades",1200,true,"https://firebasestorage.googleapis.com/v0/b/servocaretry1.appspot.com/o/KiaSonnetsunshades.jpg?alt=media&token=dcc2a97b-7297-4ad8-afc1-5184edc6c6f2"));
        productList.add(new Product(UUID.randomUUID().toString(),"Kia Sonnet Foot Mats",1000,true,"https://firebasestorage.googleapis.com/v0/b/servocaretry1.appspot.com/o/KiaSonetFootmats.jpg?alt=media&token=f38ed289-61e3-45bb-b7f1-0f7479d7dddf"));
        productList.add(new Product(UUID.randomUUID().toString(),"Kia Sonnet Seat Cushions",800,true,"https://firebasestorage.googleapis.com/v0/b/servocaretry1.appspot.com/o/KiaSonnetcushions.jpg?alt=media&token=40d03001-8b48-47d4-b434-5780f3759cf7"));
        productList.add(new Product(UUID.randomUUID().toString(),"Kia Sonnet Body covers",1500,true,"https://firebasestorage.googleapis.com/v0/b/servocaretry1.appspot.com/o/KiaSonnetBodyCover.jpg?alt=media&token=febecb22-0958-4daf-a771-17607f88ad97"));
        mutableProductList.setValue(productList);
    }
    private void loadBMWproducts(String model){
        List<Product> productList=new ArrayList<>();
        switch(model){
            case "3 Series":
                productList.add(new Product(UUID.randomUUID().toString(),"BMW Series 3 GT Seat Covers",8500,true,"https://firebasestorage.googleapis.com/v0/b/servocaretry1.appspot.com/o/seat_cover2.jpg?alt=media&token=a5e05902-e000-47a2-86c4-b96a454ee0ef"));
                productList.add(new Product(UUID.randomUUID().toString(),"BMW Series 3 Sunshades",2000,true,"https://firebasestorage.googleapis.com/v0/b/servocaretry1.appspot.com/o/bmw3seriessunshades.jpg?alt=media&token=686a0bcb-92f6-47fc-9fb2-ac4649ced48d"));
                productList.add(new Product(UUID.randomUUID().toString(),"BMW 3 series Foot Mats",2000,true,"https://firebasestorage.googleapis.com/v0/b/servocaretry1.appspot.com/o/bmw3seriesfootmats.jpg?alt=media&token=382b384f-edbb-405c-869d-5ebbf2d9559d"));
                productList.add(new Product(UUID.randomUUID().toString(),"BMW Series 3 GT Body Covers",3000,true,"https://firebasestorage.googleapis.com/v0/b/servocaretry1.appspot.com/o/bmw3seriesBodyCover.jpg?alt=media&token=8b269f09-4d3e-4c19-a1bb-58347a82b865"));
                break;

            case "5 Series":
                productList.add(new Product(UUID.randomUUID().toString(),"BMW 5 Series Sunshades",7000,true,"https://firebasestorage.googleapis.com/v0/b/servocaretry1.appspot.com/o/B5sSS.jpg?alt=media&token=04072377-c558-46b6-a526-23ecdfd218a8"));
                productList.add(new Product(UUID.randomUUID().toString(),"BMW 5 Series Foot Mats",4000,true,"https://firebasestorage.googleapis.com/v0/b/servocaretry1.appspot.com/o/BMWs5FM.jpg?alt=media&token=9d1cd7ab-3735-4cd3-9a2f-e3096f50e1bc"));
                break;
        }
        mutableProductList.setValue(productList);
    }

    private void loadMSproducts(String model){
        List<Product> productList=new ArrayList<>();
        switch(model) {
            case "SX4":
                productList.add(new Product(UUID.randomUUID().toString(), "Suzuki SX4 Seat Covers", 2000, true, "https://firebasestorage.googleapis.com/v0/b/servocaretry1.appspot.com/o/seat_cover.jpg?alt=media&token=937e5c41-c248-4880-9cf9-ca02e7d3ff8f"));
                productList.add(new Product(UUID.randomUUID().toString(), "Suzuki SX4 Sunshades", 2500, true, "https://firebasestorage.googleapis.com/v0/b/servocaretry1.appspot.com/o/SX4SS.jpg?alt=media&token=9e119e8e-b238-4920-a18d-fd3d1d665d67"));
                productList.add(new Product(UUID.randomUUID().toString(),"Suzuki SX4 Foot Mats",1200,true,"https://firebasestorage.googleapis.com/v0/b/servocaretry1.appspot.com/o/SX4FM.jpg?alt=media&token=b58cb632-1ef3-4020-aafb-207e384f5236"));
                break;
        }
        mutableProductList.setValue(productList);
    }



    private void loadToyota(String model){
        List<Product> productList=new ArrayList<>();
        switch (model){
            case "Innova":
                productList.add(new Product(UUID.randomUUID().toString(),"Toyota Innova Seat Covers",2500,true,"https://firebasestorage.googleapis.com/v0/b/servocaretry1.appspot.com/o/seat_cover.jpg?alt=media&token=937e5c41-c248-4880-9cf9-ca02e7d3ff8f"));
                productList.add(new Product(UUID.randomUUID().toString(),"Toyota Innova Sunshades",1000,true,"https://firebasestorage.googleapis.com/v0/b/servocaretry1.appspot.com/o/TISS.jpg?alt=media&token=39cf284a-7891-4992-a500-573ffa4e7eb3"));
                productList.add(new Product(UUID.randomUUID().toString(),"Toyota Innova Premium Sunshades",2500,true,"https://firebasestorage.googleapis.com/v0/b/servocaretry1.appspot.com/o/TISS.jpg?alt=media&token=39cf284a-7891-4992-a500-573ffa4e7eb3"));
                productList.add(new Product(UUID.randomUUID().toString(),"Toyota Innova Foot Mats",1499,true,"https://firebasestorage.googleapis.com/v0/b/servocaretry1.appspot.com/o/InnovaFM.jpg?alt=media&token=4d6c973b-5bb0-4da2-bdf6-68adf43ccbb8"));
                break;

            case "Innova Crysta":
                productList.add(new Product(UUID.randomUUID().toString(),"Toyota Innova Crysta Seat Covers",3000,true,"https://firebasestorage.googleapis.com/v0/b/servocaretry1.appspot.com/o/seat_cover.jpg?alt=media&token=937e5c41-c248-4880-9cf9-ca02e7d3ff8f"));
                break;
        }
        mutableProductList.setValue(productList);
    }

    private void loadMGproducts(String model){
        List<Product> productList=new ArrayList<>();
        switch(model){
            case "Hector":
                productList.add(new Product(UUID.randomUUID().toString(),"MG Hector Seat Covers",4200,true,"https://firebasestorage.googleapis.com/v0/b/servocaretry1.appspot.com/o/seat_cover.jpg?alt=media&token=937e5c41-c248-4880-9cf9-ca02e7d3ff8f"));
                productList.add(new Product(UUID.randomUUID().toString(),"MG Hector Sunshades",4000,true,"https://firebasestorage.googleapis.com/v0/b/servocaretry1.appspot.com/o/MGHectorSS.jpg?alt=media&token=de519a09-91ce-4ae0-8bf5-fea9767c7e91"));
                productList.add(new Product(UUID.randomUUID().toString(),"MG Hector Seat Cushions",1200,true,"https://firebasestorage.googleapis.com/v0/b/servocaretry1.appspot.com/o/MGHectorseatcushions.jpg?alt=media&token=d024cade-d2e4-4785-b164-90d414c4d605"));
                productList.add(new Product(UUID.randomUUID().toString(),"MG Hector Foot Mats",2000,true,"https://firebasestorage.googleapis.com/v0/b/servocaretry1.appspot.com/o/MGHectorFM.jpg?alt=media&token=69a557ad-9acb-4a73-957e-6059e8259268"));
                productList.add(new Product(UUID.randomUUID().toString(),"MG Hector Interior Ambient Lights (Blue)",2500,true,"https://firebasestorage.googleapis.com/v0/b/servocaretry1.appspot.com/o/MGHectorinteriorlights.jpg?alt=media&token=a140684b-8b9f-449e-8473-8a32e5ad476c"));
                productList.add(new Product(UUID.randomUUID().toString(),"MG Hector Body Covers",2500,true,"https://firebasestorage.googleapis.com/v0/b/servocaretry1.appspot.com/o/MGHectorBodyCovers.jpg?alt=media&token=93c8e1d2-44bb-413d-9a51-82b613e7adaa"));
                break;
        }
        mutableProductList.setValue(productList);
    }

    private void loadHondaproducts(String model){
        List<Product> productList=new ArrayList<>();
        switch(model){
            case "City":
                productList.add(new Product(UUID.randomUUID().toString(),"Honda City Seat Covers",2500,true,"https://firebasestorage.googleapis.com/v0/b/servocaretry1.appspot.com/o/seatcovers.jpg?alt=media&token=8e26b9cd-b1b3-411b-b887-f88f36d009d6"));
                productList.add(new Product(UUID.randomUUID().toString(),"Honda City Sunshades",1000,true,"https://firebasestorage.googleapis.com/v0/b/servocaretry1.appspot.com/o/HondaCitySunshades.jpg?alt=media&token=5fe9474f-3394-42ea-93a4-ed58629bca3a"));
                productList.add(new Product(UUID.randomUUID().toString(),"Honda City Seat Cushions",800,true,"https://firebasestorage.googleapis.com/v0/b/servocaretry1.appspot.com/o/HondaCitySeatCushions.jpg?alt=media&token=31e299f3-b0ef-4ab2-b33d-baf489e2d061"));
                productList.add(new Product(UUID.randomUUID().toString(),"Honda City FootMats",1500,true,"https://firebasestorage.googleapis.com/v0/b/servocaretry1.appspot.com/o/HondaCityFootMats.jpg?alt=media&token=d87939aa-ee1b-4f4b-bc72-0b3ffd209d52"));
                productList.add(new Product(UUID.randomUUID().toString(),"Honda Door logo lights",2500,true,"https://firebasestorage.googleapis.com/v0/b/servocaretry1.appspot.com/o/Hondadoorlogolights.jpg?alt=media&token=a0ee1a03-4be2-483f-94ac-0739699f1a0d"));
                productList.add(new Product(UUID.randomUUID().toString(),"Honda City Body Cover",2000,true,"https://firebasestorage.googleapis.com/v0/b/servocaretry1.appspot.com/o/HondaCityBodyCover.jpg?alt=media&token=103a9d35-8cd1-4a7b-a79c-1701fabf78cb"));
                productList.add(new Product(UUID.randomUUID().toString(),"Honda City Parking Sensors & Camera Set",3000,true,"https://firebasestorage.googleapis.com/v0/b/servocaretry1.appspot.com/o/HondaCityParkingSensors%26Lights.jpg?alt=media&token=24334d56-ccc1-4abb-92b5-f616160b8a06"));
                break;

            case "Amaze":
                productList.add(new Product(UUID.randomUUID().toString(),"Honda Amaze Seat Covers",2199,true,"https://firebasestorage.googleapis.com/v0/b/servocaretry1.appspot.com/o/seatcovers.jpg?alt=media&token=8e26b9cd-b1b3-411b-b887-f88f36d009d6"));
                productList.add(new Product(UUID.randomUUID().toString(),"Honda Amaze Sunshades",799,true,"https://firebasestorage.googleapis.com/v0/b/servocaretry1.appspot.com/o/HondaAmazeSunshades.jpg?alt=media&token=53082c0e-56cf-45c7-a118-a8b3f33f09e1"));
                productList.add(new Product(UUID.randomUUID().toString(),"Honda Amaze Seat Cushions",500,true,"https://firebasestorage.googleapis.com/v0/b/servocaretry1.appspot.com/o/HondaAmazeSeatCushions.jpg?alt=media&token=9bbfc32f-b958-44fa-8ac0-21b61e8e3e5d"));
                productList.add(new Product(UUID.randomUUID().toString(),"Honda Amaze FootMats",1200,true,"https://firebasestorage.googleapis.com/v0/b/servocaretry1.appspot.com/o/HondaCityFootMats.jpg?alt=media&token=d87939aa-ee1b-4f4b-bc72-0b3ffd209d52"));
                productList.add(new Product(UUID.randomUUID().toString(),"Honda Door logo lights",2500,true,"https://firebasestorage.googleapis.com/v0/b/servocaretry1.appspot.com/o/Hondadoorlogolights.jpg?alt=media&token=a0ee1a03-4be2-483f-94ac-0739699f1a0d"));
                productList.add(new Product(UUID.randomUUID().toString(),"Honda Amaze Body Cover",800,true,"https://firebasestorage.googleapis.com/v0/b/servocaretry1.appspot.com/o/HondaAmazeBodyCover.jpg?alt=media&token=7753ee83-3057-4f99-bc49-93840255e486"));
                productList.add(new Product(UUID.randomUUID().toString(),"Honda Amaze Parking Sensors & Camera Set",3000,true,"https://firebasestorage.googleapis.com/v0/b/servocaretry1.appspot.com/o/HondaAmazeParkingsensors%26cameras.jpg?alt=media&token=8963d605-4b53-49f2-9814-098006fdb871"));
                break;

            case "Accord":
                productList.add(new Product(UUID.randomUUID().toString(),"Honda Accord Seat Covers",3000,true,"https://firebasestorage.googleapis.com/v0/b/servocaretry1.appspot.com/o/seatcovers.jpg?alt=media&token=8e26b9cd-b1b3-411b-b887-f88f36d009d6"));
                productList.add(new Product(UUID.randomUUID().toString(),"Honda Accord Sunshades",1500,true,"https://firebasestorage.googleapis.com/v0/b/servocaretry1.appspot.com/o/HondaAccordSunshades.jpg?alt=media&token=989b8ab6-ca8d-4219-bf6d-8b995ee8c1b7"));
                productList.add(new Product(UUID.randomUUID().toString(),"Honda Accord Seat Cushions",1500,true,"https://firebasestorage.googleapis.com/v0/b/servocaretry1.appspot.com/o/HondaAccordSeatCushions.jpg?alt=media&token=154fc4d5-b469-4c5f-be6c-8b75bbbd48d5"));
                productList.add(new Product(UUID.randomUUID().toString(),"Honda Accord FootMats",2500,true,"https://firebasestorage.googleapis.com/v0/b/servocaretry1.appspot.com/o/HondaCityFootMats.jpg?alt=media&token=d87939aa-ee1b-4f4b-bc72-0b3ffd209d52"));
                productList.add(new Product(UUID.randomUUID().toString(),"Honda Accord Door Logo Lights",3000,true,"https://firebasestorage.googleapis.com/v0/b/servocaretry1.appspot.com/o/HondaAccordDoorLogoLights.jpg?alt=media&token=83831ccc-47ac-4fde-9b7c-e30a79bb8b1c"));
                productList.add(new Product(UUID.randomUUID().toString(),"Honda Accord Body Covers",1000,true,"https://firebasestorage.googleapis.com/v0/b/servocaretry1.appspot.com/o/HondaAccordBodyCover.jpg?alt=media&token=fc07d368-f1f1-459d-967c-4ddb9ead03bc"));
                productList.add(new Product(UUID.randomUUID().toString(),"Honda Accord Parking Sensors & Camera Set",4000,true,"https://firebasestorage.googleapis.com/v0/b/servocaretry1.appspot.com/o/HondaAccordParkingsensors%26cameras.jpg?alt=media&token=c37d9476-4d3c-47da-a370-479ace80448c"));
                break;

            case "CR-V":
                productList.add(new Product(UUID.randomUUID().toString(),"Honda CR-V Seat Covers",3199,true,"https://firebasestorage.googleapis.com/v0/b/servocaretry1.appspot.com/o/seatcovers.jpg?alt=media&token=8e26b9cd-b1b3-411b-b887-f88f36d009d6"));
                productList.add(new Product(UUID.randomUUID().toString(),"Honda CR-V Sunshades",1699,true,"https://firebasestorage.googleapis.com/v0/b/servocaretry1.appspot.com/o/CRVsunshades.jpg?alt=media&token=26f8be0b-8c29-472d-ba90-880b000401dc"));
                productList.add(new Product(UUID.randomUUID().toString(),"Honda CR-V Seat Cushions",1550,true,"https://firebasestorage.googleapis.com/v0/b/servocaretry1.appspot.com/o/CRVcushions.jpg?alt=media&token=bf353f7c-b186-4cdc-a9b1-827c3f47f0f5"));
                productList.add(new Product(UUID.randomUUID().toString(),"Honda CR-V FootMats",2700,true,"https://firebasestorage.googleapis.com/v0/b/servocaretry1.appspot.com/o/HondaCityFootMats.jpg?alt=media&token=d87939aa-ee1b-4f4b-bc72-0b3ffd209d52"));
                productList.add(new Product(UUID.randomUUID().toString(),"Honda Door logo lights",2500,true,"https://firebasestorage.googleapis.com/v0/b/servocaretry1.appspot.com/o/Hondadoorlogolights.jpg?alt=media&token=a0ee1a03-4be2-483f-94ac-0739699f1a0d"));
                productList.add(new Product(UUID.randomUUID().toString(),"Honda CR-V Body Covers",1500,true,"https://firebasestorage.googleapis.com/v0/b/servocaretry1.appspot.com/o/CRVbodycover.jpg?alt=media&token=af2e92b9-de78-47da-ab6f-6dde24327b70"));
                productList.add(new Product(UUID.randomUUID().toString(),"Honda CR-V Parking Sensors & Camera Set",5000,true,"https://firebasestorage.googleapis.com/v0/b/servocaretry1.appspot.com/o/CRVparkingsensors%26cameras.jpg?alt=media&token=f90177b8-dcf7-4cf2-97d8-611f5b510624"));
                break;
        }
        mutableProductList.setValue(productList);
    }

    private void loadKiaproducts(String model){
        List<Product> productList=new ArrayList<>();
        switch(model){
            case "Seltos":
                productList.add(new Product(UUID.randomUUID().toString(),"Kia Seltos Seat Covers",3040,false,"https://firebasestorage.googleapis.com/v0/b/servocaretry1.appspot.com/o/seat_cover1.jpeg?alt=media&token=26a74fe0-28af-40ef-98f2-c85155a9dfad"));
                productList.add(new Product(UUID.randomUUID().toString(),"Kia Seltos Sunshades",3000,true,"https://firebasestorage.googleapis.com/v0/b/servocaretry1.appspot.com/o/KSSS.jpg?alt=media&token=833cdc01-e2de-4771-948e-237356985c51"));
                productList.add(new Product(UUID.randomUUID().toString(),"Kia Seltos Foot Mats",1500,true,"https://firebasestorage.googleapis.com/v0/b/car-fixz.appspot.com/o/KiaSeltosfootmats.jpg?alt=media&token=cf0d7321-dbaa-48b1-834d-7d9dc1e02ba7"));
                productList.add(new Product(UUID.randomUUID().toString(),"Kia Seltos Seat Cushions",800,true,"https://firebasestorage.googleapis.com/v0/b/servocaretry1.appspot.com/o/KiaSeltoscushions.jpg?alt=media&token=a24369d6-8e33-4fcc-ad70-6f9e98c89ae7"));
                productList.add(new Product(UUID.randomUUID().toString(),"Kia Seltos Body Covers",2000,true,"https://firebasestorage.googleapis.com/v0/b/car-fixz.appspot.com/o/KiaSeltosBodyCovers.jpg?alt=media&token=155b1192-6362-414c-b85c-6c08e9ac3ac5"));
                break;

            case "Carnival":
                productList.add(new Product(UUID.randomUUID().toString(),"Kia Carnival Seat Covers",3000,true,"https://firebasestorage.googleapis.com/v0/b/servocaretry1.appspot.com/o/seat_cover1.jpeg?alt=media&token=26a74fe0-28af-40ef-98f2-c85155a9dfad"));
                productList.add(new Product(UUID.randomUUID().toString(),"Kia Carnival Sunshades",2000,true,"https://firebasestorage.googleapis.com/v0/b/servocaretry1.appspot.com/o/KiaCarnivalSunshades.jpg?alt=media&token=c1b2f5e9-eb49-4d8d-a306-1ab8d1269454"));
                productList.add(new Product(UUID.randomUUID().toString(),"Kia Carnival Foot Mats",1800,true,"https://firebasestorage.googleapis.com/v0/b/servocaretry1.appspot.com/o/KiaCarnivalFootmats.jpg?alt=media&token=ad6a9020-2a07-48f5-a72a-f65c02c5f41f"));
                productList.add(new Product(UUID.randomUUID().toString(),"Kia Carnival Seat Cushions",1000,true,"https://firebasestorage.googleapis.com/v0/b/car-fixz.appspot.com/o/KiaSeltoscushions.jpg?alt=media&token=9efcff28-9477-4e30-a2ca-fd17a86c3cc1"));
                productList.add(new Product(UUID.randomUUID().toString(),"Kia Carnival Body Covers",1500,true,"https://firebasestorage.googleapis.com/v0/b/servocaretry1.appspot.com/o/KiaCarnivalBodyCovers.jpg?alt=media&token=dc3a4e08-7f4f-48b5-9af1-685d413b46de"));
                break;

            case "Sonnet":
                productList.add(new Product(UUID.randomUUID().toString(),"Kia Sonnet Seat Covers",2000,true,"https://firebasestorage.googleapis.com/v0/b/servocaretry1.appspot.com/o/seat_cover1.jpeg?alt=media&token=26a74fe0-28af-40ef-98f2-c85155a9dfad"));
                productList.add(new Product(UUID.randomUUID().toString(),"Kia Sonnet Sunshades",1200,true,"https://firebasestorage.googleapis.com/v0/b/servocaretry1.appspot.com/o/KiaSonnetsunshades.jpg?alt=media&token=dcc2a97b-7297-4ad8-afc1-5184edc6c6f2"));
                productList.add(new Product(UUID.randomUUID().toString(),"Kia Sonnet Foot Mats",1000,true,"https://firebasestorage.googleapis.com/v0/b/servocaretry1.appspot.com/o/KiaSonetFootmats.jpg?alt=media&token=f38ed289-61e3-45bb-b7f1-0f7479d7dddf"));
                productList.add(new Product(UUID.randomUUID().toString(),"Kia Sonnet Seat Cushions",800,true,"https://firebasestorage.googleapis.com/v0/b/servocaretry1.appspot.com/o/KiaSonnetcushions.jpg?alt=media&token=40d03001-8b48-47d4-b434-5780f3759cf7"));
                productList.add(new Product(UUID.randomUUID().toString(),"Kia Sonnet Body covers",1500,true,"https://firebasestorage.googleapis.com/v0/b/servocaretry1.appspot.com/o/KiaSonnetBodyCover.jpg?alt=media&token=febecb22-0958-4daf-a771-17607f88ad97"));
                break;

        }
        mutableProductList.setValue(productList);
    }
}
