package com.music.amazon.mypoldi.binder;

import android.content.Context;

import com.music.amazon.mypoldi.dmtv.UniversalBinder;
import com.music.amazon.mypoldi.model.LeftLiveFeedItemModel;
import com.music.amazon.mypoldi.view.LeftLiveFeedItemView;

public final class LeftLiveFeedItemBinder implements
        UniversalBinder<LeftLiveFeedItemView, LeftLiveFeedItemModel>{

    @Override
    public Class<LeftLiveFeedItemModel> getModelClass() {
        return LeftLiveFeedItemModel.class;
    }

    @Override
    public LeftLiveFeedItemView createView(Context context) {
        return new LeftLiveFeedItemView(context);
    }

    @Override
    public void bind(LeftLiveFeedItemView view, LeftLiveFeedItemModel model) {
        view.bind(model);
    }
}
