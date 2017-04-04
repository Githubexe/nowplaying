package com.music.amazon.mypoldi.view;

import android.content.Context;
import android.content.res.Resources;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.music.amazon.mypoldi.R;
import com.music.amazon.mypoldi.dmtv.UniversalAdapter;
import com.music.amazon.mypoldi.model.LiveFeedItemModel;

/**
 * Created by yoyosu on 1/24/17.
 */
public final class LiveFeedView extends RelativeLayout {

    public final RecyclerView liveFeedItemRecyclerView;

    public final TextView homeScoreTextView;

    public final TextView awayScoreTextView;

    public final TextView minutesTextView;

    public final TextView scoreSeparator;

    public LiveFeedView(final Context context) {
        this(context, null);
    }

    public LiveFeedView(final Context context,
                        final AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public LiveFeedView(final Context context,
                        final AttributeSet attrs,
                        final int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        inflate(getContext(), R.layout.live_feed_view, this);
        homeScoreTextView = (TextView)findViewById(R.id.home_score_text_view);
        awayScoreTextView = (TextView)findViewById(R.id.away_score_text_view);
        scoreSeparator = (TextView)findViewById(R.id.score_separator_text_view);
        minutesTextView = (TextView)findViewById(R.id.minute_text_view);

        liveFeedItemRecyclerView = (RecyclerView)findViewById(R.id.live_feed_item_view);
        final SmoothScrollLinearLayoutManager smoothScrollLinearLayoutManager =
                new SmoothScrollLinearLayoutManager(context);
        smoothScrollLinearLayoutManager.setStackFromEnd(true);
        liveFeedItemRecyclerView.setLayoutManager(smoothScrollLinearLayoutManager);
    }

    public void setAdapter(final UniversalAdapter adapter) {
        liveFeedItemRecyclerView.setAdapter(adapter);
    }

    public void addItem(final LiveFeedItemModel data) {
        final RecyclerView.Adapter adapter = liveFeedItemRecyclerView.getAdapter();
        if (adapter instanceof UniversalAdapter) {
            ((UniversalAdapter)adapter).addItem(data);
            final Resources resources = getResources();
            final float dimenInDp = resources.getDimension(R.dimen.live_feed_height)/
                    resources.getDisplayMetrics().density;
            if (liveFeedItemRecyclerView.getHeight() >= dimenInDp) {
                liveFeedItemRecyclerView.smoothScrollToPosition(adapter.getItemCount() - 1);
            }
        } else {
            throw new RuntimeException("Not using UniversalAdapter");
        }
    }

}
