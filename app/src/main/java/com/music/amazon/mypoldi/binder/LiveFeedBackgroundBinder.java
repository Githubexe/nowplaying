package com.music.amazon.mypoldi.binder;

import android.content.Context;

import com.music.amazon.mypoldi.R;
import com.music.amazon.mypoldi.dmtv.UniversalBinder;
import com.music.amazon.mypoldi.model.LiveFeedBackgroundModel;
import com.music.amazon.mypoldi.view.LiveFeedBackgroundView;
import com.squareup.picasso.Picasso;

/**
 * Created by yoyosu on 1/16/17.
 */
public final class LiveFeedBackgroundBinder implements
        UniversalBinder<LiveFeedBackgroundView, LiveFeedBackgroundModel> {

    @Override
    public Class<LiveFeedBackgroundModel> getModelClass() {
        return LiveFeedBackgroundModel.class;
    }

    @Override
    public LiveFeedBackgroundView createView(final Context context) {
        return new LiveFeedBackgroundView(context);
    }

    @Override
    public void bind(final LiveFeedBackgroundView view,
                     final LiveFeedBackgroundModel model){
        final Picasso picasso = Picasso.with(view.getContext());
        picasso.load(model.backgroundImage)
                .placeholder(R.drawable.background1_640)
                .error(R.drawable.background1_640)
                .into(view.backgroundImageView);

        picasso.load(model.homeLogo)
                .placeholder(R.drawable.home_logo_1)
                .error(R.drawable.home_logo_1)
                .into(view.hostImageView);
        view.hostTextView.setText(model.homeName);

        picasso.load(model.awayLogo)
                .placeholder(R.drawable.away_logo_1)
                .error(R.drawable.away_logo_1)
                .into(view.awayImageView);
        view.awayTextView.setText(model.awayName);
    }
}
