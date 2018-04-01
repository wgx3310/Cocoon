package com.reid.cocoon.data.http.loader;

import com.reid.cocoon.common.content.Constant;
import com.reid.cocoon.data.api.PhotoApi;
import com.reid.cocoon.data.model.Photo;
import com.reid.cocoon.data.http.PhotoApiClient;

import java.util.List;

import io.reactivex.Observable;

public class PhotoLoader extends Loader<PhotoApi> {

    public static PhotoLoader getLoader(){
        return new PhotoLoader();
    }

    public Observable<List<Photo>> getPhotos(int pageNum, int pageCount){
        return getPhotos(pageNum, pageCount, "latest");
    }

    public Observable<List<Photo>> getPhotos(int pageNum, int pageCount, String orderBy){
        return Impl().getPhotos(pageNum, pageCount, orderBy)
                .compose(this.<List<Photo>>transformer());
    }

    public Observable<List<Photo>> getCuratedPhotos(int pageNum, int pageCount){
        return getCuratedPhotos(pageNum, pageCount, "latest");
    }

    public Observable<List<Photo>> getCuratedPhotos(int pageNum, int pageCount, String orderBy){
        return Impl().getCuratedPhotos(pageNum, pageCount, orderBy)
                .compose(this.<List<Photo>>transformer());
    }

    public Observable<Photo> getPhoto(String id){
        return Impl().getPhoto(id).compose(this.<Photo>transformer());
    }

    public Observable<List<Photo>> getPhotoRandom(String collections, boolean featured,
                                                  String userName, String query, String orientation, int count){
        return Impl().getPhotoRandom(collections, featured, userName, query, orientation, count)
                .compose(this.<List<Photo>>transformer());
    }

    @Override
    protected String getBaseUrl() {
        return Constant.BASE_URL_UNSPLASH_API;
    }

    @Override
    protected PhotoApi Impl() {
        if (impl == null){
            impl = PhotoApiClient.getInstance().get(getBaseUrl(), getType());
        }
        return impl;
    }

}
