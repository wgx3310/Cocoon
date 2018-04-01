package com.reid.cocoon.ui.recycler.viewholder;

import android.view.View;
import android.widget.TextView;

import com.reid.cocoon.R;
import com.reid.cocoon.data.model.Component;
import com.reid.cocoon.data.model.Item;

public class CategoryViewHolder extends ItemViewHolder {

    private TextView mTitle;

    public CategoryViewHolder(View itemView) {
        super(itemView);
        mTitle = itemView.findViewById(R.id.item_category_title);
    }

    @Override
    public void onBindData(Component component) {
        Item item = component.item;
        mTitle.setText(item.title);
    }
}
