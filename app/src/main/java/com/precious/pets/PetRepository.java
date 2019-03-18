package com.precious.pets;

import android.app.Application;
import android.arch.lifecycle.LiveData;

import java.util.List;

public class PetRepository {
    private Object LOCK = new Object();

    private static final String LOG_TAG = PetRepository.class.getSimpleName();

    private LiveData<List<PetHolder>> allPets;


    private LiveData<PetHolder> petById;
    private PetDao  mPetDao;

    public PetRepository(final Application application) {

        PetRoomDatabase petRoomDatabase = PetRoomDatabase.getsINSTANCE(application.getApplicationContext());
        mPetDao = petRoomDatabase.getPetDao();
        allPets = mPetDao.loadAllPet();
    }


    public PetRepository(final Application application, int id) {

        PetRoomDatabase petRoomDatabase = PetRoomDatabase.getsINSTANCE(application.getApplicationContext());
        mPetDao = petRoomDatabase.getPetDao();
        petById = mPetDao.loadPetById(id);
    }



    public LiveData<List<PetHolder>> getAllPets() {
        return allPets;
    }


    public LiveData<PetHolder> getPetById() {
        return petById;
    }

    public void deletePetHolder(PetHolder petHolder){
        mPetDao.deletePetHolder(petHolder);
    }

    public void insertPet(PetHolder petHolder){
        mPetDao.insertPet(petHolder);
    }

    public void updatePet(PetHolder petHolder){
        mPetDao.updatePetHolder(petHolder);
    }



}
