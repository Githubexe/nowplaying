package com.music.amazon.mypoldi.binder;

import android.content.Context;

import com.music.amazon.mypoldi.dmtv.UniversalBinder;
import com.music.amazon.mypoldi.model.LiveFeedItemModel.HomeLiveFeedItemModel;
import com.music.amazon.mypoldi.view.HomeLiveFeedItemView;

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
        view.bind(model);
    }
}
