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

    public final TextView timeTextView;

    public final TextView commentTextView;

    public final ImageView smallImageView;

    public final ImageView largeImageView;

    public RightLiveFeedItemView(Context context) {
        this(context, null);
    }

    public RightLiveFeedItemView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public RightLiveFeedItemView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        inflate(getContext(), R.layout.live_feed_item_view, this);
        timeTextView = (TextView)findViewById(R.id.right_time_text_view);
        commentTextView = (TextView)findViewById(R.id.right_description_text_view);
        smallImageView = (ImageView)findViewById(R.id.right_small_image_view);
        largeImageView = (ImageView)findViewById(R.id.right_large_image_view);
    }

    public void bind(final RightLiveFeedItemModel rightLiveFeedItemModel) {
        timeTextView.setText(rightLiveFeedItemModel.time);
        commentTextView.setText(rightLiveFeedItemModel.description);
        commentTextView.bringToFront();

        final Picasso picasso = Picasso.with(getContext());
        if (StringUtils.isEmpty(rightLiveFeedItemModel.smallImage) == false) {
            picasso.load(rightLiveFeedItemModel.smallImage)
                    .placeholder(R.drawable.ic_goal_r)
                    .error(R.drawable.ic_goal_r)
                    .into(smallImageView);
            smallImageView.setVisibility(View.VISIBLE);
        } else {
            //Must null the imageview so that it can be recycled
            smallImageView.setImageDrawable(null);
            smallImageView.setVisibility(View.INVISIBLE);
        }

        if (StringUtils.isEmpty(rightLiveFeedItemModel.largeImage) == false) {
            picasso.load(rightLiveFeedItemModel.largeImage)
                    .placeholder(R.drawable.ic_goal_r)
                    .error(R.drawable.ic_goal_r)
                    .into(largeImageView);
            largeImageView.setVisibility(View.VISIBLE);
        } else {
            //Must null the imageview so that it can be recycled
            largeImageView.setImageDrawable(null);
            largeImageView.setVisibility(View.INVISIBLE);
        }
    }
}
