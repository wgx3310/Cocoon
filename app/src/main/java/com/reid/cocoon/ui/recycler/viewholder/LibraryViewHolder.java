package com.reid.cocoon.ui.recycler.viewholder;

import android.view.View;
import android.widget.TextView;

import com.reid.cocoon.R;
import com.reid.cocoon.utils.IntentHelper;
import com.reid.cocoon.data.model.Component;
import com.reid.cocoon.data.model.Item;

public class LibraryViewHolder extends ItemViewHolder {

    private TextView mTitle;
    private TextView mSubtitle;

    public LibraryViewHolder(View itemView) {
        super(itemView);
        mTitle = itemView.findViewById(R.id.item_library_title);
        mSubtitle = itemView.findViewById(R.id.item_library_content);
    }

    @Override
    public void onBindData(Component component) {
        final Item item = component.item;
        mTitle.setText(item.title);
        mSubtitle.setText(item.subtitle);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IntentHelper.goWeb(v.getContext(), item.title, item.url);
            }
        });
    }
}
