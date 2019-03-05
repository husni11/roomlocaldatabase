package com.husni.myapplication.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "tb_food")
public class Food {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "food_name")
    private String food_name;

    @ColumnInfo(name = "food_desc")
    private String food_desc;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFood_name() {
        return food_name;
    }

    public void setFood_name(String food_name) {
        this.food_name = food_name;
    }

    public String getFood_desc() {
        return food_desc;
    }

    public void setFood_desc(String food_desc) {
        this.food_desc = food_desc;
    }
}
