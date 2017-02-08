package com.music.amazon.mypoldi.binder;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;

import com.music.amazon.mypoldi.adapter.MatchLiveEventAdapter;
import com.music.amazon.mypoldi.model.NowPlayingMatchLiveEventModel;
import com.music.amazon.mypoldi.view.NowPlayingMatchLiveEventView;

/**
 * Created by yoyosu on 1/25/17.
 */
public class NowPlayingMatchLiveEventBinder {
    private Context context;

    private final LinearLayoutManager layoutManager;

    public NowPlayingMatchLiveEventBinder(Context context) {
        this.context = context;
        layoutManager  = new LinearLayoutManager(context);
    }

    public NowPlayingMatchLiveEventView createView() {
        return new NowPlayingMatchLiveEventView(context);
    }

    public void bind(final NowPlayingMatchLiveEventView view,
                     final NowPlayingMatchLiveEventModel model){
        view.hostTeamScoreTextView.setText(model.hostTeamScore);
        view.visitingTeamScoreTextView.setText(model.visitingTeamScore);
        view.scoreSeparator.setText("-");

        view.minutesTextView.setText(Integer.toString(model.minutes));
        view.secondsTextView.setText(Integer.toString(model.seconds));
        view.timeStampSeparator.setText(":");

        final MatchLiveEventAdapter adapter = new MatchLiveEventAdapter(model.events);
        view.gameEventRecyclerView.setAdapter(adapter);
        layoutManager.setStackFromEnd(true);

        view.gameEventRecyclerView.setLayoutManager(layoutManager);
        view.gameEventRecyclerView.scrollToPosition(adapter.getItemCount() - 1);

    }
}
