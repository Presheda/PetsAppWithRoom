package com.precious.pets;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "pet_table")
public class PetHolder {

    private String petName;
    private String petBreed;
    private int petSex;
    private int petAge;

    @PrimaryKey (autoGenerate = true)
    private int id;


    @Ignore
    public PetHolder(String petName, String petBreed, int petSex, int petAge) {
        this.petName = petName;
        this.petBreed = petBreed;
        this.petSex = petSex;
        this.petAge = petAge;
    }

    public PetHolder(int id, String petName, String petBreed, int petSex, int petAge) {
        this.petName = petName;
        this.petBreed = petBreed;
        this.petSex = petSex;
        this.petAge = petAge;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPetName() {
        return petName;
    }

    public void setPetName(String petName) {
        this.petName = petName;
    }

    public String getPetBreed() {
        return petBreed;
    }

    public void setPetBreed(String petBreed) {
        this.petBreed = petBreed;
    }

    public int getPetSex() {
        return petSex;
    }

    public void setPetSex(int petSex) {
        this.petSex = petSex;
    }


    public int getPetAge() {
        return petAge;
    }

    public void setPetAge(int petAge) {
        this.petAge = petAge;
    }
}
