package com.reid.cocoon.data.http.loader;

import com.reid.cocoon.common.content.Constant;
import com.reid.cocoon.data.api.PhotoApi;
import com.reid.cocoon.data.model.Collection;
import com.reid.cocoon.data.model.Item;
import com.reid.cocoon.data.model.Photo;
import com.reid.cocoon.data.http.PhotoApiClient;
import com.reid.cocoon.data.model.Stats;

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

    public Observable<Stats> getPhotoStats(String id){
        return Impl().getPhotoStats(id).compose(this.<Stats>transformer());
    }

    public Observable<List<Photo>> getPhotoRandom(String collections, boolean featured,
                                                  String userName, String query, String orientation, int count){
        return Impl().getPhotoRandom(collections, featured, userName, query, orientation, count)
                .compose(this.<List<Photo>>transformer());
    }

    public Observable<Item> getPhotoDownloadLink(String id){
        return Impl().getPhotoDownloadLink(id).compose(this.<Item>transformer());
    }

    public Observable<List<Collection>> getCollections(int page, int pageCount){
        return Impl().getCollections(page, pageCount).compose(this.<List<Collection>>transformer());
    }

    public Observable<List<Collection>> getFeaturedCollections(int page, int pageCount){
        return Impl().getFeaturedCollections(page, pageCount).compose(this.<List<Collection>>transformer());
    }

    public Observable<List<Collection>> getCuratedCollections(int page, int pageCount){
        return Impl().getCuratedCollections(page, pageCount).compose(this.<List<Collection>>transformer());
    }

    public Observable<Collection> getCollection(String id){
        return Impl().getCollection(id).compose(this.<Collection>transformer());
    }

    public Observable<Collection> getCuratedCollection(String id){
        return Impl().getCuratedCollection(id).compose(this.<Collection>transformer());
    }

    public Observable<List<Collection>> getRelatedCollections(String id){
        return Impl().getRelatedCollections(id).compose(this.<List<Collection>>transformer());
    }

    public Observable<List<Photo>> getCollectionPhotos(String id, int page, int pageCount){
        return Impl().getCollectionPhotos(id, page, pageCount).compose(this.<List<Photo>>transformer());
    }

    public Observable<List<Photo>> getCuratedCollectionPhotos(String id, int page, int pageCount){
        return Impl().getCuratedCollectionPhotos(id, page, pageCount).compose(this.<List<Photo>>transformer());
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
