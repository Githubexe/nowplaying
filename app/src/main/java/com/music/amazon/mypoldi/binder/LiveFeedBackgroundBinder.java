package com.music.amazon.mypoldi.binder;

import android.content.Context;
import android.view.View;

import com.music.amazon.mypoldi.dmtv.UniversalBinder;
import com.music.amazon.mypoldi.model.LiveFeedBackgroundModel;
import com.music.amazon.mypoldi.view.LiveFeedBackgroundView;
import com.squareup.picasso.Callback;
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
        if (model.backgroundImage.isPresent()) {
            picasso.load(model.backgroundImage.get())
                    .into(view.backgroundImageView);
        }

        if (model.homeLogo.isPresent()) {
            picasso.load(model.homeLogo.get())
                    .into(view.homeImageView);
        }

        if (model.homeName.isPresent()) {
            view.homeTextView.setText(model.homeName.get());
        }

        if (model.awayLogo.isPresent()) {
            picasso.load(model.awayLogo.get())
                    .into(view.awayImageView);
        }

        if (model.awayName.isPresent()) {
            view.awayTextView.setText(model.awayName.get());
        }
    }
}
