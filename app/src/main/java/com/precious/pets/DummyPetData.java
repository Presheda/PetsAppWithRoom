package com.precious.pets;

import java.util.ArrayList;
import java.util.List;

/**
 * This is was used before the creation of the database to simulate a database
 * it contains the dummy pet details
 */

public class DummyPetData {


    private static List<PetHolder> petHolderList;
    private static DummyPetData dummyPetData;


    /**
     * A private constructor to prevent the creation of multiple instance of the class
     */
    private DummyPetData() {
    }

    public static DummyPetData getInstance(){
        dummyPetData = new DummyPetData();
        return dummyPetData;
    }

    static {
        petHolderList  = new ArrayList<>();
        loadPetData();
    }

    /**
     * Performs the work of loading the pets
     */

    private static void loadPetData() {
        PetHolder petHolder = new PetHolder("Phobia", "Dog", 1, 7);
        petHolderList.add(petHolder);

        petHolder = new PetHolder("Smart", "Dog", 1, 10);
        petHolderList.add(petHolder);

        petHolder = new PetHolder("Lizzy", "Cat", 0, 4);
        petHolderList.add(petHolder);

        petHolder = new PetHolder("Ruma", "Bird", 0, 5);
        petHolderList.add(petHolder);

        petHolder = new PetHolder("Panda", "Dog", 1, 12);
        petHolderList.add(petHolder);

        petHolder = new PetHolder("Femo", "Fish", 0, 8);
        petHolderList.add(petHolder);

        petHolder = new PetHolder("Trup", "Cat", 0, 3);
        petHolderList.add(petHolder);

        petHolder = new PetHolder("Lurry", "Bird", 0, 7);
        petHolderList.add(petHolder);

        petHolder = new PetHolder("Luka", "Dog", 1, 14);
        petHolderList.add(petHolder);

        petHolder = new PetHolder("Stacy", "Cat", 1, 9);
        petHolderList.add(petHolder);

        petHolder = new PetHolder("Rugged", "Cat", 0, 6);
        petHolderList.add(petHolder);

        petHolder = new PetHolder("Jack", "Bird", 1, 11);
        petHolderList.add(petHolder);

        petHolder = new PetHolder("Makruss", "Fish", 1, 12);
        petHolderList.add(petHolder);

        petHolder = new PetHolder("Momo", "Fish", 0, 6);
        petHolderList.add(petHolder);

        petHolder = new PetHolder("Appa", "Dog", 0, 9);
        petHolderList.add(petHolder);
    }


    public List<PetHolder> getDummyPets(){
        return petHolderList;
    }

    public PetHolder getDummyPet(int petId){

        return petHolderList.get(petId);
    }

    public int addPetHolder(PetHolder petHolder){
        petHolderList.add(petHolder);
        return petHolderList.size()-1;
    }



}
