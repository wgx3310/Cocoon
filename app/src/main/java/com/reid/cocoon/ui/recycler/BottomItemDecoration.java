package com.reid.cocoon.ui.recycler;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.reid.cocoon.R;

public class BottomItemDecoration extends RecyclerView.ItemDecoration {

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        outRect.bottom = parent.getContext().getResources().getDimensionPixelSize(R.dimen.dp_20);
    }
}
