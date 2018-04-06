package com.reid.cocoon.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.reid.cocoon.R;
import com.reid.cocoon.common.content.SettingKeys;
import com.reid.cocoon.common.utils.AppHelper;
import com.reid.cocoon.common.utils.Logger;
import com.reid.cocoon.common.utils.ViewHelper;
import com.reid.cocoon.data.http.loader.PhotoLoader;
import com.reid.cocoon.data.model.Photo;
import com.reid.cocoon.data.model.Stats;
import com.reid.cocoon.utils.PhotoHelper;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

public class PhotoDetailActivity extends BasicActivity {

    private Photo mPhoto;
    private Stats mStats;
    private ImageView mImgFull;
    private TextView mName;
    private TextView mDate;
    private ImageView mAvatar;

    private LinearLayout mStatLayout;
    private TextView mStatViews;
    private TextView mStatLikes;
    private TextView mStatDownloads;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_detail);
        initToolbar();

        handleIntent();

        initView();
    }

    private void initView() {
        mImgFull = findViewById(R.id.imgFull);
        mName = findViewById(R.id.photo_base_title);
        mDate = findViewById(R.id.photo_base_date);
        mAvatar = findViewById(R.id.photo_base_avatar);

        int screenWidth = AppHelper.getScreenWidth();
        int finalHeight = (int) (screenWidth * mPhoto.height * 1.0f / mPhoto.width);
        ViewGroup.LayoutParams layoutParams = mImgFull.getLayoutParams();
        layoutParams.height = finalHeight;
        mImgFull.setLayoutParams(layoutParams);
        Glide.with(this).load(PhotoHelper.choosePhotoFullUrl(mPhoto)).into(mImgFull);
        mName.setText("By " + mPhoto.user.name);
        mDate.setText("On " + mPhoto.createdAt.split("T")[0]);
        Glide.with(this).load(mPhoto.user.profileImage.large).apply(
                new RequestOptions().placeholder(R.drawable.ic_user_placeholder).circleCrop()).into(mAvatar);

        mStatLayout = findViewById(R.id.photo_stat_layout);
        mStatViews = findViewById(R.id.photo_stat_view);
        mStatLikes = findViewById(R.id.photo_stat_likes);
        mStatDownloads = findViewById(R.id.photo_stat_downloads);
        ViewHelper.hideView(mStatLayout);

        Disposable disposable = PhotoLoader.getLoader().getPhotoStats(mPhoto.id)
                .subscribe(new Consumer<Stats>() {
                    @Override
                    public void accept(Stats stats) throws Exception {
                        if (stats != null && !TextUtils.isEmpty(stats.id)){
                            mStats = stats;
                            setStatViews();
                        }
                    }
                });
        addDisposable(disposable);
    }

    private void setStatViews() {
        if (mStats == null) return;

        ViewHelper.showView(mStatLayout);

        mStatViews.setText(String.valueOf(mStats.views.total));
        mStatLikes.setText(String.valueOf(mStats.likes.total));
        mStatDownloads.setText(String.valueOf(mStats.downloads.total));
    }

    private void handleIntent() {
        Intent intent = getIntent();
        if (intent.getSerializableExtra(SettingKeys.KEY_PHOTO) != null){
            mPhoto = (Photo) intent.getSerializableExtra(SettingKeys.KEY_PHOTO);
        }

        Logger.e("WGX", "handleIntent " + mPhoto);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_photo_detail, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_share:
                PhotoHelper.sharePhoto(this, mPhoto);
                break;
            case R.id.action_view_on_unsplash:
                PhotoHelper.openInBrowser(this, mPhoto);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
