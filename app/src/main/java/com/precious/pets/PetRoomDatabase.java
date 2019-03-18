package com.precious.pets;

import android.app.Application;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.util.Log;

@Database(entities = {PetHolder.class}, version = 1, exportSchema = false)
public abstract class PetRoomDatabase extends RoomDatabase {

    public static final String LOG_TAG = PetRoomDatabase.class.getSimpleName();
    private static PetRoomDatabase sINSTANCE;
    private static Object LOCK = new Object();
    private static String DATABASE_NAME = "PetsRoom";



    public static PetRoomDatabase getsINSTANCE(Context context){
        if(sINSTANCE == null){
            synchronized (LOCK){
                Log.d(LOG_TAG, "Creating new database instance");
                sINSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                        PetRoomDatabase.class, DATABASE_NAME)
                        .build();
            }
        }

        return sINSTANCE;
    }



    public abstract PetDao getPetDao();
}
