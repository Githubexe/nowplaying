package com.music.amazon.mypoldi.binder;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.music.amazon.mypoldi.GameEventAdapter;
import com.music.amazon.mypoldi.R;
import com.music.amazon.mypoldi.model.NowPlayingTimelineModel;
import com.music.amazon.mypoldi.view.NowPlayingTimelineView;

/**
 * Created by yoyosu on 1/25/17.
 */
public class NowPlayingTimelineBinder {
    private Context context;

    public NowPlayingTimelineView createView(Context context) {
        this.context = context;
        return new NowPlayingTimelineView(context);
    }

    public void bind(final NowPlayingTimelineView view,
                     final NowPlayingTimelineModel model){
        view.scoreTextView.setText(model.score);

        view.timeTextView.setText(model.time);

        GameEventAdapter adapter = new GameEventAdapter(context);
        view.gameEventRecyclerView.setAdapter(adapter);
        view.gameEventRecyclerView.setLayoutManager(new LinearLayoutManager((context)));
    }
}
