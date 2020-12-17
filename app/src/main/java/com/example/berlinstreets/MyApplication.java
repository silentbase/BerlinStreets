package com.example.berlinstreets;

import android.content.Context;
import android.os.Bundle;

import com.example.berlinstreets.berlinView.LoginActivity;

public class MyApplication extends LoginActivity {

    private static Context context;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MyApplication.context = getApplicationContext();
    }

    public static Context getAppContext() {
        return MyApplication.context;
    }
}