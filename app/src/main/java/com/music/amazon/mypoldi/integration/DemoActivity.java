package com.music.amazon.mypoldi.integration;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.ViewFlipper;

import com.music.amazon.mypoldi.R;
import com.music.amazon.mypoldi.binder.CustomLinearLayoutManager;
import com.music.amazon.mypoldi.binder.LiveChannelBinder;
import com.music.amazon.mypoldi.binder.LiveFeedBackgroundBinder;
import com.music.amazon.mypoldi.binder.LiveFeedHeaderBinder;
import com.music.amazon.mypoldi.binder.LeftLiveFeedItemBinder;
import com.music.amazon.mypoldi.binder.RightLiveFeedItemBinder;
import com.music.amazon.mypoldi.dmtv.UniversalAdapter;
import com.music.amazon.mypoldi.model.GameModel;
import com.music.amazon.mypoldi.model.LeftLiveFeedItemModel;
import com.music.amazon.mypoldi.model.LiveChannelModel;
import com.music.amazon.mypoldi.model.LiveFeedBackgroundModel;
import com.music.amazon.mypoldi.model.LiveFeedHeaderModel;
import com.music.amazon.mypoldi.model.RightLiveFeedItemModel;
import com.music.amazon.mypoldi.view.LiveChannelView;
import com.music.amazon.mypoldi.view.LiveFeedBackgroundView;
import com.music.amazon.mypoldi.view.LiveFeedHeaderView;

import java.util.ArrayList;
import java.util.List;

public class DemoActivity extends Activity implements DemoLiveFeedListener {

    private UniversalAdapter universalAdapter;

    private LiveChannelBinder liveChannelBinder = new LiveChannelBinder();
    private LiveFeedBackgroundBinder backgroundBinder = new LiveFeedBackgroundBinder();

    private ViewFlipper viewFlipper;

    private LiveFeedHeaderView liveFeedHeaderView;
    private final DemoLiveFeed currentLiveFeed = new DemoLiveFeed();

    private List<GameModel> games;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.live_feed_main_acitivty);

        loadFirstGame();
    }

    private void loadFirstGame() {
        new AsyncTask<Void, Void, LiveChannelModel>() {
            @Override
            protected LiveChannelModel doInBackground(Void... voids) {
                //get the list of live games from service
                return new LiveChannelModel
                        (DemoLiveFeedData.getLiveGames());
            }

            @Override
            protected void onPostExecute(LiveChannelModel liveChannelModel) {
                games = liveChannelModel.games;
                final LiveChannelView liveChannelView =
                        (LiveChannelView) findViewById(R.id.live_channel_view);
                viewFlipper = liveChannelView.viewFlipper;
                //Add concurrent live games to flipper
                liveChannelBinder.bind(liveChannelView, liveChannelModel);
                currentLiveFeed.register(DemoActivity.this);
                switchGame();
            }
        }.execute();
    }

    private void switchGame() {
        //update background
        final int index = viewFlipper.getDisplayedChild();
        final LiveFeedBackgroundModel model = DemoLiveFeedData.getLiveFeedBackgroundModel(games.get(index).gameId);
        final LiveFeedBackgroundView backgroundView = (LiveFeedBackgroundView)
                (viewFlipper.getCurrentView());
        backgroundBinder.bind(backgroundView, model);

        //update live feed
        liveFeedHeaderView = (LiveFeedHeaderView) (backgroundView.findViewById(R.id.live_feed_view));
        universalAdapter = new UniversalAdapter(new LeftLiveFeedItemBinder(),
                new RightLiveFeedItemBinder());
        liveFeedHeaderView.liveFeedItemView.setAdapter(universalAdapter);
        final CustomLinearLayoutManager customLinearLayoutManager =
                new CustomLinearLayoutManager(this);
        customLinearLayoutManager.setStackFromEnd(true);
        liveFeedHeaderView.liveFeedItemView.setLayoutManager(customLinearLayoutManager);
        // liveFeedView.liveFeedItemView.setItemAnimator(new MyItemAnimator());

        if (currentLiveFeed != null) {
            currentLiveFeed.start();
        }
    }

    @Override
    public void onUpdateLeftLiveItem(LeftLiveFeedItemModel data) {
        List<LeftLiveFeedItemModel> added = new ArrayList<>();
        added.add(data);
        universalAdapter.addItems(added);
        liveFeedHeaderView.liveFeedItemView.smoothScrollToPosition(
                universalAdapter.getItemCount() - 1);
    }

    @Override
    public void onUpdateRightLiveItem(RightLiveFeedItemModel data) {
        List<RightLiveFeedItemModel> added = new ArrayList<>();
        added.add(data);
        universalAdapter.addItems(added);
        liveFeedHeaderView.liveFeedItemView.smoothScrollToPosition(
                universalAdapter.getItemCount() - 1);
    }

    @Override
    public void onUpdateLiveFeed(final LiveFeedHeaderModel liveFeedHeaderModel) {
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
        currentLiveFeed.stop();
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
                    switchGame();
                    event.startTracking();
                    return true;
                }
            } else if (keyCode == KeyEvent.KEYCODE_DPAD_RIGHT) {
                if (viewFlipper.getDisplayedChild() <= viewFlipper.getChildCount() - 1) {
                    viewFlipper.setInAnimation(this, R.anim.live_feed_flipper_in_from_right);
                    viewFlipper.setOutAnimation(this, R.anim.live_feed_flipper_out_to_left);
                    viewFlipper.showNext();
                    switchGame();
                    event.startTracking();
                    return true;
                }
            }
        }
        return super.onKeyDown(keyCode, event);
    }
}
