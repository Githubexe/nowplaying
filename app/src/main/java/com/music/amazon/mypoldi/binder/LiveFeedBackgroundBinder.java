package com.music.amazon.mypoldi.binder;

import android.content.Context;

import com.music.amazon.mypoldi.R;
import com.music.amazon.mypoldi.model.LiveFeedBackgroundModel;
import com.music.amazon.mypoldi.view.LiveFeedBackgroundView;
import com.squareup.picasso.Picasso;

/**
 * Created by yoyosu on 1/16/17.
 */
public final class LiveFeedBackgroundBinder {

    public LiveFeedBackgroundView createView(Context context) {
        return new LiveFeedBackgroundView(context);
    }

    public void bind(final LiveFeedBackgroundView view,
                      final LiveFeedBackgroundModel model){
        final Picasso picasso = Picasso.with(view.getContext());
        picasso.load(model.backgroundImage)
                .placeholder(R.drawable.live_feed_background)
                .error(R.drawable.live_feed_background)
                .into(view.backgroundImageView);

        picasso.load(model.homeLogo)
                .placeholder(R.drawable.home_logo)
                .error(R.drawable.home_logo)
                .into(view.hostImageView);
        view.hostTextView.setText(model.homeName);

        picasso.load(model.awayLogo)
                .placeholder(R.drawable.away_logo)
                .error(R.drawable.away_logo)
                .into(view.awayImageView);
        view.awayTextView.setText(model.awayName);
    }
}