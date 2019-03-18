package com.precious.pets;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.precious.pets.databinding.ListPetBinding;

import java.util.List;

public class PetAdapter extends RecyclerView.Adapter<PetAdapter.petView> {
    Context context;
    List<PetHolder> petHolders;



    public PetAdapter(Context context, List<PetHolder> petHolders) {
        this.context = context;
        this.petHolders = petHolders;
    }

    public void setPetList(List<PetHolder> petList){
        petHolders = petList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public petView onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        ListPetBinding listPetBinding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.list_pet, viewGroup,
                false);

        return new petView(listPetBinding.getRoot());
    }

    @Override
    public void onBindViewHolder(@NonNull petView petView, int position) {
        PetHolder petHolder = petHolders.get(position);

        petView.listPetBinding.setPetHolder(petHolder);

        petView.listPetBinding.setIRecyclerView((IRecyclerViewOnClick) context);

        petView.listPetBinding.setVariable(BR.petHolder, petHolder);

        petView.listPetBinding.executePendingBindings();


    }

    @Override
    public int getItemCount() {
        if(petHolders != null){
            return petHolders.size();
        }
        return 0;
    }

    public List<PetHolder> getPetHolders (){
        return petHolders;
    }

    class petView extends RecyclerView.ViewHolder{

        ListPetBinding listPetBinding;

        public petView(@NonNull View itemView) {
            super(itemView);
            listPetBinding = DataBindingUtil.getBinding(itemView);

        }

    }
}
