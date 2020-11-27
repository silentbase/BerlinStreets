package com.example.berlinstreets.view;

public interface IView {

    /**
     * creates a Toast, if login or register failed
     */
    void loginFailedAlert(String msg);

    /**
     * switches the activity
     */
    void switchActivity();

    /**
     * save Data we receive from the network into shared preferences
     * @param ID
     * @param email
     */
    void saveData(String ID, String email, String firstname);
}
