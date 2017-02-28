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

    public final TextView homeTextView;

    public final ImageView homeImageView;

    public final TextView awayTextView;

    public final ImageView awayImageView;

    public final LiveFeedView liveFeedView;

    public LiveFeedBackgroundView(final Context context) {
        this(context, null);
    }

    public LiveFeedBackgroundView(final Context context,
                                  final AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public LiveFeedBackgroundView(final Context context,
                                  final AttributeSet attrs,
                                  final int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        inflate(getContext(), R.layout.live_feed_background, this);
        backgroundImageView = (ImageView)findViewById(R.id.live_feed_background_image);
        homeTextView = (TextView)findViewById(R.id.home_text_view);
        homeImageView = (ImageView)findViewById(R.id.home_image_view);
        awayImageView = (ImageView)findViewById(R.id.away_image_view);
        awayTextView = (TextView)findViewById(R.id.away_text_view);
        liveFeedView = (LiveFeedView)findViewById(R.id.live_feed_view);
    }
}