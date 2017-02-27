package com.music.amazon.mypoldi.integration;

/**
 * Created by yoyosu on 2/17/17.
 */

import com.music.amazon.mypoldi.model.LiveFeedItemModel;
import com.music.amazon.mypoldi.model.LiveFeedModel;

public interface DemoLiveFeedListener {
    void onUpdateLiveFeedHeader(LiveFeedModel liveFeed);
    void onUpdateLiveItem(LiveFeedItemModel leftItem);
}
