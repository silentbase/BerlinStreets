package com.example.berlinstreets.berlinNetwork;

import android.content.Context;

public interface IUserRequest {

    /**
     * sends a post-request to register a user
     * @param firstname
     * @param surename
     * @param gender
     * @param email
     * @param password
     * @param loginContext
     */
    void registerRequest(String firstname, String surename, String gender, String email, String password, Context loginContext);

    /**
     * sends a get request to login a user
     * @param email
     * @param password
     * @param loginContext
     */
    void loginRequest(String email, String password, Context loginContext);
}
