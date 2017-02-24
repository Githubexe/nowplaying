package com.music.amazon.mypoldi.view;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.music.amazon.mypoldi.R;
import com.music.amazon.mypoldi.dmtv.UniversalAdapter;
import com.music.amazon.mypoldi.model.LiveFeedItemModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yoyosu on 1/24/17.
 */
public final class LiveFeedUpdateView extends RelativeLayout {

    public final LiveFeedItemRecyclerView liveFeedItemRecyclerView;

    public final TextView hostTeamScoreTextView;

    public final TextView visitingTeamScoreTextView;

    public final TextView minutesTextView;

    public final TextView secondsTextView;

    public final TextView timeStampSeparator;

    public final TextView scoreSeparator;

    public LiveFeedUpdateView(Context context) {
        this(context, null);
    }

    public LiveFeedUpdateView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public LiveFeedUpdateView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        inflate(getContext(), R.layout.live_feed_update_view, this);
        hostTeamScoreTextView = (TextView)findViewById(R.id.host_score_text_view);
        visitingTeamScoreTextView = (TextView)findViewById(R.id.away_score_text_view);
        scoreSeparator = (TextView)findViewById(R.id.score_separator_text_view);
        minutesTextView = (TextView)findViewById(R.id.minute_text_view);
        secondsTextView = (TextView)findViewById(R.id.second_text_view);
        timeStampSeparator = (TextView)findViewById(R.id.minute_second_separator_text_view);

        liveFeedItemRecyclerView = (LiveFeedItemRecyclerView)findViewById(R.id.live_feed_item_view);
    }

    public void addItem(LiveFeedItemModel data) {
        final List<LiveFeedItemModel> added = new ArrayList<>();
        added.add(data);
        addToAdapter(added);
    }

    private void addToAdapter(List<?> data) {
        final RecyclerView.Adapter adapter = liveFeedItemRecyclerView.getAdapter();
        if (adapter instanceof UniversalAdapter) {
            ( (UniversalAdapter)adapter).addItems(data);
        }
        liveFeedItemRecyclerView.smoothScrollToPosition(
                adapter.getItemCount() - 1);
    }
}
