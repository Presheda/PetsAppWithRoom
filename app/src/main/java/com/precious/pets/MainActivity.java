package com.precious.pets;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;

import com.precious.pets.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements IRecyclerViewOnClick{
    public static final String PET_ID = "PET_ID";
    ActivityMainBinding mainBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        setUpPets();
        setupFragment();
        deletePet();
    }

    private void deletePet() {

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT|ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder viewHolder1) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int i) {

                int position = viewHolder.getAdapterPosition();
                PetAdapter adapter = (PetAdapter) mainBinding.recyclerViewPet.getAdapter();
                List<PetHolder> pet  = adapter.getPetHolders();

                PetViewModel petViewModel = ViewModelProviders.of(MainActivity.this).get(PetViewModel.class);
                AppExecutors.getInstance().diskIO().execute(new Runnable() {
                    @Override
                    public void run() {
                        petViewModel.deletePet(pet.get(position));
                    }
                });



            }
        }).attachToRecyclerView(mainBinding.recyclerViewPet);
    }

    private void setupFragment() {
        mainBinding.fabButtonNewPet.setOnClickListener((view -> {
            EditPetFragment editPetFragment = new EditPetFragment();

            FragmentManager manager = getSupportFragmentManager();



           editPetFragment.show(manager, "dialog");

        }));
    }

    private void setUpPets() {
        PetViewModel petViewModel = ViewModelProviders.of(this).get(PetViewModel.class);

        AppExecutors.getInstance().diskIO().execute(new Runnable() {
            @Override
            public void run() {
                petViewModel.getAllPets().observe(MainActivity.this, new Observer<List<PetHolder>>() {
                    @Override
                    public void onChanged(@Nullable List<PetHolder> petHolders) {
                        mainBinding.setPetList(petHolders);


                    }
                });
            }
        });

    }

    @Override
    public void Onclicked(PetHolder petHolder) {

        EditPetFragment editPetFragment = new EditPetFragment();

        Bundle bundle = new Bundle();
        bundle.putInt(PET_ID, petHolder.getId());
        editPetFragment.setArguments(bundle);

        FragmentManager manager = getSupportFragmentManager();

        editPetFragment.show(manager, "dialog");


    }


    @Override
    public void floatinActionButtionClicked() {

    }
}
