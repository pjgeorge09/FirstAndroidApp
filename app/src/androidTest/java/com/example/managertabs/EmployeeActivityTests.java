package com.example.managertabs;

import android.support.test.espresso.contrib.DrawerActions;
import android.support.test.espresso.contrib.NavigationViewActions;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.runner.AndroidJUnit4;
import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.view.Gravity;

import com.example.managertabs.Donation.DonationsActivity;
import com.example.managertabs.EmployeeFiles.EmployeeActivity;
import com.example.managertabs.Inventory;
import com.forkingcode.espresso.contrib.DescendantViewActions;

import org.junit.Rule;
import org.junit.runner.RunWith;
import org.junit.Test;

import static android.support.test.espresso.assertion.ViewAssertions.selectedDescendantsMatch;
import static android.support.test.espresso.intent.Intents.*;
import static android.support.test.espresso.intent.matcher.IntentMatchers.*;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.matcher.ViewMatchers.hasDescendant;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.contrib.DrawerMatchers.isClosed;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
public class EmployeeActivityTests {

    @Rule
    public IntentsTestRule<EmployeeActivity> employeeActivityIntentsTestRule
            = new IntentsTestRule<>(EmployeeActivity.class, true, true);

    // Test to check if first hardcoded card is displayed
    @Test
    public void testFirstCardDisplay(){
        onView(withId(R.id.rv)).check(matches(hasDescendant(withText("John Temporary"))));
    }

    // Test to check if second hardcoded card is displayed
    @Test
    public void testSecondCardDisplay(){
        onView(withId(R.id.rv)).check(matches(hasDescendant(withText("Bryan Hilldrup"))));
    }

    // Test to check if third hardcoded card is displayed
    @Test
    public void testThirdCardDisplay(){
        onView(withId(R.id.rv)).check(matches(hasDescendant(withText("Peter George"))));
    }

    // Test to check if first card expanded on button click
    @Test
    public void firstCardExpanding(){
        onView(withId(R.id.rv)).perform(RecyclerViewActions.actionOnItemAtPosition(0,
                DescendantViewActions.performDescendantAction(withId(R.id.buttonDropDown), click())));

        onView(withText("JohnTemporary@gmail.com")).check(matches(isDisplayed()));
    }

    // Test to check if second card expanded on button click
    @Test
    public void secondCardExpanding(){
        onView(withId(R.id.rv)).perform(RecyclerViewActions.actionOnItemAtPosition(1,
                DescendantViewActions.performDescendantAction(withId(R.id.buttonDropDown), click())));

        onView(withText("hilldrupbf@vcu.edu")).check(matches(isDisplayed()));
    }

    // Test to check if third card expanded on button click
    @Test
    public void thirdCardExpanding(){
        onView(withId(R.id.rv)).perform(RecyclerViewActions.actionOnItemAtPosition(2,
                DescendantViewActions.performDescendantAction(withId(R.id.buttonDropDown), click())));

        onView(withText("petergeorge@vcu.edu")).check(matches(isDisplayed()));
    }

}
