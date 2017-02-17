package com.music.amazon.mypoldi.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.music.amazon.mypoldi.R;

/**
 * Created by yoyosu on 2/17/17.
 */
public final class ChannelView extends RelativeLayout{

    public ChannelView(final Context context) {
        this(context, null);
    }

    public ChannelView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public ChannelView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
}

