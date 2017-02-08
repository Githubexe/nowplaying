package com.music.amazon.mypoldi;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ViewFlipper;

import com.music.amazon.mypoldi.binder.NowPlayingMatchBinder;
import com.music.amazon.mypoldi.binder.NowPlayingMatchDetailsBinder;
import com.music.amazon.mypoldi.model.MatchEventModel;
import com.music.amazon.mypoldi.model.NowPlayingMatchModel;
import com.music.amazon.mypoldi.model.NowPlayingMatchDetailsModel;
import com.music.amazon.mypoldi.view.NowPlayingMatchView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class NowPlayingViewFlipperActivity extends Activity {

    //FIXME: get the number of concurrent live games from service
    private final int NUM_OF_LIVE_GAMES = 3;

    private NowPlayingMatchView backgroundView;

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
        if (timelineUpdateThread != null) {
            timelineUpdateThread.interrupt();
        }
        timelineUpdateThread = new Thread() {
            @Override
            public void run() {
                try {
                    final NowPlayingMatchDetailsBinder nowPlayingMatchDetailsBinder =
                            new NowPlayingMatchDetailsBinder(NowPlayingViewFlipperActivity.this);
                    final List<MatchEventModel> events = new ArrayList<MatchEventModel>();
                    final NowPlayingMatchDetailsModel timelineModelmodel =
                            DataProvider.createNowPlayingTimelineModel(events);

                    while (!isInterrupted()) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                final MatchEventModel eventModel = DataProvider.createLiveGameEvent();
                                events.add(eventModel);
                                final Calendar now = Calendar.getInstance();
                                //DEMO ONLY
                                timelineModelmodel.minutes = now.get(Calendar.MINUTE);
                                timelineModelmodel.seconds = now.get(Calendar.SECOND);
                                nowPlayingMatchDetailsBinder.bind(
                                        backgroundView.nowPlayingMatchDetailsView,
                                        timelineModelmodel);
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
