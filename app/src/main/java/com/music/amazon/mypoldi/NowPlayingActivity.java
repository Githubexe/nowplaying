//package com.music.amazon.mypoldi;
//
//import android.app.Activity;
//import android.content.Context;
//import android.os.Bundle;
//import android.text.format.DateFormat;
//import android.widget.TextView;
//
//import com.music.amazon.mypoldi.binder.NowPlayingBackgroundBinder;
//import com.music.amazon.mypoldi.binder.NowPlayingTimelineBinder;
//import com.music.amazon.mypoldi.model.LiveGameEvent;
//import com.music.amazon.mypoldi.model.NowPlayingBackgroundModel;
//import com.music.amazon.mypoldi.model.NowPlayingTimelineModel;
//import com.music.amazon.mypoldi.view.NowPlayingMainView;
//import com.music.amazon.mypoldi.view.NowPlayingTimelineView;
//
//import java.util.ArrayList;
//import java.util.Calendar;
//import java.util.List;
//import java.util.Timer;
//import java.util.TimerTask;
//
///**
// * Created by yoyosu on 1/17/17.
// */
//public final class NowPlayingActivity extends Activity {
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.now_playing_activity);
//
//        NowPlayingBackgroundModel model = createNowPlayingMainModel();
//        NowPlayingMainView view = (NowPlayingMainView)findViewById(R.id.now_playing_background_view);
//        new NowPlayingBackgroundBinder().bind(view, model);
//
//        updateTimeline(this, view.nowPlayingTimelineView);
//    }
//
//    private NowPlayingBackgroundModel createNowPlayingMainModel() {
//        return NowPlayingBackgroundModel.builder(
//                "test-main-uuid",
//                R.drawable.now_playing_background,
//                "Host Team",
//                R.drawable.host,
//                "Visiting Team",
//                R.drawable.visiting).build();
//    }
//
//    //DEMO data only!!!
//    private void updateTimeline(final Context context,
//                                final NowPlayingTimelineView nowPlayingTimelineView) {
//       new Thread() {
//            @Override
//            public void run() {
//                try {
//                    final List<LiveGameEvent> events = new ArrayList<LiveGameEvent>();
//                    final long start = System.currentTimeMillis();
//                    while (!isInterrupted()) {
//                        Thread.sleep(1000);
//                        runOnUiThread(new Runnable() {
//                            @Override
//                            public void run() {
//                                final long current = System.currentTimeMillis();
//                                final long seconds = (current - start) / 1000 % 60;
//                                final long minutes = (current - start) / 1000 / 60;
//                                NowPlayingTimelineModel timelineModelmodel =
//                                        createNowPlayingTimelineModel(minutes, seconds, events);
//                                new NowPlayingTimelineBinder(context).bind(nowPlayingTimelineView, timelineModelmodel);
//                            }
//                        });
//                    }
//                } catch (InterruptedException e) {
//                }
//            }
//        }.start();
//    }
//
//    private NowPlayingTimelineModel createNowPlayingTimelineModel(final long minutes,
//                                                                  final long seconds,
//                                                                  final List<LiveGameEvent> events) {
//        final LiveGameEvent.Builder builder = LiveGameEvent.builder("demo-only");
//
//        builder.withLeftEventDescritpion("Left: " + minutes*60 + seconds)
//                .withRightEventDescritpion("Right: " + minutes*60 + seconds)
//                .build();
//
//        if (seconds % 5 == 0 || seconds % 13 ==0) {
//            builder.withLeftEventTime(minutes + seconds + "\"")
//                    .withLeftEventIconResId(R.drawable.yellow_card)
//                    .withLeftMarkerIconResId(R.drawable.yellow_marker);
//        } else if (seconds % 7 == 0 || seconds % 11 == 0) {
//            builder.withRightEventTime(minutes + seconds + "\"")
//                    .withRightEventIconResId(R.drawable.yellow_card)
//                    .withRightMarkerIconResId(R.drawable.yellow_marker);
//        }
//
//        events.add(builder.build());
//
//        return NowPlayingTimelineModel.builder(
//                "test-uuid").
//                withHostTeamScore("1").
//                withVisitingTeamScore("2").
//                withMinutes(Long.toString(minutes)).
//                withSeconds(Long.toString(seconds)).
//                withEvents(events).build();
//    }
//
//
//
//}
