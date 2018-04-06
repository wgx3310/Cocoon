package com.reid.cocoon.data.api;

import com.reid.cocoon.data.model.Collection;
import com.reid.cocoon.data.model.Photo;
import com.reid.cocoon.data.model.Stats;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface PhotoApi {

    @GET("photos")
    Observable<List<Photo>> getPhotos(@Query("page") int page,
                                      @Query("per_page") int per_page,
                                      @Query("order_by") String order_by);

    @GET("photos/curated")
    Observable<List<Photo>> getCuratedPhotos(@Query("page") int page,
                                             @Query("per_page") int per_page,
                                             @Query("order_by") String order_by);

    @GET("photos/{id}")
    Observable<Photo> getPhoto(@Path("id") String id);

    @GET("photos/{id}/statistics")
    Observable<Stats> getPhotoStats(@Path("id") String id);

    /**
     * You can’t use the collections and query parameters in the same request
     * @param collections Public collection ID(‘s) to filter selection. If multiple, comma-separated
     * @param featured Limit selection to featured photos.
     * @param userName 	Limit selection to a single user.
     * @param query Limit selection to photos matching a search term.
     * @param orientation  Filter search results by photo orientation. Valid values are landscape, portrait, and squarish.
     * @param count The number of photos to return. (Default: 1; max: 30)
     * @return
     */
    @GET("/photos/random")
    Observable<List<Photo>> getPhotoRandom(@Query("collections") String collections,
                                           @Query("featured") boolean featured,
                                           @Query("username") String userName,
                                           @Query("query") String query,
                                           @Query("orientation") String orientation,
                                           @Query("count") int count);

    @GET("photos/{id}/download")
    Observable<String> getPhotoDownloadLink(@Path("id") String id);

    @GET("collections")
    Observable<List<Collection>> getCollections(@Query("page") int page, @Query("per_page") int perPage);

    @GET("collections/featured")
    Observable<List<Collection>> getFeaturedCollections(@Query("page") int page, @Query("per_page") int perPage);

    @GET("collections/curated")
    Observable<List<Collection>> getCuratedCollections(@Query("page") int page, @Query("per_page") int perPage);

    @GET("collections/{id}")
    Observable<Collection> getCollection(@Path("id") String id);

    @GET("collections/curated/{id}")
    Observable<Collection> getCuratedCollection(@Path("id") String id);

    @GET("collections/{id}/photos")
    Observable<List<Photo>> getCollectionPhotos(@Path("id") String id, @Query("page") int page, @Query("per_page") int perPage);

    @GET("collections/curated/{id}/photos")
    Observable<List<Photo>> getCuratedCollectionPhotos(@Path("id") String id, @Query("page") int page, @Query("per_page") int perPage);

    @GET("collections/{id}/related")
    Observable<List<Collection>> getRelatedCollections(@Path("id") String id);
}
