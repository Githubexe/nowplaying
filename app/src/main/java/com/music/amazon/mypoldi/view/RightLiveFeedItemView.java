package com.music.amazon.mypoldi.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.music.amazon.mypoldi.R;
import com.music.amazon.mypoldi.model.RightLiveFeedItemModel;
import com.squareup.picasso.Picasso;

import org.apache.commons.lang3.StringUtils;

/**
 * Created by yoyosu on 2/13/17.
 */
public final class RightLiveFeedItemView extends LinearLayout {

    public TextView rightTimeTextView;

    public TextView rightDescriptionTextView;

    public ImageView rightSmallImageView;

    public ImageView rightLargeImageView;

    public RightLiveFeedItemView(Context context) {
        this(context, null);
    }

    public RightLiveFeedItemView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public RightLiveFeedItemView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        inflate();
    }

    private void inflate() {
        inflate(getContext(), R.layout.live_feed_item_view, this);
        rightTimeTextView = (TextView)findViewById(R.id.right_time_text_view);
        rightDescriptionTextView = (TextView)findViewById(R.id.right_description_text_view);
        rightSmallImageView = (ImageView)findViewById(R.id.right_small_image_view);
        rightLargeImageView = (ImageView)findViewById(R.id.right_large_image_view);
    }

    public void bind(final RightLiveFeedItemModel rightLiveFeedItemModel) {
        rightTimeTextView.setText(rightLiveFeedItemModel.time);
        rightDescriptionTextView.setText(rightLiveFeedItemModel.description);
        rightDescriptionTextView.bringToFront();

        final Picasso picasso = Picasso.with(getContext());
        if (StringUtils.isEmpty(rightLiveFeedItemModel.smallImage) == false) {
            picasso.load(rightLiveFeedItemModel.smallImage)
                    .placeholder(R.drawable.rec_green)
                    .error(R.drawable.rec_green)
                    .into(rightSmallImageView);
            rightSmallImageView.setVisibility(View.VISIBLE);
        } else {
            //Must null the imageview so that it can be recycled
            rightSmallImageView.setImageDrawable(null);
            rightSmallImageView.setVisibility(View.INVISIBLE);
        }

        if (StringUtils.isEmpty(rightLiveFeedItemModel.largeImage) == false) {
            picasso.load(rightLiveFeedItemModel.largeImage)
                    .placeholder(R.drawable.rec_green)
                    .error(R.drawable.rec_green)
                    .into(rightLargeImageView);
            rightLargeImageView.setVisibility(View.VISIBLE);
        } else {
            //Must null the imageview so that it can be recycled
            rightLargeImageView.setImageDrawable(null);
            rightLargeImageView.setVisibility(View.INVISIBLE);
        }
    }
}
