package sql;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.RoomDatabase;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

import com.example.managertabs.WorkerDonations;

@Database(entities = {Worker.class}, version = 1)
public abstract class WorkerDatabase extends RoomDatabase {
    // WorkerDao is a class annotated with @Entity
    public abstract WorkerDao workerDao();

    private static volatile WorkerDatabase INSTANCE;

    //Simple singleton - is better to use dagger to inject db.
    static WorkerDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (WorkerDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            WorkerDatabase.class, "worker_database")
                            // Wipes and rebuilds instead of migrating if no Migration object
                            // IMPLEMENT MIGRATION OBJECT!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
                            .fallbackToDestructiveMigration()
                            //.addCallback(sRoomDatabaseCallback)
                            .build();
                }
            }

        }
        return INSTANCE;
    }
}