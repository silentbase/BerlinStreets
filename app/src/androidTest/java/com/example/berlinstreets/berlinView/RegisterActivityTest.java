package com.example.berlinstreets.berlinView;

import androidx.test.espresso.Espresso;
import androidx.test.ext.junit.rules.ActivityScenarioRule;

import com.example.berlinstreets.R;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

public class RegisterActivityTest {

    @Rule
    public ActivityScenarioRule<RegisterActivity> activityRule = new ActivityScenarioRule(RegisterActivity.class);

    @Before
    public void setUp(){

    }

    /**
     * test view visibility
     */
    @Test
    public void checkViewsIsVisible(){
        Espresso.onView(withId(R.id.emailEditTextRegister)).check(matches(isDisplayed()));
        Espresso.onView(withId(R.id.passwordEditTextRegister)).check(matches(isDisplayed()));
        Espresso.onView(withId(R.id.surenameEditText)).check(matches(isDisplayed()));
        Espresso.onView(withId(R.id.firstnameEditText)).check(matches(isDisplayed()));
        Espresso.onView(withId(R.id.genderEditText)).check(matches(isDisplayed()));
        Espresso.onView(withId(R.id.registerButton)).check(matches(isDisplayed()));


    }

}