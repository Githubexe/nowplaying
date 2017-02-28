package com.music.amazon.mypoldi.binder;

import android.content.Context;
import android.view.View;

import com.music.amazon.mypoldi.dmtv.UniversalBinder;
import com.music.amazon.mypoldi.model.LiveFeedItemModel.AwayLiveFeedItemModel;
import com.music.amazon.mypoldi.view.AwayLiveFeedItemView;
import com.squareup.picasso.Picasso;

/**
 * Created by yoyosu on 2/16/17.
 */
public final class AwayLiveFeedItemBinder implements
        UniversalBinder<AwayLiveFeedItemView, AwayLiveFeedItemModel> {

    @Override
    public Class<AwayLiveFeedItemModel> getModelClass() {
        return AwayLiveFeedItemModel.class;
    }

    @Override
    public AwayLiveFeedItemView createView(final Context context) {
        return new AwayLiveFeedItemView(context);
    }

    @Override
    public void bind(final AwayLiveFeedItemView view,
                     final AwayLiveFeedItemModel model) {
        CustomAnimator.animate(view);
        if (model.time.isPresent()) {
            view.timeTextView.setText(model.time.get());
        }

        if (model.comment.isPresent()) {
            view.commentTextView.setText(model.comment.get());
            view.commentTextView.bringToFront();
        }

        final Picasso picasso = Picasso.with(view.getContext());
        if (model.smallImage.isPresent()) {
            picasso.load(model.smallImage.get())
                    .into(view.smallImageView);
            view.smallImageView.setVisibility(View.VISIBLE);
        } else {
            view.smallImageView.setImageDrawable(null);
            view.smallImageView.setVisibility(View.INVISIBLE);
        }

        if (model.largeImage.isPresent()) {
            picasso.load(model.largeImage.get())
                    .into(view.largeImageView);
            view.largeImageView.setVisibility(View.VISIBLE);
        } else {
            view.largeImageView.setImageDrawable(null);
            view.largeImageView.setVisibility(View.INVISIBLE);
        }
    }
}
