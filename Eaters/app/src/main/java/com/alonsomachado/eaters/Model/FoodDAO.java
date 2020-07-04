package com.alonsomachado.eaters.Model;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;


@Dao
public interface FoodDAO {

        @Insert
        public void insertFood(Food... foods);

        @Delete
        public void deleteFood(Food... foods);

        @Query("SELECT * FROM Food")
        public List<Food> loadAllFood();

        @Query("SELECT COUNT(*) FROM Food")
        int getCount();

}

