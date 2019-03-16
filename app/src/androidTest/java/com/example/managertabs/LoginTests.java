package com.example.managertabs;

import android.support.test.runner.AndroidJUnit4;
import android.support.test.rule.ActivityTestRule;
import android.support.test.espresso.intent.rule.IntentsTestRule;

import org.junit.Rule;
import org.junit.runner.RunWith;
import org.junit.Test;

import static android.support.test.espresso.intent.Intents.*;
import static android.support.test.espresso.intent.matcher.IntentMatchers.*;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.withText;





@RunWith(AndroidJUnit4.class)
public class LoginTests {
  /*  @Rule
    public ActivityTestRule<Login> loginActivityTestRule
            = new ActivityTestRule<>(Login.class);
            */

    // Sets the startup screen for this test file to the Login screen
    @Rule
    public IntentsTestRule<Login> loginIntentsTestRule
            = new IntentsTestRule<>(Login.class);

    // Tests that logging in as a manager takes you to the managers home screen
    @Test
    public void testLoginManager(){
        // Inputs "manager" into the email field
        onView(withId(R.id.etEmail1)).perform(typeText("manager"));

        // Inputs "manager" into the password field and closes the keyboard
        onView(withId(R.id.etPassword1)).perform(typeText("manager"),
                closeSoftKeyboard());

        // Clicks the login button
        onView(withText("LOGIN")).perform(click());

        // Checks to see that the managers home screen was opened
        intended(hasComponent(ManagerHomeScreen.class.getName()));
    }

    // Tests that logging in as a worker takes you to the workers home screen
    @Test
    public void testLoginWorker(){
        // Inputs "worker" into the email field
        onView(withId(R.id.etEmail1)).perform(typeText("worker"));

        // Inputs "worker" into the password field
        onView(withId(R.id.etPassword1)).perform(typeText("worker"),
                closeSoftKeyboard());

        // Clicks the login button
        onView(withText("LOGIN")).perform(click());

        // Checks to see that the workers home screen was opened
        intended(hasComponent(WorkerHomeScreen.class.getName()));
    }
}
