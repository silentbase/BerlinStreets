package com.example.berlinstreets.berlinView;

import androidx.test.espresso.Espresso;
import androidx.test.ext.junit.rules.ActivityScenarioRule;

import com.example.berlinstreets.R;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

public class LoginActivityTest {

    @Rule
    public ActivityScenarioRule<LoginActivity> activityRule = new ActivityScenarioRule(LoginActivity.class);

    @Before
    public void setUp(){

    }


    /**
     * test view visibility
     */
    @Test
    public void checkViewsIsVisible(){
        Espresso.onView(withId(R.id.emailEdittext)).check(matches(isDisplayed()));
        Espresso.onView(withId(R.id.passwordEdittext)).check(matches(isDisplayed()));
        Espresso.onView(withId(R.id.signInButton)).check(matches(isDisplayed()));
        Espresso.onView(withId(R.id.notRegisteredTextView)).check(matches(isDisplayed()));
    }

    /**
     * test view functionality
     */
    @Test
    public void checkNotRegisteredTextViewFunctionality(){
        Espresso.onView(withId(R.id.notRegisteredTextView)).perform(click());
        Espresso.onView(withId(R.id.registerActivityLayout)).check(matches(isDisplayed()));
    }

    @Test
    public void signInButtonFunctionality(){
        Espresso.onView(withId(R.id.signInButton)).perform(click());
        Espresso.onView(withId(R.id.loginActivityLayout)).check(matches(isDisplayed()));
    }
}