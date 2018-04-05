package com.reid.permission.manager;

import android.content.Context;

public class LRequestManager extends RequestManager<Context> {

    public LRequestManager(Context host) {
        super(host);
    }

    @Override
    public void request() {
        if (mResult != null){
            mResult.onGranted(mPermissions);
        }
    }
}
