package com.music.amazon.mypoldi.integration;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.music.amazon.mypoldi.R;
import com.music.amazon.mypoldi.binder.CustomAnimator;
import com.music.amazon.mypoldi.binder.HomeLiveFeedItemBinder;
import com.music.amazon.mypoldi.binder.AwayLiveFeedItemBinder;
import com.music.amazon.mypoldi.dmtv.UniversalAdapter;
import com.music.amazon.mypoldi.model.LiveFeedItemModel;
import com.music.amazon.mypoldi.view.ChannelSwitchListener;
import com.music.amazon.mypoldi.binder.ChannelSwitcherBinder;
import com.music.amazon.mypoldi.binder.LiveFeedBackgroundBinder;
import com.music.amazon.mypoldi.binder.LiveFeedBinder;
import com.music.amazon.mypoldi.model.ChannelSwitcherModel;
import com.music.amazon.mypoldi.model.LiveFeedModel;
import com.music.amazon.mypoldi.view.ChannelSwitcherView;
import com.music.amazon.mypoldi.view.LiveFeedBackgroundView;

import java.util.List;

public class DemoActivity extends Activity implements DemoLiveFeedListener, ChannelSwitchListener {

    private ChannelSwitcherBinder channelSwitcherBinder;
    private ChannelSwitcherModel channelSwitcherModel;
    private ChannelSwitcherView channelSwitcherView;
    private LiveFeedBackgroundView currentView;

    private final DemoLiveFeed currentLiveFeed = new DemoLiveFeed();
    private List<Object> backgroundModels;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.live_feed_main_acitivty);
        channelSwitcherView = (ChannelSwitcherView)findViewById(R.id.live_channel_view);
        channelSwitcherBinder = new ChannelSwitcherBinder(new LiveFeedBackgroundBinder());
        addLiveChannels();
    }

    private void addLiveChannels() {
        //DMTVUISoccer > get the data model, RxChannel
        backgroundModels = DemoLiveFeedData.getLiveChannels();
        channelSwitcherModel = new ChannelSwitcherModel("uuid", backgroundModels);
        channelSwitcherBinder.bind(channelSwitcherView, channelSwitcherModel);
        channelSwitcherView.register(DemoActivity.this);
        currentLiveFeed.register(DemoActivity.this);
        switchChannel(0);
    }

    private void switchChannel(final int channelIndex) {
        currentView = (LiveFeedBackgroundView)channelSwitcherView.getCurrentView();
        CustomAnimator.resetPosition();
        currentView.liveFeedView.setAdapter(new UniversalAdapter(
                new HomeLiveFeedItemBinder(),
                new AwayLiveFeedItemBinder()));
        if (currentLiveFeed != null) {
            currentLiveFeed.stop();
        }

        channelSwitcherBinder.switchChannel(channelSwitcherView,
                backgroundModels.get(channelIndex));

        if (currentLiveFeed != null) {
            currentLiveFeed.start(channelIndex);
        }
    }

    @Override
    public void onChannelSwitched(final int channelIndex, final View newView) {
        this.currentView = (LiveFeedBackgroundView)newView;
        switchChannel(channelIndex);
    }

    @Override
    public void onUpdateLiveItem(LiveFeedItemModel model) {
        currentView.liveFeedView.addItem(model);
    }

    @Override
    public void onUpdateLiveFeedHeader(final LiveFeedModel liveFeedModel) {
        DemoActivity.this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                new LiveFeedBinder().bind(currentView.liveFeedView, liveFeedModel);
            }
        });
    }

    @Override
    protected void onDestroy() {
        currentLiveFeed.deregister(this);
        currentLiveFeed.stop();
        currentLiveFeed.shutdown();
        super.onDestroy();
    }

}
