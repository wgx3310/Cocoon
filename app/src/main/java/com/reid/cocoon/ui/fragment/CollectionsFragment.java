package com.reid.cocoon.ui.fragment;

import com.reid.cocoon.data.http.loader.PhotoLoader;
import com.reid.cocoon.data.model.Collection;
import com.reid.cocoon.data.model.Data;
import com.reid.cocoon.data.utils.DataWrapper;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.functions.Function;

public class CollectionsFragment extends PlasticFragment {

    public static CollectionsFragment newInstance(){
        return new CollectionsFragment();
    }

    @Override
    public Observable<Data> getData(int curPage, int pageCount) {
        return PhotoLoader.getLoader().getCollections(curPage, pageCount)
                .map(new Function<List<Collection>, Data>() {
                    @Override
                    public Data apply(List<Collection> collections) throws Exception {
                        return DataWrapper.wrapCollectionData(collections);
                    }
                });
    }
}
