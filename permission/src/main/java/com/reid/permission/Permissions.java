package com.reid.permission;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;

import com.reid.permission.manager.ActivityRequestManager;
import com.reid.permission.manager.LRequestManager;
import com.reid.permission.manager.RequestManager;
import com.reid.permission.manager.SupportRequestManager;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class Permissions {

    public static RequestManager with(Context context){
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M){
            return new LRequestManager(context);
        } else if (context instanceof AppCompatActivity){
            return new SupportRequestManager((AppCompatActivity) context);
        } else if (context instanceof Activity){
            return new ActivityRequestManager((Activity) context);
        }

        throw new IllegalArgumentException("Context is illegal");
    }

    public static boolean hasPermissions(Context context, String... perms){

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M){
            return true;
        }

        if (context == null || perms == null){
            throw new IllegalArgumentException("Can't check permissions for null context or perms");
        }

        for (String perm : perms){
            if (ContextCompat.checkSelfPermission(context, perm)!= PackageManager.PERMISSION_GRANTED){
                return false;
            }
        }

        return true;
    }

    public static List<String> getDeniedPermissions(Context context, String... permissions){
        List<String> deniedPermissions = new ArrayList<>();

        try{
            if (permissions == null || permissions.length == 0) return deniedPermissions;

            for (String perm : permissions){
                if (!hasPermissions(context, perm)){
                    deniedPermissions.add(perm);
                }
            }
        } catch (Throwable t){ }
        return deniedPermissions;
    }

    public static List<String> getRationalePermissions(Context context, String... permissions){
        List<String> rationalePermissions = new ArrayList<>();

        try{
            if (permissions == null || permissions.length == 0) return rationalePermissions;

            for (String perm : permissions){
                if (isShowRationalePermission(context, perm)){
                    rationalePermissions.add(perm);
                }
            }
        } catch (Throwable t){

        }
        return rationalePermissions;
    }

    public static boolean isShowRationalePermission(Context context, String permission) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) return false;

        try {
            PackageManager packageManager = context.getPackageManager();
            Class<?> pkManagerClass = packageManager.getClass();
            Method method = pkManagerClass.getMethod("shouldShowRequestPermissionRationale", String.class);
            if (!method.isAccessible()) method.setAccessible(true);
            return (boolean) method.invoke(packageManager, permission);
        } catch (Throwable ignored) {
            return false;
        }
    }
}
