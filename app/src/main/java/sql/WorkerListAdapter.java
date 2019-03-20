package sql;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.managertabs.R;


public class WorkerListAdapter extends RecyclerView.Adapter<WorkerListAdapter.WorkerViewHolder> {

    class WorkerViewHolder extends RecyclerView.ViewHolder {
        private final TextView WorkerItemView;

        private WorkerViewHolder(View itemView) {
            super(itemView);
            WorkerItemView = itemView.findViewById(R.id.textView);
        }
    }

    private final LayoutInflater inflater;
    private Worker[] workers;

    WorkerListAdapter(Context context) {
        inflater = LayoutInflater.from(context);
    }

    @Override
    public WorkerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = inflater.inflate(R.layout.recyclerview_worker, parent, false);
        return new WorkerViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(WorkerViewHolder holder, int position) {
        if(workers != null) {
            Worker current  = workers[position]; // Altered
            holder.WorkerItemView.setText(current.getFirstName()); // Altered
        }
        else {
            holder.WorkerItemView.setText("No Word");
        }
    }

    void setWorkers(Worker[] mWorkers) {
        workers = mWorkers;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (workers != null) {
            return workers.length; // Altered
        }
        else return 0;
    }
}
