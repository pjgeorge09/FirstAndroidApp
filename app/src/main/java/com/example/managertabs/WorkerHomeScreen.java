package com.example.managertabs;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class WorkerHomeScreen extends MainActivityWorker
        implements NavigationView.OnNavigationItemSelectedListener {

    /* onCreate method creates the screen */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Set the background for the view, includes in the XML links to it's related files and clickables
        setContentView(R.layout.activity_worker_home);

        // Creates the toolbar at the top of the screen
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//        String memoString = memoSnapshot.getResult().getString("Memo");

        // Sets the navigation drawer to still be accessible by the toolbar button. This is the sliding part
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        // Sets the side navigation to be able to be called and buttons selected. This is the clickable part.
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        TextView textView = (TextView)findViewById(R.id.memoWorker);
//        textView.setText(memoString);

    }

    /* Sets the back button to revert to the last screen. In the case that the drawer is open, it simply closes the drawer instead. */
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        // If drawer (tabs) are open, close them
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        }

        // If drawer (tabs) are closed, revert to login screen, CLEAR MEMORY
        else {
            Intent intent = new Intent(getApplicationContext(), Login.class);
            // The below code clears the stack so the activity cannot be reached. (Security bug cleared) (I.E. ERASE STACK MEMORY)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            //super.onBackPressed();
        }
    }

    /* Method used when drawer (tabs) layout is open, listens for button clicks (tab selected) and
    does a screen transition based on received */
    @Override
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
//            Intent intent = new Intent(getApplicationContext(), WorkerHomeScreen.class);
//            startActivity(intent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
