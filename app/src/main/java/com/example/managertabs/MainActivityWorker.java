package com.example.managertabs;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.Menu;
import android.view.MenuItem;

import com.google.firebase.firestore.FirebaseFirestore;
import com.example.managertabs.Donation.WorkerDonations;
import com.example.managertabs.WorkerInventory;

public class MainActivityWorker extends Master {
    //I don't think this is actually needed here, just call the INVENTORY, WORKERS, etc instead from master
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    /* onCreate method creates the screen */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    /* Sets the back button to revert to the last screen. In the case that the drawer is open, it simply closes the drawer instead. */
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        // If drawer (tabs) are open, close them
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        }
        // If drawer (tabs) are closed, revert to last activity via super.onBackPressed method
        else {
            Intent intent = new Intent(getApplicationContext(), WorkerHomeScreen.class);
            startActivity(intent);
        }
    }

    /* Creates the inflater for the Setting menu button. */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.settings_menu, menu);
        return true;
    }

    /* Method to be instantiated later, will listen for buttons in settings that are not yet designated */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        else if (id == R.id.logout) {
            Intent intent = new Intent(getApplicationContext(), Login.class);
            // The below code clears the stack so the activity cannot be reached. (Security bug cleared) (I.E. ERASE STACK MEMORY)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }

    /* Method used when drawer (tabs) layout is open, listens for button clicks (tab selected) and
    does a screen transition based on received */

    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        // If INVENTORY is selected by worker, go to WorkerInventory.class
        if (id == R.id.nav_inventory) {
            Intent intent = new Intent(getApplicationContext(), WorkerInventory.class);
            startActivity(intent);
        }
        // If INVENTORY is selected by worker, go to WorkerDonations.class
        else if (id == R.id.nav_donations) {
            Intent intent = new Intent(getApplicationContext(), WorkerDonations.class);
            startActivity(intent);
        }
        // If home is selected by worker, go to home
        else if (id == R.id.nav_home) {
            Intent intent = new Intent(getApplicationContext(), WorkerHomeScreen.class);
            startActivity(intent);
        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
