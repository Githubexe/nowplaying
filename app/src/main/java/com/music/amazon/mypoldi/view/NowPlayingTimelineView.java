package com.music.amazon.mypoldi.view;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.music.amazon.mypoldi.GameEventAdapter;
import com.music.amazon.mypoldi.R;

/**
 * Created by yoyosu on 1/24/17.
 */
public final class NowPlayingTimelineView extends RelativeLayout {

    private Context context;

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
        this.context = context;
        init();
    }

    private void init() {
        inflate(getContext(), R.layout.now_playing_game_event_view, this);
        scoreTextView = (TextView)findViewById(R.id.now_playing_score_text);
        timeTextView = (TextView)findViewById(R.id.now_playing_time_text);


        RecyclerView rv = (RecyclerView) findViewById(R.id.game_event_recycler_view);
        GameEventAdapter adapter = new GameEventAdapter(context);
        rv.setAdapter(adapter);
        rv.setLayoutManager(new LinearLayoutManager((context)));
    }

}
