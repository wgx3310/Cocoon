package com.reid.cocoon.data.http;

import android.text.TextUtils;
import android.util.ArrayMap;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.reid.cocoon.common.AppCompat;

import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


public class PhotoApiClient{

    private PhotoApiClient(){

    }

    private static class Internal{
        private static final PhotoApiClient sInstance = new PhotoApiClient();
    }

    public static PhotoApiClient getInstance(){
        return Internal.sInstance;
    }

    protected OkHttpClient getHttpClient() {
        if (httpClient != null){
            return httpClient;
        }

        httpClient = new OkHttpClient.Builder().readTimeout(20, TimeUnit.SECONDS)
                .connectTimeout(20, TimeUnit.SECONDS)
                .retryOnConnectionFailure(true)
                .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .addInterceptor(new AuthInterceptor())
                .cache(new Cache(AppCompat.getContext().getExternalCacheDir(), 10*1024*1024))
                .build();

        return httpClient;
    }

    private Map<String, Retrofit> retrofitMap = new ArrayMap<>();
    protected OkHttpClient httpClient;

    public void register(String baseUrl){
        if (TextUtils.isEmpty(baseUrl)){
            return;
        }

        Retrofit retrofit = getRetrofit(baseUrl);
        retrofitMap.put(baseUrl, retrofit);
    }

    private Retrofit getRetrofit(String baseUrl) {
        if (retrofitMap.get(baseUrl) != null){
            return retrofitMap.get(baseUrl);
        }

        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd HH:mm:ss")
                .create();

        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(getHttpClient())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson));

        Retrofit retrofit = builder.build();
        retrofitMap.put(baseUrl, retrofit);
        return retrofit;
    }

    public <S> S get(String baseUrl, Class<S> service) {
        return getRetrofit(baseUrl).create(service);
    }
}
