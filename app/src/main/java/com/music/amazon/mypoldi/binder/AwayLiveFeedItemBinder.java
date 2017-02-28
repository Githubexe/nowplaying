package com.music.amazon.mypoldi.binder;

import android.content.Context;

import com.music.amazon.mypoldi.dmtv.UniversalBinder;
import com.music.amazon.mypoldi.model.LiveFeedItemModel.AwayLiveFeedItemModel;
import com.music.amazon.mypoldi.view.AwayLiveFeedItemView;

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
        view.bind(model);
    }
}
