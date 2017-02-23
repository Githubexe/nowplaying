package com.music.amazon.mypoldi.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

import com.music.amazon.mypoldi.binder.LeftLiveFeedItemBinder;
import com.music.amazon.mypoldi.binder.RightLiveFeedItemBinder;
import com.music.amazon.mypoldi.binder.SmoothScrollLinearLayoutManager;
import com.music.amazon.mypoldi.dmtv.UniversalAdapter;

/**
 * Created by yoyosu on 2/22/17.
 */
public final class LiveFeedItemView extends RecyclerView {
    private final UniversalAdapter adapter;

    public LiveFeedItemView(Context context) {
        this(context, null);
    }

    public LiveFeedItemView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LiveFeedItemView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

        final SmoothScrollLinearLayoutManager smoothScrollLinearLayoutManager =
                new SmoothScrollLinearLayoutManager(context);
        smoothScrollLinearLayoutManager.setStackFromEnd(true);
        setLayoutManager(smoothScrollLinearLayoutManager);
        adapter = new UniversalAdapter(
                new LeftLiveFeedItemBinder(),
                new RightLiveFeedItemBinder());
        setAdapter(adapter);
    }

    @Override
    public Adapter getAdapter() {
        return adapter;
    }
}
