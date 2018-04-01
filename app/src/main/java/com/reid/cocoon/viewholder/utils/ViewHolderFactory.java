package com.reid.cocoon.viewholder.utils;

import android.view.View;
import android.widget.TextView;

import com.reid.cocoon.R;
import com.reid.cocoon.viewholder.AboutHeaderViewHolder;
import com.reid.cocoon.viewholder.EmptyViewHolder;
import com.reid.cocoon.viewholder.ItemViewHolder;
import com.reid.cocoon.viewholder.PhotoCardViewHolder;

public class ViewHolderFactory {

    public static int getLayoutByViewType(int viewType){

        int resId = R.layout.item_empty_component;

        switch (viewType){
            case ViewTypeEnum.TYPE_PHOTO_A:
                resId = R.layout.item_photo_component;
                break;
            case ViewTypeEnum.TYPE_ABOUT_HEADER:
                resId = R.layout.item_about_header_component;
                break;
            default:
                resId = R.layout.item_empty_component;
                break;
        }

        return resId;
    }

    public static ItemViewHolder getViewHolderByViewType(int viewType, View itemView){
        ItemViewHolder holder = null;
        try{
            switch (viewType){
                case ViewTypeEnum.TYPE_PHOTO_A:
                    holder = new PhotoCardViewHolder(itemView);
                    break;
                case ViewTypeEnum.TYPE_ABOUT_HEADER:
                    holder = new AboutHeaderViewHolder(itemView);
                    break;
                default:
                    holder = new EmptyViewHolder(itemView);
                    break;
            }
        }catch (Throwable t){

        }
        return holder;
    }
}
