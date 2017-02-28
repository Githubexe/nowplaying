package com.music.amazon.mypoldi.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.TextView;

import com.music.amazon.mypoldi.R;

public final class HomeLiveFeedItemView extends LiveFeedItemView {

    public HomeLiveFeedItemView(Context context) {
        this(context, null);
    }

    public HomeLiveFeedItemView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public HomeLiveFeedItemView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        timeTextView = (TextView)findViewById(R.id.left_time_text_view);
        commentTextView = (TextView)findViewById(R.id.left_comment_text_view);
        smallImageView = (ImageView)findViewById(R.id.left_small_image_view);
        largeImageView = (ImageView)findViewById(R.id.left_large_image_view);
    }
}