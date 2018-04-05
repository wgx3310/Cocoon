package com.reid.permission.listener;


public interface OnResult {

    void onGranted(String[] perms);

    void onDenied(String[] perms);
}
