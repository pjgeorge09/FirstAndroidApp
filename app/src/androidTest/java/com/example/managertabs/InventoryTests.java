package com.example.managertabs;

        import android.content.Intent;
        import android.support.test.espresso.Espresso;
        import android.support.test.espresso.contrib.DrawerActions;
        import android.support.test.espresso.contrib.NavigationViewActions;
        import android.support.test.runner.AndroidJUnit4;
        import android.support.test.espresso.intent.rule.IntentsTestRule;
        import android.view.Gravity;
        import org.junit.Before;
        import org.junit.After;
        import org.junit.Assert.*;


        import com.example.managertabs.Donation.DonationsActivity;
        import com.example.managertabs.EmployeeFiles.EmployeeActivity;
        import com.example.managertabs.Inventory;

        import org.junit.Before;
        import org.junit.Rule;
        import org.junit.runner.RunWith;
        import org.junit.Test;

        import static android.support.test.espresso.intent.Intents.*;
        import static android.support.test.espresso.intent.matcher.IntentMatchers.*;
        import static android.support.test.espresso.Espresso.onView;
        import static android.support.test.espresso.matcher.ViewMatchers.assertThat;
        import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
        import static android.support.test.espresso.matcher.ViewMatchers.withId;
        import static android.support.test.espresso.assertion.ViewAssertions.matches;
        import static android.support.test.espresso.contrib.DrawerMatchers.isClosed;
        import static android.support.test.espresso.action.ViewActions.click;
        import static android.support.test.espresso.matcher.ViewMatchers.withText;
        import static org.junit.Assert.assertEquals;
        import static org.junit.Assert.assertTrue;

@RunWith(AndroidJUnit4.class)
public class InventoryTests{

    Item item = new Item();

    @Before
    public void setUp(){


        item.setName("Chunky Soup");
        item.setType("Can");
        item.setThreshold(100);
        item.setQuantity(181);
        item.setLocation("D18");
    }
    // Sets the startup screen for this test file to the inventory screen
    @Rule
    public IntentsTestRule<Inventory> inventoryIntentsTestRule
            = new IntentsTestRule<>(Inventory.class, true, true);

    // Tests navigation from the inventory screen to the inventory screen
    @Test
    public void testInventoryToInventory() {
        // Checks to make sure the navigation drawer is closed
        // and then opens it
        onView(withId(R.id.drawer_layout))
                .check(matches(isClosed(Gravity.LEFT)))
                .perform(DrawerActions.open());

        // Clicks the inventory tab and navigates to it
        onView(withId(R.id.nav_view)).perform(NavigationViewActions.navigateTo(R.id.nav_inventory));

        // Checks to see that the correct screen is opened
    }

    // Tests navigation from the inventory screen to the donations screen
    @Test
    public void testInventoryToDonations(){
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

    // Tests navigation from the inventory screen to the donors screen
    @Test
    public void testInventoryToDonors(){
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

    // Tests navigation from the inventory screen to the staff screen
    @Test
    public void testInventoryToStaff(){
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

    @Test
    public void getNameTest1(){
        assertEquals("Chunky Soup", item.getName());
    }
    @Test
    public void getNameTest2(){
        item.setName("getNameTest2");
        assertEquals("getNameTest2", item.getName());
    }
    @Test
    public void setNameTest1(){
        item.setName("12345");
        assertEquals("12345", item.getName());
    }
    @Test
    public void setNameTest2(){
        item.setName("A really really unnecessarily long name that absolutely no one should ever use for any reason, ever.");
        assertEquals("A really really unnecessarily long name that absolutely no one should ever use for any reason, ever.", item.getName());
    }
    @Test
    public void getTypeTest1(){
        assertEquals("Can", item.getType());
    }
    @Test
    public void getTypeTest2(){
        item.setType("getNameTest2");
        assertEquals("getNameTest2", item.getType());
    }
    @Test
    public void setTypeTest1(){
        item.setType("12345"); //Integer write test
        assertEquals("12345", item.getType());
    }
    @Test
    public void setTypeTest2(){
        item.setType("A really really unnecessarily long name that absolutely no one should ever use for any reason, ever.");
        assertEquals("A really really unnecessarily long name that absolutely no one should ever use for any reason, ever.", item.getType());
    }
    @Test
    public void getThresholdTest1(){
        assertEquals(100, item.getThreshold());
    }
    @Test
    public void getThresholdTest2(){
        item.setThreshold(50);
        assertEquals(50, item.getThreshold());
    }
    @Test
    public void setThresholdTest1(){
        item.setThreshold(100000);
        assertEquals(100000, item.getThreshold());
    }
    @Test
    public void setThresholdTest2(){
        item.setThreshold((int)12.5);   //should not work
        assertEquals(12, item.getThreshold());
    }
    @Test
    public void getQuantityTest1(){
        assertEquals(181, item.getQuantity());
    }
    @Test
    public void getQuantityTest2(){
        item.setQuantity(200);
        assertEquals(200,item.getQuantity());
    }
    @Test
    public void setQuantityTest1(){
        item.setQuantity((int)12345.11);
        assertEquals(12345, item.getQuantity());
    }

    @Test
    public void getLocationTest1(){
        assertTrue(item.getLocation().equalsIgnoreCase("D18"));
    }
    @Test
    public void getLocationTest2(){
        item.setLocation("A144");
        assertTrue(item.getLocation().equalsIgnoreCase("a144"));
    }
    @Test
    public void setLocationTest1(){
        item.setLocation("12345");
        assertEquals("12345", item.getLocation());
    }
    @Test
    public void setLocationTest2(){
        item.setLocation("A really really unnecessarily long name that absolutely no one should ever use for any reason, ever.");
        assertEquals("A really really unnecessarily long name that absolutely no one should ever use for any reason, ever.", item.getLocation());
    }
}
