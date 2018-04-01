package com.reid.cocoon.data.utils;

import com.reid.cocoon.viewholder.utils.ViewTypeEnum;
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
}
