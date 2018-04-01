package com.reid.cocoon.data.utils;

import com.reid.cocoon.data.model.Item;
import com.reid.cocoon.ui.recycler.utils.ViewTypeEnum;
import com.reid.cocoon.data.model.Component;
import com.reid.cocoon.data.model.Data;
import com.reid.cocoon.data.model.Photo;

import java.util.ArrayList;
import java.util.List;

public class DataWrapper {
    public static Data wrapPhotoData(List<Photo> photos){
        Data data = new Data();
        if (photos == null || photos.size() <= 0){
            return data;
        }

        data.components = new ArrayList<>();
        for (Photo photo : photos){
            Component component = new Component();
            component.photo = photo;
            component.type = ViewTypeEnum.TYPE_PHOTO_A;
            data.components.add(component);
        }
        return data;
    }

    public static Data buildAboutData(){
        Data data = new Data();
        data.components = new ArrayList<>();

        Component headerComponent = new Component();
        headerComponent.type = ViewTypeEnum.TYPE_ABOUT_HEADER;
        data.components.add(headerComponent);

        Component libraryCategory = new Component();
        libraryCategory.type = ViewTypeEnum.TYPE_CATEGORY;
        Item categoryItem = new Item();
        categoryItem.title = "LIBRARIES";
        libraryCategory.item = categoryItem;
        data.components.add(libraryCategory);

        Component glideLibrary = new Component();
        glideLibrary.type = ViewTypeEnum.TYPE_ABOUT_LIBRARY;
        Item glideItem = new Item();
        glideItem.title = "Glide";
        glideItem.subtitle = "Glide is a fast and efficient open source media management and image loading framework for Android that wraps media decoding, memory and disk caching, and resource pooling into a simple and easy to use interface.";
        glideItem.url = "https://github.com/bumptech/glide";
        glideLibrary.item = glideItem;
        data.components.add(glideLibrary);

        Component rxjavaLibrary = new Component();
        rxjavaLibrary.type = ViewTypeEnum.TYPE_ABOUT_LIBRARY;
        Item rxjavaItem = new Item();
        rxjavaItem.title = "RxJava";
        rxjavaItem.subtitle = "Reactive Extensions for the JVM – a library for composing asynchronous and event-based programs using observable sequences for the Java VM.";
        rxjavaItem.url = "https://github.com/ReactiveX/RxJava";
        rxjavaLibrary.item = rxjavaItem;
        data.components.add(rxjavaLibrary);

        Component retrofitLibrary = new Component();
        retrofitLibrary.type = ViewTypeEnum.TYPE_ABOUT_LIBRARY;
        Item retrofitItem = new Item();
        retrofitItem.title = "Retrofit 2.0";
        retrofitItem.subtitle = "Type-safe HTTP client for Android and Java by Square, Inc.";
        retrofitItem.url = "http://square.github.io/retrofit/";
        retrofitLibrary.item = retrofitItem;
        data.components.add(retrofitLibrary);

        Component okhttpLibrary = new Component();
        okhttpLibrary.type = ViewTypeEnum.TYPE_ABOUT_LIBRARY;
        Item okhttpItem = new Item();
        okhttpItem.title = "OkHttp";
        okhttpItem.subtitle = "An HTTP+HTTP/2 client for Android and Java applications.";
        okhttpItem.url = "http://square.github.io/okhttp/";
        okhttpLibrary.item = okhttpItem;
        data.components.add(okhttpLibrary);

        Component gsonLibrary = new Component();
        gsonLibrary.type = ViewTypeEnum.TYPE_ABOUT_LIBRARY;
        Item gsonItem = new Item();
        gsonItem.title = "Gson";
        gsonItem.subtitle = "A Java serialization/deserialization library to convert Java Objects into JSON and back";
        gsonItem.url = "https://github.com/google/gson";
        gsonLibrary.item = gsonItem;
        data.components.add(gsonLibrary);

        return data;
    }
}
