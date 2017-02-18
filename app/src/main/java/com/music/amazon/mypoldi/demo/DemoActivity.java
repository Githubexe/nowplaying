package com.music.amazon.mypoldi.demo;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.ViewFlipper;

import com.music.amazon.mypoldi.R;
import com.music.amazon.mypoldi.binder.CustomLinearLayoutManager;
import com.music.amazon.mypoldi.binder.LiveChannelBinder;
import com.music.amazon.mypoldi.binder.LiveFeedBackgroundBinder;
import com.music.amazon.mypoldi.binder.LiveFeedBinder;
import com.music.amazon.mypoldi.binder.LeftLiveFeedItemBinder;
import com.music.amazon.mypoldi.binder.RightLiveFeedItemBinder;
import com.music.amazon.mypoldi.dmtv.UniversalAdapter;
import com.music.amazon.mypoldi.model.LeftLiveFeedItemModel;
import com.music.amazon.mypoldi.model.LiveChannelModel;
import com.music.amazon.mypoldi.model.LiveFeedBackgroundModel;
import com.music.amazon.mypoldi.model.LiveFeedModel;
import com.music.amazon.mypoldi.model.RightLiveFeedItemModel;
import com.music.amazon.mypoldi.view.LiveChannelView;
import com.music.amazon.mypoldi.view.LiveFeedBackgroundView;
import com.music.amazon.mypoldi.view.LiveFeedView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class DemoActivity extends Activity {

    private UniversalAdapter universalAdapter;
    private LiveFeedBackgroundBinder backgroundBinder;
    private LiveChannelBinder liveChannelBinder;
    private ViewFlipper viewFlipper;

    final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
    ScheduledFuture<?> scheduledFuture;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.live_feed_main_acitivty);
        init();
        updateLayout();
    }


    private void init() {
        //get concurrent games from service
        final LiveChannelModel liveChannelModel = new LiveChannelModel
                (DemoLiveFeedData.getLiveGames());
        final LiveChannelView liveChannelView = (LiveChannelView)findViewById(R.id.live_channel_view);
        liveChannelBinder = new LiveChannelBinder();
        //Add concurrent live games to flipper
        liveChannelBinder.bind(liveChannelView, liveChannelModel);
        viewFlipper = liveChannelView.viewFlipper;
        backgroundBinder = new LiveFeedBackgroundBinder();
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
                    viewFlipper.setInAnimation(this, R.anim.live_feed_flipper_in_from_left);
                    viewFlipper.setOutAnimation(this, R.anim.live_feed_flipper_out_to_right);
                    viewFlipper.showPrevious();
                    updateLayout();
                    event.startTracking();
                    return true;
                }
            } else if (keyCode == KeyEvent.KEYCODE_DPAD_RIGHT) {
                if (viewFlipper.getDisplayedChild() <= viewFlipper.getChildCount() - 1) {
                    viewFlipper.setInAnimation(this, R.anim.live_feed_flipper_in_from_right);
                    viewFlipper.setOutAnimation(this, R.anim.live_feed_flipper_out_to_left);
                    viewFlipper.showNext();
                    updateLayout();
                    event.startTracking();
                    return true;
                }
            }
        }
        return super.onKeyDown(keyCode,event);
    }

    private void updateLayout() {
        if (scheduledFuture != null) {
            scheduledFuture.cancel(true);
        }

        final int childId = viewFlipper.getDisplayedChild();

        final LiveFeedBackgroundModel model = DemoLiveFeedData.createLiveFeedBackgroundModel(childId);
        final LiveFeedBackgroundView backgroundView = (LiveFeedBackgroundView) (viewFlipper.getCurrentView());

        final LiveFeedView liveFeedView = (LiveFeedView) (backgroundView.findViewById(R.id.live_feed_view));
        backgroundBinder.bind(backgroundView, model);

        universalAdapter = (new UniversalAdapter(new LeftLiveFeedItemBinder(),
                new RightLiveFeedItemBinder()));
        liveFeedView.liveFeedItemView.setAdapter(universalAdapter);
        final CustomLinearLayoutManager customLinearLayoutManager =
                new CustomLinearLayoutManager(this);
        customLinearLayoutManager.setStackFromEnd(true);
        liveFeedView.liveFeedItemView.setLayoutManager(customLinearLayoutManager);

       // liveFeedView.liveFeedItemView.setItemAnimator(new MyItemAnimator());

        scheduledFuture = scheduler.scheduleAtFixedRate(
                new UpdateEventRunnable(liveFeedView),
                2, //initial delay
                1, //interval
                TimeUnit.SECONDS);
    }

    //DEMO purpose only, will be replaced by LiveFeedSubscriber logics
    private class UpdateEventRunnable implements Runnable {
        private final LiveFeedView liveFeedView;
        public UpdateEventRunnable(LiveFeedView view) {
            this.liveFeedView = view;
        }
        final LiveFeedBinder liveFeedBinder =
                new LiveFeedBinder();

        final LiveFeedModel liveFeedModel =
                DemoLiveFeedData.createLiveFeedModel();


        @Override
        public void run() {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    DemoLiveFeedData.counter ++;
                    if (DemoLiveFeedData.counter % 3 == 0 ||
                            DemoLiveFeedData.counter % 7 == 0) {
                        final LeftLiveFeedItemModel eventModel = DemoLiveFeedData.createLeftLiveFeedItemModel();
                        List<LeftLiveFeedItemModel> added = new ArrayList<LeftLiveFeedItemModel>();
                        added.add(eventModel);
                        universalAdapter.addItems(added);

                    } else {
                        final RightLiveFeedItemModel eventModel = DemoLiveFeedData.createRightLiveFeedItemModel();
                        List<RightLiveFeedItemModel> added = new ArrayList<RightLiveFeedItemModel>();
                        added.add(eventModel);
                        universalAdapter.addItems(added);
                    }

                    liveFeedView.liveFeedItemView.smoothScrollToPosition(
                                universalAdapter.getItemCount() - 1);

                    //DEMO purpose only
                    final Calendar now = Calendar.getInstance();
                    liveFeedModel.elapsedTime = now.get(Calendar.MINUTE) +
                            " : " + now.get(Calendar.SECOND);

                    viewFlipper.findViewById(R.id.live_feed_view);
                    liveFeedBinder.bind(
                            liveFeedView,
                            liveFeedModel);

                }
            });
        }
    }

}