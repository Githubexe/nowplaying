package com.music.amazon.mypoldi.binder;

import android.content.Context;
import android.graphics.Rect;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.music.amazon.mypoldi.GameEventAdapter;
import com.music.amazon.mypoldi.R;
import com.music.amazon.mypoldi.model.NowPlayingTimelineModel;
import com.music.amazon.mypoldi.view.NowPlayingTimelineView;

/**
 * Created by yoyosu on 1/25/17.
 */
public class NowPlayingTimelineBinder {
    private Context context;

    private final LinearLayoutManager layoutManager;

    public NowPlayingTimelineBinder(Context context) {
        this.context = context;
        layoutManager  = new LinearLayoutManager(context);
    }

    public NowPlayingTimelineView createView() {
        return new NowPlayingTimelineView(context);
    }

    public void bind(final NowPlayingTimelineView view,
                     final NowPlayingTimelineModel model){
        view.hostTeamScoreTextView.setText(model.hostTeamScore);
        view.visitingTeamScoreTextView.setText(model.visitingTeamScore);

        view.minutesTextView.setText(model.minutes);
        view.secondsTextView.setText(model.seconds);

        final GameEventAdapter adapter = new GameEventAdapter(model.events);
        view.gameEventRecyclerView.setAdapter(adapter);

        view.gameEventRecyclerView.setLayoutManager(layoutManager);

        view.gameEventRecyclerView.scrollToPosition(adapter.getItemCount() - 1);
    }
}
