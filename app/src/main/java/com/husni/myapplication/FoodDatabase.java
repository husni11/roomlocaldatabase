package com.husni.myapplication;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.husni.myapplication.model.Food;

//Abstract Class

@Database(entities = Food.class, version = 2)
public abstract class FoodDatabase extends RoomDatabase {

    private static volatile FoodDatabase INSTANCE;

    public abstract FoodDao foodDao();

    static FoodDatabase getDatabase(Context c){

        if(INSTANCE == null){
            INSTANCE = Room.databaseBuilder(c.getApplicationContext(), FoodDatabase.class,"db_food")
                    .allowMainThreadQueries()
                    .build();
        }
        return INSTANCE;
    }
}
