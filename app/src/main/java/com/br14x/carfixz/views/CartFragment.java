package com.br14x.carfixz.views;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DividerItemDecoration;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.br14x.carfixz.BookingIDgenerator;
import com.br14x.carfixz.MainActivity;
import com.br14x.carfixz.Order;
import com.br14x.carfixz.R;
import com.br14x.carfixz.UserOrder;
import com.br14x.carfixz.adapters.CartListAdapter;
import com.br14x.carfixz.databinding.FragmentCartBinding;

import com.br14x.carfixz.models.CartItem;
import com.br14x.carfixz.viewmodels.ShopViewModel;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;


public class CartFragment extends Fragment implements CartListAdapter.CartInterface {

    private static final String TAG="CartFragment";
    ShopViewModel shopViewModel;
    FragmentCartBinding fragmentCartBinding;
    NavController navController;
    private DatabaseReference mDatabase;
    Random rand;


    public CartFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        fragmentCartBinding=FragmentCartBinding.inflate(inflater,container,false);
        return fragmentCartBinding.getRoot();

    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        navController= Navigation.findNavController(view);
        final CartListAdapter cartListAdapter=new CartListAdapter(this);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        rand=new Random();
        fragmentCartBinding.cartRecyclerView.setAdapter(cartListAdapter);
        fragmentCartBinding.cartRecyclerView.addItemDecoration(new DividerItemDecoration(getContext(),DividerItemDecoration.VERTICAL));


        shopViewModel=new ViewModelProvider(requireActivity()).get(ShopViewModel.class);
        shopViewModel.getCart().observe(getViewLifecycleOwner(), new Observer<List<CartItem>>() {
            @Override
            public void onChanged(List<CartItem> cartItems) {
                cartListAdapter.submitList(cartItems);
                fragmentCartBinding.placeOrderButton.setEnabled(cartItems.size()>0);
            }
        });
        shopViewModel.getTotalPrice().observe(getViewLifecycleOwner(), new Observer<Double>() {
            @Override
            public void onChanged(Double aDouble) {
                fragmentCartBinding.orderTotalTextView.setText("Total: " + aDouble.toString()+" ₹");
            }
        });

        fragmentCartBinding.placeOrderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BookingIDgenerator obj=new BookingIDgenerator();
                String x=obj.getRandomString(5);
                String OID="OID"+x;
                LiveData<List<CartItem>> mutableCart;
                mutableCart=shopViewModel.getCart();
                LiveData<Double> totalPrice=shopViewModel.getTotalPrice();
                List<CartItem> order=new ArrayList<>(mutableCart.getValue());
                List<Order> newOrder=new ArrayList<Order>();

                UserOrder od=new UserOrder();

                for(int i=0;i<order.size();i++) {
                    Log.i("Order items:", order.get(i).getProduct().getName());
                    Order o=new Order();
                    o.item_id=order.get(i).getProduct().getId();
                    o.name=order.get(i).getProduct().getName();
                    o.Quantity=order.get(i).getQuantity();
                    o.cost_per_unit=order.get(i).getProduct().getPrice();
                    newOrder.add(o);
                    //mDatabase.child("Orders").updateChildren();
                }
                Log.i("A:",newOrder.toString());
                od.orderID=OID;
                od.orderItems=newOrder;
                od.totalPrice=totalPrice;
                MainActivity.ord.add(od);
                Log.i("X",od.orderItems.toString());
                mDatabase.child("Orders").child(OID).child("Items").setValue(newOrder);
                mDatabase.child("Orders").child(OID).child("Order Total").setValue(totalPrice);
                navController.navigate(R.id.action_cartFragment_to_orderFragment);
                shopViewModel.resetcart();
            }
        });
    }

    @Override
    public void deleteItem(CartItem cartItem) {
        Log.d(TAG,"deleteItem"+cartItem.getProduct().getName());
        shopViewModel.removeItemFromCart(cartItem);
    }

    @Override
    public void changeQuantity(CartItem cartItem,int quantity){
        shopViewModel.changeQuantity(cartItem,quantity);
    }
}
