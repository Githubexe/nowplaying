package com.music.amazon.mypoldi.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
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
        rightSmallImageView = (ImageView)findViewById(R.id.right_small_image_view);
        rightLargeImageView = (ImageView)findViewById(R.id.right_large_image_view);
    }

    public void bind(final LiveFeedItemModel liveFeedItemModel) {
        if (liveFeedItemModel.isHome) {
            bindHomeView(liveFeedItemModel);
        } else {
            bindAwayView(liveFeedItemModel);
        }
        Animation animation = AnimationUtils.loadAnimation(getContext(),
                R.anim.live_feed_item_up_from_bottom);
        startAnimation(animation);
    }

    private void bindHomeView(final LiveFeedItemModel liveFeedItemModel) {
        leftTimeTextView.setText(liveFeedItemModel.time);
        leftDescriptionTextView.setText(liveFeedItemModel.description);
        leftDescriptionTextView.bringToFront();

        final Picasso picasso = Picasso.with(getContext());
        if (StringUtils.isEmpty(liveFeedItemModel.smallImage) == false) {
            picasso.load(liveFeedItemModel.smallImage)
                    .placeholder(R.drawable.rec_blue)
                    .error(R.drawable.rec_blue)
                    .into(leftSmallImageView);
            leftSmallImageView.setVisibility(View.VISIBLE);
        } else {
            leftSmallImageView.setImageDrawable(null);
            leftSmallImageView.setVisibility(View.INVISIBLE);
        }
        if (StringUtils.isEmpty(liveFeedItemModel.largeImage) == false) {
            picasso.load(liveFeedItemModel.largeImage)
                    .placeholder(R.drawable.rec_blue)
                    .error(R.drawable.rec_blue)
                    .into(leftLargeImageView);
            leftLargeImageView.setVisibility(View.VISIBLE);
        } else {
            leftLargeImageView.setImageDrawable(null);
            leftLargeImageView.setVisibility(View.INVISIBLE);
        }
        rightTimeTextView.setText(null);
        rightDescriptionTextView.setText(null);
        rightSmallImageView.setImageDrawable(null);
        rightSmallImageView.setVisibility(View.INVISIBLE);
        rightLargeImageView.setImageDrawable(null);
        rightLargeImageView.setVisibility(View.INVISIBLE);
    }

    private void bindAwayView(final LiveFeedItemModel liveFeedItemModel) {
        rightTimeTextView.setText(liveFeedItemModel.time);
        rightDescriptionTextView.setText(liveFeedItemModel.description);
        rightDescriptionTextView.bringToFront();

        final Picasso picasso = Picasso.with(getContext());
        if (StringUtils.isEmpty(liveFeedItemModel.smallImage) == false) {
            picasso.load(liveFeedItemModel.smallImage)
                    .placeholder(R.drawable.rec_green)
                    .error(R.drawable.rec_green)
                    .into(rightSmallImageView);
            rightSmallImageView.setVisibility(View.VISIBLE);
        } else {
            //Must null the imageview so that it can be recycled
            rightSmallImageView.setImageDrawable(null);
            rightSmallImageView.setVisibility(View.INVISIBLE);
        }

        if (StringUtils.isEmpty(liveFeedItemModel.largeImage) == false) {
            picasso.load(liveFeedItemModel.largeImage)
                    .placeholder(R.drawable.rec_green)
                    .error(R.drawable.rec_green)
                    .into(rightLargeImageView);
            rightLargeImageView.setVisibility(View.VISIBLE);
        } else {
            //Must null the imageview so that it can be recycled
            rightLargeImageView.setImageDrawable(null);
            rightLargeImageView.setVisibility(View.INVISIBLE);
        }

        leftTimeTextView.setText(null);
        leftDescriptionTextView.setText(null);
        //Must null the imageviews so that they can be recycled
        leftSmallImageView.setImageDrawable(null);
        leftSmallImageView.setVisibility(View.INVISIBLE);
        leftLargeImageView.setImageDrawable(null);
        leftLargeImageView.setVisibility(View.INVISIBLE);
    }

}
