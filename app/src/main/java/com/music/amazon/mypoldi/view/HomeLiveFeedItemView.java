package com.music.amazon.mypoldi.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.music.amazon.mypoldi.R;

public final class HomeLiveFeedItemView extends LinearLayout {
    public final TextView timeTextView;

    public final TextView commentTextView;

    public final ImageView smallImageView;

    public final ImageView largeImageView;

    public HomeLiveFeedItemView(final Context context) {
        this(context, null);
    }

    public HomeLiveFeedItemView(final Context context,
                                final AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public HomeLiveFeedItemView(final Context context,
                                final AttributeSet attrs,
                                final int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        inflate(getContext(), R.layout.live_feed_item_home, this);

        timeTextView = (TextView)findViewById(R.id.home_time_text_view);
        commentTextView = (TextView)findViewById(R.id.home_comment_text_view);
        smallImageView = (ImageView)findViewById(R.id.home_small_image_view);
        largeImageView = (ImageView)findViewById(R.id.home_large_image_view);
    }
}
