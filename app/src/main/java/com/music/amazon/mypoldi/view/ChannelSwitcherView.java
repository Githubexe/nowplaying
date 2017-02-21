package com.music.amazon.mypoldi.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.ViewFlipper;

import com.music.amazon.mypoldi.R;

/**
 * Created by yoyosu on 2/17/17.
 */
public final class ChannelSwitcherView extends RelativeLayout{

    public final ViewFlipper viewFlipper;

    public ChannelSwitcherView(final Context context) {
        this(context, null);
    }

    public ChannelSwitcherView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public ChannelSwitcherView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        inflate(getContext(), R.layout.live_feed_channel, this);

        viewFlipper = (ViewFlipper) findViewById(R.id.live_feed_flipper);
        viewFlipper.setInAnimation(context, R.anim.live_feed_flipper_in_from_right);
        viewFlipper.setOutAnimation(context, R.anim.live_feed_flipper_out_to_left);
    }

    public View getCurrentView() {
        return viewFlipper.getCurrentView();
    }

    public int showNext() {
        viewFlipper.showNext();
        return viewFlipper.getDisplayedChild();
    }

    public int showPrevious() {
        viewFlipper.showPrevious();
        return viewFlipper.getDisplayedChild();
    }

}

