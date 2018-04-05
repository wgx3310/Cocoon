package com.reid.permission.manager;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.os.Build;
import android.support.annotation.RequiresApi;

import com.reid.permission.Const;
import com.reid.permission.Permissions;
import com.reid.permission.agent.AgentFragment;

import java.util.List;

public class ActivityRequestManager extends RequestManager<Activity> {

    public ActivityRequestManager(Activity host) {
        super(host);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void request() {
        if (mResult == null) return;

        List<String> deniedPermissions = Permissions.getDeniedPermissions(mHost, mPermissions);
        if (deniedPermissions == null || deniedPermissions.size() == 0){
            if (mResult != null){
                mResult.onGranted(mPermissions);
            }
            return;
        }

        final List<String> rationalePermissions = Permissions.getRationalePermissions(mHost, deniedPermissions.toArray(new String[0]));
        if (rationalePermissions != null && rationalePermissions.size() > 0){
            //TODO show rational dialog
            AlertDialog.Builder builder = new AlertDialog.Builder(mHost);
            builder.setMessage(mRationale)
                    .setCancelable(false)
                    .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            requestPermissionsDirectly(rationalePermissions);
                        }
                    }).setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            }).create().show();
        } else {
            requestPermissionsDirectly(deniedPermissions);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void requestPermissionsDirectly(List<String> permissions) {
        AgentFragment agentFragment;
        Fragment fragment = mHost.getFragmentManager().findFragmentByTag(Const.TAG_FRAGMENT);
        if (fragment != null){
            agentFragment = (AgentFragment) fragment;
        } else {
            agentFragment = new AgentFragment();
            mHost.getFragmentManager().beginTransaction().add(agentFragment, Const.TAG_FRAGMENT).commitAllowingStateLoss();
        }

        agentFragment.setCallback(mCallback);
        agentFragment.requestPermissions(permissions.toArray(new String[0]), Const.DEFAULT_REQUEST_CODE);
    }

}
