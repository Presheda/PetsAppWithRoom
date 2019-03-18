package com.precious.pets;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Update;

import java.util.List;


@Dao
public interface PetDao {

    @Query("SELECT * FROM pet_table")
    LiveData<List<PetHolder>> loadAllPet();

    @Query("SELECT * FROM pet_table Where id=:id")
    LiveData<PetHolder> loadPetById(int id);

    @Insert
    void insertPet(PetHolder petHolder);

    @Delete
    void deletePetHolder(PetHolder petHolder);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updatePetHolder(PetHolder petHolder);

}
