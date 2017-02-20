package com.music.amazon.mypoldi.binder;

import android.content.Context;

import com.music.amazon.mypoldi.dmtv.UniversalBinder;
import com.music.amazon.mypoldi.model.LiveFeedHeaderModel;
import com.music.amazon.mypoldi.view.LiveFeedHeaderView;

/**
 * Created by yoyosu on 1/25/17.
 */
public class LiveFeedHeaderBinder implements
        UniversalBinder<LiveFeedHeaderView, LiveFeedHeaderModel> {

    @Override
    public Class<LiveFeedHeaderModel> getModelClass() {
        return LiveFeedHeaderModel.class;
    }

    @Override
    public LiveFeedHeaderView createView(final Context context) {
        return new LiveFeedHeaderView(context);
    }

    public void bind(final LiveFeedHeaderView view,
                     final LiveFeedHeaderModel model){
        view.hostTeamScoreTextView.setText(Integer.toString(model.hostScore));
        view.visitingTeamScoreTextView.setText(Integer.toString(model.awayScore));
        view.scoreSeparator.setText("-");

        //Assume the time is in the format of "minutes : seconds"
        final String[] time = model.elapsedTime.split(":");
        if (time.length >= 1) {
            view.minutesTextView.setText(time[0].trim());
        }
        if (time.length == 2) {
            view.timeStampSeparator.setText(":");
            view.secondsTextView.setText(time[1].trim());
        }
    }
}
