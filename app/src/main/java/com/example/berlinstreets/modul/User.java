package com.example.berlinstreets.modul;

import android.content.Context;
import android.util.Patterns;

import com.example.berlinstreets.network.UserRequest;

import java.io.StringBufferInputStream;

public class User implements IUser {

    private String firstname;
    private String surename;
    private String gender;
    private String email;
    private String password;

    private Context loginContext;

    /**
     * constructr for login
     *
     * @param email
     * @param password
     */
    public User(String email, String password, Context loginContext) {
        this.email = email;
        this.password = password;
        this.loginContext = loginContext;
    }

    /**
     * constructr for register
     *
     * @param email
     * @param password
     */
    public User(String firstname, String surename, String gender, String email, String password, Context loginContext) {
        this.firstname = firstname;
        this.surename = surename;
        this.gender = gender;
        this.email = email;
        this.password = password;
        this.loginContext = loginContext;
    }

    @Override
    public boolean isLoginDataValid() {

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            return false;
        }

        if (password.length() < 6) {
            return false;
        }

        return true;
    }

    @Override
    public boolean isRegisterDataValid() {

        if (firstname.length() < 1 || surename.length() < 1 || gender.length() < 1) {
            return false;
        }

        int firstnameSpace = firstname.trim().indexOf(" ");
        int surenameSpace = surename.trim().indexOf(" ");
        int genderSpace = gender.trim().indexOf(" ");
        int emailSpace = email.trim().indexOf(" ");
        int passwordSpace = password.trim().indexOf(" ");

        if (firstnameSpace != -1) {
            return false;
        }
        if (surenameSpace != -1) {
            return false;
        }
        if (genderSpace != -1) {
            return false;
        }
        if (emailSpace != -1) {
            return false;
        }
        if (passwordSpace != -1) {
            return false;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            return false;
        }

        if (password.length() < 6) {
            return false;
        }
        return true;
    }

    @Override
    public void sendLoginRequest() {
        UserRequest userRequest = new UserRequest();
        userRequest.loginRequest(email, password, loginContext);
    }

    @Override
    public void sendRegisterRequest() {
        UserRequest userRequest = new UserRequest();
        userRequest.registerRequest(firstname, surename, gender, email, password, loginContext);
    }
}
