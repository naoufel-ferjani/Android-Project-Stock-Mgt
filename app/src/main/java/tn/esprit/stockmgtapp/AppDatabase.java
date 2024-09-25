package tn.esprit.stockmgtapp;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.annotation.NonNull;

@Database(entities = {User.class, Stock.class}, version = 2, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    private static volatile AppDatabase instance;  // Mark instance as volatile to ensure thread safety

    // Define DAOs
    public abstract UserDao userDao();
    public abstract StockDao stockDao();  // Add StockDao here

    // Singleton pattern to ensure only one instance of AppDatabase is created
    public static synchronized AppDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                            AppDatabase.class, "user_database")
                    .fallbackToDestructiveMigration() // Allow destructive migrations to drop the old database
                    .addCallback(roomCallback)        // Add callback to initialize data if needed
                    .allowMainThreadQueries()         // Caution: allows DB queries on the main thread
                    .build();
        }
        return instance;
    }

    // Callback to handle database creation or opening
    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            // Initialize your database with default data if needed
        }

        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);
            // This can be used to populate or check data every time the database is opened
        }
    };
}
