package com.music.amazon.mypoldi.binder;

import android.content.Context;
import com.music.amazon.mypoldi.model.NowPlayingTimelineModel;
import com.music.amazon.mypoldi.view.NowPlayingTimelineView;

/**
 * Created by yoyosu on 1/25/17.
 */
public class NowPlayingTimelineBinder {
    public NowPlayingTimelineView createView(Context context) {
        return new NowPlayingTimelineView(context);
    }

    public void bind(final NowPlayingTimelineView view,
                     final NowPlayingTimelineModel model){

        view.scoreTextView.setText(model.score);

        view.timeTextView.setText(model.time);
    }
}
