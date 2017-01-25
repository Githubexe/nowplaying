package com.music.amazon.mypoldi.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.music.amazon.mypoldi.R;

/**
 * Created by yoyosu on 1/24/17.
 */
public final class NowPlayingTimelineView extends RelativeLayout {
    public ImageView leftCard;

    public TextView leftText;

    public ImageView rightCard;

    public TextView rightText;


    public NowPlayingTimelineView(Context context) {
        this(context, null);

    }

    public NowPlayingTimelineView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public NowPlayingTimelineView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        inflate(getContext(), R.layout.row, this);
        leftCard = (ImageView)findViewById(R.id.leftCard);
        leftText = (TextView)findViewById(R.id.leftText);
        rightCard = (ImageView)findViewById(R.id.rightCard);
        rightText = (TextView) findViewById(R.id.rightText);
    }
}
