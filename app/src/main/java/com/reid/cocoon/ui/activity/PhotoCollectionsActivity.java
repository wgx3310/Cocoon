package com.reid.cocoon.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.reid.cocoon.R;
import com.reid.cocoon.common.content.SettingKeys;
import com.reid.cocoon.common.utils.ViewHelper;
import com.reid.cocoon.data.model.Collection;
import com.reid.cocoon.ui.fragment.CollectionDetailFragment;
import com.reid.cocoon.utils.PhotoHelper;

public class PhotoCollectionsActivity extends BasicActivity {

    private Collection mCollection;

    private TextView mTitle;
    private TextView mDescription;
    private ImageView mAvatar;
    private TextView mSubtitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_collections);
        initToolbar();

        handleIntent();
        initView();
    }

    private void initView() {
        mTitle = findViewById(R.id.collection_title);
        mDescription = findViewById(R.id.collection_description);
        mAvatar = findViewById(R.id.collection_avatar);
        mSubtitle = findViewById(R.id.collection_subtitle);

        if (mCollection!= null){
            mTitle.setText(mCollection.title);
            if (!TextUtils.isEmpty(mCollection.description)){
                mDescription.setText(mCollection.description);
                ViewHelper.showView(mDescription);
            }else {
                ViewHelper.hideView(mDescription);
            }
            Glide.with(this).load(mCollection.user.profileImage.medium).apply(
                    new RequestOptions().placeholder(R.drawable.ic_user_placeholder).circleCrop()).into(mAvatar);
            mSubtitle.setText(mCollection.user.name);

            getSupportFragmentManager().beginTransaction().add(R.id.content_layout, CollectionDetailFragment.newInstance(mCollection)).commitAllowingStateLoss();
        }
    }

    private void handleIntent() {
        Intent intent = getIntent();
        if (intent.getSerializableExtra(SettingKeys.KEY_COLLECTION) != null){
            mCollection = (Collection) intent.getSerializableExtra(SettingKeys.KEY_COLLECTION);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_collection_detail, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_share:
                PhotoHelper.shareCollection(this, mCollection);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
