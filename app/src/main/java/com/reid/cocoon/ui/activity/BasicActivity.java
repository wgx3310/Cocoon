package com.reid.cocoon.ui.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.reid.cocoon.R;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public class BasicActivity extends AppCompatActivity {
    protected final String TAG = this.getClass().getSimpleName();

    protected Handler mHandler = new Handler(Looper.getMainLooper());

    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    protected Toolbar mToolbar;

    public Handler getMainHandler(){
        if (mHandler == null){
            mHandler = new Handler(Looper.getMainLooper());
        }
        return mHandler;
    }

    protected void addDisposable(Disposable disposable){
        if (disposable == null){
            return;
        }

        if (compositeDisposable == null){
            compositeDisposable = new CompositeDisposable();
        }
        compositeDisposable.add(disposable);
    }

    protected void initToolbar(){
        initToolbar("");
    }

    protected void initToolbar(int resId){
        initToolbar(getString(resId));
    }

    protected void initToolbar(String title){
        mToolbar = findViewById(R.id.toolbar);
        if (mToolbar != null){
            setSupportActionBar(mToolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    finish();
                }
            });
        }
        setTitle(title);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onDestroy() {
        if (mHandler != null){
            mHandler.removeCallbacksAndMessages(null);
            mHandler = null;
        }
        if (compositeDisposable != null){
            compositeDisposable.dispose();
            compositeDisposable = null;
        }
        super.onDestroy();
    }
}
