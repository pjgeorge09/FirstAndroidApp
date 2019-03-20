package sql;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.example.managertabs.R;

import java.util.List;

import sql.WorkerListAdapter;
import sql.WorkerViewModel;

public class testRoom extends AppCompatActivity {


    public class MainActivity extends AppCompatActivity {

        public static final int NEW_WORD_ACTIVITY_REQUEST_CODE = 1;

        private WorkerViewModel mWorkerViewModel;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_testroom_main);

            Toolbar toolbar = findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);

            RecyclerView recyclerView = findViewById(R.id.recyclerview);
            final WorkerListAdapter adapter = new WorkerListAdapter(this);
            recyclerView.setAdapter(adapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));

            // Get a new or existing ViewModel from the ViewModelProvider.
            mWorkerViewModel = ViewModelProviders.of(this).get(WorkerViewModel.class);

            // Add an observer on the LiveData returned by getAlphabetizedWords.
            // The onChanged() method fires when the observed data changes and the activity is
            // in the foreground.
            mWorkerViewModel.getAllWorkers().observe(this, new Observer<Worker[]>() {
                @Override
                public void onChanged(@Nullable final Worker[] workers) {
                    // Update the cached copy of the words in the adapter.
                    adapter.setWorkers(workers);
                }
            });

            FloatingActionButton fab = findViewById(R.id.fab);
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(MainActivity.this, newWorkerActivity.class);
                    startActivityForResult(intent, NEW_WORD_ACTIVITY_REQUEST_CODE);
                }
            });
        }

        public void onActivityResult(int requestCode, int resultCode, Intent data) {
            super.onActivityResult(requestCode, resultCode, data);

        }
    }
}

