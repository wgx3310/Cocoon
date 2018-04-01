package com.reid.cocoon.viewholder;

import android.view.View;
import android.widget.TextView;

import com.reid.cocoon.R;
import com.reid.cocoon.common.utils.AppHelper;
import com.reid.cocoon.data.model.Component;

public class AboutHeaderViewHolder extends ItemViewHolder {

    private TextView mAppVersion;
    private TextView mTitle;

    public AboutHeaderViewHolder(View itemView) {
        super(itemView);
        mAppVersion = itemView.findViewById(R.id.app_version);
        mTitle = itemView.findViewById(R.id.about_header_title);
    }

    @Override
    public void onBindData(Component component) {
        String appVerName = AppHelper.getAppVerName();
        mAppVersion.setText(appVerName);
        mTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }


}
