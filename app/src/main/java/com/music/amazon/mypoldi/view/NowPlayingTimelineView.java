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
public final class NowPlayingTimelineView extends RelativeLayout {

    public RecyclerView gameEventRecyclerView;

    public TextView scoreTextView;

    public TextView timeTextView;


    public NowPlayingTimelineView(Context context) {
        this(context, null);
    }

    public NowPlayingTimelineView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public NowPlayingTimelineView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        inflate(getContext(), R.layout.now_playing_game_event_view, this);
        scoreTextView = (TextView)findViewById(R.id.now_playing_score_text);
        timeTextView = (TextView)findViewById(R.id.now_playing_time_text);
        gameEventRecyclerView = (RecyclerView) findViewById(R.id.game_event_recycler_view);
    }

}
