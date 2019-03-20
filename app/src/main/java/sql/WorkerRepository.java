package sql;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

public class WorkerRepository {

    private WorkerDao workerDao;
    private LiveData<Worker[]> allWorkers;

    WorkerRepository(Application application){
        WorkerDatabase db = WorkerDatabase.getDatabase(application);
        workerDao = db.workerDao();
        allWorkers = workerDao.getAllWorkers();
    }

    LiveData<Worker[]> getAllWorkers(){
        return allWorkers;
    }

    public void insert (Worker worker){
        new insertAsyncTask(workerDao).execute(worker);
    }

    private static class insertAsyncTask extends AsyncTask<Worker, Void, Void> {

        private WorkerDao mAsyncTaskDao;

        insertAsyncTask(WorkerDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Worker... params) {
            mAsyncTaskDao.insertWorker(params[0]);
            return null;
        }
    }
}
