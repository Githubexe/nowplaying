package com.music.amazon.mypoldi.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
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
public abstract class LiveFeedItemView extends LinearLayout {

    public TextView timeTextView;

    public TextView commentTextView;

    public ImageView smallImageView;

    public ImageView largeImageView;

    public LiveFeedItemView(Context context) {
        this(context, null);
    }

    public LiveFeedItemView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public LiveFeedItemView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        inflate(getContext(), R.layout.live_feed_item_view, this);
    }

    public void bind(final LiveFeedItemModel liveFeedItemModel) {
        if (liveFeedItemModel.time.isPresent()) {
            timeTextView.setText(liveFeedItemModel.time.get());
        }

        if (liveFeedItemModel.comment.isPresent()) {
            commentTextView.setText(liveFeedItemModel.comment.get());
            commentTextView.bringToFront();
        }

        final Picasso picasso = Picasso.with(getContext());
        if (liveFeedItemModel.smallImage.isPresent()) {
            if (StringUtils.isEmpty(liveFeedItemModel.smallImage.get()) == false) {
                picasso.load(liveFeedItemModel.smallImage.get())
                        .placeholder(R.drawable.ic_goal_l)
                        .error(R.drawable.ic_goal_l)
                        .into(smallImageView);
                smallImageView.setVisibility(View.VISIBLE);
            } else {
                smallImageView.setImageDrawable(null);
                smallImageView.setVisibility(View.INVISIBLE);
            }
        }
        if (liveFeedItemModel.largeImage.isPresent()) {
            if (StringUtils.isEmpty(liveFeedItemModel.largeImage.get()) == false) {
                picasso.load(liveFeedItemModel.largeImage.get())
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
}
