package com.husni.myapplication;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.husni.myapplication.model.Food;

import java.util.List;

@Dao
public interface FoodDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void add(Food food);

    @Query("SELECT * FROM tb_food ORDER BY id ASC")
    List<Food> getFood();

    @Delete
    void delete(Food food);
}

