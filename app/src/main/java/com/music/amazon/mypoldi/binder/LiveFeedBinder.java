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
        view.homeScoreTextView.setText(Integer.toString(model.getHomeScore()));
        view.awayScoreTextView.setText(Integer.toString(model.getAwayScore()));
        view.scoreSeparator.setText("-");

        //Assume the time is in the format of "minutes : seconds"
        final String[] time = model.getElapsedTime().get().split(":");
        if (time.length >= 1) {
            view.minutesTextView.setText(time[0].trim());
        }
        if (time.length == 2) {
            view.timeStampSeparator.setText(":");
            view.secondsTextView.setText(time[1].trim());
        }
    }
}
