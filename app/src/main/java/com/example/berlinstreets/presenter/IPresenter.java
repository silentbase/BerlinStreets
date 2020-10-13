package com.example.berlinstreets.presenter;

public interface IPresenter {

    /**
     * login-parameters: email,password
     * Register-parameters: firstname, surename, gender, email, password
     * @param data
     */
    void setData(String... data);
}
