package com.music.amazon.mypoldi.binder;

import android.content.Context;
import android.graphics.PointF;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSmoothScroller;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;

import com.music.amazon.mypoldi.model.LiveFeedModel;
import com.music.amazon.mypoldi.view.LiveFeedView;

/**
 * Created by yoyosu on 1/25/17.
 */
public class LiveFeedBinder {
    private Context context;

    private final LinearLayoutManager layoutManager;

    public LiveFeedBinder(Context context) {
        this.context = context;
        layoutManager  = new MyCustomLayoutManager(context);
    }

    public LiveFeedView createView() {
        return new LiveFeedView(context);
    }

    public void bind(final LiveFeedView view,
                     final LiveFeedModel model){
        view.hostTeamScoreTextView.setText(Integer.toString(model.hostScore));
        view.visitingTeamScoreTextView.setText(Integer.toString(model.awayScore));
        view.scoreSeparator.setText("-");

        //Assume the time is in the format of "minutes : seconds"
        final String[] time = model.elapsedTime.split(":");
        if (time.length >= 1) {
            view.minutesTextView.setText(time[0].trim());
        }
        if (time.length == 2) {
            view.timeStampSeparator.setText(":");
            view.secondsTextView.setText(time[1].trim());
        }

        final LiveFeedItemBinder adapter = new LiveFeedItemBinder(model.events, context);
        view.liveFeedItemViewLayout.setAdapter(adapter);
        layoutManager.setStackFromEnd(true);

        view.liveFeedItemViewLayout.setLayoutManager(layoutManager);
        view.liveFeedItemViewLayout.smoothScrollToPosition(adapter.getItemCount() - 1);
    }

    private class MyCustomLayoutManager extends LinearLayoutManager {
        private static final float MILLISECONDS_PER_INCH = 100f;

        private Context context;

        public MyCustomLayoutManager(Context context) {
            super(context);
            this.context = context;
        }

        @Override
        public void smoothScrollToPosition(RecyclerView recyclerView,
                                           RecyclerView.State state, final int position) {
            LinearSmoothScroller smoothScroller =
                    new LinearSmoothScroller(context) {
                        @Override
                        public PointF computeScrollVectorForPosition(int targetPosition) {
                            return MyCustomLayoutManager.this
                                    .computeScrollVectorForPosition(targetPosition);
                        }

                        //This returns the milliseconds it takes to scroll one pixel.
                        @Override
                        protected float calculateSpeedPerPixel(DisplayMetrics displayMetrics) {
                            return MILLISECONDS_PER_INCH/displayMetrics.densityDpi;
                        }
                    };

            smoothScroller.setTargetPosition(position);
            startSmoothScroll(smoothScroller);
        }
    }

}
