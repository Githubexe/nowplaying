package com.music.amazon.mypoldi.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.music.amazon.mypoldi.R;

public final class AwayLiveFeedItemView extends LinearLayout {
    public final TextView timeTextView;

    public final TextView commentTextView;

    public final ImageView smallImageView;

    public final ImageView largeImageView;

    public AwayLiveFeedItemView(final Context context) {
        this(context, null);
    }

    public AwayLiveFeedItemView(final Context context,
                                final AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public AwayLiveFeedItemView(final Context context,
                                final AttributeSet attrs,
                                final int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        inflate(getContext(), R.layout.live_feed_item_away, this);

        timeTextView = (TextView)findViewById(R.id.away_time_text_view);
        commentTextView = (TextView)findViewById(R.id.away_comment_text_view);
        smallImageView = (ImageView)findViewById(R.id.away_small_image_view);
        largeImageView = (ImageView)findViewById(R.id.away_large_image_view);
    }
}