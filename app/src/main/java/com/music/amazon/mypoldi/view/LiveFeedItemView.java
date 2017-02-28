package com.music.amazon.mypoldi.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.music.amazon.mypoldi.R;
import com.music.amazon.mypoldi.model.LiveFeedItemModel;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;


/**
 * Created by yoyosu on 2/13/17.
 */
public abstract class LiveFeedItemView extends LinearLayout {

    public TextView timeTextView;

    public TextView commentTextView;

    public ImageView smallImageView;

    public ImageView largeImageView;

    public LiveFeedItemView(final Context context) {
        this(context, null);
    }

    public LiveFeedItemView(final Context context,
                            final AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public LiveFeedItemView(final Context context,
                            final AttributeSet attrs,
                            final int defStyleAttr) {
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

        loadSmallImage(liveFeedItemModel);

        loadLargeImage(liveFeedItemModel);
    }

    private void loadSmallImage(final LiveFeedItemModel liveFeedItemModel) {
        final Picasso picasso = Picasso.with(getContext());
        if (liveFeedItemModel.smallImage.isPresent()) {
            picasso.load(liveFeedItemModel.smallImage.get())
                    .into(smallImageView, new Callback() {
                @Override public void onSuccess() {
                    smallImageView.setVisibility(View.VISIBLE);
                }
                @Override public void onError() {
                    smallImageView.setImageDrawable(null);
                    smallImageView.setVisibility(View.INVISIBLE);
                }
            });
        } else {
            smallImageView.setImageDrawable(null);
            smallImageView.setVisibility(View.INVISIBLE);
        }
    }

    private void loadLargeImage(final LiveFeedItemModel liveFeedItemModel) {
        final Picasso picasso = Picasso.with(getContext());
        if (liveFeedItemModel.largeImage.isPresent()) {
            picasso.load(liveFeedItemModel.largeImage.get())
                    .into(largeImageView, new Callback() {
                @Override public void onSuccess() {
                    largeImageView.setVisibility(View.VISIBLE);
                }
                @Override public void onError() {
                    largeImageView.setImageDrawable(null);
                    largeImageView.setVisibility(View.INVISIBLE);
                }
            });
        } else {
            largeImageView.setImageDrawable(null);
            largeImageView.setVisibility(View.INVISIBLE);
        }
    }
}
