package com.music.amazon.mypoldi.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.music.amazon.mypoldi.R;
import com.music.amazon.mypoldi.model.LiveFeedBackgroundModel;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

/**
 * Created by yoyosu on 1/16/17.
 */
public final class LiveFeedBackgroundView extends RelativeLayout {

    public final ImageView backgroundImageView;

    public final TextView homeTextView;

    public final ImageView homeImageView;

    public final TextView awayTextView;

    public final ImageView awayImageView;

    public final LiveFeedView liveFeedView;

    public LiveFeedBackgroundView(final Context context) {
        this(context, null);
    }

    public LiveFeedBackgroundView(final Context context,
                                  final AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public LiveFeedBackgroundView(final Context context,
                                  final AttributeSet attrs,
                                  final int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        inflate(getContext(), R.layout.live_feed_background, this);
        backgroundImageView = (ImageView)findViewById(R.id.live_feed_background_image);
        homeTextView = (TextView)findViewById(R.id.home_text_view);
        homeImageView = (ImageView)findViewById(R.id.home_image_view);
        awayImageView = (ImageView)findViewById(R.id.away_image_view);
        awayTextView = (TextView)findViewById(R.id.away_text_view);
        liveFeedView = (LiveFeedView)findViewById(R.id.live_feed_view);
    }

    public void bind(final LiveFeedBackgroundModel model) {
        final Picasso picasso = Picasso.with(getContext());
        if (model.backgroundImage.isPresent()) {
            picasso.load(model.backgroundImage.get())
                    .into(backgroundImageView, new Callback() {
                        @Override
                        public void onSuccess() {
                            backgroundImageView.setVisibility(View.VISIBLE);
                        }
                        @Override
                        public void onError() {
                            backgroundImageView.setImageDrawable(null);
                            backgroundImageView.setVisibility(View.INVISIBLE);
                        }
                    });
        }

        if (model.homeLogo.isPresent()) {
            picasso.load(model.homeLogo.get())
                    .into(homeImageView, new Callback() {
                        @Override
                        public void onSuccess() {
                            homeImageView.setVisibility(View.VISIBLE);
                        }
                        @Override
                        public void onError() {
                            homeImageView.setImageDrawable(null);
                            homeImageView.setVisibility(View.INVISIBLE);
                        }
                    });
        }

        if (model.homeName.isPresent()) {
            homeTextView.setText(model.homeName.get());
        }

        if (model.awayLogo.isPresent()) {
            picasso.load(model.awayLogo.get())
                    .into(awayImageView, new Callback() {
                        @Override
                        public void onSuccess() {
                            awayImageView.setVisibility(View.VISIBLE);
                        }
                        @Override
                        public void onError() {
                            awayImageView.setImageDrawable(null);
                            awayImageView.setVisibility(View.INVISIBLE);
                        }
                    });
        }

        if (model.awayName.isPresent()) {
            awayTextView.setText(model.awayName.get());
        }
    }
}