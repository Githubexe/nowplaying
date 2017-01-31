package com.music.amazon.mypoldi;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.widget.TextView;

import com.music.amazon.mypoldi.binder.NowPlayingMainBinder;
import com.music.amazon.mypoldi.binder.NowPlayingTimelineBinder;
import com.music.amazon.mypoldi.model.GameEvent;
import com.music.amazon.mypoldi.model.NowPlayingMainModel;
import com.music.amazon.mypoldi.model.NowPlayingTimelineModel;
import com.music.amazon.mypoldi.view.NowPlayingMainView;
import com.music.amazon.mypoldi.view.NowPlayingTimelineView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

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

        updateTimeline(this, view.nowPlayingTimelineView);
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

    private void updateTimeline(final Context context,
                                final NowPlayingTimelineView nowPlayingTimelineView) {
       new Thread() {
            @Override
            public void run() {
                try {
                    final long start = System.currentTimeMillis();
                    while (!isInterrupted()) {
                        Thread.sleep(1000);
                        final long current = System.currentTimeMillis();
                        final long seconds = (current - start) / 1000 % 60;
                        final long minutes = (current - start) / 1000 / 60;
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                NowPlayingTimelineModel timelineModelmodel =
                                        createNowPlayingTimelineModel(Long.toString(minutes),Long.toString(seconds));
                                new NowPlayingTimelineBinder(context).bind(nowPlayingTimelineView, timelineModelmodel);
                            }
                        });
                    }
                } catch (InterruptedException e) {
                }
            }
        }.start();
    }

    private NowPlayingTimelineModel createNowPlayingTimelineModel(String minutes, String seconds) {
        final List<GameEvent> events = new ArrayList<GameEvent>();
        //for (int i = 0; i < 250; i++) {
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
        //}

//        events.add(GameEvent.builder("test-event-uuid")
//                .withLeftEventDescritpion("LEFT END")
//                .withRightEventDescritpion("RIGHT END")
//                .build());

        return NowPlayingTimelineModel.builder(
                "test-uuid").
                withHostTeamScore("1").
                withVisitingTeamScore("2").
                withMinutes(minutes).
                withSeconds(seconds).
                withEvents(events).build();
    }



}
