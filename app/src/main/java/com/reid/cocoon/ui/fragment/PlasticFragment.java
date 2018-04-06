package com.reid.cocoon.ui.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.reid.cocoon.R;
import com.reid.cocoon.data.model.Data;
import com.reid.cocoon.ui.recycler.adapter.ListAdapter;
import com.reid.plastic.OnLoadMoreListener;
import com.reid.plastic.PlasticView;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

public abstract class PlasticFragment extends DisposableFragment implements SwipeRefreshLayout.OnRefreshListener {

    protected PlasticView mPlasticView;
    protected ProgressBar mLoadingView;

    protected ListAdapter mAdapter;

    protected int curPage = 1;
    protected boolean isLoading;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_list, null);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
    }

    protected void initView(@NonNull View view) {
        mLoadingView = view.findViewById(R.id.progress_loading);
        mLoadingView.setVisibility(View.VISIBLE);

        mPlasticView = view.findViewById(R.id.plastic);
        mPlasticView.setLayoutManager(new LinearLayoutManager(getContext()));
        mPlasticView.setLimitNumberToCallLoadMore(3);
        mPlasticView.setRefreshColorSchemeResources(R.color.red, R.color.yellow, R.color.green, R.color.blue);
        mPlasticView.setOnRefreshListener(this);
        mPlasticView.setVisibility(View.GONE);

        mAdapter = new ListAdapter();
        mPlasticView.setAdapter(mAdapter);
        mPlasticView.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                loadData(true);
            }
        });

        loadData(false);
    }

    public abstract Observable<Data> getData(int curPage, int pageCount);

    public void loadData(boolean loadMore) {
        if (isLoading) {
            return;
        }

        curPage = loadMore ? curPage + 1 : 1;
        Disposable disposable = getData(curPage, 10)
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        isLoading = true;
                    }
                })
                .doOnNext(new Consumer<Data>() {
                    @Override
                    public void accept(Data module) throws Exception {
                        mLoadingView.setVisibility(View.GONE);
                        mPlasticView.setVisibility(View.VISIBLE);
                    }
                })
                .subscribe(new Consumer<Data>() {
                    @Override
                    public void accept(Data module) throws Exception {
                        isLoading = false;
                        if (module != null && module.components != null && module.components.size() > 0) {
                            if (curPage == 1) {
                                mAdapter.setData(module);
                            } else {
                                mAdapter.addData(module);
                            }
                            mPlasticView.loadMoreComplete();
                        } else {
                            mPlasticView.setNoMore(true);
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        isLoading = false;
                        mPlasticView.loadMoreComplete();
                    }
                });
        addDisposable(disposable);
    }

    @Override
    public void onRefresh() {
        loadData(false);
    }
}
