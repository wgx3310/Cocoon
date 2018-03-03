package com.reid.plastic;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;


public class LoadMoreFooter extends LinearLayout {

    public final static int STATE_LOADING = 0;
    public final static int STATE_COMPLETE = 1;
    public final static int STATE_NOMORE = 2;

    private ProgressBar mProgressBar;
    private TextView mText;

    private String loadingHint;
    private String noMoreHint;
    private String loadingDoneHint;

    public LoadMoreFooter(Context context) {
        this(context, null);
    }

    public LoadMoreFooter(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        setGravity(Gravity.CENTER);
        int size = getResources().getDimensionPixelSize(R.dimen.dp_5);
        setPadding(0, size, 0, size);
        int height = getResources().getDimensionPixelOffset(R.dimen.dp_50);
        setLayoutParams(new RecyclerView.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, height));

        mProgressBar = new ProgressBar(getContext(), null, android.R.attr.progressBarStyle);
        addView(mProgressBar);
        mText = new TextView(getContext());
        mText.setText(getContext().getString(R.string.loading));

        if(TextUtils.isEmpty(loadingHint)){
            loadingHint = (String)getContext().getText(R.string.loading);
        }
        if(TextUtils.isEmpty(noMoreHint)){
            noMoreHint = (String)getContext().getText(R.string.load_no_more);
        }
        if(TextUtils.isEmpty(loadingDoneHint)){
            loadingDoneHint = (String)getContext().getText(R.string.loading_done);
        }

        LayoutParams layoutParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins( (int)getResources().getDimension(R.dimen.dp_10),0,0,0 );

        mText.setLayoutParams(layoutParams);
        addView(mText);
    }

    public void setLoadingHint(String hint) {
        loadingHint = hint;
    }

    public void setNoMoreHint(String hint) {
        noMoreHint = hint;
    }

    public void setLoadingDoneHint(String hint) {
        loadingDoneHint = hint;
    }

    public void  setState(int state) {
        switch(state) {
            case STATE_LOADING:
                mProgressBar.setVisibility(View.VISIBLE);
                mText.setText(loadingHint);
                this.setVisibility(View.VISIBLE);
                break;
            case STATE_COMPLETE:
                mText.setText(loadingDoneHint);
                this.setVisibility(View.GONE);
                break;
            case STATE_NOMORE:
                mText.setText(noMoreHint);
                mProgressBar.setVisibility(View.GONE);
                this.setVisibility(View.VISIBLE);
                break;
        }
    }

}
