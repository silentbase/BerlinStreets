package com.example.berlinstreets.berlinModul;

import android.content.Context;
import android.util.Patterns;

import com.example.berlinstreets.berlinNetwork.UserRequest;

public class User implements IUser {

    private String ID;
    private String firstname;
    private String surename;
    private String gender;
    private String email;
    private String password;

    private Context context;

    /**
     * constructr for login
     *
     * @param email
     * @param password
     */
    public User(String email, String password, Context context) {
        this.email = email;
        this.password = password;
        this.context = context;
    }

    /**
     * constructr for register
     *
     * @param email
     * @param password
     */
    public User(String firstname, String surename, String gender, String email, String password, Context context) {
        this.firstname = firstname;
        this.surename = surename;
        this.gender = gender;
        this.email = email;
        this.password = password;
        this.context = context;
    }

    /**
     * empty constructor for jUnit tests
     */
    public User() {
    }

    @Override
    public boolean isLoginDataValid() {

        if (!Patterns.EMAIL_ADDRESS.matcher(email.trim()).matches()) {
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

        if (firstnameSpace != -1 || surenameSpace != -1 || genderSpace != -1 || emailSpace != -1 || passwordSpace != -1) {
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
        userRequest.loginRequest(email, password, context);
    }

    @Override
    public void sendRegisterRequest() {
        UserRequest userRequest = new UserRequest();
        userRequest.registerRequest(firstname, surename, gender, email, password, context);
    }

    @Override
    public String toString() {
        return "User{" +
                "ID='" + ID + '\'' +
                ", firstname='" + firstname + '\'' +
                ", surename='" + surename + '\'' +
                ", gender='" + gender + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    /*************************
     GETTERS
     *************************/
    public String getID() {
        return ID;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getSurename() {
        return surename;
    }

    public String getGender() {
        return gender;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    /*************************
     SETTERS
     *************************/

    public void setID(String ID) {
        this.ID = ID;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setSurename(String surename) {
        this.surename = surename;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setContext(Context context) {
        this.context = context;
    }
}
