package com.example.managertabs;

import android.support.test.espresso.contrib.DrawerActions;
import android.support.test.espresso.contrib.NavigationViewActions;
import android.support.test.runner.AndroidJUnit4;
import android.support.test.rule.ActivityTestRule;
import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.view.Gravity;

import org.junit.Rule;
import org.junit.runner.RunWith;
import org.junit.Test;

import static android.support.test.espresso.intent.Intents.*;
import static android.support.test.espresso.intent.matcher.IntentMatchers.*;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.matcher.ViewMatchers.isClickable;
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.contrib.DrawerMatchers.isClosed;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
public class WorkerDonationsTests{
    // Sets the startup screen for this test file to the workers donations screen
    @Rule
    public IntentsTestRule<WorkerDonations> workerDonationsIntentsTestRule
            = new IntentsTestRule<>(WorkerDonations.class, true, true);

    // Tests navigation from the workers donations screen to the workers inventory screen
    @Test
    public void testWorkersDonationsToWorkersInventory(){
        // Checks to make sure the navigation drawer is closed
        // and then opens it
        onView(withId(R.id.drawer_layout))
                .check(matches(isClosed(Gravity.LEFT)))
                .perform(DrawerActions.open());

        // Clicks the inventory tab and navigates to it
        onView(withId(R.id.nav_view)).perform(NavigationViewActions.navigateTo(R.id.nav_inventory));

        // Checks to see that the correct screen is opened
        intended(hasComponent(WorkerInventory.class.getName()));
    }

    // Tests navigation from the workers donations screen to the workers donations screen
    @Test
    public void testWorkersDonationsToWorkersDonations(){
        // Checks to make sure the navigation drawer is closed
        // and then opens it
        onView(withId(R.id.drawer_layout))
                .check(matches(isClosed(Gravity.LEFT)))
                .perform(DrawerActions.open());

        // Clicks the donations tab and navigates to it
        onView(withId(R.id.nav_view)).perform(NavigationViewActions.navigateTo(R.id.nav_donations));

        // Checks to see that the correct screen is opened
        intended(hasComponent(WorkerDonations.class.getName()));
    }

}
