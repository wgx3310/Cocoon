package com.reid.permission.listener;

import android.support.annotation.NonNull;

public interface IPermissionCallback {

    void onRequestPermissionsResult(@NonNull String[] permissions, @NonNull int[] grantResults);
}
