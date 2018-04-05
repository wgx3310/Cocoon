package com.reid.cocoon.common.utils;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;

import com.reid.cocoon.common.AppCompat;
import com.reid.cocoon.common.content.SettingKeys;
import com.reid.cocoon.data.model.Photo;
import com.reid.cocoon.ui.activity.AboutActivity;
import com.reid.cocoon.ui.activity.PhotoDetailActivity;
import com.reid.cocoon.ui.activity.SettingsActivity;
import com.reid.cocoon.ui.activity.WebActivity;

public class IntentHelper {

    public static void goPhotoDetail(Context context, Photo photo){
        if (photo == null) return;

        if (context == null) context = AppCompat.getContext();

        Intent intent = new Intent(context, PhotoDetailActivity.class);
        intent.putExtra(SettingKeys.KEY_PHOTO, photo);
        context.startActivity(intent);
    }

    public static void goSettings(Context context){
        if (context == null) context = AppCompat.getContext();

        Intent setting = new Intent(context, SettingsActivity.class);
        context.startActivity(setting);
    }

    public static void goAbout(Context context){
        if (context == null) context = AppCompat.getContext();

        Intent about = new Intent(context, AboutActivity.class);
        context.startActivity(about);
    }

    public static void goWeb(Context context, String title, String uri){
        if (TextUtils.isEmpty(uri)) return;

        if (context == null) context = AppCompat.getContext();

        Intent webIntent = new Intent(context, WebActivity.class);
        webIntent.putExtra(SettingKeys.KEY_TITLE, title);
        webIntent.putExtra(SettingKeys.KEY_URL, uri);
        context.startActivity(webIntent);
    }
}
