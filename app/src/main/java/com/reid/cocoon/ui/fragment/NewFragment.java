package com.reid.cocoon.ui.fragment;

import android.support.annotation.NonNull;
import android.view.View;

import com.reid.cocoon.data.model.Data;
import com.reid.cocoon.data.model.Photo;
import com.reid.cocoon.data.http.loader.PhotoLoader;
import com.reid.cocoon.data.utils.DataWrapper;
import com.reid.cocoon.ui.recycler.BottomItemDecoration;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.functions.Function;


public class NewFragment extends PlasticFragment {

    public static NewFragment newInstance(){
        return new NewFragment();
    }

    @Override
    protected void initView(@NonNull View view) {
        super.initView(view);
        mPlasticView.addItemDecoration(new BottomItemDecoration());
    }

    @Override
    public Observable<Data> getData(int curPage, int pageCount) {
        return PhotoLoader.getLoader().getPhotos(curPage, pageCount)
                .map(new Function<List<Photo>, Data>() {
                    @Override
                    public Data apply(List<Photo> photos) throws Exception {
                        return DataWrapper.wrapPhotoData(photos);
                    }
                });
    }
}
