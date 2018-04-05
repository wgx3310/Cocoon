package com.reid.cocoon.ui.recycler.viewholder;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.reid.cocoon.R;
import com.reid.cocoon.common.utils.AppHelper;
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

        mMaxHeight = getContext().getResources().getDimensionPixelSize(R.dimen.dp_250);
    }

    @Override
    public void onBindData(Component component) {
        Photo photo = component.photo;

        int screenWidth = AppHelper.getScreenWidth();
        int finalHeight = (int) (screenWidth / ((float)photo.width/(float)photo.height));
        finalHeight = Math.min(finalHeight, mMaxHeight);
        ViewGroup.LayoutParams layoutParams = mImage.getLayoutParams();
        layoutParams.height = finalHeight;
        mImage.setLayoutParams(layoutParams);

        mText.setText(photo.user.userName);
        Glide.with(itemView.getContext()).load(PhotoHelper.choosePhotoUrl(photo)).into(mImage);
    }
}
