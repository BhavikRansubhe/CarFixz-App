package com.br14x.carfixz.views;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.br14x.carfixz.R;
import com.br14x.carfixz.databinding.FragmentProductDetailsBinding;
import com.br14x.carfixz.viewmodels.ShopViewModel;


public class ProductDetailsFragment extends Fragment {

    FragmentProductDetailsBinding fragmentProductDetailsBinding;
    ShopViewModel shopViewModel;

    public ProductDetailsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_product_details, container, false);

        fragmentProductDetailsBinding=FragmentProductDetailsBinding.inflate(inflater,container,false);
        return  fragmentProductDetailsBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        shopViewModel=new ViewModelProvider(requireActivity()).get(ShopViewModel.class);
        fragmentProductDetailsBinding.setShopViewModel(shopViewModel);
    }
}
