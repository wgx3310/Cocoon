package com.reid.cocoon.ui.recycler.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.reid.cocoon.R;
import com.reid.cocoon.ui.recycler.viewholder.EmptyViewHolder;
import com.reid.cocoon.ui.recycler.viewholder.ItemViewHolder;
import com.reid.cocoon.ui.recycler.utils.ViewHolderFactory;
import com.reid.cocoon.data.model.Component;
import com.reid.cocoon.data.model.Data;

import java.util.ArrayList;
import java.util.List;

public class ListAdapter extends RecyclerView.Adapter<ItemViewHolder> {

    private List<Component> components = new ArrayList<>();

    public void setData(Data data){
        if (data != null && data.components != null && data.components.size() > 0){
            components.clear();
            components.addAll(data.components);
            notifyDataSetChanged();
        }
    }

    public void addData(Data data){
        if (data != null && data.components != null && data.components.size() > 0){
            components.addAll(data.components);
            notifyDataSetChanged();
        }
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        int resId = ViewHolderFactory.getLayoutByViewType(viewType);

        View inflate = LayoutInflater.from(parent.getContext()).inflate(resId, parent, false);

        ItemViewHolder holder = ViewHolderFactory.getViewHolderByViewType(viewType, inflate);

        if (holder == null){
            inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_empty_component, parent, false);
            holder = new EmptyViewHolder(inflate);
        }

        return holder;
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
        holder.bindData(components.get(position));
    }

    @Override
    public int getItemViewType(int position) {

        if (components != null && components.get(position) != null){
            Component component = components.get(position);
            return component.type;
        }
        return super.getItemViewType(position);
    }

    @Override
    public int getItemCount() {
        if (components == null || components.size() <= 0){
            return 0;
        }

        return components.size();
    }

}
