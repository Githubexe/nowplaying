package com.music.amazon.mypoldi.integration;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;

import com.music.amazon.mypoldi.R;
import com.music.amazon.mypoldi.binder.ChannelSwitcherBinder;
import com.music.amazon.mypoldi.binder.LiveFeedBackgroundBinder;
import com.music.amazon.mypoldi.binder.LiveFeedUpdateBinder;
import com.music.amazon.mypoldi.model.LeftLiveFeedItemModel;
import com.music.amazon.mypoldi.model.ChannelSwitcherModel;
import com.music.amazon.mypoldi.model.LiveFeedBackgroundModel;
import com.music.amazon.mypoldi.model.LiveFeedUpdateModel;
import com.music.amazon.mypoldi.model.RightLiveFeedItemModel;
import com.music.amazon.mypoldi.view.ChannelSwitcherView;
import com.music.amazon.mypoldi.view.LiveFeedBackgroundView;
import com.music.amazon.mypoldi.view.LiveFeedUpdateView;

import java.util.List;

public class DemoActivity extends Activity implements DemoLiveFeedListener {

    private ChannelSwitcherBinder channelSwitcherBinder;
    private ChannelSwitcherModel channelSwitcherModel;
    private ChannelSwitcherView channelSwitcherView;

    private LiveFeedBackgroundBinder backgroundBinder;
    private LiveFeedUpdateView liveFeedUpdateView;
    private final DemoLiveFeed currentLiveFeed = new DemoLiveFeed();

    private List<LiveFeedBackgroundModel> backgroundModels;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.live_feed_main_acitivty);
        channelSwitcherView = (ChannelSwitcherView)findViewById(R.id.live_channel_view);

        backgroundBinder = new LiveFeedBackgroundBinder();
        channelSwitcherBinder = new ChannelSwitcherBinder(backgroundBinder);
        addLiveChannels();
    }

    private void addLiveChannels() {
        backgroundModels = DemoLiveFeedData.getLiveChannels();
        channelSwitcherModel = new ChannelSwitcherModel(backgroundModels.toArray());
        channelSwitcherBinder.bind(channelSwitcherView, channelSwitcherModel);

        currentLiveFeed.register(DemoActivity.this);
        switchChannel(0);
    }

    private void switchChannel(final int channelIndex) {
        if (currentLiveFeed != null) {
            currentLiveFeed.stop();
        }
        final LiveFeedBackgroundModel backgroundModel = backgroundModels.get(channelIndex);
        final LiveFeedBackgroundView backgroundView = (LiveFeedBackgroundView)
                (channelSwitcherView.getCurrentView());
        backgroundBinder.bind(backgroundView, backgroundModel);

        liveFeedUpdateView = (LiveFeedUpdateView) (backgroundView.findViewById(R.id.live_feed_view));
        if (currentLiveFeed != null) {
            currentLiveFeed.start(channelIndex);
        }
    }

    @Override
    public void onUpdateLeftLiveItem(LeftLiveFeedItemModel data) {
        liveFeedUpdateView.onUpdateLeftLiveItem(data);
    }

    @Override
    public void onUpdateRightLiveItem(RightLiveFeedItemModel data) {
        liveFeedUpdateView.onUpdateRightLiveItem(data);
    }

    @Override
    public void onUpdateLiveFeedHeader(final LiveFeedUpdateModel liveFeedUpdateModel) {
        DemoActivity.this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                new LiveFeedUpdateBinder().bind(liveFeedUpdateView, liveFeedUpdateModel);
            }
        });
    }

    @Override
    protected void onDestroy() {
        currentLiveFeed.deregister(this);
        currentLiveFeed.shutdown();
        super.onDestroy();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (event.getAction() == KeyEvent.ACTION_DOWN) {
            if (keyCode == KeyEvent.KEYCODE_DPAD_LEFT) {
                switchChannel(channelSwitcherView.showPrevious());
                event.startTracking();
                return true;
            } else if (keyCode == KeyEvent.KEYCODE_DPAD_RIGHT) {
                switchChannel(channelSwitcherView.showNext());
                event.startTracking();
                return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }
}
