package com.example.berlinstreets.berlinPresenter;

public interface IPresenter {

    /**
     * login-parameters: email,password
     * Register-parameters: firstname, surename, gender, email, password
     * Marker-parameters: userID, title, street, houseNumber, postalcode
     * @param data
     */
    void setData(String... data);
}
