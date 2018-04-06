package com.reid.cocoon.ui.recycler.viewholder;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.reid.cocoon.R;
import com.reid.cocoon.common.utils.AppHelper;
import com.reid.cocoon.utils.IntentHelper;
import com.reid.cocoon.data.model.Component;
import com.reid.cocoon.data.model.Photo;
import com.reid.cocoon.utils.PhotoHelper;

public class PhotoCardViewHolder extends ItemViewHolder {

    private int mMaxHeight;
    private ImageView mImage;
    private TextView mText;

    public PhotoCardViewHolder(View itemView) {
        super(itemView);
        mImage = itemView.findViewById(R.id.item_card_img);
        mText = itemView.findViewById(R.id.item_card_text);

        mMaxHeight = getContext().getResources().getDimensionPixelSize(R.dimen.dp_360);
    }

    @Override
    public void onBindData(Component component) {
        final Photo photo = component.photo;
        if (photo == null) return;

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IntentHelper.goPhotoDetail(v.getContext(), photo);
            }
        });
        int screenWidth = AppHelper.getScreenWidth();
        int finalHeight = (int) (screenWidth / ((float)photo.width/(float)photo.height));
        finalHeight = Math.min(finalHeight, mMaxHeight);
        ViewGroup.LayoutParams layoutParams = mImage.getLayoutParams();
        layoutParams.height = finalHeight;
        mImage.setLayoutParams(layoutParams);

        mText.setText(photo.user.userName);
        Glide.with(itemView.getContext()).load(PhotoHelper.choosePhotoUrl(photo))
                .apply(new RequestOptions().placeholder(PhotoHelper.getPhotoColorDrawable(photo)))
                .into(mImage);
    }
}
