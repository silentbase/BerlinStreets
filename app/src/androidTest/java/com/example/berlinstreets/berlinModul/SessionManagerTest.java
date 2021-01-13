package com.example.berlinstreets.berlinModul;

import android.content.Context;

import androidx.test.espresso.Espresso;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import com.example.berlinstreets.R;
import com.example.berlinstreets.berlinView.MainActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.*;

@RunWith(AndroidJUnit4.class)
public class SessionManagerTest {

    Context appContext;
    SessionManager sessionManager;

    @Rule
    public ActivityScenarioRule<MainActivity> activityRule= new ActivityScenarioRule(MainActivity.class);

    @Test
    public void useAppContext() {
        // Context of the app under test.
        appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        assertEquals("com.example.berlinstreets", appContext.getPackageName());
    }

    @Test
    public void testLogoutButtonVisibility(){
        Espresso.onView(withId(R.id.logoutTextView)).perform(click());
        //Espresso.onView(withId(R.id.logoutTextView)).check(matches(isDisplayed()));
    }
}