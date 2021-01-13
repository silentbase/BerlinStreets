package com.example.berlinstreets;

import com.example.berlinstreets.berlinModul.User;
import com.example.berlinstreets.berlinPresenter.LoginPresenter;
import com.squareup.okhttp.mockwebserver.MockWebServer;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class NetworkTest2 {
    User user;

    @Before
    public void createUser() {
        user = new User();
    }

    @Test
    public void sendLoginRequest() {
        MockWebServer mockWebServer = new MockWebServer();

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