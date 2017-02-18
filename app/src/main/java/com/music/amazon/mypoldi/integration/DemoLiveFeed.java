package com.music.amazon.mypoldi.integration;

import com.music.amazon.mypoldi.model.LeftLiveFeedItemModel;
import com.music.amazon.mypoldi.model.LiveFeedModel;
import com.music.amazon.mypoldi.model.RightLiveFeedItemModel;

import java.util.ArrayList;
import java.util.Calendar;
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

    public void start() {
        final LiveFeedModel liveFeedModel =
                DemoLiveFeedData.createLiveFeedModel("");
        if (on.getAndSet(true) == false) {
            if (future != null) {
                future.cancel(true);
            }
            future = scheduler.scheduleAtFixedRate(
                    new Runnable() {
                        @Override
                        public void run() {
                            if (on.get() == true) {
                                final Calendar now = Calendar.getInstance();
                                liveFeedModel.elapsedTime = now.get(Calendar.MINUTE) +
                                        " : " + now.get(Calendar.SECOND);
                                final Object data = DemoLiveFeedData.generateLiveFeedItemData();
                                for (DemoLiveFeedListener liveFeedListener: liveFeedListeners) {
                                    liveFeedListener.onUpdateLiveFeed(liveFeedModel);
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
                    2, //initial delay
                    1, //interval
                    TimeUnit.SECONDS);
        }
    }

    public void stop() {
        if (on.getAndSet(false) == true) {
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
