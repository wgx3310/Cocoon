package com.reid.cocoon.viewholder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.reid.cocoon.R;
import com.reid.cocoon.common.utils.AppHelper;
import com.reid.cocoon.data.model.Component;
import com.reid.cocoon.data.model.Photo;

public class PhotoCardViewHolder extends ItemViewHolder {

    private View mCard;
    private ImageView mImage;
    private TextView mText;

    public PhotoCardViewHolder(View itemView) {
        super(itemView);
        mCard = itemView.findViewById(R.id.item_card);
        mImage = itemView.findViewById(R.id.item_card_img);
        mText = itemView.findViewById(R.id.item_card_text);
    }

    @Override
    public void onBindData(Component component) {
        Photo photo = component.photo;

        int screenWidth = AppHelper.getScreenWidth();
        int finalHeight = (int) (screenWidth / ((float)photo.width/(float)photo.height));
        mImage.setMinimumHeight(finalHeight);
        mText.setText(photo.user.userName);
        Glide.with(itemView.getContext()).load(photo.urls.regular).into(mImage);
    }
}
