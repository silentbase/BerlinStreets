package com.example.berlinstreets.network;

import android.content.Context;

public interface UserRequestInterface {

    /**
     * @param firstname
     * @param surename
     * @param gender
     * @param email
     * @param password
     * @param loginContext
     */
    void registerRequest(String firstname, String surename, String gender, String email, String password, Context loginContext);

    /**
     * @param email
     * @param password
     * @param loginContext
     */
    void loginRequest(String email, String password, Context loginContext);
}
