package com.example.berlinstreets;

import android.content.Context;

import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.example.berlinstreets.modul.User;
import com.example.berlinstreets.network.UserRequest;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    User user;

    @Before
    public void createUser() {
        user = new User();
    }

    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        assertEquals("com.example.berlinstreets", appContext.getPackageName());
    }

    @Test
    public void sendLoginRequest() {
        user.setEmail("suheib@gmail.com");
        user.setPassword("password123");

        user.setLoginContext(MyApplication.getAppContext());
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        user.sendLoginRequest();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        assertNotEquals(null,user.getID());
    }
    @Test
    public void sendLoginRequest2() {
        user.setEmail("suheib@gmail.com");
        user.setPassword("password123");

        UserRequest userRequest = new UserRequest();
        userRequest.loginRequest("suheib@gmail.com","password123",MyApplication.getAppContext());
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        assertNotEquals(null,user.getID());
    }
}