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
        for (int i = 0; i < 250; i++) {
            events.add(GameEvent.builder("test-event-uuid")
                    .withLeftEventDescritpion("Host team event ")
                    .withRightEventDescritpion("Visiting team event ")
                    .build());

            events.add(GameEvent.builder("test-event-uuid")
                    .withLeftEventTime("32'")
                    .withLeftEventDescritpion("A event ")
                    .withLeftEventIconResId(R.drawable.yellow_card)
                    .withLeftMarkerIconResId(R.drawable.yellow_marker)
                    .withRightEventDescritpion("B event ")
                    .build());

            events.add(GameEvent.builder("test-event-uuid")
                    .withLeftEventTime("48'")
                    .withLeftEventDescritpion("C event ")
                    .withLeftMarkerIconResId(R.drawable.yellow_marker)
                    .withRightEventTime("52'")
                    .withRightEventDescritpion("D event ")
                    .withRightEventIconResId(R.drawable.yellow_card)
                    .build());
        }

        events.add(GameEvent.builder("test-event-uuid")
                .withLeftEventDescritpion("LEFT END")
                .withRightEventDescritpion("RIGHT END")
                .build());

        return NowPlayingTimelineModel.builder(
                "test-uuid").
                withHostTeamScore("1").
                withVisitingTeamScore("2").
                withTime("69 : 13").
                withEvents(events).build();
    }

}
