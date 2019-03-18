package com.precious.pets;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import java.util.List;

public class PetViewModel extends AndroidViewModel {



    private LiveData<List<PetHolder>> allPets;
    private LiveData<PetHolder> petById;
    private final PetRepository petRepository;
    private Application application;


    public PetViewModel(@NonNull Application application) {
        super(application);
        this.application = application;
        petRepository = new PetRepository(application);
        allPets = petRepository.getAllPets();
    }


    public LiveData<List<PetHolder>> getAllPets() {
        return allPets;
    }

    public LiveData<PetHolder> getPetById(int id){
       PetRepository petRepository = new PetRepository(application, id);

       petById = petRepository.getPetById();

       return petById;

    }

    public void deletePet(PetHolder petHolder){
        petRepository.deletePetHolder(petHolder);
    }

    public void insertPet(PetHolder petHolder){
        petRepository.insertPet(petHolder);
    }

    public void updatePet(PetHolder petHolder){
        petRepository.updatePet(petHolder);
    }
}
