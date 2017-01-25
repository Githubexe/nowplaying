package com.music.amazon.mypoldi;

import android.app.Activity;
import android.os.Bundle;

import com.music.amazon.mypoldi.binder.NowPlayingMainBinder;
import com.music.amazon.mypoldi.binder.NowPlayingTimelineBinder;
import com.music.amazon.mypoldi.model.NowPlayingMainModel;
import com.music.amazon.mypoldi.model.NowPlayingTimelineModel;
import com.music.amazon.mypoldi.view.NowPlayingMainView;

/**
 * Created by yoyosu on 1/17/17.
 */
public final class NowPlayingActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.now_playing_activity);

        NowPlayingMainModel model = createNowPlayingMainModel();
        NowPlayingMainView view = (NowPlayingMainView)findViewById(R.id.now_playing_main_view);
        new NowPlayingMainBinder().bind(view, model);

        NowPlayingTimelineModel timelineModelmodel = createNowPlayingTimelineModel();
        new NowPlayingTimelineBinder().bind(view.nowPlayingTimelineView, timelineModelmodel);
    }

    private NowPlayingMainModel createNowPlayingMainModel() {
        return NowPlayingMainModel.builder(
                "test-uuid",
                R.drawable.blurred,
                "LEFT",
                R.drawable.host,
                "RIGHT",
                R.drawable.visiting).build();
    }

    private NowPlayingTimelineModel createNowPlayingTimelineModel() {
        return NowPlayingTimelineModel.builder(
                "test-uuid").
                withScore("2 - 3").
                withTime("56 : 22").build();
    }
}
