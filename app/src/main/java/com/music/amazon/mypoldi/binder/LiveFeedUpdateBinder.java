package com.music.amazon.mypoldi.binder;

import android.content.Context;

import com.music.amazon.mypoldi.dmtv.UniversalBinder;
import com.music.amazon.mypoldi.model.LiveFeedUpdateModel;
import com.music.amazon.mypoldi.view.LiveFeedUpdateView;

/**
 * Created by yoyosu on 1/25/17.
 */
public class LiveFeedUpdateBinder implements
        UniversalBinder<LiveFeedUpdateView, LiveFeedUpdateModel> {

    @Override
    public Class<LiveFeedUpdateModel> getModelClass() {
        return LiveFeedUpdateModel.class;
    }

    @Override
    public LiveFeedUpdateView createView(final Context context) {
        return new LiveFeedUpdateView(context);
    }

    public void bind(final LiveFeedUpdateView view,
                     final LiveFeedUpdateModel model){
        view.hostTeamScoreTextView.setText(Integer.toString(model.getHomeScore()));
        view.visitingTeamScoreTextView.setText(Integer.toString(model.getAwayScore()));
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
