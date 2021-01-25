package com.br14x.carfixz;

public class Order {
    public String item_id;
   public String name;
   public int Quantity;
   public double cost_per_unit;

   public void set(String name,int Quantity){
       this.name=name;
       this.Quantity=Quantity;
   }
}