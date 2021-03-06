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
        view.homeScoreTextView.setText(model.getHomeScore());
        view.awayScoreTextView.setText(model.getAwayScore());
        view.scoreSeparator.setText("-");

        //Assume the time is in the format of "minutes : seconds"
        final String[] time = model.getGameClock().get().split(":");
        if (time.length >= 1) {
            view.minutesTextView.setText(time[0].trim());
        }
    }
}
