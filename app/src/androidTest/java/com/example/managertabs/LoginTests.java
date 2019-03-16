package com.example.managertabs;

import static android.support.test.espresso.intent.Intents.*;
import static android.support.test.espresso.intent.matcher.IntentMatchers.*;
import android.support.test.runner.AndroidJUnit4;
import android.support.test.rule.ActivityTestRule;
import android.support.test.espresso.intent.rule.IntentsTestRule;

import org.junit.Rule;
import org.junit.runner.RunWith;
import org.junit.Test;

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

    @Rule
    public IntentsTestRule<Login> loginActivityTestRule
            = new IntentsTestRule<>(Login.class);

    @Test
    public void testLoginManager(){
        onView(withId(R.id.etEmail1)).perform(typeText("manager"));
        onView(withId(R.id.etPassword1)).perform(typeText("manager"),
                closeSoftKeyboard());
        onView(withText("LOGIN")).perform(click());
        intended(hasComponent(ManagerHomeScreen.class.getName()));
    }

    @Test
    public void testLoginWorker(){
        onView(withId(R.id.etEmail1)).perform(typeText("worker"));
        onView(withId(R.id.etPassword1)).perform(typeText("worker"),
                closeSoftKeyboard());
        onView(withText("LOGIN")).perform(click());
        intended(hasComponent(WorkerHomeScreen.class.getName()));
    }
}
