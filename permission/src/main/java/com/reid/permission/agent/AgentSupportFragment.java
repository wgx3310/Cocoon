package com.reid.permission.agent;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;

import com.reid.permission.Const;
import com.reid.permission.listener.IPermissionCallback;

import java.util.List;

public class AgentSupportFragment extends Fragment {

    private IPermissionCallback mCallback;
    private List<String> mCachedPermissions;

    public void setCallback(IPermissionCallback callback){
        mCallback = callback;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (mCallback != null){
            mCallback.onRequestPermissionsResult(permissions, grantResults);
            mCallback = null;
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (isAdded() && mCachedPermissions != null && mCachedPermissions.size() > 0){
            requestPermissions(mCachedPermissions.toArray(new String[0]), Const.DEFAULT_REQUEST_CODE);
            mCachedPermissions = null;
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        if (isAdded() && mCachedPermissions != null && mCachedPermissions.size() > 0){
            requestPermissions(mCachedPermissions.toArray(new String[0]), Const.DEFAULT_REQUEST_CODE);
            mCachedPermissions = null;
        }
    }

    public void requestPermissions(List<String> permissions) {
        if (isAdded()){
            requestPermissions(permissions.toArray(new String[0]), Const.DEFAULT_REQUEST_CODE);
        } else {
            mCachedPermissions = permissions;
        }
    }
}
