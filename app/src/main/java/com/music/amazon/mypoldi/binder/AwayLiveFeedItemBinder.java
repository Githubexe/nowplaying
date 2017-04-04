package com.music.amazon.mypoldi.binder;

import android.content.Context;
import android.view.View;

import com.music.amazon.mypoldi.R;
import com.music.amazon.mypoldi.dmtv.UniversalBinder;
import com.music.amazon.mypoldi.model.LiveFeedItemModel;
import com.music.amazon.mypoldi.view.AwayLiveFeedItemView;
import com.squareup.picasso.Picasso;

/**
 * Created by yoyosu on 2/16/17.
 */
public final class AwayLiveFeedItemBinder implements
        UniversalBinder<AwayLiveFeedItemView, LiveFeedItemModel.AwayLiveFeedItemModel> {

    @Override
    public Class<LiveFeedItemModel.AwayLiveFeedItemModel> getModelClass() {
        return LiveFeedItemModel.AwayLiveFeedItemModel.class;
    }

    @Override
    public AwayLiveFeedItemView createView(final Context context) {
        return new AwayLiveFeedItemView(context);
    }

    @Override
    public void bind(final AwayLiveFeedItemView view,
                     final LiveFeedItemModel.AwayLiveFeedItemModel model) {
        CustomAnimator.animate(view);
        if (model.subText.isPresent()) {
            view.subTextView.setText(model.subText.get());
            view.subTextView.setVisibility(View.VISIBLE);
        } else {
            view.subTextView.setText(null);
            view.subTextView.setVisibility(View.INVISIBLE);
        }

        if (model.mainText.isPresent()) {
            view.mainTextView.setText(model.mainText.get());
            view.mainTextView.setVisibility(View.VISIBLE);
        } else {
            view.mainTextView.setText(null);
            view.mainTextView.setVisibility(View.INVISIBLE);
        }

        if (model.imageType1.isPresent()) {
            final LiveFeedItemModel.ICON_TYPE type = model.imageType1.get();
            if (LiveFeedItemModel.ICON_TYPE.GOAL.equals(type)) {
                view.imageView1.setImageResource(R.drawable.ic_fusball_right_largest);
                view.imageView1.setVisibility(View.VISIBLE);
            } else if (LiveFeedItemModel.ICON_TYPE.RED_CARD.equals(type)) {
                view.imageView1.setImageResource(R.drawable.ic_fusball_red_right_largest);
                view.imageView1.setVisibility(View.VISIBLE);
            } else if (LiveFeedItemModel.ICON_TYPE.YELLOW_CARD.equals(type)) {
                view.imageView1.setImageResource(R.drawable.ic_fusball_yellow_right_largest);
                view.imageView1.setVisibility(View.VISIBLE);
            } else if (LiveFeedItemModel.ICON_TYPE.YELLOW_RED_CARD.equals(type)) {
                view.imageView1.setImageResource(R.drawable.ic_fusball_yellowred_right_largest);
                view.imageView1.setVisibility(View.VISIBLE);
            } else if (LiveFeedItemModel.ICON_TYPE.YELLOW_RED_CARD.equals(type)) {
                view.imageView1.setImageResource(R.drawable.ic_fusball_yellowred_right_largest);
                view.imageView1.setVisibility(View.VISIBLE);
            } else if (LiveFeedItemModel.ICON_TYPE.SUBSTITUTION.equals(type)) {
                view.imageView1.setImageResource(R.drawable.ic_fusball_substitution_largest);
                view.imageView1.setVisibility(View.VISIBLE);
            }
        } else {
            view.imageView1.setImageDrawable(null);
            view.imageView1.setVisibility(View.INVISIBLE);
        }

        final Picasso picasso = Picasso.with(view.getContext());
        if (model.image2.isPresent()) {
            picasso.load(model.image2.get())
                    .into(view.imageView2);
            view.imageView2.setVisibility(View.VISIBLE);
        } else {
            view.imageView2.setImageDrawable(null);
            view.imageView2.setVisibility(View.INVISIBLE);
        }
    }
}
