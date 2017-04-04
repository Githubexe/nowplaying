package com.music.amazon.mypoldi.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.music.amazon.mypoldi.R;

public final class AwayLiveFeedItemView extends LinearLayout {
    public final TextView mainTextView;

    public final TextView subTextView;

    public final ImageView imageView1;

    public final ImageView imageView2;

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

        mainTextView = (TextView)findViewById(R.id.away_main_text_view);
        subTextView = (TextView)findViewById(R.id.away_sub_text_view);
        imageView1 = (ImageView)findViewById(R.id.away_image_view1);
        imageView2 = (ImageView)findViewById(R.id.away_image_view2);
    }
}
