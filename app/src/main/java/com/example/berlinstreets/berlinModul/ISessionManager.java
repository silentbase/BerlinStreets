package com.example.berlinstreets.berlinModul;

import java.util.HashMap;

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
     * clears shared prefs and thus logs out user
     */
    void logout();

    /**
     * returns user data
     * @return
     */
    HashMap<String, String> getUserData();
}
