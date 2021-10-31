package com.example.nutritionapp.Food;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.nutritionapp.interfaces.FoodDao;

@Database(entities = {FoodEntity.class}, version = 1)
public abstract class FoodDatabase extends RoomDatabase {
    private static FoodDatabase instance;
    public abstract FoodDao foodDao();

    public static synchronized FoodDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    FoodDatabase.class, "food_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build();
        }
        return instance;
    }

    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsyncTask(instance).execute();
        }
    };

    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void> {
        private FoodDao foodDao;

        private PopulateDbAsyncTask(FoodDatabase db) {
            foodDao = db.foodDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            return null;
        }
    }
}
