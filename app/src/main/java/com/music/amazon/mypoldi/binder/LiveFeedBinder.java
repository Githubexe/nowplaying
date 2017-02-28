package com.music.amazon.mypoldi.binder;

import android.content.Context;

import com.music.amazon.mypoldi.dmtv.UniversalBinder;
import com.music.amazon.mypoldi.model.LiveFeedModel;
import com.music.amazon.mypoldi.view.LiveFeedView;

/**
 * Created by yoyosu on 1/25/17.
 */
public class LiveFeedBinder implements
        UniversalBinder<LiveFeedView, LiveFeedModel> {

    @Override
    public Class<LiveFeedModel> getModelClass() {
        return LiveFeedModel.class;
    }

    @Override
    public LiveFeedView createView(final Context context) {
        return new LiveFeedView(context);
    }

    public void bind(final LiveFeedView view,
                     final LiveFeedModel model){
        view.bind(model);
    }
}
