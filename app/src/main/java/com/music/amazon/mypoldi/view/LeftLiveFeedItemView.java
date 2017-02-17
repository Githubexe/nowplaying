package com.music.amazon.mypoldi.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.music.amazon.mypoldi.R;
import com.music.amazon.mypoldi.model.LeftLiveFeedItemModel;
import com.squareup.picasso.Picasso;

import org.apache.commons.lang3.StringUtils;

/**
 * Created by yoyosu on 2/13/17.
 */
public final class LeftLiveFeedItemView extends LinearLayout {

    public final TextView leftTimeTextView;

    public final TextView leftDescriptionTextView;

    public final ImageView leftSmallImageView;

    public final ImageView leftLargeImageView;

    public LeftLiveFeedItemView(Context context) {
        this(context, null);
    }

    public LeftLiveFeedItemView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public LeftLiveFeedItemView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        inflate(getContext(), R.layout.live_feed_item_view, this);
        leftTimeTextView = (TextView)findViewById(R.id.left_time_text_view);
        leftDescriptionTextView = (TextView)findViewById(R.id.left_description_text_view);
        leftSmallImageView = (ImageView)findViewById(R.id.left_small_image_view);
        leftLargeImageView = (ImageView)findViewById(R.id.left_large_image_view);
    }

    public void bind(final LeftLiveFeedItemModel leftLiveFeedItemModel) {
        leftTimeTextView.setText(leftLiveFeedItemModel.time);
        leftDescriptionTextView.setText(leftLiveFeedItemModel.description);
        leftDescriptionTextView.bringToFront();

        final Picasso picasso = Picasso.with(getContext());
        if (StringUtils.isEmpty(leftLiveFeedItemModel.smallImage) == false) {
            picasso.load(leftLiveFeedItemModel.smallImage)
                    .placeholder(R.drawable.rec_blue)
                    .error(R.drawable.rec_blue)
                    .into(leftSmallImageView);
            leftSmallImageView.setVisibility(View.VISIBLE);
        } else {
            leftSmallImageView.setImageDrawable(null);
            leftSmallImageView.setVisibility(View.INVISIBLE);
        }
        if (StringUtils.isEmpty(leftLiveFeedItemModel.largeImage) == false) {
            picasso.load(leftLiveFeedItemModel.largeImage)
                    .placeholder(R.drawable.rec_blue)
                    .error(R.drawable.rec_blue)
                    .into(leftLargeImageView);
            leftLargeImageView.setVisibility(View.VISIBLE);
        } else {
            leftLargeImageView.setImageDrawable(null);
            leftLargeImageView.setVisibility(View.INVISIBLE);
        }
    }
}
