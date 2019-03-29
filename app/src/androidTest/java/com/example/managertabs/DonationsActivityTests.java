package com.example.managertabs;

import android.support.test.espresso.contrib.DrawerActions;
import android.support.test.espresso.contrib.NavigationViewActions;
import android.support.test.runner.AndroidJUnit4;
import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.view.Gravity;

import com.example.managertabs.Donation.DonationsActivity;
import com.example.managertabs.Inventory.Inventory;

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
public class DonationsActivityTests {
    // Sets the startup screen for this test file to the donations screen
    @Rule
    public IntentsTestRule<DonationsActivity> donationsIntentsTestRule
            = new IntentsTestRule<>(DonationsActivity.class, true, true);

    // Tests navigation from the donations screen to the inventory screen
    @Test
    public void testDonationsToInventory(){
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

    // Tests navigation from the donations screen to the donations screen
    @Test
    public void testDonationsToDonations(){
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

    // Tests navigation from the donations screen to the donors screen
    @Test
    public void testDonationsToDonors(){
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

    // Tests navigation from the donations screen to the staff screen
    @Test
    public void testDonationsToStaff(){
        // Checks to make sure the navigation drawer is closed
        // and then opens it
        onView(withId(R.id.drawer_layout))
                .check(matches(isClosed(Gravity.LEFT)))
                .perform(DrawerActions.open());

        // Clicks the staff tab and navigates to it
        onView(withId(R.id.nav_view)).perform(NavigationViewActions.navigateTo(R.id.nav_staff));

        // Checks to see that the correct screen is opened
        intended(hasComponent(Staff.class.getName()));
    }

}