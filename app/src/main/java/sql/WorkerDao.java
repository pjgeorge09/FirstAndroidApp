package sql;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

@Dao
public interface WorkerDao {
    @Insert
    void insertWorker(Worker worker);

    @Query("SELECT * FROM workers ORDER BY first_name ASC")
    LiveData<Worker[]> getAllWorkers();

    @Update
    void updateWorker(Worker worker);

    @Delete
    void deleteWorker(Worker worker);
}
