package com.music.amazon.mypoldi;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ViewFlipper;

import com.music.amazon.mypoldi.binder.NowPlayingBackgroundBinder;
import com.music.amazon.mypoldi.binder.NowPlayingTimelineBinder;
import com.music.amazon.mypoldi.model.LiveGameEventModel;
import com.music.amazon.mypoldi.model.NowPlayingBackgroundModel;
import com.music.amazon.mypoldi.model.NowPlayingTimelineModel;
import com.music.amazon.mypoldi.view.NowPlayingBackgroundView;
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

    private Thread timelineUpdateThread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.now_playing_view_flipper_activity);
        viewFlipper = (ViewFlipper) findViewById(R.id.now_playing_view_flipper);
        addNextView(0);
        updateData();
    }

    @Override
    protected void onDestroy() {
        if (timelineUpdateThread != null) {
            timelineUpdateThread.interrupt();
        }
        super.onDestroy();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (event.getAction() == KeyEvent.ACTION_DOWN) {
            if (keyCode == KeyEvent.KEYCODE_DPAD_LEFT) {
                if (viewFlipper.getDisplayedChild() >= 0) {
                    viewFlipper.setInAnimation(this, R.anim.now_playing_view_flipper_in_from_left);
                    viewFlipper.setOutAnimation(this, R.anim.now_playing_view_flipper_out_to_right);
                    viewFlipper.showPrevious();
                    updateData();
                    event.startTracking();
                    return true;
                }
            } else if (keyCode == KeyEvent.KEYCODE_DPAD_RIGHT) {
                if (viewFlipper.getChildCount() < NUM_OF_LIVE_GAMES) {
                    addNextView(viewFlipper.getDisplayedChild() + 1);
                }
                if (viewFlipper.getDisplayedChild() <= NUM_OF_LIVE_GAMES - 1) {
                    viewFlipper.setInAnimation(this, R.anim.now_playing_view_flipper_in_from_right);
                    viewFlipper.setOutAnimation(this, R.anim.now_playing_view_flipper_out_to_left);
                    viewFlipper.showNext();
                    updateData();
                    event.startTracking();
                    return true;
                }
            }
        }
        return super.onKeyDown(keyCode,event);
    }

    private void addNextView(int index) {
        final NowPlayingBackgroundView view = new NowPlayingBackgroundView(this);
        final int viewId = View.generateViewId();
        viewLayoutIds.add(index, viewId);
        view.setId(viewId);
        viewFlipper.addView(view);
    }

    private void updateData() {
        if (timelineUpdateThread != null) {
            timelineUpdateThread.interrupt();
        }
        final NowPlayingBackgroundView backgroundView = updateBackgroundView();
        updateTimelineView(this, backgroundView.nowPlayingTimelineView);
    }

    private NowPlayingBackgroundView updateBackgroundView() {
        final int childId = viewFlipper.getDisplayedChild();
        final int viewLayoutId = viewLayoutIds.get(childId);
        NowPlayingBackgroundModel model = DataProvider.createNowPlayingMainModel(childId);
        NowPlayingBackgroundView view = (NowPlayingBackgroundView) (viewFlipper.findViewById(viewLayoutId));
        new NowPlayingBackgroundBinder().bind(view, model);
        return view;
    }

    private void updateTimelineView(final Context context,
                                final NowPlayingTimelineView nowPlayingTimelineView) {
        timelineUpdateThread = new Thread() {
            @Override
            public void run() {
                try {
                    final NowPlayingTimelineBinder nowPlayingTimelineBinder = new NowPlayingTimelineBinder(context);
                    final List<LiveGameEventModel> events = new ArrayList<LiveGameEventModel>();
                    final long start = System.currentTimeMillis();
                    while (!isInterrupted()) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                NowPlayingTimelineModel timelineModelmodel =
                                        DataProvider.createNowPlayingTimelineModel(start, events);
                                nowPlayingTimelineBinder.bind(nowPlayingTimelineView, timelineModelmodel);
                            }
                        });
                        Thread.sleep(1000);
                    }
                } catch (InterruptedException e) {
                }
            }
        };
        timelineUpdateThread.start();
    }
}
