package com.example.berlinstreets.berlinNetwork.registerTests;


import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.ViewInteraction;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import com.example.berlinstreets.R;
import com.example.berlinstreets.berlinView.MainActivity;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

@LargeTest
@RunWith(AndroidJUnit4.class)

/**
 *  --NOTE--
 * These tests can only be executed correctly,
 * if no one is logged in
 */
public class RegisterSucceeded {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    /**
     * This test can only be executed correctly once.
     * For a second successful execution change the email that needs to be registered
     */
    @Test
    public void registerSucceeded() {
        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        try {
            Thread.sleep(700);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ViewInteraction appCompatButton = onView(
                allOf(withId(R.id.registerButtonMain), withText("register"),
                        childAtPosition(
                                allOf(withId(R.id.mainLayout),
                                        childAtPosition(
                                                withId(android.R.id.content),
                                                0)),
                                3),
                        isDisplayed()));
        appCompatButton.perform(click());

        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        try {
            Thread.sleep(700);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ViewInteraction appCompatEditText = onView(
                allOf(withId(R.id.firstnameEditText),
                        childAtPosition(
                                allOf(withId(R.id.registerLinearLayout),
                                        childAtPosition(
                                                withId(R.id.registerActivityLayout),
                                                0)),
                                0),
                        isDisplayed()));
        appCompatEditText.perform(replaceText("s"), closeSoftKeyboard());

        ViewInteraction appCompatEditText2 = onView(
                allOf(withId(R.id.surenameEditText),
                        childAtPosition(
                                allOf(withId(R.id.registerLinearLayout),
                                        childAtPosition(
                                                withId(R.id.registerActivityLayout),
                                                0)),
                                1),
                        isDisplayed()));
        appCompatEditText2.perform(replaceText("s"), closeSoftKeyboard());

        ViewInteraction appCompatEditText3 = onView(
                allOf(withId(R.id.genderEditText),
                        childAtPosition(
                                allOf(withId(R.id.registerLinearLayout),
                                        childAtPosition(
                                                withId(R.id.registerActivityLayout),
                                                0)),
                                2),
                        isDisplayed()));
        appCompatEditText3.perform(replaceText("s"), closeSoftKeyboard());

        ViewInteraction appCompatEditText4 = onView(
                allOf(withId(R.id.emailEditTextRegister),
                        childAtPosition(
                                allOf(withId(R.id.registerLinearLayout),
                                        childAtPosition(
                                                withId(R.id.registerActivityLayout),
                                                0)),
                                3),
                        isDisplayed()));
        //change the e-mail if u wanna execute the test multiple times (e.g. test2@gmail.de)
        appCompatEditText4.perform(replaceText("test@gmail.de"), closeSoftKeyboard());

        ViewInteraction appCompatEditText5 = onView(
                allOf(withId(R.id.passwordEditTextRegister),
                        childAtPosition(
                                allOf(withId(R.id.registerLinearLayout),
                                        childAtPosition(
                                                withId(R.id.registerActivityLayout),
                                                0)),
                                4),
                        isDisplayed()));
        appCompatEditText5.perform(replaceText("111111"), closeSoftKeyboard());

        ViewInteraction appCompatButton2 = onView(
                allOf(withId(R.id.registerButton), withText("register"),
                        childAtPosition(
                                allOf(withId(R.id.registerActivityLayout),
                                        childAtPosition(
                                                withId(android.R.id.content),
                                                0)),
                                1),
                        isDisplayed()));
        appCompatButton2.perform(click());

        try {
            Thread.sleep(2500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Espresso.onView(withId(R.id.mainLayout)).check(matches(isDisplayed()));

    }

    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }
}
