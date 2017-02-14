package com.music.amazon.mypoldi.binder;

import android.content.Context;

import com.music.amazon.mypoldi.model.LiveFeedModel;
import com.music.amazon.mypoldi.view.LiveFeedView;

/**
 * Created by yoyosu on 1/25/17.
 */
public class LiveFeedBinder {
    private Context context;

    public LiveFeedBinder(Context context) {
        this.context = context;
    }

    public LiveFeedView createView() {
        return new LiveFeedView(context);
    }

    public void bind(final LiveFeedView view,
                     final LiveFeedModel model){
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
