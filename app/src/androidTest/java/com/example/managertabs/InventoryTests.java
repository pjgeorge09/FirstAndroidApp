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
public class InventoryTests{
    @Rule
    public IntentsTestRule<Inventory> inventoryIntentsTestRule
            = new IntentsTestRule<>(Inventory.class, true, true);

    @Test
    public void testInventoryToInventory(){
        onView(withId(R.id.drawer_layout))
                .check(matches(isClosed(Gravity.LEFT)))
                .perform(DrawerActions.open());
        onView(withId(R.id.nav_view)).perform(NavigationViewActions.navigateTo(R.id.nav_inventory));
        intended(hasComponent(Inventory.class.getName()));
    }

    @Test
    public void testInventoryToDonations(){
        onView(withId(R.id.drawer_layout))
                .check(matches(isClosed(Gravity.LEFT)))
                .perform(DrawerActions.open());
        onView(withId(R.id.nav_view)).perform(NavigationViewActions.navigateTo(R.id.nav_donations));
        intended(hasComponent(Donations.class.getName()));
    }

    @Test
    public void testInventoryToDonors(){
        onView(withId(R.id.drawer_layout))
                .check(matches(isClosed(Gravity.LEFT)))
                .perform(DrawerActions.open());
        onView(withId(R.id.nav_view)).perform(NavigationViewActions.navigateTo(R.id.nav_donors));
        intended(hasComponent(Donors.class.getName()));
    }

    @Test
    public void testInventoryToStaff(){
        onView(withId(R.id.drawer_layout))
                .check(matches(isClosed(Gravity.LEFT)))
                .perform(DrawerActions.open());
        onView(withId(R.id.nav_view)).perform(NavigationViewActions.navigateTo(R.id.nav_staff));
        intended(hasComponent(Staff.class.getName()));
    }
}
