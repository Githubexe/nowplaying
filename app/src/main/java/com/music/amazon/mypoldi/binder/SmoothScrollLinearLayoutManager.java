package com.music.amazon.mypoldi.binder;

import android.content.Context;
import android.graphics.PointF;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSmoothScroller;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;

public class SmoothScrollLinearLayoutManager extends LinearLayoutManager {
    private static final float MILLISECONDS_PER_INCH = 100f;

    private final Context context;

    public SmoothScrollLinearLayoutManager(final Context context) {
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
                        return SmoothScrollLinearLayoutManager.this
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

    @Override
    public boolean supportsPredictiveItemAnimations() {
        return true;
    }
}