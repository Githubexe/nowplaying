package com.music.amazon.mypoldi.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.music.amazon.mypoldi.R;

/**
 * Created by yoyosu on 2/13/17.
 */
public final class LiveFeedItemView extends RelativeLayout {
    public TextView leftTimeTextView;

    public TextView leftDescriptionTextView;

    public ImageView leftSmallImageView;

    public ImageView leftLargeImageView;

    public TextView rightTimeTextView;

    public TextView rightDescriptionTextView;

    public ImageView rightSmallImageView;

    public ImageView rightLargeImageView;

    public LiveFeedItemView(Context context) {
        this(context, null);
    }

    public LiveFeedItemView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public LiveFeedItemView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        leftTimeTextView = (TextView)findViewById(R.id.left_time_text_view);
        leftDescriptionTextView = (TextView)findViewById(R.id.left_description_text_view);
        leftSmallImageView = (ImageView)findViewById(R.id.left_small_image_view);
        leftLargeImageView = (ImageView)findViewById(R.id.left_large_image_view);

        rightTimeTextView = (TextView)findViewById(R.id.right_time_text_view);
        rightDescriptionTextView = (TextView)findViewById(R.id.right_description_text_view);
        rightSmallImageView = (ImageView)findViewById(R.id.right_event_image_view);
        rightLargeImageView = (ImageView)findViewById(R.id.right_large_image_view);
    }


}
