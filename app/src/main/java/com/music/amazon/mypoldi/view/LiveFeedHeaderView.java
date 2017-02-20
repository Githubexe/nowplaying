package com.music.amazon.mypoldi.view;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.music.amazon.mypoldi.R;

/**
 * Created by yoyosu on 1/24/17.
 */
public final class LiveFeedHeaderView extends RelativeLayout {

    public final RecyclerView liveFeedItemView;

    public final TextView hostTeamScoreTextView;

    public final TextView visitingTeamScoreTextView;

    public final TextView minutesTextView;

    public final TextView secondsTextView;

    public final TextView timeStampSeparator;

    public final TextView scoreSeparator;

    public LiveFeedHeaderView(Context context) {
        this(context, null);
    }

    public LiveFeedHeaderView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public LiveFeedHeaderView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        inflate(getContext(), R.layout.live_feed_header_view, this);
        hostTeamScoreTextView = (TextView)findViewById(R.id.host_score_text_view);
        visitingTeamScoreTextView = (TextView)findViewById(R.id.away_score_text_view);
        scoreSeparator = (TextView)findViewById(R.id.score_separator_text_view);
        minutesTextView = (TextView)findViewById(R.id.minute_text_view);
        secondsTextView = (TextView)findViewById(R.id.second_text_view);
        timeStampSeparator = (TextView)findViewById(R.id.minute_second_separator_text_view);
        liveFeedItemView = (RecyclerView) findViewById(R.id.live_feed_item_view);
    }
}
