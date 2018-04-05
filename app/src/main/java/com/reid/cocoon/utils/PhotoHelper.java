package com.reid.cocoon.utils;

import android.text.TextUtils;

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
}
