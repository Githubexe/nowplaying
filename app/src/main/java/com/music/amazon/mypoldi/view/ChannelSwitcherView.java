package com.music.amazon.mypoldi.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.ViewFlipper;

import com.music.amazon.mypoldi.R;
import com.music.amazon.mypoldi.binder.ChannelSwitchListener;

/**
 * Created by yoyosu on 2/17/17.
 */
public final class ChannelSwitcherView extends RelativeLayout{

    private ChannelSwitchListener channelSwitchListener;

    public final ViewFlipper viewFlipper;

    public ChannelSwitcherView(final Context context) {
        this(context, null);
    }

    public ChannelSwitcherView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public ChannelSwitcherView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        inflate(getContext(), R.layout.channel, this);
        setFocusable(true);

        viewFlipper = (ViewFlipper)findViewById(R.id.channel_flipper);
        viewFlipper.setInAnimation(context, R.anim.in_from_right);
        viewFlipper.setOutAnimation(context, R.anim.out_to_left);
    }

    public View getCurrentView() {
        return viewFlipper.getCurrentView();
    }

    public void register(final ChannelSwitchListener listener) {
        channelSwitchListener = listener;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (event.getAction() == KeyEvent.ACTION_DOWN) {
            if (keyCode == KeyEvent.KEYCODE_DPAD_LEFT) {
                viewFlipper.showPrevious();
                event.startTracking();
                if (channelSwitchListener != null) {
                    channelSwitchListener.onChannelSwitched(
                            viewFlipper.getDisplayedChild());
                }
                return true;
            } else if (keyCode == KeyEvent.KEYCODE_DPAD_RIGHT) {
                viewFlipper.showNext();
                event.startTracking();
                if (channelSwitchListener != null) {
                    channelSwitchListener.onChannelSwitched(
                            viewFlipper.getDisplayedChild());
                }
                return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }
}

