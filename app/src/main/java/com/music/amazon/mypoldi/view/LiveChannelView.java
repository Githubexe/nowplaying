package com.music.amazon.mypoldi.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;
import android.widget.ViewFlipper;

import com.music.amazon.mypoldi.R;

/**
 * Created by yoyosu on 2/17/17.
 */
public final class LiveChannelView extends RelativeLayout{

    public final ViewFlipper viewFlipper;

    public LiveChannelView(final Context context) {
        this(context, null);
    }

    public LiveChannelView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public LiveChannelView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        inflate(getContext(), R.layout.live_feed_channel, this);
        viewFlipper = (ViewFlipper) findViewById(R.id.live_feed_flipper);
    }

}

