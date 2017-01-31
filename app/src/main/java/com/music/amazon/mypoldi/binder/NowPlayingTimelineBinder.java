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

    public NowPlayingTimelineBinder(Context context) {
        this.context = context;
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

        GameEventAdapter adapter = new GameEventAdapter(model.events);
        view.gameEventRecyclerView.setAdapter(adapter);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        view.gameEventRecyclerView.setLayoutManager(layoutManager);

//        final ScrollingLinearLayoutManager scrollingLinearLayoutManager =
//                new ScrollingLinearLayoutManager(context, LinearLayoutManager.VERTICAL, false, 1000);

        view.gameEventRecyclerView.setLayoutManager(layoutManager);


       // layoutManager.scrollToPositionWithOffset(6, 200);
        VerticalSpaceItemDecoration verticalSpaceItemDecoration = new VerticalSpaceItemDecoration(
                context.getResources().getInteger(R.integer.now_playing_timeline_event_vertical_space));
        view.gameEventRecyclerView.addItemDecoration(verticalSpaceItemDecoration);
    }

    class VerticalSpaceItemDecoration extends RecyclerView.ItemDecoration {
        private final int verticalSpaceHeight;

        public VerticalSpaceItemDecoration(int verticalSpaceHeight) {
            this.verticalSpaceHeight = verticalSpaceHeight;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent,
                                   RecyclerView.State state) {
            outRect.bottom = verticalSpaceHeight;
        }
    }
}
