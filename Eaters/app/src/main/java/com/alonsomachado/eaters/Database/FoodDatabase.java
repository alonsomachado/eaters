package com.alonsomachado.eaters.Database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.alonsomachado.eaters.Model.Food;
import com.alonsomachado.eaters.Model.FoodDAO;


@Database(entities = {Food.class}, version = 1)
public abstract class FoodDatabase extends RoomDatabase {


        private static volatile FoodDatabase INSTANCE;


        public static FoodDatabase getDatabase(final Context context) {
            if (INSTANCE == null) {
                synchronized (FoodDatabase.class) {
                    if (INSTANCE == null) {
                        INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                FoodDatabase.class, "food_database")
                                .allowMainThreadQueries()
                                .build();
                    }
                }
            }
            return INSTANCE;
        }


        public abstract FoodDAO getFoodDao();
}

