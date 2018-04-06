package com.reid.cocoon.common.utils;

import android.view.View;

public class ViewHelper {
    public static void showView(View... views){
        if (views == null || views.length == 0) return;

        for (View view : views){
            if (view != null) {
                view.setVisibility(View.VISIBLE);
            }
        }
    }

    public static void hideView(View... views){
        if (views == null || views.length == 0) return;

        for (View view : views){
            if (view != null) {
                view.setVisibility(View.GONE);
            }
        }
    }
}
