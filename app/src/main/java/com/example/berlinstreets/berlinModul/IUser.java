package com.example.berlinstreets.berlinModul;

public interface IUser {

    /**
     * check whether login data is valid or not
     * @return
     */
    boolean isLoginDataValid();

    /**
     * check whether register data is valid or not
     * @return
     */
    boolean isRegisterDataValid();
    /**
     * send PostRequest to check if email/password combination already exists
     */
    void sendLoginRequest();

    /**
     * send PostRequest to save new user to db
     */
    void sendRegisterRequest();
}
