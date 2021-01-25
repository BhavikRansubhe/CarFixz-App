package com.br14x.carfixz.repositories;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.br14x.carfixz.models.CartItem;
import com.br14x.carfixz.models.Product;

import java.util.ArrayList;
import java.util.List;

public class CartRepo {


    private MutableLiveData<List<CartItem>> mutableCart=new MutableLiveData<>();
    private MutableLiveData<Double> mutableTotalPrice=new MutableLiveData<>();

    public LiveData<List<CartItem>> getCart() {
        if(mutableCart.getValue()==null){
            initCart();
            calculateCartTotal();
        }
        return mutableCart;
    }

    public void initCart(){
        mutableCart.setValue(new ArrayList<CartItem>());
        calculateCartTotal();
    }

    public boolean addItemToCart(Product product){
        if(mutableCart.getValue()==null){
            initCart();
            calculateCartTotal();
        }

        List<CartItem> cartItems=new ArrayList<>(mutableCart.getValue());
        for(CartItem cartItem:cartItems){                                     //This entire loop is for already existing cart Items
            if(cartItem.getProduct().getId().equals(product.getId())){
                if(cartItem.getQuantity()==5){
                    return false;
                }

                int index=cartItems.indexOf(cartItem);
                cartItem.setQuantity(cartItem.getQuantity()+1);    //previous quantity plus 1
                cartItems.set(index,cartItem);
                mutableCart.setValue(cartItems);
                calculateCartTotal();
                return true;
            }
        }

        CartItem cartItem=new CartItem(product,1);                //for new cart items not already existing
        cartItems.add(cartItem);
        mutableCart.setValue(cartItems);
        calculateCartTotal();
        return true;
    }

    public void removeItemFromCart(CartItem cartItem) {
        if(mutableCart.getValue()==null){
            return;
        }

        List<CartItem> cartItemList=new ArrayList<CartItem>(mutableCart.getValue());

        cartItemList.remove(cartItem);
        mutableCart.setValue(cartItemList);
        calculateCartTotal();
    }

    public void changeQuantity(CartItem cartItem, int quantity) {
        if(mutableCart.getValue()==null){
            return;
        }
        CartItem updatedCartItem=new CartItem(cartItem.getProduct(), quantity);
        List<CartItem> cartItemList=new ArrayList<CartItem>(mutableCart.getValue());
        int index=cartItemList.indexOf(cartItem);
        cartItemList.set(index,updatedCartItem);
        mutableCart.setValue(cartItemList);
        calculateCartTotal();
    }

    private void calculateCartTotal(){
        if(mutableCart.getValue()==null){
            mutableTotalPrice.setValue(0.0);
            return;
        }
        double total=0.0;
        List<CartItem> cartItemList=new ArrayList<CartItem>(mutableCart.getValue());

        for(CartItem cartItem:cartItemList){
            total+=cartItem.getProduct().getPrice()*cartItem.getQuantity();
            mutableTotalPrice.setValue(total);
        }
    }

    public LiveData<Double> getTotalPrice(){
        if(mutableTotalPrice==null){
            mutableTotalPrice.setValue(0.0);
        }
        return mutableTotalPrice;
    }
}
