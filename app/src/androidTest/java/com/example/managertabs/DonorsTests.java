package com.example.managertabs;

import android.support.test.espresso.contrib.DrawerActions;
import android.support.test.espresso.contrib.NavigationViewActions;
import android.support.test.runner.AndroidJUnit4;
import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.view.Gravity;

import com.example.managertabs.Donation.DonationsActivity;
import com.example.managertabs.EmployeeFiles.EmployeeActivity;
import com.example.managertabs.Inventory;

import org.junit.Rule;
import org.junit.runner.RunWith;
import org.junit.Test;

import static android.support.test.espresso.intent.Intents.*;
import static android.support.test.espresso.intent.matcher.IntentMatchers.*;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.contrib.DrawerMatchers.isClosed;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
public class DonorsTests{
    // Sets the startup screen for this test file to the donors screen
    @Rule
    public IntentsTestRule<Donors> donorsIntentsTestRule
            = new IntentsTestRule<>(Donors.class, true, true);

    // Tests navigation from the donors screen to the inventory screen
    @Test
    public void testDonorsToInventory(){
        // Checks to make sure the navigation drawer is closed
        // and then opens it
        onView(withId(R.id.drawer_layout))
                .check(matches(isClosed(Gravity.LEFT)))
                .perform(DrawerActions.open());

        // Clicks the inventory tab and navigates to it
        onView(withId(R.id.nav_view)).perform(NavigationViewActions.navigateTo(R.id.nav_inventory));

        // Checks to see that the correct screen is opened
        intended(hasComponent(Inventory.class.getName()));
    }

    // Tests navigation from the donors screen to the donations screen
    @Test
    public void testDonorsToDonations(){
        // Checks to make sure the navigation drawer is closed
        // and then opens it
        onView(withId(R.id.drawer_layout))
                .check(matches(isClosed(Gravity.LEFT)))
                .perform(DrawerActions.open());

        // Clicks the donations tab and navigates to it
        onView(withId(R.id.nav_view)).perform(NavigationViewActions.navigateTo(R.id.nav_donations));

        // Checks to see that the correct screen is opened
        intended(hasComponent(DonationsActivity.class.getName()));
    }

    // Tests navigation from the donors screen to the donors screen
    @Test
    public void testDonorsToDonors(){
        // Checks to make sure the navigation drawer is closed
        // and then opens it
        onView(withId(R.id.drawer_layout))
                .check(matches(isClosed(Gravity.LEFT)))
                .perform(DrawerActions.open());

        // Clicks the donors tab and navigates to it
        onView(withId(R.id.nav_view)).perform(NavigationViewActions.navigateTo(R.id.nav_donors));

        // Checks to see that the correct screen is opened
        intended(hasComponent(Donors.class.getName()));
    }

    // Tests navigation from the donors screen to the staff screen
    @Test
    public void testDonorsToStaff(){
        // Checks to make sure the navigation drawer is closed
        // and then opens it
        onView(withId(R.id.drawer_layout))
                .check(matches(isClosed(Gravity.LEFT)))
                .perform(DrawerActions.open());

        // Clicks the staff tab and navigates to it
        onView(withId(R.id.nav_view)).perform(NavigationViewActions.navigateTo(R.id.nav_staff));

        // Checks to see that the correct screen is opened
        intended(hasComponent(EmployeeActivity.class.getName()));
    }
}
