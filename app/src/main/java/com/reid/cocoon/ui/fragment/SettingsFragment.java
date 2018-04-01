package com.reid.cocoon.ui.fragment;

import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.PreferenceScreen;
import android.support.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.reid.cocoon.R;
import com.reid.cocoon.common.AppCompat;
import com.reid.cocoon.common.content.Tips;
import com.reid.cocoon.common.utils.AppHelper;
import com.reid.cocoon.common.utils.IntentHelper;
import com.reid.cocoon.data.io.ACache;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Action;
import io.reactivex.schedulers.Schedulers;


/**
 * Created by reid on 2017/9/11.
 */

public class SettingsFragment extends PreferenceFragment implements Preference.OnPreferenceClickListener{

    private static final String KEY_CLEAR_IMAGE_CACHE = "clear_image_cache";
    private static final String KEY_CLEAR_DATA_CACHE = "clear_data_cache";
    private static final String KEY_APP_VERSION = "app_version";
    private static final String KEY_APP_ABOUT = "app_about";

    private PreferenceScreen mClearImageCache;
    private PreferenceScreen mClearDataCache;
    private Preference mVersionScreen;
    private Preference mAbout;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.pref_settings);
        init();
    }

    private void init() {

        mClearImageCache = (PreferenceScreen) findPreference(KEY_CLEAR_IMAGE_CACHE);
        mClearImageCache.setOnPreferenceClickListener(this);

        mClearDataCache = (PreferenceScreen) findPreference(KEY_CLEAR_DATA_CACHE);
        mClearDataCache.setOnPreferenceClickListener(this);

        mVersionScreen = findPreference(KEY_APP_VERSION);
        mVersionScreen.setSummary(AppHelper.getAppVerName());
        mVersionScreen.setOnPreferenceClickListener(this);

        mAbout = findPreference(KEY_APP_ABOUT);
        mAbout.setOnPreferenceClickListener(this);
    }

    @Override
    public boolean onPreferenceClick(Preference preference) {
        String key = preference.getKey();
        switch (key){
            case KEY_CLEAR_IMAGE_CACHE:
                clearImageCache();
                break;
            case KEY_CLEAR_DATA_CACHE:
                clearDataCache();
                break;
            case KEY_APP_VERSION:
                Tips.toast(R.string.setting_last_version);
                break;
            case KEY_APP_ABOUT:
                IntentHelper.goAbout(getActivity());
                break;
        }
        return false;
    }

    private void clearDataCache() {
        Observable.create(new ObservableOnSubscribe<Void>() {
            @Override
            public void subscribe(ObservableEmitter<Void> e) throws Exception {
                ACache.get(getActivity()).clear();
                e.onComplete();
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnComplete(new Action() {
                    @Override
                    public void run() throws Exception {
                        Tips.toast("清除完毕");
                    }
                }).subscribe();
    }

    private void clearImageCache() {
        Observable.create(new ObservableOnSubscribe<Void>() {
            @Override
            public void subscribe(ObservableEmitter<Void> e) throws Exception {
                Glide.get(AppCompat.getContext()).clearDiskCache();
                e.onComplete();
            }
        }).subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .doOnComplete(new Action() {
            @Override
            public void run() throws Exception {
                Tips.toast("清除完毕");
            }
        }).subscribe();
    }
}
