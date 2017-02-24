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
public abstract class LiveFeedItemView extends LinearLayout {

    public final TextView timeTextView;

    public final TextView commentTextView;

    public final ImageView smallImageView;

    public final ImageView largeImageView;

    public LiveFeedItemView(Context context) {
        this(context, null);
    }

    public LiveFeedItemView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public LiveFeedItemView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        inflate(getContext(), R.layout.live_feed_item_view, this);
        timeTextView = (TextView)findViewById(R.id.time_text_view);
        commentTextView = (TextView)findViewById(R.id.description_text_view);
        smallImageView = (ImageView)findViewById(R.id.small_image_view);
        largeImageView = (ImageView)findViewById(R.id.left_large_image_view);
    }

    public void bind(final LeftLiveFeedItemModel leftLiveFeedItemModel) {
        timeTextView.setText(leftLiveFeedItemModel.time);
        commentTextView.setText(leftLiveFeedItemModel.description);
        commentTextView.bringToFront();

        final Picasso picasso = Picasso.with(getContext());
        if (StringUtils.isEmpty(leftLiveFeedItemModel.smallImage) == false) {
            picasso.load(leftLiveFeedItemModel.smallImage)
                    .placeholder(R.drawable.ic_goal_l)
                    .error(R.drawable.ic_goal_l)
                    .into(smallImageView);
            smallImageView.setVisibility(View.VISIBLE);
        } else {
            smallImageView.setImageDrawable(null);
            smallImageView.setVisibility(View.INVISIBLE);
        }
        if (StringUtils.isEmpty(leftLiveFeedItemModel.largeImage) == false) {
            picasso.load(leftLiveFeedItemModel.largeImage)
                    .placeholder(R.drawable.ic_goal_l)
                    .error(R.drawable.ic_goal_l)
                    .into(largeImageView);
            largeImageView.setVisibility(View.VISIBLE);
        } else {
            largeImageView.setImageDrawable(null);
            largeImageView.setVisibility(View.INVISIBLE);
        }
    }
}
