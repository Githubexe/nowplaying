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
import com.music.amazon.mypoldi.view.LiveFeedUpdateView;

import java.util.List;

public class DemoActivity extends Activity implements DemoLiveFeedListener, ChannelSwitchListener {

    private ChannelSwitcherBinder channelSwitcherBinder;
    private ChannelSwitcherModel channelSwitcherModel;
    private ChannelSwitcherView channelSwitcherView;
    private View currentView;

    private final DemoLiveFeed currentLiveFeed = new DemoLiveFeed();
    private List<LiveFeedBackgroundModel> backgroundModels;

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
        channelSwitcherModel = new ChannelSwitcherModel(backgroundModels.toArray());
        channelSwitcherBinder.bind(channelSwitcherView, channelSwitcherModel);
        channelSwitcherView.register(DemoActivity.this);
        currentLiveFeed.register(DemoActivity.this);
        switchChannel(0);
    }

    private void switchChannel(final int channelIndex) {
        currentView = channelSwitcherView.getCurrentView();
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
        return (LiveFeedUpdateView) (currentView.findViewById(R.id.live_feed_view));
    }

    @Override
    public void onChannelSwitched(final int viewId, final View newView) {
        this.currentView = newView;
        switchChannel(viewId);
    }

    @Override
    public void onUpdateLeftLiveItem(LeftLiveFeedItemModel data) {
        getLiveFeedUpdateView().onUpdateLeftLiveItem(data);
    }

    @Override
    public void onUpdateRightLiveItem(RightLiveFeedItemModel data) {
        getLiveFeedUpdateView().onUpdateRightLiveItem(data);
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
