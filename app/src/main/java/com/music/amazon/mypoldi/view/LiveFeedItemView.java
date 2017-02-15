package com.music.amazon.mypoldi.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.music.amazon.mypoldi.R;
import com.music.amazon.mypoldi.model.LiveFeedItemModel;
import com.squareup.picasso.Picasso;

import org.apache.commons.lang3.StringUtils;

/**
 * Created by yoyosu on 2/13/17.
 */
public final class LiveFeedItemView extends LinearLayout {
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
        inflate();
    }

    private void inflate() {
        inflate(getContext(), R.layout.live_feed_item_view, this);
        leftTimeTextView = (TextView)findViewById(R.id.left_time_text_view);
        leftDescriptionTextView = (TextView)findViewById(R.id.left_description_text_view);
        leftSmallImageView = (ImageView)findViewById(R.id.left_small_image_view);
        leftLargeImageView = (ImageView)findViewById(R.id.left_large_image_view);

        rightTimeTextView = (TextView)findViewById(R.id.right_time_text_view);
        rightDescriptionTextView = (TextView)findViewById(R.id.right_description_text_view);
        rightSmallImageView = (ImageView)findViewById(R.id.right_event_image_view);
        rightLargeImageView = (ImageView)findViewById(R.id.right_large_image_view);
    }

    public void bind(LiveFeedItemModel liveFeedItemModel) {
        final Picasso picasso = Picasso.with(getContext());

        if (liveFeedItemModel.isHome) {
            leftTimeTextView.setText(liveFeedItemModel.time);
            leftDescriptionTextView.setText(liveFeedItemModel.description);
            leftDescriptionTextView.bringToFront();
            if (StringUtils.isEmpty(liveFeedItemModel.smallImage) == false) {
                picasso.load(liveFeedItemModel.smallImage)
                        .placeholder(R.drawable.yellow_card)
                        .error(R.drawable.yellow_card)
                        .into(leftSmallImageView);
            }

            if (StringUtils.isEmpty(liveFeedItemModel.largeImage) == false) {
                picasso.load(liveFeedItemModel.largeImage)
                        .placeholder(R.drawable.yellow_card)
                        .error(R.drawable.yellow_card)
                        .into(leftLargeImageView);

            }
        } else {
            rightTimeTextView.setText(liveFeedItemModel.time);
            rightDescriptionTextView.setText(liveFeedItemModel.description);
            rightDescriptionTextView.bringToFront();

            if (StringUtils.isEmpty(liveFeedItemModel.smallImage) == false) {
                picasso.load(liveFeedItemModel.smallImage)
                        .placeholder(R.drawable.yellow_card)
                        .error(R.drawable.yellow_card)
                        .into(rightSmallImageView);
            }

            if (StringUtils.isEmpty(liveFeedItemModel.largeImage) == false) {
                picasso.load(liveFeedItemModel.largeImage)
                        .placeholder(R.drawable.yellow_card)
                        .error(R.drawable.yellow_card)
                        .into(rightLargeImageView);
            }
        }
    }

}
