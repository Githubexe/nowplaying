package com.music.amazon.mypoldi.binder;

import android.content.Context;

import com.music.amazon.mypoldi.dmtv.UniversalBinder;
import com.music.amazon.mypoldi.model.LiveFeedItemModel;
import com.music.amazon.mypoldi.view.LiveFeedItemView;

public final class LiveFeedItemBinder implements
        UniversalBinder<LiveFeedItemView, LiveFeedItemModel>{

    @Override
    public Class<LiveFeedItemModel> getModelClass() {
        return LiveFeedItemModel.class;
    }

    @Override
    public LiveFeedItemView createView(Context context) {
        return new LiveFeedItemView(context);
    }

    @Override
    public void bind(LiveFeedItemView view, LiveFeedItemModel model) {
        view.bind(model);
    }
}
