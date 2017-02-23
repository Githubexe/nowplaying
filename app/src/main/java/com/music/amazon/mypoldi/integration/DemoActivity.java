package com.music.amazon.mypoldi.integration;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.music.amazon.mypoldi.R;
import com.music.amazon.mypoldi.view.ChannelSwitchListener;
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
        if (currentLiveFeed != null) {
            currentLiveFeed.stop();
        }

        channelSwitcherBinder.switchChannel(channelSwitcherView,
                backgroundModels.get(channelIndex));

        if (currentLiveFeed != null) {
            currentLiveFeed.start(channelIndex);
        }
    }

    private LiveFeedUpdateView getLiveFeedUpdateView() {
        return currentView.getLiveFeedUpdateView();
    }

    @Override
    public void onChannelSwitched(final int viewId, final View newView) {
        this.currentView = (LiveFeedBackgroundView)newView;
        switchChannel(viewId);
    }

    @Override
    public void onUpdateLeftLiveItem(LeftLiveFeedItemModel model) {
        getLiveFeedUpdateView().addLeft(model);
    }

    @Override
    public void onUpdateRightLiveItem(RightLiveFeedItemModel model) {
        getLiveFeedUpdateView().addRight(model);
    }

    @Override
    public void onUpdateLiveFeedHeader(final LiveFeedUpdateModel liveFeedUpdateModel) {
        DemoActivity.this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                new LiveFeedUpdateBinder().bind(getLiveFeedUpdateView(), liveFeedUpdateModel);
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
