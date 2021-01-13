package com.example.berlinstreets.berlinModul;

import android.content.Context;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

/**
 * Note to myself: The reason why this test had to be an instrumented unit test instead of a local one is
 * cause of the usage of the (android) Pattern class.
 */
@RunWith(AndroidJUnit4.class)
public class UserTest {

    private User user;

    @Before
    public void setUp() {
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
    //test if true password
    @Test
    public void testUserRegisterDataValidation() {
        user.setFirstname("Suheib");
        user.setSurename("Al-Khatib");
        user.setEmail("suheib@mail.de");
        user.setGender("male");
        user.setPassword("pass123");

        assertTrue(user.isRegisterDataValid());
    }

    //test if false password
    @Test
    public void testUserRegisterDataValidation2() {
        user.setFirstname("Suheib");
        user.setSurename("Al-Khatib");
        user.setEmail("suheib@mail.de");
        user.setGender("male");
        //password length has to be at least 6
        user.setPassword("123");

        assertFalse(user.isRegisterDataValid());
    }

    //test if true email
    @Test
    public void testUserRegisterDataValidation3() {
        user.setFirstname("Suheib");
        user.setSurename("Al-Khatib");
        user.setEmail("suheib@mail.de");
        user.setGender("male");
        user.setPassword("pass123");

        assertTrue(user.isRegisterDataValid());
    }

    //test if false email
    @Test
    public void testUserRegisterDataValidation4() {
        user.setFirstname("Suheib");
        user.setSurename("Al-Khatib");
        user.setEmail("suheibmail.de");
        user.setGender("male");
        user.setPassword("pass123");

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