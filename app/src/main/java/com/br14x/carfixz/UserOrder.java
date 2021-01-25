package com.br14x.carfixz;

import androidx.lifecycle.LiveData;

import java.util.List;

public class UserOrder {
    public String orderID;
    public List<Order> orderItems;
    public LiveData<Double> totalPrice;
}
