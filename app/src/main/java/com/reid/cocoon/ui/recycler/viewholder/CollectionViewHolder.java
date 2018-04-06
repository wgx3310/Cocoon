package com.reid.cocoon.ui.recycler.viewholder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.reid.cocoon.R;
import com.reid.cocoon.data.model.Collection;
import com.reid.cocoon.data.model.Component;
import com.reid.cocoon.utils.PhotoHelper;

public class CollectionViewHolder extends ItemViewHolder {

    private ImageView mImg;
    private TextView mName;
    private TextView mCount;

    public CollectionViewHolder(View itemView) {
        super(itemView);
        mImg = itemView.findViewById(R.id.item_collection_img);
        mName = itemView.findViewById(R.id.item_collection_name);
        mCount = itemView.findViewById(R.id.item_collection_size);
    }

    @Override
    public void onBindData(Component component) {
        Collection collection = component.collection;
        if (collection == null || collection.coverPhoto == null) return;

        Glide.with(mContext).load(PhotoHelper.choosePhotoUrl(collection.coverPhoto))
                .apply(new RequestOptions().placeholder(PhotoHelper.getPhotoColorDrawable(collection.coverPhoto)))
                .into(mImg);

        mName.setText(collection.title);
        mCount.setText(collection.totalPhotos + " Photos");
    }
}
