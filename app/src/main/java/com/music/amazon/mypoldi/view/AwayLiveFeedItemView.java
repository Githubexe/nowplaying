package com.music.amazon.mypoldi.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.TextView;

import com.music.amazon.mypoldi.R;

public final class AwayLiveFeedItemView extends LiveFeedItemView {

    public AwayLiveFeedItemView(Context context) {
        this(context, null);
    }

    public AwayLiveFeedItemView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public AwayLiveFeedItemView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        timeTextView = (TextView)findViewById(R.id.right_time_text_view);
        commentTextView = (TextView)findViewById(R.id.right_comment_text_view);
        smallImageView = (ImageView)findViewById(R.id.right_small_image_view);
        largeImageView = (ImageView)findViewById(R.id.right_large_image_view);
    }
}
