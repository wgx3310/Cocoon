package com.reid.cocoon.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.reid.cocoon.R;
import com.reid.cocoon.data.utils.DataWrapper;
import com.reid.cocoon.ui.recycler.adapter.ListAdapter;

public class AboutActivity extends BasicActivity {

    private RecyclerView mRecyclerView;
    private ListAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        initToolbar(R.string.app_name);

        mRecyclerView = findViewById(R.id.recycler);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new ListAdapter();
        mAdapter.setData(DataWrapper.buildAboutData());
        mRecyclerView.setAdapter(mAdapter);
    }
}
