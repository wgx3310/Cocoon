package com.reid.permission.manager;

import android.content.DialogInterface;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;

import com.reid.permission.Const;
import com.reid.permission.Permissions;
import com.reid.permission.agent.AgentSupportFragment;

import java.util.List;

public class SupportRequestManager extends RequestManager<AppCompatActivity> {

    public SupportRequestManager(AppCompatActivity host) {
        super(host);
    }

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

    private void requestPermissionsDirectly(final List<String> permissions) {
        final AgentSupportFragment agentFragment;
        Fragment fragment = mHost.getSupportFragmentManager().findFragmentByTag(Const.TAG_FRAGMENT);
        if (fragment != null){
            agentFragment = (AgentSupportFragment) fragment;
        } else {
            agentFragment = new AgentSupportFragment();
            mHost.getSupportFragmentManager().beginTransaction()
                    .add(agentFragment, Const.TAG_FRAGMENT)
                    .commit();
        }

        agentFragment.setCallback(mCallback);
        agentFragment.requestPermissions(permissions);
    }
}
