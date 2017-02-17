package com.music.amazon.mypoldi.binder;

import android.content.Context;

import com.music.amazon.mypoldi.dmtv.UniversalBinder;
import com.music.amazon.mypoldi.model.RightLiveFeedItemModel;
import com.music.amazon.mypoldi.view.RightLiveFeedItemView;

/**
 * Created by yoyosu on 2/16/17.
 */
public final class RightLiveFeedItemBinder implements
        UniversalBinder<RightLiveFeedItemView, RightLiveFeedItemModel> {

    @Override
    public Class<RightLiveFeedItemModel> getModelClass() {
        return RightLiveFeedItemModel.class;
    }

    @Override
    public RightLiveFeedItemView createView(final Context context) {
        return new RightLiveFeedItemView(context);
    }

    @Override
    public void bind(final RightLiveFeedItemView view,
                     final RightLiveFeedItemModel model) {
        view.bind(model);
    }
}
