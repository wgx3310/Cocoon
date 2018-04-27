package com.reid.cocoon.utils;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.text.TextUtils;
import com.reid.cocoon.common.AppCompat;
import com.reid.cocoon.common.content.Constant;
import com.reid.cocoon.common.content.Tips;
import com.reid.cocoon.data.model.Collection;
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

    public static void shareCollection(Context context, Collection collection){
        if (collection == null || collection.links == null || TextUtils.isEmpty(collection.links.html)) return;

        if (context == null) context = AppCompat.getContext();

        Intent share = new Intent(Intent.ACTION_SEND);
        share.setType("text/plain");
        share.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);

        share.putExtra(Intent.EXTRA_SUBJECT, "Unsplash Collection");
        share.putExtra(Intent.EXTRA_TEXT, collection.links.html + Constant.UNSPLASH_UTM_PARAMETERS);

        context.startActivity(Intent.createChooser(share, "分享到"));
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

    public static String getPhotoColor(Photo photo){
        String color = "#C0C0C0";
        if (photo != null && !TextUtils.isEmpty(photo.color)){
            color = photo.color;
        }
        return color;
    }

    public static Drawable getPhotoColorDrawable(Photo photo){
        String photoColor = getPhotoColor(photo);
        return new ColorDrawable(Color.parseColor(photoColor));
    }

    public static void downloadPhoto(final Context context, final Photo photo){
        if (context == null || photo == null || photo.urls == null) return;

        PhotoDownloadHelper helper = new PhotoDownloadHelper(context, photo);
        helper.setOnDownloadResult(new PhotoDownloadHelper.OnDownloadResult() {
            @Override
            public void onStart() {
                Tips.toast("下载开始");
            }

            @Override
            public void onSuccess() {
                Tips.toast("下载成功");
            }

            @Override
            public void onFail(String error) {
                Tips.toast("下载失败: " + error);
            }
        });
        helper.startDownload();

//        final String url = photo.urls.raw;
//        String filename = photo.id + "_" + "raw.png";
//
//        File dir = context.getExternalFilesDir("images");
//        final String path = new File(dir, filename).getAbsolutePath();
//        FileDownloader.getImpl().create(url).setPath(path)
//                .setWifiRequired(true)
//                .setListener(new FileDownloadSampleListener() {
//
//                    @Override
//                    protected void started(BaseDownloadTask task) {
//                        super.started(task);
//                        Tips.toast("下载开始");
//                    }
//
//                    @Override
//                    protected void completed(BaseDownloadTask task) {
//                        super.completed(task);
//                        Tips.toast("下载成功，请查看" + path);
//                    }
//
//                    @Override
//                    protected void error(BaseDownloadTask task, Throwable e) {
//                        super.error(task, e);
//                        Tips.toast("下载失败");
//                    }
//                }).start();

    }
}
