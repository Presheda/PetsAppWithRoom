package com.precious.pets;

import android.databinding.BindingAdapter;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.animation.Animation;
import android.widget.TextView;

import java.util.List;

import static android.support.v7.widget.DividerItemDecoration.VERTICAL;

public class customPetBindingAdapter {

    @BindingAdapter("setPetList")
    public static void setupPetList(RecyclerView recyclerView, List<PetHolder> petHolderList){
        if (petHolderList != null){

            PetAdapter petAdapter = (PetAdapter) recyclerView.getAdapter();

            if(petAdapter == null) {


                petAdapter = new PetAdapter(recyclerView.getContext(), petHolderList);

                LinearLayoutManager layoutManager = new LinearLayoutManager(recyclerView.getContext());
                recyclerView.setLayoutManager(layoutManager);


                DividerItemDecoration decoration = new DividerItemDecoration(recyclerView.getContext(), VERTICAL);
                recyclerView.addItemDecoration(decoration);

                recyclerView.setAdapter(petAdapter);

                return;
            }

            petAdapter.setPetList(petHolderList);
        }
    }

    @BindingAdapter("setSex")
    public static void setPetSex(TextView textView, int petSex){
        if(petSex == 0){
            textView.setText("Male");
            return;
        }
        textView.setText("Female");
    }
}
