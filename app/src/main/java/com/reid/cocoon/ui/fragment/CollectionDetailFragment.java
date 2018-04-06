package com.reid.cocoon.ui.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import com.reid.cocoon.common.content.SettingKeys;
import com.reid.cocoon.data.http.loader.PhotoLoader;
import com.reid.cocoon.data.model.Collection;
import com.reid.cocoon.data.model.Data;
import com.reid.cocoon.data.model.Photo;
import com.reid.cocoon.data.utils.DataWrapper;
import com.reid.cocoon.ui.recycler.BottomItemDecoration;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.functions.Function;

public class CollectionDetailFragment extends PlasticFragment {

    public static CollectionDetailFragment newInstance(Collection collection){
        CollectionDetailFragment fragment = new CollectionDetailFragment();
        Bundle data = new Bundle();
        data.putSerializable(SettingKeys.KEY_COLLECTION, collection);
        fragment.setArguments(data);
        return fragment;
    }

    private Collection mCollection;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mCollection = (Collection) getArguments().getSerializable(SettingKeys.KEY_COLLECTION);
    }

    @Override
    protected void initView(@NonNull View view) {
        super.initView(view);
        mPlasticView.addItemDecoration(new BottomItemDecoration());
    }

    @Override
    public Observable<Data> getData(int curPage, int pageCount) {
        return PhotoLoader.getLoader().getCollectionPhotos(mCollection.id, curPage, pageCount)
                .map(new Function<List<Photo>, Data>() {
                    @Override
                    public Data apply(List<Photo> photos) throws Exception {
                        return DataWrapper.wrapPhotoData(photos);
                    }
                });
    }
}
