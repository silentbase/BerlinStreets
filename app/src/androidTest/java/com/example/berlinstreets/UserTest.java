package com.example.berlinstreets;

import android.content.Context;
import android.content.ContextWrapper;

import com.example.berlinstreets.MyApplication;
import com.example.berlinstreets.modul.User;
import com.example.berlinstreets.presenter.LoginPresenter;
import com.example.berlinstreets.view.LoginActivity;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class UserTest {
    User user;

    @Before
    public void createUser() {
        user = new User();
    }

    @Test
    public void sendLoginRequest() {
        user.setEmail("suheib@gmail.com");
        user.setPassword("password123");


        user.sendLoginRequest();

        assertNotEquals(user.getID(), null);
    }

    @Test
    public void sendLoginRequest2() {
        LoginPresenter loginPresenter;

        assertNotEquals(user.getID(), null);
    }

    @Test
    public void sendRegisterRequest() {
    }
}