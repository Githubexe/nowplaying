package com.music.amazon.mypoldi.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.music.amazon.mypoldi.R;

/**
 * Created by yoyosu on 1/16/17.
 */
public final class LiveFeedBackgroundView extends RelativeLayout {

    public final ImageView backgroundImageView;

    public final TextView hostTextView;

    public final ImageView hostImageView;

    public final TextView awayTextView;

    public final ImageView awayImageView;

    public LiveFeedBackgroundView(Context context) {
        this(context, null);
    }

    public LiveFeedBackgroundView(Context context, AttributeSet attributeSet) {
       this(context, attributeSet, 0);
    }

    public LiveFeedBackgroundView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        inflate(getContext(), R.layout.live_feed_background, this);
        backgroundImageView = (ImageView)findViewById(R.id.live_feed_background_image);
        hostTextView = (TextView)findViewById(R.id.host_text_view);
        hostImageView = (ImageView)findViewById(R.id.host_image_view);
        awayImageView = (ImageView)findViewById(R.id.away_image_view);
        awayTextView = (TextView)findViewById(R.id.away_text_view);
    }

    public LiveFeedUpdateView getLiveFeedUpdateView() {
        return (LiveFeedUpdateView)findViewById(R.id.live_feed_view);
    }
}