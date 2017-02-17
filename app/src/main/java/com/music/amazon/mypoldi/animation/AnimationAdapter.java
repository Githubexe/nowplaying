package com.music.amazon.mypoldi.animation;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.music.amazon.mypoldi.R;
import com.music.amazon.mypoldi.dmtv.UniversalAdapter;

import java.util.List;

/**
 * Created by yoyosu on 2/16/17.
 */
public class AnimationAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private RecyclerView.Adapter adapter;

    public AnimationAdapter(UniversalAdapter adapter) {
        this.adapter = adapter;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return adapter.onCreateViewHolder(parent, viewType);
    }

    @Override
    public void registerAdapterDataObserver(RecyclerView.AdapterDataObserver observer) {
        super.registerAdapterDataObserver(observer);
        adapter.registerAdapterDataObserver(observer);
    }

    @Override
    public void unregisterAdapterDataObserver(RecyclerView.AdapterDataObserver observer) {
        super.unregisterAdapterDataObserver(observer);
        adapter.unregisterAdapterDataObserver(observer);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        adapter.onBindViewHolder(holder, position);
        Animation animation = AnimationUtils.loadAnimation(holder.itemView.getContext(),
                R.anim.live_feed_item_up_from_bottom);
        holder.itemView.startAnimation(animation);
    }


    public void addItems(final List<?> items) {
        ((UniversalAdapter)adapter).addItems(items);
    }

    @Override
    public void onViewRecycled(RecyclerView.ViewHolder holder) {
        adapter.onViewRecycled(holder);
    }

    @Override
    public int getItemCount() {
        return adapter.getItemCount();
    }

    @Override
    public int getItemViewType(int position) {
        return adapter.getItemViewType(position);
    }

    @Override
    public long getItemId(int position) {
        return adapter.getItemId(position);
    }

    @Override
    public void onViewDetachedFromWindow(RecyclerView.ViewHolder holder) {
        adapter.onViewDetachedFromWindow(holder);
        holder.itemView.clearAnimation();
    }


}