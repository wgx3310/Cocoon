package com.reid.cocoon.ui.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.reid.cocoon.R;
import com.reid.cocoon.common.content.SettingKeys;

public class WebActivity extends BasicActivity {

    private ProgressBar mProgressBar;
    private WebView mWebView;

    private String mExtraTitle;
    private String mUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);

        handleIntent();
        initToolbar(mExtraTitle);

        initView();
    }

    private void handleIntent() {
        Intent intent = getIntent();
        if (intent != null){
            mExtraTitle = intent.getStringExtra(SettingKeys.KEY_TITLE);
            mUrl = intent.getStringExtra(SettingKeys.KEY_URL);
        }
    }

    private void initView() {
        mProgressBar = findViewById(R.id.progress_bar);
        mProgressBar.setProgress(0);

        mWebView = findViewById(R.id.web_view);
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        mWebView.getSettings().setSupportZoom(true);
        mWebView.setWebChromeClient(mChromeClient);
        mWebView.setWebViewClient(mViewClient);

        if (!TextUtils.isEmpty(mUrl)){
            mWebView.loadUrl(mUrl);
        }
    }

    private WebChromeClient mChromeClient = new WebChromeClient(){
        @Override
        public void onReceivedTitle(WebView view, String title) {
            super.onReceivedTitle(view, title);
            if (TextUtils.isEmpty(mExtraTitle)){
                setTitle(title);
            }
        }

        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            super.onProgressChanged(view, newProgress);
            mProgressBar.setVisibility(View.VISIBLE);
            mProgressBar.setProgress(newProgress);
            if (newProgress >= 100){
                mProgressBar.setVisibility(View.GONE);
            }
        }
    };

    private WebViewClient mViewClient = new WebViewClient(){

    };

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        if (event.getKeyCode() == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN){
            if (mWebView != null && mWebView.canGoBack()){
                mWebView.goBack();
                return true;
            }
        }
        return super.dispatchKeyEvent(event);
    }

    @Override
    protected void onDestroy() {
        if (mWebView != null){
            mWebView.stopLoading();
            mWebView.clearCache(true);
            mWebView.clearHistory();
            mWebView.setWebViewClient(null);
            mWebView.setWebChromeClient(null);
            mWebView.freeMemory();
            mWebView.pauseTimers();
            mWebView.destroy();
            mWebView = null;
        }
        super.onDestroy();
    }
}
