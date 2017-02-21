package com.music.amazon.mypoldi.integration;

import com.music.amazon.mypoldi.model.LeftLiveFeedItemModel;
import com.music.amazon.mypoldi.model.LiveFeedUpdateModel;
import com.music.amazon.mypoldi.model.RightLiveFeedItemModel;

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

    public void start(String gameId) {
        if (on.getAndSet(true) == false) {
            final LiveFeedUpdateModel liveFeedUpdateModel =
                    DemoLiveFeedData.generateLiveFeedHeaderModel(gameId);
            future = scheduler.scheduleAtFixedRate(
                    new Runnable() {
                        final String[] times = liveFeedUpdateModel.elapsedTime.split(":");
                        int min = Integer.parseInt(times[0].trim());
                        int sec = Integer.parseInt(times[1].trim());
                        @Override
                        public void run() {
                            if (on.get() == true) {
                                if (sec++ >= 59) {
                                    sec = 0;
                                    min++;
                                }
                                liveFeedUpdateModel.elapsedTime =
                                        ((min < 10) ?  String.valueOf("0" + min)
                                                : String.valueOf(min)) + " : "
                                        + ((sec < 10) ?  String.valueOf("0" + sec)
                                                : String.valueOf(sec));
                                final Object data = DemoLiveFeedData.generateLiveFeedItemData();
                                for (DemoLiveFeedListener liveFeedListener: liveFeedListeners) {
                                    liveFeedListener.onUpdateLiveFeedHeader(liveFeedUpdateModel);
                                    if (data instanceof LeftLiveFeedItemModel) {
                                        liveFeedListener.onUpdateLeftLiveItem(
                                                (LeftLiveFeedItemModel)data);
                                    } else if (data instanceof RightLiveFeedItemModel){
                                        liveFeedListener.onUpdateRightLiveItem(
                                                (RightLiveFeedItemModel)data);
                                    }
                                }
                            }
                        }
                    },
                    0, //initial delay
                    1, //interval
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
