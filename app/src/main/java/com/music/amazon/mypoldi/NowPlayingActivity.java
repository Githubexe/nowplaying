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

    //DEMO data only!!!
    private void updateTimeline(final Context context,
                                final NowPlayingTimelineView nowPlayingTimelineView) {
       new Thread() {
            @Override
            public void run() {
                try {
                    final List<GameEvent> events = new ArrayList<GameEvent>();
//                    for (int i = 0; i < 250; i++) {
//                        events.add(GameEvent.builder("test-event-uuid")
//                                .withLeftEventDescritpion("Host team event ")
//                                .withRightEventDescritpion("Visiting team event ")
//                                .build());
//
//                        events.add(GameEvent.builder("test-event-uuid")
//                                .withLeftEventDescritpion("A team event ")
//                                .withRightEventDescritpion("B team event ")
//                                .build());
//
//                        events.add(GameEvent.builder("test-event-uuid")
//                                .withLeftEventTime("32'")
//                                .withLeftEventDescritpion("A event ")
//                                .withLeftEventIconResId(R.drawable.yellow_card)
//                                .withLeftMarkerIconResId(R.drawable.yellow_marker)
//                                .withRightEventDescritpion("B event ")
//                                .build());
//
//                        events.add(GameEvent.builder("test-event-uuid")
//                                .withLeftEventTime("48'")
//                                .withLeftEventDescritpion("C event ")
//                                .withLeftMarkerIconResId(R.drawable.yellow_marker)
//                                .withRightEventTime("52'")
//                                .withRightEventDescritpion("D event ")
//                                .withRightEventIconResId(R.drawable.yellow_card)
//                                .build());
//                    }

                    final long start = System.currentTimeMillis();
                    while (!isInterrupted()) {
                        Thread.sleep(1000);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                final long current = System.currentTimeMillis();
                                final long seconds = (current - start) / 1000 % 60;
                                final long minutes = (current - start) / 1000 / 60;
                                events.add(GameEvent.builder("test-event-uuid")
                                        .withLeftEventDescritpion("Left: " + minutes + seconds)
                                        .withRightEventDescritpion("Right: " + seconds)
                                        .build());

                                events.add(GameEvent.builder("test-event-uuid")
                                        .withLeftEventTime(minutes + seconds + "\"")
                                        .withLeftEventDescritpion("L2 event "  + minutes + seconds)
                                        .withLeftEventIconResId(R.drawable.yellow_card)
                                        .withLeftMarkerIconResId(R.drawable.yellow_marker)
                                        .withRightEventDescritpion("R2 event " + seconds)
                                        .build());

                                events.add(GameEvent.builder("test-event-uuid")
                                        .withLeftEventDescritpion("L3: " + minutes + seconds)
                                        .withRightEventDescritpion("L4: " + seconds)
                                        .build());

                                NowPlayingTimelineModel timelineModelmodel =
                                        createNowPlayingTimelineModel(Long.toString(minutes),Long.toString(seconds), events);
                                new NowPlayingTimelineBinder(context).bind(nowPlayingTimelineView, timelineModelmodel);
                            }
                        });
                    }
                } catch (InterruptedException e) {
                }
            }
        }.start();
    }

    private NowPlayingTimelineModel createNowPlayingTimelineModel(final String minutes,
                                                                  final String seconds,
                                                                  final List<GameEvent> events) {
//        events.add(GameEvent.builder("test-event-uuid")
//                .withLeftEventDescritpion("Host ")
//                .withRightEventDescritpion("Visiting")
//                .build());
//
//        events.add(GameEvent.builder("test-event-uuid")
//                .withLeftEventDescritpion("L1 event ")
//                .withRightEventDescritpion("R1 event ")
//                .build());
//
//        events.add(GameEvent.builder("test-event-uuid")
//                .withLeftEventTime("32'")
//                .withLeftEventDescritpion("L2 event ")
//                .withLeftEventIconResId(R.drawable.yellow_card)
//                .withLeftMarkerIconResId(R.drawable.yellow_marker)
//                .withRightEventDescritpion("R2 event ")
//                .build());
//
//        events.add(GameEvent.builder("test-event-uuid")
//                .withLeftEventTime("48'")
//                .withLeftEventDescritpion("L3 event ")
//                .withLeftMarkerIconResId(R.drawable.yellow_marker)
//                .withRightEventTime("52'")
//                .withRightEventDescritpion("R3 event ")
//                .withRightEventIconResId(R.drawable.yellow_card)
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
