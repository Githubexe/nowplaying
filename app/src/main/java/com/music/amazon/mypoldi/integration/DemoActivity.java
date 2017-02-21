package com.music.amazon.mypoldi.integration;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.KeyEvent;

import com.music.amazon.mypoldi.R;
import com.music.amazon.mypoldi.binder.ChannelSwitcherBinder;
import com.music.amazon.mypoldi.binder.LiveFeedBackgroundBinder;
import com.music.amazon.mypoldi.binder.LiveFeedHeaderBinder;
import com.music.amazon.mypoldi.model.GameModel;
import com.music.amazon.mypoldi.model.LeftLiveFeedItemModel;
import com.music.amazon.mypoldi.model.ChannelSwitcherModel;
import com.music.amazon.mypoldi.model.LiveFeedBackgroundModel;
import com.music.amazon.mypoldi.model.LiveFeedHeaderModel;
import com.music.amazon.mypoldi.model.RightLiveFeedItemModel;
import com.music.amazon.mypoldi.view.ChannelSwitcherView;
import com.music.amazon.mypoldi.view.LiveFeedBackgroundView;
import com.music.amazon.mypoldi.view.LiveFeedHeaderView;

import java.util.List;

public class DemoActivity extends Activity implements DemoLiveFeedListener {

    private ChannelSwitcherBinder channelSwitcherBinder;
    private ChannelSwitcherModel channelSwitcherModel;
    private ChannelSwitcherView channelSwitcherView;

    private LiveFeedBackgroundBinder backgroundBinder;
    private LiveFeedHeaderView liveFeedHeaderView;
    private final DemoLiveFeed currentLiveFeed = new DemoLiveFeed();

    private List<Object> games;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.live_feed_main_acitivty);

        backgroundBinder = new LiveFeedBackgroundBinder();
        channelSwitcherBinder = new ChannelSwitcherBinder(backgroundBinder);
        channelSwitcherModel = new ChannelSwitcherModel(LiveFeedBackgroundModel.class);
        channelSwitcherView = (ChannelSwitcherView) findViewById(R.id.live_channel_view);

        addViews();
    }

    private void addViews() {
        new AsyncTask<Void, Void, List<Object>>() {
            @Override
            protected List<Object> doInBackground(Void... voids) {
                //get the list of live games from service
                games = DemoLiveFeedData.getLiveGames();
                return games;
            }

            @Override
            protected void onPostExecute(List<Object> games) {
                for (int i = 0; i < games.size(); i++) {
                    channelSwitcherBinder.bind(
                            channelSwitcherView,
                            channelSwitcherModel);
                }
                currentLiveFeed.register(DemoActivity.this);
                switchGame(0);
            }
        }.execute();
    }

    private void switchGame(int viewIndex) {
        final String newGameId = ((GameModel)games.get(viewIndex)).gameId;
        if (currentLiveFeed != null) {
            currentLiveFeed.stop();
            currentLiveFeed.start(newGameId);
        }

        final LiveFeedBackgroundModel model = DemoLiveFeedData.
                generateLiveFeedBackgroundModel(newGameId);
        final LiveFeedBackgroundView backgroundView = (LiveFeedBackgroundView)
                (channelSwitcherView.getCurrentView());
        backgroundBinder.bind(backgroundView, model);
        liveFeedHeaderView = (LiveFeedHeaderView) (backgroundView.findViewById(R.id.live_feed_view));
        // itemView.setItemAnimator(new MyItemAnimator());
    }

    @Override
    public void onUpdateLeftLiveItem(LeftLiveFeedItemModel data) {
        liveFeedHeaderView.onUpdateLeftLiveItem(data);
    }

    @Override
    public void onUpdateRightLiveItem(RightLiveFeedItemModel data) {
        liveFeedHeaderView.onUpdateRightLiveItem(data);
    }

    @Override
    public void onUpdateLiveFeedHeader(final LiveFeedHeaderModel liveFeedHeaderModel) {
        DemoActivity.this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                new LiveFeedHeaderBinder().bind(liveFeedHeaderView, liveFeedHeaderModel);
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
                switchGame(channelSwitcherView.showPrevious());
                event.startTracking();
                return true;
            } else if (keyCode == KeyEvent.KEYCODE_DPAD_RIGHT) {
                switchGame(channelSwitcherView.showNext());
                event.startTracking();
                return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }
}
