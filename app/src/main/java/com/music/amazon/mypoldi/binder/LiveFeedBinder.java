package com.music.amazon.mypoldi.binder;

import android.content.Context;
import android.graphics.PointF;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSmoothScroller;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;

import com.music.amazon.mypoldi.adapter.LiveFeedItemAdapter;
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
        view.hostTeamScoreTextView.setText(model.hostTeamScore);
        view.visitingTeamScoreTextView.setText(model.visitingTeamScore);
        view.scoreSeparator.setText("-");

        view.minutesTextView.setText(Integer.toString(model.minutes));
        view.secondsTextView.setText(Integer.toString(model.seconds));
        view.timeStampSeparator.setText(":");

        final LiveFeedItemAdapter adapter = new LiveFeedItemAdapter(model.events, context);
        view.gameEventRecyclerView.setAdapter(adapter);
        layoutManager.setStackFromEnd(true);

        view.gameEventRecyclerView.setLayoutManager(layoutManager);
        view.gameEventRecyclerView.smoothScrollToPosition(adapter.getItemCount() - 1);
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
