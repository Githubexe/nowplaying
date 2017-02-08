package com.music.amazon.mypoldi;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ViewFlipper;

import com.music.amazon.mypoldi.binder.NowPlayingMatchBinder;
import com.music.amazon.mypoldi.binder.NowPlayingMatchDetailsBinder;
import com.music.amazon.mypoldi.model.NowPlayingMatchDetailsEvent;
import com.music.amazon.mypoldi.model.NowPlayingMatchModel;
import com.music.amazon.mypoldi.model.NowPlayingMatchDetailsModel;
import com.music.amazon.mypoldi.view.NowPlayingMatchView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class NowPlayingViewFlipperActivity extends Activity {

    //FIXME: get the number of concurrent live games from service
    private final int NUM_OF_LIVE_GAMES = 3;

    private NowPlayingMatchView backgroundView;

    private ViewFlipper viewFlipper;

    private List<Integer> viewLayoutIds = new ArrayList<Integer>();

    final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

    ScheduledFuture<?> scheduledFuture;

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
        scheduler.shutdown();
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
        final NowPlayingMatchView view = new NowPlayingMatchView(this);
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
        NowPlayingMatchModel model = DataProvider.createNowPlayingBackgroundModel(childId);
        NowPlayingMatchView view = (NowPlayingMatchView) (viewFlipper.findViewById(viewLayoutId));
        backgroundView = view;
        new NowPlayingMatchBinder().bind(view, model);
    }

    private void updateTimelineView() {
        if (scheduledFuture != null) {
            scheduledFuture.cancel(true);
        }
        scheduledFuture = scheduler.scheduleAtFixedRate(
                new UpdateEventRunnable(),
                2, //initial delay
                1, //interval
                TimeUnit.SECONDS);
    }

    private class UpdateEventRunnable implements Runnable {
        final NowPlayingMatchDetailsBinder nowPlayingMatchDetailsBinder =
                new NowPlayingMatchDetailsBinder(NowPlayingViewFlipperActivity.this);

        final List<NowPlayingMatchDetailsEvent> events = new ArrayList<NowPlayingMatchDetailsEvent>();

        final NowPlayingMatchDetailsModel timelineModelmodel =
                DataProvider.createNowPlayingTimelineModel(events);

        @Override
        public void run() {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    final NowPlayingMatchDetailsEvent eventModel = DataProvider.createLiveGameEvent();
                    events.add(eventModel);
                    //DEMO purpose only
                    final Calendar now = Calendar.getInstance();
                    timelineModelmodel.minutes = now.get(Calendar.MINUTE);
                    timelineModelmodel.seconds = now.get(Calendar.SECOND);

                    nowPlayingMatchDetailsBinder.bind(
                            backgroundView.nowPlayingMatchDetailsView,
                            timelineModelmodel);
                }
            });
        }
    }
}