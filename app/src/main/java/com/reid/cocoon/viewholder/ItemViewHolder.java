package com.reid.cocoon.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.reid.cocoon.data.model.Component;

public abstract class ItemViewHolder extends RecyclerView.ViewHolder {

    protected Component mComponent;

    public ItemViewHolder(View itemView) {
        super(itemView);
    }

    public final void bindData(Component component) {
        mComponent = component;
        if (component != null){
            onBindData(component);
        }
    }

    public abstract void onBindData(Component component);
}
