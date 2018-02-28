package com.reid.cocoon.common;

import android.content.Context;

public class AppCompat {
    private static Context sContext;

    public static Context getContext(){
        return sContext;
    }

    public static void init(Context context){
        sContext = context.getApplicationContext();
    }
}
