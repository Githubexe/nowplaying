package com.music.amazon.mypoldi.view;

import android.content.Context;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.music.amazon.mypoldi.R;

import org.w3c.dom.Text;

/**
 * Created by yoyosu on 1/24/17.
 */
public final class NowPlayingTimelineView extends RelativeLayout {

    public RecyclerView gameEventRecyclerView;

    public TextView hostTeamScoreTextView;

    public TextView visitingTeamScoreTextView;

    public TextView leftTimeStampTextView;

    public TextView rightTimeStampTextView;

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
        hostTeamScoreTextView = (TextView)findViewById(R.id.now_playing_host_team_score_text);
        visitingTeamScoreTextView = (TextView)findViewById(R.id.now_playing_visiting_team_score_text);
        leftTimeStampTextView = (TextView)findViewById(R.id.now_playing_left_time_stamp_text);
        rightTimeStampTextView = (TextView)findViewById(R.id.now_playing_right_time_stamp_text);
        gameEventRecyclerView = (RecyclerView) findViewById(R.id.game_event_recycler_view);
    }
}
