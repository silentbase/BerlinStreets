package com.example.berlinstreets.berlinModul;

import android.content.Context;

import androidx.test.platform.app.InstrumentationRegistry;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class UserTest {

    private User user;

    @Before
    public void createUser() {
        this.user = new User();
    }

    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        assertEquals("com.example.berlinstreets", appContext.getPackageName());
    }
    /**
     * test RegisterValidation
     */
    //test if true
    @Test
    public void testUserRegisterDataValidation() {
        user.setFirstname("Suheib");
        user.setSurename("Al-Khatib");
        user.setEmail("suheib@mail.de");
        user.setGender("male");
        user.setPassword("pass123");

        assertTrue(user.isRegisterDataValid());
    }

    //test if false
    @Test
    public void testUserRegisterDataValidation2() {
        user.setFirstname("Suheib");
        user.setSurename("Al-Khatib");
        user.setEmail("suheib@mail.de");
        user.setGender("male");
        user.setPassword("123");

        assertFalse(user.isRegisterDataValid());
    }

    /**
     * test loginValidation
     */
    //test if true
    @Test
    public void testUserLoginDataValidation() {
        user.setFirstname("Suheib");
        user.setSurename("Al-Khatib");
        user.setEmail("suheib@mail.de");
        user.setGender("male");
        user.setPassword("pass123");

        assertTrue(user.isLoginDataValid());
    }

    //test if false
    @Test
    public void testUserLoginDataValidation2() {
        user.setFirstname("Suheib");
        user.setSurename("Al-Khatib");
        user.setEmail("suheib@mail.de");
        user.setGender("male");
        user.setPassword("");

        assertFalse(user.isLoginDataValid());
    }
}