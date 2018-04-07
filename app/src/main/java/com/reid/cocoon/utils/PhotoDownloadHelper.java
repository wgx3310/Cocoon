package com.reid.cocoon.utils;

import android.Manifest;
import android.content.Context;
import android.text.TextUtils;
import android.util.Pair;

import com.liulishuo.filedownloader.BaseDownloadTask;
import com.liulishuo.filedownloader.FileDownloadSampleListener;
import com.liulishuo.filedownloader.FileDownloader;
import com.reid.cocoon.common.utils.Logger;
import com.reid.cocoon.data.http.loader.PhotoLoader;
import com.reid.cocoon.data.model.Item;
import com.reid.cocoon.data.model.Photo;
import com.reid.permission.Permissions;
import com.reid.permission.listener.OnResult;

import java.io.File;

import io.reactivex.functions.Consumer;

public class PhotoDownloadHelper implements OnResult {

    private static final String TAG = "PhotoDownloadHelper";

    private Context mContext;
    private Photo mPhoto;
    private String mFileName;
    private String mPath;
    private String mRealUrl;

    private OnDownloadResult mOnDownloadResult;

    public void setOnDownloadResult(OnDownloadResult onDownloadResult) {
        this.mOnDownloadResult = onDownloadResult;
    }

    public PhotoDownloadHelper(Context context, Photo photo){
        mContext = context;
        mPhoto = photo;
    }

    public static Pair<String, String> getDownloadUrlAndDefinition(Photo photo){
        Pair<String, String> urlAndDefinition = new Pair<>("", "");
        if (photo == null || photo.urls == null) return urlAndDefinition;

        String url = photo.urls.raw;
        String definition = "raw";
        if (TextUtils.isEmpty(url)){
            url = photo.urls.full;
            definition = "full";
        }

        if (TextUtils.isEmpty(url)){
            url = photo.urls.regular;
            definition = "regular";
        }
        if (TextUtils.isEmpty(url)){
            url = photo.urls.small;
            definition = "small";
        }
        if (TextUtils.isEmpty(url)){
            url = photo.urls.thumb;
            definition = "thumb";
        }
        if (TextUtils.isEmpty(url)){
            url = "";
            definition = "";
        }

        return new Pair<>(url, definition);
    }

    public void startDownload(){
        if (mContext == null || mPhoto == null || mPhoto.urls == null){
            if (mOnDownloadResult != null){
                mOnDownloadResult.onFail("无效的下载地址");
            }
            return;
        }

        Pair<String, String> urlAndDefinition = getDownloadUrlAndDefinition(mPhoto);

        mRealUrl = urlAndDefinition.first;
        mFileName = mPhoto.id + "_" + urlAndDefinition.second + ".png";
        File dir = mContext.getExternalFilesDir("images");
        File file = new File(dir, mFileName);
        mPath = file.getAbsolutePath();
        if (file.exists()){
            if (mOnDownloadResult != null){
                mOnDownloadResult.onSuccess();
            }
            return;
        }

        Permissions.with(mContext).permission(Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .rationale("请允许App访问您的SD卡，否则将下载失败")
                .result(this)
                .request();
    }

    private void requestDownloadLink() {
        PhotoLoader.getLoader().getPhotoDownloadLink(mPhoto.id)
                .subscribe(new Consumer<Item>() {
                    @Override
                    public void accept(Item item) throws Exception {

                    }
                });
    }

    private void startDownloadReally(String url) {
        FileDownloader.getImpl().create(url).setPath(mPath)
                .setWifiRequired(true)
                .setListener(new FileDownloadSampleListener() {

                    @Override
                    protected void started(BaseDownloadTask task) {
                       notifyDownloadStart();
                    }

                    @Override
                    protected void completed(BaseDownloadTask task) {
                        notifyDownloadSuccess();
                    }

                    @Override
                    protected void error(BaseDownloadTask task, Throwable e) {
                        notifyDownloadFail(e.getMessage());
                    }
                }).start();
    }

    @Override
    public void onGranted(String[] perms) {
        try {
            if (perms != null && perms.length > 0 && perms[0] == Manifest.permission.WRITE_EXTERNAL_STORAGE){
                requestDownloadLink();
                startDownloadReally(mRealUrl);
            } else {
                notifyDownloadFail("权限不足");
            }
        }catch (Throwable t){
            Logger.e(TAG, "startDownload err: " + t.getMessage());
        }
    }

    @Override
    public void onDenied(String[] perms) {
        notifyDownloadFail("权限不足");
    }

    private void notifyDownloadStart(){
        if (mOnDownloadResult != null){
            mOnDownloadResult.onStart();
        }
    }

    private void notifyDownloadSuccess(){
        if (mOnDownloadResult != null){
            mOnDownloadResult.onSuccess();
        }
    }

    private void notifyDownloadFail(String err){
        if (mOnDownloadResult != null){
            mOnDownloadResult.onFail(err);
        }
    }


    public interface OnDownloadResult{
        void onStart();
        void onSuccess();
        void onFail(String error);
    }
}
