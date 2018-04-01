package com.reid.cocoon.common.utils;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;

import com.reid.cocoon.common.AppCompat;
import com.reid.cocoon.common.content.SettingKeys;
import com.reid.cocoon.ui.activity.WebActivity;

public class IntentHelper {

    public static void goWeb(Context context, String title, String uri){
        if (TextUtils.isEmpty(uri)) return;

        if (context == null) context = AppCompat.getContext();

        Intent webIntent = new Intent(context, WebActivity.class);
        webIntent.putExtra(SettingKeys.KEY_TITLE, title);
        webIntent.putExtra(SettingKeys.KEY_URL, uri);
        context.startActivity(webIntent);
    }
}
