package com.reid.plastic;

import android.content.Context;
import android.support.annotation.ColorRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;


public class PlasticView extends FrameLayout {

    private static final int TYPE_HEADER = 200000;
    private static final int TYPE_FOOTER = 100000;

    private SparseArray<DecorativeView> mHeaderViews = new SparseArray<>();

    private boolean isLoadingMoreData;
    private OnLoadMoreListener onLoadMoreListener;
    private boolean enableLoadMore;
    private boolean isNoMore;
    private int limitNumberToCallLoadMore = 1;
    private LoadMoreFooter loadMoreFooter;

    private SwipeRefreshLayout mRefreshLayout;

    private RecyclerView mRecyclerView;
    private WrapperAdapter mWrapperAdapter;
    private PlasticAdapterDataObserver mAdapterDataObserver = new PlasticAdapterDataObserver();

    private ViewStub mEmptyStub;
    private View mEmptyView;

    public PlasticView(@NonNull Context context) {
        this(context, null);
    }

    public PlasticView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PlasticView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {
        inflate(getContext(), R.layout.layout_plastic, this);

        mEmptyStub = findViewById(R.id.plastic_empty);

        mRefreshLayout = findViewById(R.id.plastic_ptr_layout);
        mRefreshLayout.setEnabled(false);

        mRecyclerView = findViewById(R.id.plastic_recycler);

        mRecyclerView.addOnChildAttachStateChangeListener(
                new RecyclerView.OnChildAttachStateChangeListener() {
            @Override
            public void onChildViewAttachedToWindow(View view) {
                RecyclerView.ViewHolder viewHolder = mRecyclerView.getChildViewHolder(view);
                if (viewHolder != null){
                    processLoadMore(viewHolder.getAdapterPosition());
                }
            }

            @Override
            public void onChildViewDetachedFromWindow(View view) {

            }
        });

        loadMoreFooter = new LoadMoreFooter(getContext());
        loadMoreFooter.setVisibility(View.GONE);
    }

    //// SwipeRefreshLayout

    public void setOnRefreshListener(SwipeRefreshLayout.OnRefreshListener onRefreshListener){
        if (onRefreshListener != null){
            mRefreshLayout.setEnabled(true);
            mRefreshLayout.setColorSchemeResources(R.color.red, R.color.yellow, R.color.green, R.color.blue);
            mRefreshLayout.setOnRefreshListener(onRefreshListener);
        }else {
            mRefreshLayout.setEnabled(false);
        }
    }

    public void setRefreshColorSchemeResources(@ColorRes int... colorResIds){
        mRefreshLayout.setColorSchemeResources(colorResIds);
    }

    public void setRefreshing(boolean refreshing){
        mRefreshLayout.setRefreshing(refreshing);
    }

    public void refreshComplete(){
        mRefreshLayout.setRefreshing(false);
    }

    ///// RecyclerView

    public void setLayoutManager(RecyclerView.LayoutManager layoutManager){
        mRecyclerView.setLayoutManager(layoutManager);
    }

    public void setAdapter(RecyclerView.Adapter adapter){
        if (mWrapperAdapter != null && mWrapperAdapter.getOriginAdapter() != null){
            mWrapperAdapter.getOriginAdapter().unregisterAdapterDataObserver(mAdapterDataObserver);
            mWrapperAdapter = null;
        }

        refreshComplete();

        if (adapter != null){
            adapter.registerAdapterDataObserver(mAdapterDataObserver);
            mWrapperAdapter = new WrapperAdapter(adapter);
            mRecyclerView.setAdapter(mWrapperAdapter);
            mAdapterDataObserver.onChanged();
        }
    }

    //// public method

    public void addItemDecoration(RecyclerView.ItemDecoration decor){
        mRecyclerView.addItemDecoration(decor);
    }

    public void addHeaderView(DecorativeView view){
        if (view == null) return;

        mHeaderViews.put(TYPE_HEADER + getHeaderViewCount(), view);
        if (mWrapperAdapter != null){
            mWrapperAdapter.notifyItemInserted(getHeaderViewCount() - 1);
        }
    }

    public int getHeaderViewCount(){
        return mHeaderViews.size();
    }

    public void setLimitNumberToCallLoadMore(int limit){
        if (limit < 1){
            limit = 1;
        }

        limitNumberToCallLoadMore = limit;
    }

