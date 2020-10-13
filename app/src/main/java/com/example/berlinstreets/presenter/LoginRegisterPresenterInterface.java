package com.example.berlinstreets.presenter;

public interface LoginRegisterPresenterInterface {

    /**
     * login-parameters: email,password
     * Register-parameters: firstname, surename, gender, email, password
     * @param data
     */
    void dataReception(String... data) throws Exception;
}
