package com.music.amazon.mypoldi.integration;

import com.music.amazon.mypoldi.model.LiveFeedItemModel;
import com.music.amazon.mypoldi.model.LiveFeedModel;

import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Created by yoyosu on 2/17/17.
 */
public final class DemoLiveFeed {

    private ArrayList<DemoLiveFeedListener> liveFeedListeners = new ArrayList<>();

    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

    private ScheduledFuture<?> future;

    private static final AtomicBoolean on = new AtomicBoolean(false);

    public void start(int channelIndex) {
        if (on.getAndSet(true) == false) {
            final LiveFeedModel liveFeedModel =
                    DemoLiveFeedData.generateLiveFeedModel(channelIndex);

            future = scheduler.scheduleAtFixedRate(
                    new Runnable() {
                        final String clock = liveFeedModel.getGameClock().get();
                        int min = Integer.parseInt(clock);
                        int sec = 0;
                        @Override
                        public void run() {
                            if (on.get() == true) {
                                if (sec++ >= 59) {
                                    min++;
                                }
                                liveFeedModel.setGameClock(Integer.toString(min));
                                final LiveFeedItemModel data = DemoLiveFeedData.generateLiveFeedItemData();
                                for (DemoLiveFeedListener liveFeedListener: liveFeedListeners) {
                                    liveFeedListener.onUpdateLiveFeedHeader(liveFeedModel);
                                    liveFeedListener.onUpdateLiveItem(data);
                                }
                            }
                        }
                    },
                    0, //initial delay
                    2, //interval
                    TimeUnit.SECONDS);
        }
    }

    public void stop() {
        if (on.getAndSet(false) == true) {
            if (future != null) {
                future.cancel(true);
            }
        }
    }

    public void shutdown() {
        if (scheduler != null) {
            scheduler.shutdown();
        }
    }

    public void register(DemoLiveFeedListener liveFeedListener) {
        this.liveFeedListeners.add(liveFeedListener);
    }

    public void deregister(DemoLiveFeedListener liveFeedListener) {
        this.liveFeedListeners.remove(liveFeedListener);
    }
}
