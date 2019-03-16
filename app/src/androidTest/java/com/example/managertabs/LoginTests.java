package com.example.managertabs;

import android.support.test.runner.AndroidJUnit4;
import android.support.test.rule.ActivityTestRule;

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
    @Rule
    public ActivityTestRule<Login> loginActivityTestRule
            = new ActivityTestRule<>(Login.class);

    @Test
    public void testLogin(){
        onView(withId(R.id.etEmail1)).perform(typeText("manager"));
        onView(withId(R.id.etPassword1)).perform(typeText("manager"),
                closeSoftKeyboard());
        onView(withText("LOGIN")).perform(click());
    }
}
