package sql;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

public class WorkerViewModel extends AndroidViewModel {
    private WorkerRepository workerRepository;
    private LiveData<Worker[]> allWorkers;

    public WorkerViewModel(Application application) {
        super(application);
        workerRepository = new WorkerRepository(application);
        allWorkers = workerRepository.getAllWorkers();
    }

    LiveData<Worker[]> getAllWorkers() {
        return allWorkers;
    }

    public void insert(Worker worker) {
        workerRepository.insert(worker);
    }



}
