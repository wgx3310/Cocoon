package com.reid.cocoon.utils;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;

import com.reid.cocoon.common.AppCompat;
import com.reid.cocoon.common.content.Constant;
import com.reid.cocoon.data.model.Photo;
import com.reid.cocoon.data.model.Urls;

public class PhotoHelper {

    public static String choosePhotoUrl(Photo photo){
        if (photo == null || photo.urls == null) return "";

        Urls urls = photo.urls;
        String url = urls.regular;
        if (TextUtils.isEmpty(url)){
            url = urls.full;
        }
        if (TextUtils.isEmpty(url)){
            url = urls.small;
        }
        if (TextUtils.isEmpty(url)){
            url = urls.thumb;
        }
        if (TextUtils.isEmpty(url)){
            url = urls.raw;
        }
        return url;
    }

    public static String choosePhotoFullUrl(Photo photo){
        if (photo == null || photo.urls == null) return "";

        Urls urls = photo.urls;
        String url = urls.full;
        if (TextUtils.isEmpty(url)){
            url = urls.regular;
        }
        if (TextUtils.isEmpty(url)){
            url = urls.raw;
        }
        if (TextUtils.isEmpty(url)){
            url = urls.small;
        }
        if (TextUtils.isEmpty(url)){
            url = urls.thumb;
        }
        return url;
    }

    public static void sharePhoto(Context context, Photo photo){
        if (photo == null || photo.links == null || TextUtils.isEmpty(photo.links.html)) return;

        if (context == null) context = AppCompat.getContext();

        Intent share = new Intent(Intent.ACTION_SEND);
        share.setType("text/plain");
        share.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);

        share.putExtra(Intent.EXTRA_SUBJECT, "Unsplash Image");
        share.putExtra(Intent.EXTRA_TEXT, photo.links.html + Constant.UNSPLASH_UTM_PARAMETERS);

        context.startActivity(Intent.createChooser(share, "分享到"));
    }

    public static void openInBrowser(Context context, Photo photo){
        if (photo == null || photo.links == null || TextUtils.isEmpty(photo.links.html)) return;

        if (context == null) context = AppCompat.getContext();

        Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(photo.links.html + Constant.UNSPLASH_UTM_PARAMETERS));
        context.startActivity(i);
    }
}
