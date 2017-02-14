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
public final class LiveFeedView extends RelativeLayout {

    public RecyclerView liveFeedItemViewLayout;

    public TextView hostTeamScoreTextView;

    public TextView visitingTeamScoreTextView;

    public TextView minutesTextView;

    public TextView secondsTextView;

    public TextView timeStampSeparator;

    public TextView scoreSeparator;

    public LiveFeedView(Context context) {
        this(context, null);
    }

    public LiveFeedView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public LiveFeedView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        inflate(getContext(), R.layout.live_feed_view, this);
        hostTeamScoreTextView = (TextView)findViewById(R.id.host_score_text_view);
        visitingTeamScoreTextView = (TextView)findViewById(R.id.away_score_text_view);
        scoreSeparator = (TextView)findViewById(R.id.score_separator_text_view);
        minutesTextView = (TextView)findViewById(R.id.minute_text_view);
        secondsTextView = (TextView)findViewById(R.id.second_text_view);
        timeStampSeparator = (TextView)findViewById(R.id.minute_second_separator_text_view);
        liveFeedItemViewLayout = (RecyclerView) findViewById(R.id.live_feed_item_view);
    }
}