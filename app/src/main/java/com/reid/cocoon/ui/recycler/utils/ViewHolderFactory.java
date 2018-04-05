package com.reid.cocoon.ui.recycler.utils;

import android.view.View;

import com.reid.cocoon.R;
import com.reid.cocoon.ui.recycler.viewholder.AboutHeaderViewHolder;
import com.reid.cocoon.ui.recycler.viewholder.CategoryViewHolder;
import com.reid.cocoon.ui.recycler.viewholder.CollectionViewHolder;
import com.reid.cocoon.ui.recycler.viewholder.EmptyViewHolder;
import com.reid.cocoon.ui.recycler.viewholder.ItemViewHolder;
import com.reid.cocoon.ui.recycler.viewholder.LibraryViewHolder;
import com.reid.cocoon.ui.recycler.viewholder.PhotoCardViewHolder;

public class ViewHolderFactory {

    public static int getLayoutByViewType(int viewType){

        int resId = R.layout.item_empty_component;

        switch (viewType){
            case ViewTypeEnum.TYPE_PHOTO_A:
                resId = R.layout.item_photo_component;
                break;
            case ViewTypeEnum.TYPE_COLLECTION_A:
                resId = R.layout.item_collection_component;
                break;
            case ViewTypeEnum.TYPE_CATEGORY:
                resId = R.layout.item_category_component;
                break;
            case ViewTypeEnum.TYPE_ABOUT_HEADER:
                resId = R.layout.item_about_header_component;
                break;
            case ViewTypeEnum.TYPE_ABOUT_LIBRARY:
                resId = R.layout.item_library_component;
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
                case ViewTypeEnum.TYPE_COLLECTION_A:
                    holder = new CollectionViewHolder(itemView);
                    break;
                case ViewTypeEnum.TYPE_CATEGORY:
                    holder = new CategoryViewHolder(itemView);
                    break;
                case ViewTypeEnum.TYPE_ABOUT_HEADER:
                    holder = new AboutHeaderViewHolder(itemView);
                    break;
                case ViewTypeEnum.TYPE_ABOUT_LIBRARY:
                    holder = new LibraryViewHolder(itemView);
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
