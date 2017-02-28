package com.music.amazon.mypoldi.view;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.music.amazon.mypoldi.R;
import com.music.amazon.mypoldi.dmtv.UniversalAdapter;
import com.music.amazon.mypoldi.model.LiveFeedItemModel;
import com.music.amazon.mypoldi.model.LiveFeedModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yoyosu on 1/24/17.
 */
public final class LiveFeedView extends RelativeLayout {

    public final RecyclerView liveFeedItemRecyclerView;

    public final TextView homeScoreTextView;

    public final TextView awayScoreTextView;

    public final TextView minutesTextView;

    public final TextView secondsTextView;

    public final TextView timeStampSeparator;

    public final TextView scoreSeparator;

    public LiveFeedView(Context context) {
        this(context, null);
    }

    public LiveFeedView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public LiveFeedView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        inflate(getContext(), R.layout.live_feed_update_view, this);
        homeScoreTextView = (TextView)findViewById(R.id.host_score_text_view);
        awayScoreTextView = (TextView)findViewById(R.id.away_score_text_view);
        scoreSeparator = (TextView)findViewById(R.id.score_separator_text_view);
        minutesTextView = (TextView)findViewById(R.id.minute_text_view);
        secondsTextView = (TextView)findViewById(R.id.second_text_view);
        timeStampSeparator = (TextView)findViewById(R.id.minute_second_separator_text_view);

        liveFeedItemRecyclerView = (RecyclerView)findViewById(R.id.live_feed_item_view);
        final SmoothScrollLinearLayoutManager smoothScrollLinearLayoutManager =
                new SmoothScrollLinearLayoutManager(context);
        smoothScrollLinearLayoutManager.setStackFromEnd(true);
        liveFeedItemRecyclerView.setLayoutManager(smoothScrollLinearLayoutManager);
    }

    public void setAdapter(final UniversalAdapter adapter) {
        liveFeedItemRecyclerView.setAdapter(adapter);
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

    public void bind(LiveFeedModel model) {
        homeScoreTextView.setText(Integer.toString(model.getHomeScore()));
        awayScoreTextView.setText(Integer.toString(model.getAwayScore()));
        scoreSeparator.setText("-");

        //Assume the time is in the format of "minutes : seconds"
        final String[] time = model.getElapsedTime().get().split(":");
        if (time.length >= 1) {
            minutesTextView.setText(time[0].trim());
        }
        if (time.length == 2) {
            timeStampSeparator.setText(":");
            secondsTextView.setText(time[1].trim());
        }
    }
}
