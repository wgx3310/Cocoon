package com.reid.cocoon;

import android.app.Application;

import com.reid.cocoon.common.AppCompat;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        AppCompat.init(getApplicationContext());
    }
}
