package com.reid.cocoon.ui.recycler.viewholder;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.reid.cocoon.data.model.Component;

public abstract class ItemViewHolder extends RecyclerView.ViewHolder {

    protected Component mComponent;
    protected Context mContext;

    public Context getContext() {
        return mContext;
    }

    public ItemViewHolder(View itemView) {
        super(itemView);
        mContext = itemView.getContext();
    }

    public final void bindData(Component component) {
        mComponent = component;
        if (component != null){
            onBindData(component);
        }
    }

    public abstract void onBindData(Component component);
}
