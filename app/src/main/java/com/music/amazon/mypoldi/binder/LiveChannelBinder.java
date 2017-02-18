package com.music.amazon.mypoldi.binder;

import android.content.Context;

import com.music.amazon.mypoldi.dmtv.UniversalBinder;
import com.music.amazon.mypoldi.model.LiveChannelModel;
import com.music.amazon.mypoldi.model.GameModel;
import com.music.amazon.mypoldi.view.LiveChannelView;
import com.music.amazon.mypoldi.view.LiveFeedBackgroundView;

import java.util.Set;

/**
 * Created by yoyosu on 2/17/17.
 */
public final class LiveChannelBinder implements
        UniversalBinder<LiveChannelView, LiveChannelModel> {
    @Override
    public Class<LiveChannelModel> getModelClass() {
        return LiveChannelModel.class;
    }

    @Override
    public LiveChannelView createView(Context context) {
        return new LiveChannelView(context);
    }

    @Override
    public void bind(LiveChannelView view, LiveChannelModel model) {
        view.viewFlipper.removeAllViews();

        final Set<GameModel> games = model.games;
        for (GameModel game : games) {
            if (game.live) {
                final LiveFeedBackgroundView backgroundView =
                        new LiveFeedBackgroundView(view.getContext());
                backgroundView.setId(game.gameId.hashCode());
                view.viewFlipper.addView(backgroundView);
            }
        }
    }

}
