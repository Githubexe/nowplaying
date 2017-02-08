package com.music.amazon.mypoldi.binder;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;

import com.music.amazon.mypoldi.adapter.MatchEventAdapter;
import com.music.amazon.mypoldi.model.NowPlayingMatchDetailsModel;
import com.music.amazon.mypoldi.view.NowPlayingMatchDetailsView;

/**
 * Created by yoyosu on 1/25/17.
 */
public class NowPlayingMatchDetailsBinder {
    private Context context;

    private final LinearLayoutManager layoutManager;

    public NowPlayingMatchDetailsBinder(Context context) {
        this.context = context;
        layoutManager  = new LinearLayoutManager(context);
    }

    public NowPlayingMatchDetailsView createView() {
        return new NowPlayingMatchDetailsView(context);
    }

    public void bind(final NowPlayingMatchDetailsView view,
                     final NowPlayingMatchDetailsModel model){
        view.hostTeamScoreTextView.setText(model.hostTeamScore);
        view.visitingTeamScoreTextView.setText(model.visitingTeamScore);
        view.scoreSeparator.setText("-");

        view.minutesTextView.setText(Integer.toString(model.minutes));
        view.secondsTextView.setText(Integer.toString(model.seconds));
        view.timeStampSeparator.setText(":");

        final MatchEventAdapter adapter = new MatchEventAdapter(model.events);
        view.gameEventRecyclerView.setAdapter(adapter);
        layoutManager.setStackFromEnd(true);

        view.gameEventRecyclerView.setLayoutManager(layoutManager);
        view.gameEventRecyclerView.scrollToPosition(adapter.getItemCount() - 1);

    }
}
