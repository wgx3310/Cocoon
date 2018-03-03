package com.reid.cocoon.data.api;

import com.reid.cocoon.data.bean.Photo;

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
}
