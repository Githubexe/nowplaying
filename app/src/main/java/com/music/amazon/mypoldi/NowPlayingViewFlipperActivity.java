package com.music.amazon.mypoldi;

import android.app.Activity;
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

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class NowPlayingViewFlipperActivity extends Activity {

    //FIXME: get it from service
    private final int NUM_OF_LIVE_GAMES = 3;

    private NowPlayingBackgroundView backgroundView;

    private ViewFlipper viewFlipper;

    private List<Integer> viewLayoutIds = new ArrayList<Integer>();

    private Thread timelineUpdateThread;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.now_playing_view_flipper_activity);
        viewFlipper = (ViewFlipper) findViewById(R.id.now_playing_view_flipper);
        addNextView(0);
        updateLayout();
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
                    updateLayout();
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
                    updateLayout();
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

    private void updateLayout() {
        updateBackgroundView();
        updateTimelineView();;
    }

    private void updateBackgroundView() {
        final int childId = viewFlipper.getDisplayedChild();
        final int viewLayoutId = viewLayoutIds.get(childId);
        NowPlayingBackgroundModel model = DataProvider.createNowPlayingBackgroundModel(childId);
        NowPlayingBackgroundView view = (NowPlayingBackgroundView) (viewFlipper.findViewById(viewLayoutId));
        backgroundView = view;
        new NowPlayingBackgroundBinder().bind(view, model);
    }

    private void updateTimelineView() {
        if (timelineUpdateThread != null) {
            timelineUpdateThread.interrupt();
        }
        timelineUpdateThread = new Thread() {
            @Override
            public void run() {
                try {
                    final NowPlayingTimelineBinder nowPlayingTimelineBinder = new NowPlayingTimelineBinder(NowPlayingViewFlipperActivity.this);
                    final List<LiveGameEventModel> events = new ArrayList<LiveGameEventModel>();
                    final NowPlayingTimelineModel timelineModelmodel =
                            DataProvider.createNowPlayingTimelineModel(events);

                    while (!isInterrupted()) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                final LiveGameEventModel eventModel = DataProvider.createLiveGameEvent();
                                events.add(eventModel);
                                final Calendar now = Calendar.getInstance();
                                timelineModelmodel.minutes = now.get(Calendar.MINUTE);
                                timelineModelmodel.seconds = now.get(Calendar.SECOND);
                                nowPlayingTimelineBinder.bind(backgroundView.nowPlayingTimelineView, timelineModelmodel);
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
