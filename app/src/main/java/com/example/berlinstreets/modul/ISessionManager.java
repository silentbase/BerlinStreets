package com.example.berlinstreets.modul;

public interface ISessionManager {

    /**
     * puts data into shared prefs
     * @param Id
     * @param email
     * @param firstname
     */
    void createSession(String Id, String email, String firstname);

    /**
     * checks if logged in
     * @return
     */
    boolean isLoggedIn();

    /**
     * checks if user is logged in and changes activity from Login - to Map activity
     * if he is
     */
    void checkLoginAppStart();
    /**
     * checks if user is logged in and changes activity from map - to login activity
     * if not
     */
    void checkLoginOnMap();

    /**
     * clears shared prefs and thus logs out user
     */
    void logout();
}