    public void setEmptyView(View emptyView){
        if (emptyView != null){
            if (emptyView.getParent() != null){
                ((ViewGroup)emptyView.getParent()).removeView(emptyView);
            }
            if (mEmptyView != null && mEmptyView.getParent() != null){
                ((ViewGroup)mEmptyView.getParent()).removeView(mEmptyView);
            }
            mEmptyView = emptyView;

            RelativeLayout.LayoutParams emptyParams = new RelativeLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT);
            addView(mEmptyView, emptyParams);

            mAdapterDataObserver.onChanged();
        }
    }

    public boolean isLoadingData(){
        return mRefreshLayout.isRefreshing() || isLoadingMoreData;
    }

    public OnLoadMoreListener getOnLoadMoreListener() {
        return onLoadMoreListener;
    }

    public void setOnLoadMoreListener(OnLoadMoreListener onLoadMoreListener) {
        this.onLoadMoreListener = onLoadMoreListener;
        setLoadMoreEnable(onLoadMoreListener != null);
    }

    public boolean isLoadMoreEnable() {
        return enableLoadMore;
    }

    public void setLoadMoreEnable(boolean enableLoadMore) {
        this.enableLoadMore = enableLoadMore;
    }

    public void loadMoreComplete(){
        isLoadingMoreData = false;
        setLoadMoreFooterState(LoadMoreFooter.STATE_COMPLETE);
    }

    public void setNoMore(boolean noMore){
        isLoadingMoreData = false;
        isNoMore = noMore;
        if (loadMoreFooter != null){
            if (noMore && mWrapperAdapter != null && mWrapperAdapter.getOriginItemCount() > 0){
                loadMoreFooter.setState(LoadMoreFooter.STATE_NOMORE);
            } else {
                loadMoreFooter.setState(LoadMoreFooter.STATE_COMPLETE);
            }
        }
        if (mAdapterDataObserver != null){
            mAdapterDataObserver.onChanged();
        }
    }

    public void addOnChildAttachStateChangeListener(RecyclerView.OnChildAttachStateChangeListener listener){
        mRecyclerView.addOnChildAttachStateChangeListener(listener);
    }

    //// private method

    private void processLoadMore(int position) {
        if (!isLoadingData()
                && getOnLoadMoreListener() != null
                && isLoadMoreEnable() && !isNoMore){
            int itemCount = mWrapperAdapter.getItemCount();
            if (position + limitNumberToCallLoadMore >= itemCount){
                isLoadingMoreData = true;
                setLoadMoreFooterState(LoadMoreFooter.STATE_LOADING);
                getOnLoadMoreListener().onLoadMore();
            }
        }
    }

    private void setLoadMoreFooterState(int state){
        if (loadMoreFooter != null){
            loadMoreFooter.setState(state);
        }
    }

    private void ensureEmptyNonNull(){
        if (mEmptyView != null) return;

        if (mEmptyStub != null){
            mEmptyView = mEmptyStub.inflate();
            mEmptyView.setVisibility(View.GONE);
        }
    }

    //// inner class

    private class WrapperAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

        private RecyclerView.Adapter mOriginAdapter;
        public WrapperAdapter(RecyclerView.Adapter adapter) {
            mOriginAdapter = adapter;
        }

        public RecyclerView.Adapter getOriginAdapter(){
            return mOriginAdapter;
        }

        public int getOriginItemCount(){
            if (mOriginAdapter != null){
                return mOriginAdapter.getItemCount();
            }

            return 0;
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            if (viewType == TYPE_FOOTER){
                return new SimpleViewHolder(loadMoreFooter);
            }
            if (viewType >= TYPE_HEADER){
                DecorativeView decorativeView = mHeaderViews.get(viewType);
                if (decorativeView != null){
                    View view = decorativeView.getView(parent);
                    if (view != null){
                        return new SimpleViewHolder(view);
                    }
                }
                return getEmptyViewHolder(parent);
            }
            return mOriginAdapter.onCreateViewHolder(parent, viewType);
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            if (isHeader(position) && isSimpleViewHolder(holder)){
                DecorativeView decorativeView = mHeaderViews.get(TYPE_HEADER + position);
                if (decorativeView != null){
                    decorativeView.bindView(position);
                }
            }else if (!isSimpleViewHolder(holder)){
                mOriginAdapter.onBindViewHolder(holder, getOriginPosition(position));
            }
        }

        @Override
        public int getItemViewType(int position) {
            if (isFooter(position)){
                return TYPE_FOOTER;
            }
            if (isHeader(position)){
                return TYPE_HEADER + position;
            }
            return mOriginAdapter.getItemViewType(getOriginPosition(position));
        }

        @Override
        public int getItemCount() {
            if (mOriginAdapter == null){
                return 0;
            }
            return mOriginAdapter.getItemCount() + (isLoadMoreEnable()?1:0) + getHeaderViewCount();
        }

        private boolean isHeader(int position){
            return position >= 0 && position < getHeaderViewCount();
        }

        private boolean isFooter(int position){
            return isLoadMoreEnable() && position == getItemCount() - 1;
        }

        private boolean isSimpleViewHolder(RecyclerView.ViewHolder viewHolder){
            return viewHolder != null && viewHolder instanceof SimpleViewHolder;
        }

        private int getOriginPosition(int position){
            if (isHeader(position) || isFooter(position)){
                return position;
            }

            return position - getHeaderViewCount();
        }

        @Override
        public void onAttachedToRecyclerView(RecyclerView recyclerView) {
            super.onAttachedToRecyclerView(recyclerView);
            if (mOriginAdapter != null){
                mOriginAdapter.onAttachedToRecyclerView(recyclerView);
            }
            RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
            if (layoutManager instanceof GridLayoutManager){
                final GridLayoutManager manager = (GridLayoutManager) layoutManager;
                final GridLayoutManager.SpanSizeLookup spanSizeLookup = manager.getSpanSizeLookup();
                manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                    @Override
                    public int getSpanSize(int position) {
                        if (isFooter(position)){
                            return manager.getSpanCount();
                        } else if (isHeader(position)){
                            return manager.getSpanCount();
                        } else if (spanSizeLookup != null){
                            return spanSizeLookup.getSpanSize(position);
                        }
                        return 1;
                    }
                });
            }
        }

        @Override
        public void onDetachedFromRecyclerView(RecyclerView recyclerView) {
            super.onDetachedFromRecyclerView(recyclerView);
            if (mOriginAdapter != null){
                mOriginAdapter.onDetachedFromRecyclerView(recyclerView);
            }
        }

        @Override
        public void onViewAttachedToWindow(RecyclerView.ViewHolder holder) {
            super.onViewAttachedToWindow(holder);
            ViewGroup.LayoutParams lp = holder.itemView.getLayoutParams();
            if (lp != null
                    && lp instanceof StaggeredGridLayoutManager.LayoutParams
                    && (isSimpleViewHolder(holder))) {
                StaggeredGridLayoutManager.LayoutParams p = (StaggeredGridLayoutManager.LayoutParams) lp;
                p.setFullSpan(true);
            }
            if (!isSimpleViewHolder(holder)){
                mOriginAdapter.onViewAttachedToWindow(holder);
            }
        }

        @Override
        public void onViewDetachedFromWindow(RecyclerView.ViewHolder holder) {
            if (!isSimpleViewHolder(holder))
                mOriginAdapter.onViewDetachedFromWindow(holder);
        }

        @Override
        public void onViewRecycled(RecyclerView.ViewHolder holder) {
            if (!isSimpleViewHolder(holder))
                mOriginAdapter.onViewRecycled(holder);
        }

        @Override
        public boolean onFailedToRecycleView(RecyclerView.ViewHolder holder) {
            if (isSimpleViewHolder(holder)){
                return true;
            }
            return mOriginAdapter.onFailedToRecycleView(holder);
        }

        @Override
        public void unregisterAdapterDataObserver(RecyclerView.AdapterDataObserver observer) {
            mOriginAdapter.unregisterAdapterDataObserver(observer);
        }

        @Override
        public void registerAdapterDataObserver(RecyclerView.AdapterDataObserver observer) {
            mOriginAdapter.registerAdapterDataObserver(observer);
        }
    }

    private class SimpleViewHolder extends RecyclerView.ViewHolder {
        public SimpleViewHolder(View itemView) {
            super(itemView);
        }
    }

    private SimpleViewHolder getEmptyViewHolder(ViewGroup parent){
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_empty_holder, parent, false);
        return new SimpleViewHolder(itemView);
    }

    private class PlasticAdapterDataObserver extends RecyclerView.AdapterDataObserver{
        @Override
        public void onChanged() {
            refreshComplete();

            if (mWrapperAdapter != null){
                mWrapperAdapter.notifyDataSetChanged();
            }

            int emptyCount = 0;
            if (isLoadMoreEnable()){
                emptyCount += 1;
            }

            if ((mWrapperAdapter != null && mWrapperAdapter.getItemCount() == emptyCount)
                    || mWrapperAdapter == null) {
                ensureEmptyNonNull();
                mEmptyView.setVisibility(View.VISIBLE);
                mRecyclerView.setVisibility(View.GONE);
            } else if (mWrapperAdapter != null){
                if (mEmptyView != null){
                    mEmptyView.setVisibility(View.GONE);
                }
                mRecyclerView.setVisibility(View.VISIBLE);
            }
        }

        @Override
        public void onItemRangeInserted(int positionStart, int itemCount) {
            super.onItemRangeInserted(positionStart, itemCount);
            if (mWrapperAdapter != null)
                mWrapperAdapter.notifyItemRangeInserted(positionStart, itemCount);
        }

        @Override
        public void onItemRangeMoved(int fromPosition, int toPosition, int itemCount) {
            super.onItemRangeMoved(fromPosition, toPosition, itemCount);
            if (mWrapperAdapter != null)
                mWrapperAdapter.notifyItemMoved(fromPosition, toPosition);
        }

        @Override
        public void onItemRangeRemoved(int positionStart, int itemCount) {
            super.onItemRangeRemoved(positionStart, itemCount);
            if (mWrapperAdapter != null)
                mWrapperAdapter.notifyItemRangeRemoved(positionStart, itemCount);
        }

        @Override
        public void onItemRangeChanged(int positionStart, int itemCount) {
            super.onItemRangeChanged(positionStart, itemCount);
            if (mWrapperAdapter != null)
                mWrapperAdapter.notifyItemRangeChanged(positionStart, itemCount);
        }

        @Override
        public void onItemRangeChanged(int positionStart, int itemCount, Object payload) {
            super.onItemRangeChanged(positionStart, itemCount, payload);
            if (mWrapperAdapter != null)
                mWrapperAdapter.notifyItemRangeChanged(positionStart, itemCount, payload);
        }
    }
}
