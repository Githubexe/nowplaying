package com.music.amazon.mypoldi.binder;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;

import com.music.amazon.mypoldi.adapter.LiveGameEventAdapter;
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
        view.scoreSeparator.setText("-");

        view.minutesTextView.setText(Integer.toString(model.minutes));
        view.secondsTextView.setText(Integer.toString(model.seconds));
        view.timeStampSeparator.setText(":");

        final LiveGameEventAdapter adapter = new LiveGameEventAdapter(model.events);
        view.gameEventRecyclerView.setAdapter(adapter);
        layoutManager.setStackFromEnd(true);

        view.gameEventRecyclerView.setLayoutManager(layoutManager);
        view.gameEventRecyclerView.scrollToPosition(adapter.getItemCount() - 1);

    }
}
