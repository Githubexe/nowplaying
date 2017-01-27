package com.music.amazon.mypoldi;

import android.app.Activity;
import android.os.Bundle;

import com.music.amazon.mypoldi.binder.NowPlayingMainBinder;
import com.music.amazon.mypoldi.binder.NowPlayingTimelineBinder;
import com.music.amazon.mypoldi.model.GameEvent;
import com.music.amazon.mypoldi.model.NowPlayingMainModel;
import com.music.amazon.mypoldi.model.NowPlayingTimelineModel;
import com.music.amazon.mypoldi.view.NowPlayingMainView;

import java.util.ArrayList;
import java.util.List;

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
        new NowPlayingTimelineBinder(this).bind(view.nowPlayingTimelineView, timelineModelmodel);
    }

    private NowPlayingMainModel createNowPlayingMainModel() {
        return NowPlayingMainModel.builder(
                "test-main-uuid",
                R.drawable.now_playing_background,
                "Host Team",
                R.drawable.host,
                "Visiting Team",
                R.drawable.visiting).build();
    }

    private NowPlayingTimelineModel createNowPlayingTimelineModel() {
        final List<GameEvent> events = new ArrayList<GameEvent>();
        events.add(GameEvent.builder("test-event-uuid")
                .withLeftEventDescritpion("Host team event AAAAAAAAAA")
                .withRightEventDescritpion("Visiting team event AAAAAAAAA")
                .withRightEventIconResId(R.drawable.yellow_card)
                .build());

        events.add(GameEvent.builder("test-event-uuid")
                .withLeftEventDescritpion("A event BBBBBBBBBB")
                .withLeftEventIconResId(R.drawable.yellow_card)
                .withRightEventTime("32'")
                .withRightEventDescritpion("B event BBBBBBBBB")
                .withRightMarkerIconResId(R.drawable.green_marker)
                .build());

        events.add(GameEvent.builder("test-event-uuid")
                .withLeftEventTime("48'")
                .withLeftEventDescritpion("C event BBBBBBBBBB")
                .withLeftMarkerIconResId(R.drawable.green_marker)
                .withRightEventDescritpion("D event BBBBBBBBB")
                .withRightEventIconResId(R.drawable.yellow_card)
                .build());

        return NowPlayingTimelineModel.builder(
                "test-uuid").
                withScore("1 - 2").
                withTime("32 : 13").
                withEvents(events).build();
    }
}
