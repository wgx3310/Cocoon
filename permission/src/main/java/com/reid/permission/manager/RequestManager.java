package com.reid.permission.manager;

import android.content.Context;
import android.support.annotation.NonNull;

import com.reid.permission.Permissions;
import com.reid.permission.listener.IPermissionCallback;
import com.reid.permission.listener.OnResult;

import java.util.ArrayList;
import java.util.List;


public abstract class RequestManager<T extends Context>{

    protected T mHost;

    protected String[] mPermissions;

    protected String mRationale;

    protected OnResult mResult;

    public RequestManager(T host){
        mHost = host;
    }

    public RequestManager permission(String... permissions){
        this.mPermissions = permissions;
        return this;
    }

    public RequestManager rationale(String rationale){
        mRationale = rationale;
        return this;
    }

    public RequestManager result(OnResult result){
        mResult = result;
        return this;
    }

    public abstract void request();

    protected IPermissionCallback mCallback = new IPermissionCallback() {
        @Override
        public void onRequestPermissionsResult(@NonNull String[] permissions, @NonNull int[] grantResults) {
            List<String> deniedPermissions = Permissions.getDeniedPermissions(mHost, permissions);
            if (deniedPermissions == null || deniedPermissions.size() == 0){
                if (mResult != null){
                    mResult.onGranted(mPermissions);
                }
            } else if (permissions.length == mPermissions.length){
                if (mResult != null){
                    mResult.onDenied(mPermissions);
                }
            } else {
                List<String> grantedPermissions = new ArrayList<>();
                for (String perm : mPermissions){
                    if (!deniedPermissions.contains(perm)){
                        grantedPermissions.add(perm);
                    }
                }

                if (mResult != null){
                    mResult.onGranted(grantedPermissions.toArray(new String[0]));
                } else {
                    mResult.onDenied(deniedPermissions.toArray(new String[0]));
                }
            }
        }
    };
}
