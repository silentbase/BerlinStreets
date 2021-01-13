package com.example.berlinstreets.berlinView;

import androidx.test.espresso.Espresso;
import androidx.test.ext.junit.rules.ActivityScenarioRule;

import com.example.berlinstreets.R;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;


public class MainActivityTest {

    @Rule
    public ActivityScenarioRule<MainActivity> activityRule = new ActivityScenarioRule(MainActivity.class);

    @Before
    public void setUp(){

    }

    /**
     * test button visibility
     */
    @Test
    public void checkMapButtonIsVisible(){
        Espresso.onView(withId(R.id.mapButton)).check(matches(isDisplayed()));
    }
    @Test
    public void checkLoginButtonIsVisible(){
        Espresso.onView(withId(R.id.loginButton)).check(matches(isDisplayed()));
    }
    @Test
    public void checkRegisterButtonIsVisible(){
        Espresso.onView(withId(R.id.registerButtonMain)).check(matches(isDisplayed()));
    }

    /**
     * test button functionality
     */

    @Test
    public void checkMapButtonSwitchesToMapActivity(){
        Espresso.onView(withId(R.id.mapButton)).perform(click());
        Espresso.onView(withId(R.id.map)).check(matches(isDisplayed()));

    }
    @Test
    public void checkLoginButtonSwitchesToLoginActivity(){
        Espresso.onView(withId(R.id.loginButton)).perform(click());
        Espresso.onView(withId(R.id.loginLayout)).check(matches(isDisplayed()));
    }
    @Test
    public void checkRegisterButtonSwitchesToRegisterActivity(){
        Espresso.onView(withId(R.id.registerButtonMain)).perform(click());
        Espresso.onView(withId(R.id.registerLinearLayout)).check(matches(isDisplayed()));
    }
    @After
    public void tearDown(){

    }

}