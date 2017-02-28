package com.music.amazon.mypoldi.binder;

import android.content.Context;
import android.view.View;

import com.music.amazon.mypoldi.dmtv.UniversalBinder;
import com.music.amazon.mypoldi.model.LiveFeedItemModel.HomeLiveFeedItemModel;
import com.music.amazon.mypoldi.view.HomeLiveFeedItemView;
import com.squareup.picasso.Picasso;

public final class HomeLiveFeedItemBinder implements
        UniversalBinder<HomeLiveFeedItemView, HomeLiveFeedItemModel>{

    @Override
    public Class<HomeLiveFeedItemModel> getModelClass() {
        return HomeLiveFeedItemModel.class;
    }

    @Override
    public HomeLiveFeedItemView createView(final Context context) {
        return new HomeLiveFeedItemView(context);
    }

    @Override
    public void bind(final HomeLiveFeedItemView view, final HomeLiveFeedItemModel model) {
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
