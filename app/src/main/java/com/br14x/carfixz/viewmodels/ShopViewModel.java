package com.br14x.carfixz.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.br14x.carfixz.models.CartItem;
import com.br14x.carfixz.models.Product;
import com.br14x.carfixz.repositories.CartRepo;
import com.br14x.carfixz.repositories.ShopRepo;

import java.util.List;

public class ShopViewModel extends ViewModel {

    ShopRepo shopRepo=new ShopRepo();
    CartRepo cartRepo=new CartRepo();

    MutableLiveData<Product> mutableProduct=new MutableLiveData<>();

    public LiveData<List<Product>> getProducts(){

        return shopRepo.getProducts();
    }

    public void setProduct(Product product){                         //Used for setting the clicked product in the view
        mutableProduct.setValue(product);
    }

    public LiveData<Product> getProduct(){
        return mutableProduct;               //here you can always return a product whose type is mutable livedata
    }

    public LiveData<List<CartItem>> getCart(){
        return cartRepo.getCart();
    }

    public boolean addItemToCart(Product product){
        return cartRepo.addItemToCart(product);
    }
    public void removeItemFromCart(CartItem cartItem){
        cartRepo.removeItemFromCart(cartItem);

    }

    public void changeQuantity(CartItem cartItem,int quantity){
        cartRepo.changeQuantity(cartItem,quantity);
    }

    public LiveData<Double> getTotalPrice(){
        return cartRepo.getTotalPrice();
    }

    public void resetcart(){
        cartRepo.initCart();
    }
}
