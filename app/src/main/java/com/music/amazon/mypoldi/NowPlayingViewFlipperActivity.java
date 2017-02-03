package com.music.amazon.mypoldi;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ViewFlipper;

import com.music.amazon.mypoldi.binder.NowPlayingMainBinder;
import com.music.amazon.mypoldi.binder.NowPlayingTimelineBinder;
import com.music.amazon.mypoldi.model.GameEvent;
import com.music.amazon.mypoldi.model.NowPlayingMainModel;
import com.music.amazon.mypoldi.model.NowPlayingTimelineModel;
import com.music.amazon.mypoldi.view.NowPlayingMainView;
import com.music.amazon.mypoldi.view.NowPlayingTimelineView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yoyosu on 2/2/17.
 */
public class NowPlayingViewFlipperActivity extends Activity {

    //FIXME: get it from service
    private final int NUM_OF_LIVE_GAMES = 3;

    private ViewFlipper viewFlipper;

    private List<Integer> viewLayoutIds = new ArrayList<Integer>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.now_playing_view_flipper_activity);
        viewFlipper = (ViewFlipper) findViewById(R.id.now_playing_view_flipper);
        for (int i = 0; i < NUM_OF_LIVE_GAMES; i++) {
            final NowPlayingMainView view = new NowPlayingMainView(this);
            final int viewId = View.generateViewId();
            viewLayoutIds.add(i, viewId);
            view.setId(viewId);
            viewFlipper.addView(view);
        }

        updateData();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (event.getAction() == KeyEvent.ACTION_DOWN) {
            if (keyCode == KeyEvent.KEYCODE_DPAD_LEFT) {
                if (viewFlipper.getDisplayedChild() >= 0) {
                    viewFlipper.setInAnimation(this, R.anim.in_from_left);
                    viewFlipper.setOutAnimation(this, R.anim.out_to_right);
                    viewFlipper.showPrevious();
                    updateData();
                    event.startTracking();
                    return true;
                }
            } else if (keyCode == KeyEvent.KEYCODE_DPAD_RIGHT) {
                if (viewFlipper.getDisplayedChild() < NUM_OF_LIVE_GAMES - 1) {
                    viewFlipper.setInAnimation(this, R.anim.in_from_right);
                    viewFlipper.setOutAnimation(this, R.anim.out_to_left);
                    viewFlipper.showNext();
                    updateData();
                    event.startTracking();
                    return true;
                } else  if (viewFlipper.getDisplayedChild() == NUM_OF_LIVE_GAMES - 1) {
                    viewFlipper.setDisplayedChild(0); //back to the first item
                    updateData();
                    event.startTracking();
                    return true;
                }
            }
        }
        return false;
    }

    private void updateData() {
        final int childId = viewFlipper.getDisplayedChild();
        final int viewLayoutId = viewLayoutIds.get(childId);
        NowPlayingMainModel model = createNowPlayingMainModel(childId);
        NowPlayingMainView view = (NowPlayingMainView) (viewFlipper.findViewById(viewLayoutId));
        new NowPlayingMainBinder().bind(view, model);
       // updateTimeline(this, view.nowPlayingTimelineView);
    }

    private NowPlayingTimelineModel createNowPlayingTimelineModel(final long minutes,
                                                                  final long seconds,
                                                                  final List<GameEvent> events) {
        final GameEvent.Builder builder = GameEvent.builder("demo-only");
        builder.withLeftEventDescritpion("Left: " + minutes*60 + seconds)
                .withRightEventDescritpion("Right: " + minutes*60 + seconds)
                .build();

        if (seconds % 5 == 0 || seconds % 13 ==0) {
            builder.withLeftEventTime(minutes + seconds + "\"")
                    .withLeftEventIconResId(R.drawable.yellow_card)
                    .withLeftMarkerIconResId(R.drawable.yellow_marker);
        } else if (seconds % 7 == 0 || seconds % 11 == 0) {
            builder.withRightEventTime(minutes + seconds + "\"")
                    .withRightEventIconResId(R.drawable.yellow_card)
                    .withRightMarkerIconResId(R.drawable.yellow_marker);
        }

        events.add(builder.build());

        return NowPlayingTimelineModel.builder(
                "test-uuid").
                withHostTeamScore("1").
                withVisitingTeamScore("2").
                withMinutes(Long.toString(minutes)).
                withSeconds(Long.toString(seconds)).
                withEvents(events).build();
    }

    //DEMO data only!!!
    private void updateTimeline(final Context context,
                                final NowPlayingTimelineView nowPlayingTimelineView) {
        new Thread() {
            @Override
            public void run() {
                try {
                    final List<GameEvent> events = new ArrayList<GameEvent>();
                    final long start = System.currentTimeMillis();
                    while (!isInterrupted()) {
                        Thread.sleep(1000);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                final long current = System.currentTimeMillis();
                                final long seconds = (current - start) / 1000 % 60;
                                final long minutes = (current - start) / 1000 / 60;
                                NowPlayingTimelineModel timelineModelmodel =
                                        createNowPlayingTimelineModel(minutes, seconds, events);
                                new NowPlayingTimelineBinder(context).bind(nowPlayingTimelineView, timelineModelmodel);
                            }
                        });
                    }
                } catch (InterruptedException e) {
                }
            }
        }.start();
    }

    private NowPlayingMainModel createNowPlayingMainModel(int childId) {
        return NowPlayingMainModel.builder(
                "test-main-uuid",
                R.drawable.now_playing_background,
                "Host Team #" + childId,
                R.drawable.host,
                "Visiting Team #" + childId,
                R.drawable.visiting).build();
    }
}
